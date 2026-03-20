package com.unibridge.app.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.unibridge.app.Result;
import com.unibridge.app.admin.controller.AdminLoginController;
import com.unibridge.app.admin.controller.AdminLoginOkController;
import com.unibridge.app.admin.controller.AdminMainController;
import com.unibridge.app.admin.controller.AdminMenteeBoardController;
import com.unibridge.app.admin.controller.AdminMentorBoardController;
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
		
		System.out.println("AdminFrontController 접근");
		
		switch (target) {
		case "login.admin":
		case "/login.admin":
			System.out.println("로그인 화면 출력 준비");
			result = new AdminLoginController().execute(request, response);
			System.out.println("로그인 화면 출력 완료");
			break;
			
		case "loginOk.admin":
		case "/loginOk.admin":
			System.out.println("로그인 확인 준비");
			result = new AdminLoginOkController().execute(request,response);
			System.out.println("로그인 확인 완료");
			break;
			
	    case "main.admin":
	    case "/main.admin":
	         System.out.println("관리자 메인 페이지 화면 출력 준비");
	         result = new AdminMainController().execute(request, response);
	         System.out.println("관리자 메인 페이지 화면 출력 완료");
	         break;
		
	    case "menteeBoardList.admin":
	    case "/menteeBoardList.admin":
	    	System.out.println("멘티 게시판 화면 출력 준비");
	    	result = new AdminMenteeBoardController().execute(request,response);
	    	System.out.println("멘티 게시판 화면 출력 완료");
	    	break;
	         
	    case "mentorBoardList.admin":
	    case "/mentorBoardList.admin":
	    	System.out.println("멘토 게시판 화면 출력 준비");
	    	result = new AdminMentorBoardController().execute(request, response);
	    	System.out.println("멘토 게시판 화면 출력 완료");
	    	break;
	    	
	    
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
		
		if(result != null && result.getPath() != null) {
			if(result.isRedirect()) {
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
