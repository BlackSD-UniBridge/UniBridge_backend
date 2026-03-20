package com.unibridge.app.admin.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.unibridge.app.Execute;
import com.unibridge.app.Result;
import com.unibridge.app.admin.dao.AdminReportDeleteDAO;

public class AdminReportDeleteController implements Execute {
	AdminReportDeleteDAO adminReportDeletDAO = new AdminReportDeleteDAO();
	
	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Result outResult = new Result();
		String method = request.getMethod().toUpperCase();
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
	}

	private void doPost(HttpServletRequest request, HttpServletResponse response, Result result) throws ServletException, IOException  {
		try {
			String strReportNumber = request.getParameter("reportNumber");
			if (strReportNumber.isEmpty() || strReportNumber == null) {
				response.sendError(HttpServletResponse.SC_BAD_REQUEST, "유효하지 않은 접근입니다.");
				throw new ServletException("Invalid access detected.");
			}
			int reportNumber = Integer.parseInt(strReportNumber);
			
			HttpSession httpSession = request.getSession(false);
			if (httpSession == null) {
				response.sendError(HttpServletResponse.SC_BAD_REQUEST, "유효하지 않은 접근입니다.");
				throw new ServletException("Invalid access detected.");
			}
			
			this.adminReportDeletDAO.deleteReportByReportNumber(reportNumber);
	
			Integer matchingNumber = (Integer) httpSession.getAttribute("lastMatchingNumber");
			if (matchingNumber == null) {
				// 유효하지 않은 matchingNumber에 대해 report 메인창으로 redirect			
				result.setPath(request.getContextPath() + "/report.admin");
				result.setRedirect(true);
				return;
			}
			
			result.setPath(request.getContextPath() + "/reportList.admin?matchingNumber=" + matchingNumber);
			result.setRedirect(true);
		} catch (NumberFormatException e) {
			result.setPath(request.getContextPath() + "/report.admin");
			result.setRedirect(true);
		}
	}
}
