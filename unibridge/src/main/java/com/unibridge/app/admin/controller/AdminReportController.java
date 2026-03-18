package com.unibridge.app.admin.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.unibridge.app.Execute;
import com.unibridge.app.Result;
import com.unibridge.app.member.dao.MemberDAO;

public class AdminReportController implements Execute {
	MemberDAO memberDAO = new MemberDAO();
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
		outResult.setPath(request.getContextPath() + "/app/admin/adminReport/report.jsp");
	}

	private void doPost(HttpServletRequest request, HttpServletResponse response) {
	}
}
