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

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request, response);
	}

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		Result result = null; // 결과 객체를 담을 변수
		System.out.println("MentorSearchFrontController 실행!!");
		String target = request.getRequestURI().substring(request.getContextPath().length());
		System.out.println("현재 경로 : " + target);

		if (target.equals("/app/user/mentorSearch/mentorSearchOk.sch")) {
			new MentorSearchOkController().execute(request, response);
		}
		// 2. 멘토 상세 페이지 보기 요청
		else if (target.equals("/mentor/mentorDetailOk.sch")) {
			result = new MentorDetailOkController().execute(request, response); // 결과를 변수에 담음
		}

		// 3. 단순 페이지 이동 (데이터 처리 없이 JSP만 띄울 때)
		else if (target.equals("/mentor/mentorSearch.sch")) {
			request.getRequestDispatcher("/app/user/mentorSearch/mentorSearch.jsp").forward(request, response);
		}

		if (result != null) {
			if (result.isRedirect()) {
				response.sendRedirect(result.getPath());
			} else {
				request.getRequestDispatcher(result.getPath()).forward(request, response);
			}
		}

	}

}
