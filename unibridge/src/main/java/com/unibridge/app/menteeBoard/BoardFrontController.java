package com.example.app.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.app.Result;

/**
 * Servlet implementation class BoardFrontController
 */
public class BoardFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardFrontController() {
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
		String target = request.getRequestURI().substring(request.getContextPath().length());
		System.out.println("BoardFrontController 현재 경로 : " + target);
		Result result = new Result();
		
		switch(target) {
		case  "/board/boardListOk.bo":
			System.out.println("게시물 목록 처리 요청");
			result = new BoardListOkController().execute(request, response);
			System.out.println("게시물 목록 처리 완료");
			break;
		case "/board/boardReadOk.bo":
			System.out.println("게시물 상세 페이지 처리 요청");
			result = new BoardReadOkController().execute(request, response);
			System.out.println("게시물 상세 페이지 처리 완료");
			break;
		case "/board/boardWrite.bo":
			System.out.println("게시글 작성 페이지 이동 요청");
			result = new BoardWriteController().execute(request, response);
			System.out.println("게시글 작성 페이지 이동 완료");
			break;
		case "/board/boardWriteOk.bo":
			System.out.println("게시글 작성 요청");
			result = new BoardWriteOkController().execute(request, response);
			System.out.println("게시글 작성 완료");
			break;
		case "/board/boardUpdate.bo":
			System.out.println("게시글 수정 페이지 이동 요청");
			result = new BoardUpdateController().execute(request, response);
			System.out.println("게시글 수정 페이지 이동 완료");
			break;
		case "/board/boardUpdateOk.bo":
			System.out.println("게시글 수정 완료 요청");
			result = new BoardUpdateOkController().execute(request, response);
			System.out.println("게시글 수정 완료");
			break;
		case "/board/boardDeleteOk.bo":
			System.out.println("게시글 삭제 완료 요청");
			result = new BoardDeleteOkController().execute(request, response);
			
		}
		
		if(result != null && result.getPath() != null) {
			if(result.isRedirect()) {
				response.sendRedirect(result.getPath());
			} else {
				request.getRequestDispatcher(result.getPath()).forward(request, response);
			}
		}
		
		
	}

}
