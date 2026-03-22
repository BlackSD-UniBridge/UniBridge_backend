package com.unibridge.app.admin.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.unibridge.app.Execute;
import com.unibridge.app.Result;
import com.unibridge.app.admin.dao.AdMentorBoardDAO;

public class AdminMentorBoardEditController implements Execute {

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Result result = new Result();
		
		System.out.println("게시글 수정 페이지 컨트롤러 이동 완료");
		int boardNumber = Integer.valueOf(request.getParameter("boardNumber"));
		AdMentorBoardDAO boardDAO = new AdMentorBoardDAO();
		
		System.out.println("boardNumber 값 : " + boardNumber);
		request.setAttribute("board", boardDAO.selectPage(boardNumber));

		
		result.setPath("/app/admin/adminBoard/mentorBoard/mentorBoardEdit.jsp");
		result.setRedirect(false);
		
		return result;
	}

}
