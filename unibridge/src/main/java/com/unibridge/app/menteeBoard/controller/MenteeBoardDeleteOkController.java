package com.unibridge.app.menteeBoard.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.unibridge.app.Execute;
import com.unibridge.app.Result;
import com.unibridge.app.menteeBoard.dao.MenteeBoardDAO;

public class MenteeBoardDeleteOkController implements Execute{

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("===MenteeBoardDeleteOkController===");
		
		MenteeBoardDAO MenteeBoardDAO = new MenteeBoardDAO();
		Result result = new Result();
		
		//게시글 번호 받기
		int MenteeBoardNumber = Integer.parseInt(request.getParameter("MenteeBoardNumber"));
		
		//업로드 경로
		final String UPLOAD_PATH = request.getSession().getServletContext().getRealPath("/") + "upload/";
		
		// 게시글 삭제 전 댓글 먼저 삭제
		MenteeBoardDAO.deleteCommentByBoard(MenteeBoardNumber);
		//게시글 삭제
		MenteeBoardDAO.deleteBoard(MenteeBoardNumber);
		
		//이동
		result.setPath(request.getContextPath() + "/app/user/mentee/menteeBoard/MenteeBoardList.meb");
		result.setRedirect(true);
		
		return result;
	}
	

}











