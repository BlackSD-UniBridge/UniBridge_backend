package com.unibridge.app.admin.controller;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.unibridge.app.Execute;
import com.unibridge.app.Result;
import com.unibridge.app.admin.dto.AdminReportListDTO;
import com.unibridge.app.admin.dao.AdminReportDetailDAO;

public class AdminReportDetailController implements Execute {
	AdminReportDetailDAO adminReportDetailDAO = new AdminReportDetailDAO();
		
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

	@SuppressWarnings("unchecked")
	private void doGet(HttpServletRequest request, HttpServletResponse response, Result result) throws ServletException, IOException {
		String strReportNumber = request.getParameter("reportNumber");
		if (strReportNumber.isEmpty() || strReportNumber == null) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "유효하지 않은 접근입니다.");
			throw new ServletException("Invalid access detected.");
		}
		int reportNumber = Integer.parseInt(strReportNumber);
		
		HttpSession httpSession = request.getSession(false);
		if (httpSession == null || httpSession.getAttribute("learningReports") == null) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "유효하지 않은 접근입니다.");
			throw new ServletException("Invalid access detected.");
		}
		List<AdminReportListDTO> reports = (List<AdminReportListDTO>) httpSession.getAttribute("learningReports");
		AdminReportListDTO curReport = reports.stream()
			    .filter(value -> Objects.equals(value.getLrReportNumber(), reportNumber))
			    .findFirst()
			    .orElse(null);
		String subjectName = this.adminReportDetailDAO.selectSubjectBySubjectNumber(curReport.getLrSubjectNumber());
		httpSession.setAttribute("curLearningReport", curReport);
		
		Gson gson = new Gson();
		request.setAttribute("subjectName", subjectName);
		request.setAttribute("curLearningReport", gson.toJson(curReport));
		
		result.setRedirect(false);
		result.setPath("/app/admin/adminReport/reportDetail.jsp");
	}

	private void doPost(HttpServletRequest request, HttpServletResponse response, Result result) {
	}
}
