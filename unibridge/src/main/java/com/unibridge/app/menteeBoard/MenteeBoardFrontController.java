package com.unibridge.app.menteeBoard;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.unibridge.app.Result;
import com.unibridge.app.menteeBoard.controller.MenteeBoardDeleteOkController;
import com.unibridge.app.menteeBoard.controller.MenteeBoardListOkController;
import com.unibridge.app.menteeBoard.controller.MenteeBoardReadOkController;
import com.unibridge.app.menteeBoard.controller.MenteeBoardUpdateController;
import com.unibridge.app.menteeBoard.controller.MenteeBoardUpdateOkController;
import com.unibridge.app.menteeBoard.controller.MenteeBoardWriteController;
import com.unibridge.app.menteeBoard.controller.MenteeBoardWriteOkController;

/**
 * Servlet implementation class BoardFrontController
 */
public class MenteeBoardFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MenteeBoardFrontController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request, response);
	}
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String requestURI = request.getRequestURI();
		String target     = extractTargetPath(requestURI);
		System.out.println("MenteeBoardFrontController target : " + target);

		Result result = new Result();

		switch (target) {
			case "MenteeBoardList.meb":
				System.out.println("Mentee 게시물 목록 처리 요청");
				result = new MenteeBoardListOkController().execute(request, response);
				break;

			case "MenteeBoardRead.meb":
				System.out.println("Mentee 게시물 상세 페이지 처리 요청");
				result = new MenteeBoardReadOkController().execute(request, response);
				break;
				
			case "MenteeBoardWrite.meb":
			    if ("POST".equals(request.getMethod())) {
			        System.out.println("Mentee 게시물 작성 처리");
			        result = new MenteeBoardWriteOkController().execute(request, response);
			    } else {
			        System.out.println("Mentee 게시물 작성 페이지 이동");
			        result = new MenteeBoardWriteController().execute(request, response);
			    }
			    break;

			case "MenteeBoardUpdate.meb":
				if ("POST".equals(request.getMethod())) {
					System.out.println("Mentee 게시물 목록 처리 요청");
					result = new MenteeBoardUpdateOkController().execute(request, response);
				}else {
					System.out.println("Mentee 게시물 수정 페이지 이동");
					result = new MenteeBoardUpdateController().execute(request, response);
				}
				break;

			case "MenteeBoardDelete.meb":
				System.out.println("Mentee 게시물 목록 처리 요청");
				result = new MenteeBoardDeleteOkController().execute(request, response);
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
