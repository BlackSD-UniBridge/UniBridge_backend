package com.unibridge.app.mentorSearch.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		System.out.println("MentorSearchFrontController 실행!!");
		String target = request.getRequestURI().substring(request.getContextPath().length());
		System.out.println("현재 경로 : " + target);

		if (target.equals("/mentor/mentorSearchOk.sch")) {
			// 멘토 검색 페이지 로직 실행
			new MentorSearchOkController().execute(request, response);
		} 
		// 2. 멘토 상세 페이지 보기 요청
		else if (target.equals("/mentor/mentorDetailOk.sch")) {
			new MentorDetailOkController().execute(request, response);
		}

		// 3. 단순 페이지 이동 (데이터 처리 없이 JSP만 띄울 때)
		else if (target.equals("/mentor/mentorSearch.sch")) {
			request.getRequestDispatcher("/app/user/mentorSearch/mentorSearch.jsp").forward(request, response);
		}

	}

}
