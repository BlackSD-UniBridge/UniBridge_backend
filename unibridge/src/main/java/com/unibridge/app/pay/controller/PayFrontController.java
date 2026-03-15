package com.unibridge.app.pay.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class PayFrontController extends HttpServlet {

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

		System.err.println("###################################################");
		System.err.println("### 컨트롤러 진입 성공! 경로: " + request.getRequestURI());
		System.err.println("###################################################");

		String requestURI = request.getRequestURI();
	    String contextPath = request.getContextPath();
	    String target = requestURI.substring(contextPath.length());

	    System.out.println(">>> [DEBUG] 최종 target 경로: " + target);

	    if (target.contains("paymentFinish.pay")) {
	        System.out.println(">>> [7] 결제 승인 로직 시작 (PaymentOkController 진입)");
	        new PaymentOkController().execute(request, response);
	        return; // 승인 컨트롤러 안에서 forward를 하므로 여기서 반드시 return해야 합니다.
	    } 
	    
	    // 2. 결제 준비 단계
	    else if (target.contains("paymentOk.pay")) {
	        System.out.println(">>> [2] 결제 준비 로직 시작");
	        kakaoPayReady(request, response);
	    }
	}

	private void kakaoPayReady(HttpServletRequest request, HttpServletResponse response) {
		try {
			
			// 1. request객체로부터 session을 가져옵니다.
	        HttpSession session = request.getSession(); 
	        
	        // 2. 세션에서 저장해둔 tid를 꺼냅니다.
	        String tid = (String) session.getAttribute("tid");
	        String pgToken = request.getParameter("pg_token");
	        
			String itemName = request.getParameter("item_name");
			String totalAmount = request.getParameter("total_amount");

			URL url = new URL("https://open-api.kakaopay.com/online/v1/payment/ready");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();

			conn.setRequestMethod("POST");
			conn.setRequestProperty("Authorization", "SECRET_KEY DEV343EB9FECD7DD7A4D3803ED70C568F6C3F88D");
			// ★ Content-type을 json으로 변경합니다.
			conn.setRequestProperty("Content-type", "application/json;charset=utf-8");
			conn.setDoOutput(true);

			// 데이터를 JSON 형태로 만듭니다. (문자열 더하기로 간단히 구현)
			String jsonParams = "{" + "\"cid\":\"TC0ONETIME\"," + "\"partner_order_id\":\"1001\","
					+ "\"partner_user_id\":\"unibridge\"," + "\"item_name\":\"" + itemName + "\"," + "\"quantity\":1,"
					+ "\"total_amount\":" + totalAmount + "," + "\"tax_free_amount\":0,"
					+ "\"approval_url\":\"http://localhost:8888" + request.getContextPath() + "/paymentFinish.pay\","
					+ "\"cancel_url\":\"http://localhost:8888" + request.getContextPath() + "/payment.pay\","
					+ "\"fail_url\":\"http://localhost:8888" + request.getContextPath() + "/payment.pay\"" + "}";

			System.out.println(">>> [JSON 전송 확인]: " + jsonParams);

			try (OutputStream out = conn.getOutputStream()) {
				byte[] input = jsonParams.getBytes("utf-8");
				out.write(input, 0, input.length);
			}

			int code = conn.getResponseCode();
			System.out.println(">>> [4] 카카오 서버 응답 코드: " + code);

			if (code == 200) {
			    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
			    StringBuilder sb = new StringBuilder();
			    String line;
			    while ((line = br.readLine()) != null) { sb.append(line); }

			    String result = sb.toString();
			    System.out.println(">>> [5] 카카오 응답 데이터: " + result);

			    // 1. 서버가 준 데이터에서 tid를 먼저 추출합니다.
			    String tidFromServer = result.split("\"tid\":\"")[1].split("\"")[0];
			    String pcUrl = result.split("\"next_redirect_pc_url\":\"")[1].split("\"")[0];

			    // 2. 추출한 tid를 세션에 새로 저장합니다.
			    session.setAttribute("tid", tidFromServer);
			    System.out.println(">>> [TID 세션 저장 완료]: " + tidFromServer);
			    
			    response.sendRedirect(pcUrl);
			} else {
				BufferedReader errorBr = new BufferedReader(new InputStreamReader(conn.getErrorStream(), "utf-8"));
				System.out.println(">>> [ERROR] 상세 내용: " + errorBr.readLine());
			}

		} catch (Exception e) {
			System.out.println(">>> [EXCEPTION] 자바 예외 발생");
			e.printStackTrace();
		}
	}
}
