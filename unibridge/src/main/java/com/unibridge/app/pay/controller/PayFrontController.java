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

public class PayFrontController extends HttpServlet {

	private String kakaoSecretKey;

	@Override
	public void init() throws ServletException {
		// properties 파일을 읽어오는 로직 (ConfigReader를 사용 중이라면 생략 가능하지만 유지합니다)
		Properties props = new Properties();
		try (InputStream is = getClass().getClassLoader().getResourceAsStream("config/api.properties")) {
			if (is != null) {
				props.load(is);
				this.kakaoSecretKey = props.getProperty("kakao.secret.key");
			} else {
				System.err.println(">>> [ERROR] api.properties 파일을 찾을 수 없습니다.");
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

		System.out.println(">>> [DEBUG] 컨트롤러 진입 - target: " + target);

		if (target.contains("paymentFinish.pay")) {
			System.out.println(">>> [7] 결제 승인 로직 시작 (PaymentOkController 진입)");
			new PaymentOkController().execute(request, response);
		} else if (target.contains("paymentOk.pay")) {
			System.out.println(">>> [2] 결제 준비 로직 시작");
			kakaoPayReady(request, response);
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