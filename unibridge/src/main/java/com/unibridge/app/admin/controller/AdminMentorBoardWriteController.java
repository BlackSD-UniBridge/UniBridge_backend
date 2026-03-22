package com.unibridge.app.admin.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.unibridge.app.Execute;
import com.unibridge.app.Result;
import com.unibridge.app.admin.dao.AdminDAO;


public class AdminMentorBoardWriteController implements Execute {

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("게시글 작성 페이지 컨트롤러 이동 완료");
		AdminDAO adminDAO = new AdminDAO();
		Result result = new Result();
		HttpSession session = request.getSession();
		Integer adminNumber = (Integer)session.getAttribute("adminNumber");
		String path = null;
		
		path = "/app/admin/adminBoard/mentorBoard/mentorBoardWrite.jsp";
		request.setAttribute("admidNickname", adminDAO.getMemberNickname(adminNumber));
		
		result.setPath(path);
		result.setRedirect(false);
		
		return result;
		
		
	}


	
}









