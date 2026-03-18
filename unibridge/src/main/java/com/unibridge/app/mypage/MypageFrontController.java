package com.unibridge.app.mypage;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.unibridge.app.Result;

public class MypageFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MypageFrontController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doProcess(request, response);
	}
	
	private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	    request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");

	    System.out.println("\n=== [MypageFrontController] ===");
	    
	    String requestURI = request.getRequestURI();
		String target = extractUserRole(requestURI);
	    Result result = null;
		
		switch (target) {
		case  "auth/mentor":
		case "/auth/mentor": 
			System.out.println("멘토 마이페이지 이동");
			result = new MentorFrontController().execute(request, response);
			break;
		case  "auth/mentee":
		case "/auth/mentee":
			System.out.println("멘티 마이페이지 이동");
			result = new MenteeFrontController().execute(request, response);
			break;
		case "auth/undecided":
		case "/auth/undecided":
			System.out.println("미정 마이페이지 이동");
		    result = new UndecidedFrontController().execute(request, response);
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
	
	private String extractUserRole(String requestUri) {
		if (requestUri.contains("/auth/mentor")) {
			return "/auth/mentor";
		}
		
		if (requestUri.contains("/auth/mentee")) {
			return "/auth/mentee";
		}
		if (requestUri.contains("/auth/undecided")) {
			return "/auth/undecided";
		}
		return "";
	}
}
