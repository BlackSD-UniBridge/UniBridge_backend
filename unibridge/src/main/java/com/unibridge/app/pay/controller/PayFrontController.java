package com.unibridge.app.pay.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.unibridge.app.Result;

public class PayFrontController extends HttpServlet {

	private String kakaoSecretKey;

	@Override
	public void init() throws ServletException {
	    Properties props = new Properties();
	    // [수정] 파일명을 context.properties로, 경로를 정확하게 맞춤
	    try (InputStream is = getClass().getClassLoader().getResourceAsStream("context.properties")) {
	        if (is != null) {
	            props.load(is);
	            // context.properties 안에 있는 키값으로 가져옴
	            this.kakaoSecretKey = props.getProperty("kakao.secret.key");
	            System.out.println(">>> [LOG] 카카오 시크릿 키 로드 완료");
	        } else {
	            // [참고] 파일이 src/main/resources/config/ 폴더 안에 있다면 
	            // "config/context.properties"로 경로를 수정해야 합니다.
	            System.err.println(">>> [ERROR] context.properties 파일을 찾을 수 없습니다.");
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {

	    String requestURI = request.getRequestURI();
	    String contextPath = request.getContextPath();
	    String target = requestURI.substring(contextPath.length());
	    Result result = null;

	    System.out.println(">>> [DEBUG] 컨트롤러 진입 - target: " + target);

	    // 1. 결제 정보 확인 페이지 이동
	    if (target.contains("/payment.pay")) {
	        result = new PaymentController().execute(request, response);
	    } 
	    // 2. 카카오페이 결제 준비 (Ready API 호출)
	    else if (target.contains("/paymentOk.pay")) {
	        System.out.println(">>> [2] 카카오페이 결제 준비 시작");
	        kakaoPayReady(request, response);
	        // 리다이렉트를 수행하므로 result는 null 유지
	    } 
	    // 3. 결제 승인 처리 (새로운 컨트롤러로 변경!)
	    else if (target.contains("/paymentFinish.pay")) {
	        System.out.println(">>> [7] 결제 승인 및 DB 저장 로직 시작");
	        // [수정 포인트] PaymentOkController 대신 PaymentFinishController 호출
	        result = new PaymentFinishController().execute(request, response);
	    }

	    // 페이지 이동 처리
	    if (result != null) {
	        if (result.isRedirect()) {
	            response.sendRedirect(result.getPath());
	        } else {
	            request.getRequestDispatcher(result.getPath()).forward(request, response);
	        }
	    }
	}

	private void kakaoPayReady(HttpServletRequest request, HttpServletResponse response) {
		try {
			HttpSession session = request.getSession();

			// 전달받은 상품 정보
			String itemName = request.getParameter("item_name");
			String totalAmount = request.getParameter("total_amount");

			// 1. 호스트 정보를 동적으로 가져오기 (localhost:9999 하드코딩 제거)
			String scheme = request.getScheme(); // http
			String serverName = request.getServerName(); // localhost 또는 IP
			int serverPort = request.getServerPort(); // 9999 또는 8888 등
			String contextPath = request.getContextPath(); // /unibridge

			// 베이스 URL 조립 (예: http://localhost:9999/unibridge)
			String baseUrl = String.format("%s://%s:%d%s", scheme, serverName, serverPort, contextPath);

			URL url = new URL("https://open-api.kakaopay.com/online/v1/payment/ready");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();

			conn.setRequestMethod("POST");

			// Authorization 설정
			String secretKey = "SECRET_KEY " + ConfigReader.getProperty("kakao.secret.key");
			conn.setRequestProperty("Authorization", secretKey);
			conn.setRequestProperty("Content-type", "application/json;charset=utf-8");
			conn.setDoOutput(true);

			// 2. 동적 URL이 적용된 JSON 생성
			String jsonParams = String.format("{" + "\"cid\":\"TC0ONETIME\"," + "\"partner_order_id\":\"1001\","
					+ "\"partner_user_id\":\"unibridge\"," + "\"item_name\":\"%s\"," + "\"quantity\":1,"
					+ "\"total_amount\":%s," + "\"tax_free_amount\":0," + "\"approval_url\":\"%s/paymentFinish.pay\","
					+ "\"cancel_url\":\"%s/payment.pay\"," + "\"fail_url\":\"%s/payment.pay\"" + "}", itemName,
					totalAmount, baseUrl, baseUrl, baseUrl);

			System.out.println(">>> [JSON 전송 확인]: " + jsonParams);

			try (OutputStream out = conn.getOutputStream()) {
				out.write(jsonParams.getBytes("utf-8"));
			}

			int code = conn.getResponseCode();
			System.out.println(">>> [4] 카카오 서버 응답 코드: " + code);

			if (code == 200) {
				BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
				StringBuilder sb = new StringBuilder();
				String line;
				while ((line = br.readLine()) != null) {
					sb.append(line);
				}

				String result = sb.toString();

				// TID 및 Redirect URL 추출
				String tidFromServer = result.split("\"tid\":\"")[1].split("\"")[0];
				String pcUrl = result.split("\"next_redirect_pc_url\":\"")[1].split("\"")[0];

				// 승인 단계에서 사용하기 위해 TID를 세션에 저장
				session.setAttribute("tid", tidFromServer);
				System.out.println(">>> [TID 세션 저장 완료]: " + tidFromServer);

				response.sendRedirect(pcUrl);
			} else {
				try (BufferedReader errorBr = new BufferedReader(
						new InputStreamReader(conn.getErrorStream(), "utf-8"))) {
					System.out.println(">>> [ERROR] 상세 내용: " + errorBr.readLine());
				}
			}

		} catch (Exception e) {
			System.out.println(">>> [EXCEPTION] 자바 예외 발생");
			e.printStackTrace();
		}
	}
}