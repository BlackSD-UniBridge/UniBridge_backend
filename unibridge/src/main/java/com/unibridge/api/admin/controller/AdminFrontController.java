package com.unibridge.api.admin.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.unibridge.api.ApiResult;

public class AdminFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminFrontController() {
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
		
		switch (target) {
		case  "adminMM/searchUsers.admin":
		case "/adminMM/searchUsers.admin":
			return new AdminUserMMController().execute(request, response);
		case  "report/searchUsers.admin":
		case "/report/searchUsers.admin":
			return new AdminReportController().execute(request, response);
		default:
			return null;
		}
	}
	
	private String extractTargetPath(String requetUri) {
		String[] uriPaths = requetUri.split("/api/admin");
		String   targetUriPath = uriPaths[uriPaths.length - 1];
		return targetUriPath;
	}
	
	private boolean checkValidUrl(String requestUri) {		
		if (!requestUri.endsWith(".admin")) {
			return false;
		}
		
		return true;
	}
}
