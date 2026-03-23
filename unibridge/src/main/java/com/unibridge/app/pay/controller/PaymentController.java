package com.unibridge.app.pay.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.unibridge.app.Execute;
import com.unibridge.app.Result;
import com.unibridge.app.mentorSearch.dao.MentorSearchDAO;
import com.unibridge.app.mentorSearch.dto.MentorSearchDTO;

public class PaymentController implements Execute {
	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response) {
		Result result = new Result();
		HttpSession session = request.getSession();

		try {
			// 파라미터 이름을 확인하세요 (상세페이지에서 memberNumber로 보낸다고 가정)
			String mentorNumberStr = request.getParameter("memberNumber");
			System.out.println(">>> [DEBUG] 넘어온 멘토 번호: " + mentorNumberStr);

			// 파라미터가 null일 경우에 대한 방어 코드
			if (mentorNumberStr == null || mentorNumberStr.isEmpty()) {
				System.out.println("[Log] memberNumber 파라미터가 전송되지 않았습니다.");
				result.setPath("/mentor/mentorSearchOk.sch"); // 에러 시 목록으로 리다이렉트
				result.setRedirect(true);
				return result;
			}

			long memberNumber = Long.parseLong(mentorNumberStr);

			session.setAttribute("matchingNumber", 1L);
			session.setAttribute("payAmount", "10000");

			request.setAttribute("mentorNumber", memberNumber);
			request.setAttribute("totalAmount", 10000);

			// 데이터 조회
			MentorSearchDAO dao = new MentorSearchDAO();
			MentorSearchDTO mentor = dao.selectMentorDetail(memberNumber);

			// 조회된 결과가 없을 경우 처리
			if (mentor == null) {
				System.out.println("[Log] 해당 번호의 멘토 정보를 찾을 수 없습니다.");
			}

			request.setAttribute("mentor", mentor);

			// 경로 설정 (오타 주의: /app/user/mentorSearch/payment/payment.jsp)
			result.setPath("/app/user/mentorSearch/payment/payment.jsp");
			result.setRedirect(false);

		} catch (Exception e) {
			e.printStackTrace();
			// 에러 발생 시 최소한의 경로 설정 (안 하면 NullPointerException 발생)
			result.setPath("/index.main");
			result.setRedirect(true);
		}
		return result;
	}
}