package com.unibridge.app.mypage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.unibridge.app.Execute;
import com.unibridge.app.Result;

import com.unibridge.app.member.controller.DeleteController;
import com.unibridge.app.member.controller.UpdateController;
import com.unibridge.app.mypage.matching.controller.MatchingController;
import com.unibridge.app.mypage.matching.controller.PayLogController;
import com.unibridge.app.mypage.survey.controller.SurveyController;

public class MenteeFrontController implements Execute {
	Result outResult = new Result();
	
	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    
	    String requestURI = request.getRequestURI();
	    // 로그를 찍어보면 /unibridge/mvc/auth/undecided/myPage.my 처럼 전체가 나옵니다.
	    System.out.println("[Log] 하위 컨트롤러 수신 URI: " + requestURI);

	    Result result = new Result();

	    // switch 대신 if-else contains 방식을 사용하면 경로가 복잡해도 정확히 잡아냅니다.
	    if (requestURI.contains("myPage.my")) {
	        System.out.println("계정관리 요청 수신");
	        result = new UpdateController().execute(request, response);
	    } 
	    else if (requestURI.contains("survey.my")) {
	        System.out.println("설문 요청 수신");
	        result = new SurveyController().execute(request, response);
	    } 
	    else if (requestURI.contains("delete.my")) {
	        System.out.println("회원탈퇴 신청 요청 수신");
	        result = new DeleteController().execute(request, response);
	    } 
	    else if (requestURI.contains("matching.my")) {
	        System.out.println("매칭 정보 조회 수신");
	        result = new MatchingController().execute(request, response);
	    } 
	    else if (requestURI.contains("log.my")) {
	        System.out.println("결제 정보 조회 수신");
	        result = new PayLogController().execute(request, response);
	    } 
	    else {
	        System.out.println("[Warn] 매칭되는 기능을 찾을 수 없음: " + requestURI);
	    }
	    
	    return result;
	}
	
	private String extractTargetPath(String requestUri) {
		String[] splitedPaths = requestUri.split("/");
		String   target = splitedPaths[splitedPaths.length - 1];
		return target;
	}
}
