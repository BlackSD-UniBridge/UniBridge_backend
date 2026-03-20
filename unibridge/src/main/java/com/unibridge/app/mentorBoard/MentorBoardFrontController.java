package com.unibridge.app.mentorBoard;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.unibridge.app.Result;
import com.unibridge.app.mentorBoard.controller.MentorBoardDeleteOkController;
import com.unibridge.app.mentorBoard.controller.MentorBoardListOkController;
import com.unibridge.app.mentorBoard.controller.MentorBoardReadOkController;
import com.unibridge.app.mentorBoard.controller.MentorBoardUpdateController;
import com.unibridge.app.mentorBoard.controller.MentorBoardUpdateOkController;
import com.unibridge.app.mentorBoard.controller.MentorBoardWriteController;
import com.unibridge.app.mentorBoard.controller.MentorBoardWriteOkController;

public class MentorBoardFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public MentorBoardFrontController() {
		super();
	}

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
		String requestURI = request.getRequestURI();
		String target = extractTargetPath(requestURI);
		System.out.println("MentorBoardFrontController target : " + target);

		Result result = new Result();

		switch (target) {
			case "mentorBoardList.mob":
			case "MentorBoardList.mob":
				System.out.println("Mentor 게시물 목록 처리 요청");
				result = new MentorBoardListOkController().execute(request, response);
				break;

			case "mentorBoardRead.mob":
			case "MentorBoardRead.mob":
				System.out.println("Mentor 게시물 상세 페이지 처리 요청");
				result = new MentorBoardReadOkController().execute(request, response);
				break;

			case "mentorBoardWrite.mob":
			case "MentorBoardWrite.mob":
				if ("POST".equals(request.getMethod())) {
					System.out.println("Mentor 게시물 작성 처리");
					result = new MentorBoardWriteOkController().execute(request, response);
				} else {
					System.out.println("Mentor 게시물 작성 페이지 이동");
					result = new MentorBoardWriteController().execute(request, response);
				}
				break;

			case "mentorBoardUpdate.mob":
			case "MentorBoardUpdate.mob":
				if ("POST".equals(request.getMethod())) {
					System.out.println("Mentor 게시물 수정 처리");
					result = new MentorBoardUpdateOkController().execute(request, response);
				} else {
					System.out.println("Mentor 게시물 수정 페이지 이동");
					result = new MentorBoardUpdateController().execute(request, response);
				}
				break;

			case "mentorBoardDelete.mob":
			case "MentorBoardDelete.mob":
				System.out.println("Mentor 게시물 삭제 처리");
				result = new MentorBoardDeleteOkController().execute(request, response);
				break;

			default:
				System.out.println("실패! 현재 target 값: " + target);
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
