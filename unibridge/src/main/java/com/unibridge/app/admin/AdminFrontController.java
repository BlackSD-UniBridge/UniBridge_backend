package com.unibridge.app.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.unibridge.app.Result;
import com.unibridge.app.admin.controller.AdminReportController;
import com.unibridge.app.admin.controller.AdminUserMMController;

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
	
	private void doProcess(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String requestURI = request.getRequestURI();
		String target = extractTargetPath(requestURI);
		Result result = new Result();
		
		switch (target) {
		case  "report.admin":
		case "/report.admin":
			result = new AdminReportController().execute(request, response);
			break;
		case  "userMM.admin":
		case "/userMM.admin":
			result = new AdminUserMMController().execute(request, response);
			break;
		default:
			response.sendError(HttpServletResponse.SC_NOT_FOUND, "요청한 기능을 사용할 수 없습니다.");
			return;
		}
		
	    if (result != null) {
	        if (result.isRedirect()) {
	            response.sendRedirect(result.getPath());
	        } else {
	            request.getRequestDispatcher(result.getPath()).forward(request, response);
	        }
	    }
	}
	
	private String extractTargetPath(String requestUri) {
		String[] splitedPaths = requestUri.split("/");
		String   target = splitedPaths[splitedPaths.length - 1];
		return target;
	}
}
