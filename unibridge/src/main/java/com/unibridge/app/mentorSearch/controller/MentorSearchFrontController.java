package com.unibridge.app.mentorSearch.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.unibridge.app.Result;

/**
 * Servlet implementation class MentorSearchFrontController
 */
public class MentorSearchFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		// 1. 요청 경로 추출
		String target = request.getRequestURI().substring(request.getContextPath().length());
		System.out.println("MentorSearchFrontController 현재 경로 : " + target);

		// 2. 결과 객체 초기화
		Result result = new Result();

		// 3. 분기 처리 (Switch-case 구조)
		switch (target) {
		case "/app/user/mentorSearch/mentorSearchOk.sch":
			System.out.println("멘토 검색 처리 요청");
			// MentorSearchOkController가 Result를 반환하도록 설계되어 있다면 아래와 같이 작성
			result = new MentorSearchOkController().execute(request, response);
			System.out.println("멘토 검색 처리 완료");
			break;

		case "/mentor/mentorDetailOk.sch":
			System.out.println("멘토 상세 페이지 처리 요청");
			result = new MentorDetailOkController().execute(request, response);
			System.out.println("멘토 상세 페이지 처리 완료");
			break;

		case "/mentor/mentorSearch.sch":
			System.out.println("단순 페이지 이동 요청");
			// 단순 이동도 Result 객체에 경로와 방식(forward)을 담아 일관성 있게 처리
			result.setPath("/app/user/mentorSearch/mentorSearch.jsp");
			result.setRedirect(false);
			break;
		}

		// 4. 전송 처리 (공통 로직)
		if (result != null && result.getPath() != null) {
			if (result.isRedirect()) {
				response.sendRedirect(result.getPath());
			} else {
				request.getRequestDispatcher(result.getPath()).forward(request, response);
			}
		}
	}
}