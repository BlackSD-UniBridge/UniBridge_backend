package com.unibridge.app.main;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.unibridge.app.Result;
import com.unibridge.app.main.controller.MainController;

public class MainFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public MainFrontController() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doProcess(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doProcess(request, response);
	}

	private void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		System.out.println("\n[MainFrontController] 요청 진입");

		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();

		// [수정] contextPath(/unibridge) 이후의 모든 경로를 target으로 잡습니다.
		// 결과 예: "/mvc/auth/mentor/myPage.my"
		String target = requestURI.substring(contextPath.length());

		System.out.println("[디버깅] 전체 URI: " + requestURI);
		System.out.println("[디버깅] 추출된 target: [" + target + "]");

		Result result = null;

		try {
			// [수정] 요청한 주소 체계와 1:1로 매칭
			if (target.equals("/index.main")) {
				result = new MainController().execute(request, response);
			}
			// 멘토 마이페이지 경로 매핑
			else if (target.equals("/mvc/auth/mentor/myPage.my")) {
				System.out.println("-> 멘토 마이페이지 매칭 성공");
				result = new Result();
				// 실제 파일 위치 (이미지 기준)
				result.setPath("/app/user/mentor/myPage/myPage.jsp");
				result.setRedirect(false);
			}
			// 멘티 마이페이지 경로 매핑
			else if (target.equals("/mvc/auth/mentee/myPage.my")) {
				System.out.println("-> 멘티 마이페이지 매칭 성공");
				result = new Result();
				result.setPath("/app/user/mentee/myPage/myPage.jsp");
				result.setRedirect(false);
			}
			// 미정(Pending) 마이페이지 경로 매핑
			else if (target.equals("/mvc/auth/pending/myPage.my")) {
				result = new Result();
				result.setPath("/app/user/pending/info.jsp");
				result.setRedirect(false);
			}

			// 이동 처리
			if (result != null) {
				if (result.isRedirect()) {
					response.sendRedirect(result.getPath());
				} else {
					request.getRequestDispatcher(result.getPath()).forward(request, response);
				}
			} else {
				System.out.println("[경고] 매칭되는 경로가 없습니다: " + target);
				response.sendError(404);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String extractTargetPath(String requestUri) {
		String[] splitedPaths = requestUri.split("/");
		return splitedPaths[splitedPaths.length - 1];
	}
}