package com.unibridge.app.admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.unibridge.app.Execute;
import com.unibridge.app.Result;

public class AdminUserMMController implements Execute {
	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Result result = new Result();
		String method = request.getMethod().toUpperCase();
		
		switch (method) {
		case "GET":
			this.doGet(request, response, result);
			break;
		case "POST":
			this.doPost(request, response, result);
			break;
		default:
			break;
		}
		return result;
	}
	
	private void doGet(HttpServletRequest request, HttpServletResponse response, Result result) {
		result.setRedirect(true);
		result.setPath(request.getContextPath() + "/app/admin/adminUserManagement/userList.jsp");
	}

	private void doPost(HttpServletRequest request, HttpServletResponse response, Result result) {
		this.doGet(request, response, result);
	}
}
