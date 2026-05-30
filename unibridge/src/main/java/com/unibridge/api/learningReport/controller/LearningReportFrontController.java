package com.unibridge.api.learningReport.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.unibridge.api.ApiResult;
import com.unibridge.api.admin.controller.AdminReportController;
import com.unibridge.api.admin.controller.AdminReportListController;
import com.unibridge.api.admin.controller.AdminUserMMController;
import com.unibridge.api.learningReport.controller.LearningReportWriteController;

public class LearningReportFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LearningReportFrontController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doProcess(request, response);
	}
	
	/* 시작 URL : /api/admin ... */
	private void doProcess(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		ApiResult<?> responseObject = this.handleProcess(request, response);
		if (responseObject == null) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND, "요청한 기능을 사용할 수 없습니다.");
			return;
		}
		
		response.setContentType(responseObject.getContentType());
		response.setCharacterEncoding("UTF-8");
		
		PrintWriter respWriter = response.getWriter();
		respWriter.write(responseObject.getRawData().toString());
	}
	
	private ApiResult<String> handleProcess(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String requestURI = request.getRequestURI();
		String target = extractTargetPath(requestURI);
		if (!this.checkValidUrl(requestURI)) {
			return null;
		}
		
		ApiResult<String> apiResult = new ApiResult<String>();
		switch (target) {
		case  "lr/searchAllReports.rep":
		case "/lr/searchAllReports.rep":
		case  "lr/selectAllSubjects.rep":
		case "/lr/selectAllSubjects.rep":
		case  "lr/searchAllReportsNum.rep":
		case "/lr/searchAllReportsNum.rep":
			apiResult = new LearningReportController().execute(request, response);
			break;
		case  "lr/newLearningReport.rep":
		case "/lr/newLearningReport.rep":
			apiResult = new LearningReportWriteController().execute(request, response);
			break;
		case  "lr/modifyLearningReport.rep":
		case "/lr/modifyLearningReport.rep":
			apiResult = new LearningReportModifyController().execute(request, response);
			break;
		default:
			break;
		}
		
		return apiResult;
	}
	
	private String extractTargetPath(String requetUri) {
		String[] uriPaths = requetUri.split("/api/user");
		String   targetUriPath = uriPaths[uriPaths.length - 1];
		return targetUriPath;
	}
	
	private boolean checkValidUrl(String requestUri) {		
		if (!requestUri.endsWith(".rep")) {
			return false;
		}
		
		return true;
	}
}
