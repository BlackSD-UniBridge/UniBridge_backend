package com.unibridge.app.noticeBoard;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.unibridge.app.Result;
import com.unibridge.app.noticeBoard.controller.NoticeBoardListOkController;
import com.unibridge.app.noticeBoard.controller.NoticeBoardReadOkController;

/**
 * Servlet implementation class BoardFrontController
 */
public class NoticeBoardFrontController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	public NoticeBoardFrontController() {
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

		String requestURI = request.getRequestURI();
		String target     = extractTargetPath(requestURI);
		System.out.println("NoticeBoardFrontController target : " + target);

		Result result = new Result();

		switch (target) {
			case "noticeBoardList.ntb":
//			case "/noticeBoardListOk.ntb":
				System.out.println("게시물 목록 처리 요청");
				result = new NoticeBoardListOkController().execute(request, response);
				break;

			case "noticeBoardRead.ntb":
//			case "/noticeBoardReadOk.ntb":
				System.out.println("게시물 상세 페이지 처리 요청");
				result = new NoticeBoardReadOkController().execute(request, response);
				break;

			default:
				System.out.println("실패! 현재 target 값: " + target); // 디버깅용
				response.sendError(HttpServletResponse.SC_NOT_FOUND, "요청한 기능을 사용할 수 없습니다.");
				return;
		}

		if (result != null && result.getPath() != null) {
			if (result.isRedirect()) {
				response.sendRedirect(result.getPath());
			} else {
				request.getRequestDispatcher(result.getPath()).forward(request, response);
			}
		}
	}

	private String extractTargetPath(String requestUri) {
		String[] splitedPaths = requestUri.split("/");
		return splitedPaths[splitedPaths.length - 1];
	}
	
}
