package com.unibridge.app.admin.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.unibridge.app.Execute;
import com.unibridge.app.Result;
import com.unibridge.app.admin.dao.AdMentorBoardDAO;

public class AdminMentorBoardDeleteOkController implements Execute{

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("게시글 삭제 서블릿 접근 완료");
		AdMentorBoardDAO boardDAO = new AdMentorBoardDAO();
		Result result = new Result();
		
		int boardNumber = Integer.parseInt(request.getParameter("boardNumber"));
		System.out.println(boardNumber);
		
		
		boardDAO.deleteBoard(boardNumber);
		System.out.println("게시글 삭제 완료");
		
		String path = request.getContextPath() + "/app/admin/adminBoard/mentorBoard/mentorBoardList.admin";
		result.setPath(path);
		result.setRedirect(true);
		
		return result;
	}

}
