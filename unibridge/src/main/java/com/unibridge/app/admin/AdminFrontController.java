package com.unibridge.app.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.unibridge.app.Result;
import com.unibridge.app.admin.controller.AdminLoginController;
import com.unibridge.app.admin.controller.AdminLoginOkController;
import com.unibridge.app.admin.controller.AdminLogoutOkController;
import com.unibridge.app.admin.controller.AdminMainController;
import com.unibridge.app.admin.controller.AdminMenteeBoardController;
import com.unibridge.app.admin.controller.AdminMenteeBoardDeleteOkController;
import com.unibridge.app.admin.controller.AdminMenteeBoardDetailController;
import com.unibridge.app.admin.controller.AdminMenteeBoardEditController;
import com.unibridge.app.admin.controller.AdminMenteeBoardEditOkController;
import com.unibridge.app.admin.controller.AdminMenteeBoardWriteController;
import com.unibridge.app.admin.controller.AdminMenteeBoardWriteOkController;
import com.unibridge.app.admin.controller.AdminMentorBoardController;
import com.unibridge.app.admin.controller.AdminMentorBoardDeleteOkController;
import com.unibridge.app.admin.controller.AdminMentorBoardDetailController;
import com.unibridge.app.admin.controller.AdminMentorBoardEditController;
import com.unibridge.app.admin.controller.AdminMentorBoardEditOkController;
import com.unibridge.app.admin.controller.AdminMentorBoardWriteController;
import com.unibridge.app.admin.controller.AdminMentorBoardWriteOkController;
import com.unibridge.app.admin.controller.AdminReportController;
import com.unibridge.app.admin.controller.AdminReportDeleteController;
import com.unibridge.app.admin.controller.AdminReportDetailController;
import com.unibridge.app.admin.controller.AdminReportListController;
import com.unibridge.app.admin.controller.AdminUserMMController;
import com.unibridge.app.admin.controller.AdminUserMMDetailController;
import com.unibridge.app.admin.controller.AdminUserMMDetailWaitController;

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
			
		case "logoutOk.admin":
		case "/logoutOk.admin":
			System.out.println("로그아웃 준비");
			result = new AdminLogoutOkController().execute(request, response);
			System.out.println("로그아웃 완료");
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
	    	
	    case "menteeBoardDetail.admin":
	    case "/menteeBoardDetail.admin":
	    	System.out.println("멘티 게시판 상세 화면 출력 준비");
	    	result = new AdminMenteeBoardDetailController().execute(request,response);
	    	System.out.println("멘티 게시판 상세 화면 출력 완료");
	    	break;
	    	
	    case "menteeBoardWrite.admin":
	    case "/menteeBoardWrite.admin":
	    	System.out.println("멘티 게시판 글 생성 화면 출력 준비");
	    	result = new AdminMenteeBoardWriteController().execute(request, response);
	        System.out.println("멘티 게시판 글 생성 화면 출력 완료");
	        break;
	        
	    case "menteeBoardWriteOk.admin":
	    case "/menteeBoardWriteOk.admin":
	    	System.out.println("멘티 게시판 글 생성 준비");
	    	result = new AdminMenteeBoardWriteOkController().execute(request, response);
	    	System.out.println("멘티 게시판 글 작성 완료");
	    	break;
	        
	    case "menteeBoardEdit.admin":
	    case "/menteeBoardEdit.admin":
	    	System.out.println("멘티 게시판 글 수정 화면 출력 준비");
	    	result = new AdminMenteeBoardEditController().execute(request, response);
	    	System.out.println("멘티 게시판 글 수정 화면 출력 완료");
	    	break;

	    case "menteeBoardEditOk.admin":
	    case "/menteeBoardEditOk.admin":
	    	System.out.println("멘티 게시판 글 수정 준비");
	    	result = new AdminMenteeBoardEditOkController().execute(request, response);
	    	System.out.println("멘티 게시판 글 수정 완료");
	    	break;
	    	
	    case "menteeBoardDeleteOk.admin":
	    case "/menteeBoardDeleteOk.admin":
	    	System.out.println("멘티 게시판 글 삭제 준비");
	    	result = new AdminMenteeBoardDeleteOkController().execute(request, response);
	    	System.out.println("멘티 게시판 글 삭제 완료");
	    	break;
	    	
	    	
	    	
	    case "mentorBoardList.admin":
	    case "/mentorBoardList.admin":
	    	System.out.println("멘티 게시판 화면 출력 준비");
	    	result = new AdminMentorBoardController().execute(request,response);
	    	System.out.println("멘티 게시판 화면 출력 완료");
	    	break;
	    	
	    case "mentorBoardDetail.admin":
	    case "/mentorBoardDetail.admin":
	    	System.out.println("멘티 게시판 상세 화면 출력 준비");
	    	result = new AdminMentorBoardDetailController().execute(request,response);
	    	System.out.println("멘티 게시판 상세 화면 출력 완료");
	    	break;
	    	
	    case "mentorBoardWrite.admin":
	    case "/mentorBoardWrite.admin":
	    	System.out.println("멘티 게시판 글 생성 화면 출력 준비");
	    	result = new AdminMentorBoardWriteController().execute(request, response);
	        System.out.println("멘티 게시판 글 생성 화면 출력 완료");
	        break;
	        
	    case "mentorBoardWriteOk.admin":
	    case "/mentorBoardWriteOk.admin":
	    	System.out.println("멘티 게시판 글 생성 준비");
	    	result = new AdminMentorBoardWriteOkController().execute(request, response);
	    	System.out.println("멘티 게시판 글 작성 완료");
	    	break;
	        
	    case "mentorBoardEdit.admin":
	    case "/mentorBoardEdit.admin":
	    	System.out.println("멘티 게시판 글 수정 화면 출력 준비");
	    	result = new AdminMentorBoardEditController().execute(request, response);
	    	System.out.println("멘티 게시판 글 수정 화면 출력 완료");
	    	break;

	    case "mentorBoardEditOk.admin":
	    case "/mentorBoardEditOk.admin":
	    	System.out.println("멘티 게시판 글 수정 준비");
	    	result = new AdminMentorBoardEditOkController().execute(request, response);
	    	System.out.println("멘티 게시판 글 수정 완료");
	    	break;
	    	
	    case "mentorBoardDeleteOk.admin":
	    case "/mentorBoardDeleteOk.admin":
	    	System.out.println("멘티 게시판 글 삭제 준비");
	    	result = new AdminMentorBoardDeleteOkController().execute(request, response);
	    	System.out.println("멘티 게시판 글 삭제 완료");
	    	break;
	    	
	    
	    case  "report.admin":
		case "/report.admin":
			result = new AdminReportController().execute(request, response);
			break;
			
		case  "reportList.admin":
		case "/reportList.admin":
			result = new AdminReportListController().execute(request, response);
			break;
			
		case  "reportDetail.admin":
		case "/reportDetail.admin":
			result = new AdminReportDetailController().execute(request, response);
			break;
			
		case  "reportDelete.admin":
		case "/reportDelete.admin":
			result = new AdminReportDeleteController().execute(request, response);
			break;
			
		case  "userMM.admin":
		case "/userMM.admin":
			result = new AdminUserMMController().execute(request, response);
			break;
			
		case  "userMMDetail.admin":
		case "/userMMDetail.admin":
			result = new AdminUserMMDetailController().execute(request, response);
			break;
			
		case  "userMMDetailWait.admin":
		case "/userMMDetailWait.admin":
			result = new AdminUserMMDetailWaitController().execute(request, response);
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
