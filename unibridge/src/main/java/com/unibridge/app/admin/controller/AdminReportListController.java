package com.unibridge.app.admin.controller;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.unibridge.app.Execute;
import com.unibridge.app.Result;
import com.unibridge.app.admin.dao.AdminReportListDAO;
import com.unibridge.app.admin.dto.AdminReportListDTO;

public class AdminReportListController implements Execute {
	AdminReportListDAO adminReportDetailDAO = new AdminReportListDAO();
	
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
		Integer matchingNumber = Integer.parseInt(request.getParameter("matchingNumber"));
		List<AdminReportListDTO> learningReports = this.adminReportDetailDAO.selectReportDetail(matchingNumber);
		
		HttpSession httpSession = request.getSession();
		if (httpSession == null) {
			result.setRedirect(true);
			result.setPath(request.getContextPath() + "/index.main");
			return;
		}
		
		httpSession.setAttribute("lastMatchingNumber", matchingNumber);
		httpSession.setAttribute("learningReports", learningReports);
		
		List<Integer> weekList = learningReports.stream()
			    .map(AdminReportListDTO::getLrReportWeek)
			    .distinct()
			    .sorted()
			    .collect(Collectors.toList());
		
		Gson gson = new Gson();
		request.setAttribute("learningReports", gson.toJson(learningReports));
		request.setAttribute("learningReportWeeks", gson.toJson(weekList));
		
		result.setRedirect(false);
		result.setPath("/app/admin/adminReport/reportList.jsp");
	}

	private void doPost(HttpServletRequest request, HttpServletResponse response, Result result) {
	}
}
