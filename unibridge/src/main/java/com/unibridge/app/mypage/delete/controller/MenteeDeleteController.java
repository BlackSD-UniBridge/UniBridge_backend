package com.unibridge.app.mypage.delete.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.unibridge.app.Execute;
import com.unibridge.app.Result;
import com.unibridge.app.mypage.entrypoint.dao.MentorDAO;

public class MenteeDeleteController implements Execute {
	MentorDAO memberDAO = new MentorDAO();
	Result outResult = new Result();
	
	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String method = request.getMethod().toUpperCase();
		switch (method) {
		case "GET":
			this.doGet(request, response);
			break;
		case "POST":
			this.doPost(request, response);
			break;
		default:
			break;
		}
		return outResult;
	}
	
	private void doGet(HttpServletRequest request, HttpServletResponse response) {
		outResult.setRedirect(true);
		outResult.setPath(request.getContextPath() + "/app/user/mentor/myPage/userDelete/userDelete.jsp");
	}
	
	private void doPost(HttpServletRequest request, HttpServletResponse response) {
		
	}
}
