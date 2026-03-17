package com.unibridge.app.pay.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.unibridge.app.Execute;
import com.unibridge.app.Result;
import com.unibridge.app.pay.dao.PaymentDAO;
import com.unibridge.app.pay.dto.PaymentDTO;

import java.io.IOException;

public class PaymentOkController implements Execute {

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Result result = new Result();
		HttpSession session = request.getSession();

		try {
			// 1. 설정 정보 로드
			String secretKey = "SECRET_KEY " + ConfigReader.getProperty("kakao.secret.key");
			String cid = ConfigReader.getProperty("kakao.cid");
			String tid = (String) session.getAttribute("tid");
			String pgToken = request.getParameter("pg_token");

			// 2. 카카오페이 승인 API 호출 준비
			URL url = new URL("https://open-api.kakaopay.com/online/v1/payment/approve");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Authorization", secretKey);
			conn.setRequestProperty("Content-type", "application/json;charset=utf-8");
			conn.setDoOutput(true);

			// 승인 요청 JSON (partner_order_id 등은 가입/예약 번호와 연동 권장)
			String jsonParams = "{" + "\"cid\":\"" + cid + "\"," + "\"tid\":\"" + tid + "\","
					+ "\"partner_order_id\":\"1001\"," + "\"partner_user_id\":\"unibridge\"," + "\"pg_token\":\""
					+ pgToken + "\"" + "}";

			try (OutputStream out = conn.getOutputStream()) {
				out.write(jsonParams.getBytes("utf-8"));
			}

			int code = conn.getResponseCode();

			if (code == 200) {

				// 1. 응답 데이터 처리 (필요시 JSON 파싱하여 금액 등 추출)
				BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
				StringBuilder sb = new StringBuilder();
				String line;
				while ((line = br.readLine()) != null) {
					sb.append(line);
				}
				System.out.println(">>> [카카오 승인 응답]: " + sb.toString());

				// 2. DB 저장을 위한 DTO 세팅
				PaymentDTO payDTO = new PaymentDTO();

				// 세션이나 리퀘스트에서 필요한 정보 가져오기
				Long memberNumber = (Long) session.getAttribute("memberNumber");
				// 매칭 번호는 결제 전 단계에서 생성되어 전달되었다고 가정
				Long matchingNumber = (Long) session.getAttribute("matchingNumber");

				payDTO.setMemberNumber(memberNumber != null ? memberNumber : 0L);
				payDTO.setMatchingNumber(matchingNumber != null ? matchingNumber : 0L);
				payDTO.setPayAmount("10000"); // 예시 금액 (실제 데이터에 맞게 수정)
				payDTO.setPayMethod("카카오페이");
				payDTO.setPayStatus("SUCCESS");

				// 3. DAO 호출하여 저장
				PaymentDAO dao = new PaymentDAO();
				dao.insertPayment(payDTO);

				System.out.println(">>> [DB 저장 완료] 회원번호 " + memberNumber + "의 결제 내역 저장 성공");
				
				// 4. 세션 정리 및 결과 페이지 이동
				session.removeAttribute("tid");
				result.setPath(request.getContextPath() + "/app/user/payment/paymentFinish.jsp");
				result.setRedirect(true);

			} else {
				// [실패] 에러 처리
				response.setContentType("text/html; charset=UTF-8");
				response.getWriter().print("<script>alert('결제 승인에 실패하였습니다.'); location.href='index.jsp';</script>");
				return null; // 스크립트로 직접 응답했으므로 null 리턴
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}
}