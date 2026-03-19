package com.unibridge.app.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.unibridge.app.Execute;
import com.unibridge.app.Result;
import com.unibridge.app.member.dao.MemberDAO;

public class SignoutController implements Execute {
	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String method = request.getMethod().toUpperCase();
		Result outResult = new Result();

		switch (method) {
		case "GET":
			this.doGet(request, response, outResult);
			break;
		case "POST":
			this.doPost(request, response, outResult);
			break;
		default:
			break;
		}
		return outResult;
	}

	private void doGet(HttpServletRequest request, HttpServletResponse response, Result result) {
		HttpSession oldSession = request.getSession(false);
		if (oldSession != null) {
			oldSession.invalidate();
		}
		
		result.setRedirect(true);
		result.setPath(request.getContextPath() + "/index.main");
	}

	private void doPost(HttpServletRequest request, HttpServletResponse response, Result result) {
	}
}
