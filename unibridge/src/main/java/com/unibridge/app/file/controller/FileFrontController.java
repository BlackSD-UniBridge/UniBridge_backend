package com.unibridge.app.file.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FileFrontController
 */
public class FileFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FileFrontController() {
		super();
		// TODO Auto-generated constructor stub
	}

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

		System.out.println("FileFrontController 실행!!");

		String requestURI = request.getRequestURI();
		String target = request.getRequestURI().substring(request.getContextPath().length());

		System.out.println("파일 프론트 컨트롤러 현재 경로 : " + target);

		switch (target) {
		case "download.file":
			// 첨부파일 다운로드
			new FileDownloadController().execute(request, response);
			break;
		case "/display.file":
			new FileDisplayController().execute(request, response);
			break;

		default:
			System.out.println("파일 컨트롤러 - 매칭 없음: " + target);
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			break;
		}
	}

	private String extractTargetPath(String requestUri) {
		String[] parts = requestUri.split("/");
		return parts[parts.length - 1];
	}
}
