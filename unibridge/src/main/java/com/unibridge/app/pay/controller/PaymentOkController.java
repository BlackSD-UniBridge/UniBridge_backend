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
import com.unibridge.app.mypage.matching.dao.MatchingDAO;
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

			// 승인 요청 JSON
			String jsonParams = "{" + "\"cid\":\"" + cid + "\"," + "\"tid\":\"" + tid + "\","
					+ "\"partner_order_id\":\"1001\"," + "\"partner_user_id\":\"unibridge\"," + "\"pg_token\":\""
					+ pgToken + "\"" + "}";

			try (OutputStream out = conn.getOutputStream()) {
				out.write(jsonParams.getBytes("utf-8"));
			}

			int code = conn.getResponseCode();

			if (code == 200) {
				// 1. 응답 데이터 처리
				BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
				StringBuilder sb = new StringBuilder();
				String line;
				while ((line = br.readLine()) != null) {
					sb.append(line);
				}
				System.out.println(">>> [카카오 승인 응답]: " + sb.toString());

				// 2. [수정] 세션에서 필요한 정보 미리 모두 가져오기 (순서 중요)
				Long memberNumber = (Long) session.getAttribute("memberNumber");
				Long matchingNumber = (Long) session.getAttribute("matchingNumber");
				String payAmount = (String) session.getAttribute("payAmount");

				// 안전장치
				if (memberNumber == null) memberNumber = 1L;
				if (payAmount == null) payAmount = "10000";

				// 3. DTO 세팅 (DB 구조에 맞게 회원번호/매칭번호 제외)
				PaymentDTO payDTO = new PaymentDTO();
				payDTO.setPayAmount(payAmount);
				payDTO.setPayMethod("카카오페이");
				payDTO.setPayStatus("SUCCESS");

				// 4. DAO 호출하여 결제 내역 저장
				PaymentDAO payDao = new PaymentDAO();
				payDao.insertPayment(payDTO);
				
				// [출력] 이제 memberNumber 변수가 선언되었으므로 정상 실행됩니다.
				System.out.println(">>> [DB 저장 완료] 회원번호 " + memberNumber + "의 결제 로그 생성 성공");

				// 5. 생성된 결제 ID 확보 및 매칭 정보 업데이트
				long payId = payDao.getLatestPayId();
				
				/* 이 시점에서 MatchingDAO 등을 이용해 
				   UPDATE UB_MATCHING SET PAY_ID = #{payId} WHERE MATCHING_NUMBER = #{matchingNumber} 를 수행
				*/
				MatchingDAO matchingDao = new MatchingDAO();
				matchingDao.updatePayId(matchingNumber, payId); 
				
				System.out.println(">>> [매칭 연결 완료] 결제번호: " + payId + "를 매칭번호: " + matchingNumber + "에 연결함");

				// 6. 결과 페이지로 보낼 정보 조회 (Join 쿼리 사용)
				// 결제 정보와 매칭 정보를 한꺼번에 확인하기 위해 memberNumber 사용
				PaymentDTO latestPay = payDao.selectLatestPaymentByMember(memberNumber);
				request.setAttribute("payInfo", latestPay);

				// 7. 세션 정리 및 페이지 이동
				session.removeAttribute("tid");
				session.removeAttribute("matchingNumber");
				session.removeAttribute("payAmount");

				result.setPath("/app/user/mentorSearch/payment/paymentFinish.jsp");
				result.setRedirect(false);

			} else {
				// 결제 승인 실패 처리
				response.setContentType("text/html; charset=UTF-8");
				response.getWriter().print("<script>alert('결제 승인에 실패하였습니다.'); location.href='"
						+ request.getContextPath() + "/mentor/mentorSearchOk.sch';</script>");
				return null;
			}

		} catch (Exception e) {
			e.printStackTrace();
			response.setContentType("text/html; charset=UTF-8");
			response.getWriter().print("<script>alert('결제 처리 중 오류가 발생했습니다.'); history.back();</script>");
			return null;
		}

		return result;
	}
}