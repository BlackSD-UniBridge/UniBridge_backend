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
		String target = extractTargetPath(requestURI);
		
		switch (target) {
		case  "myPage.my":
			System.out.println("계정관리 요청 수신");
			this.outResult = new UpdateController().execute(request, response);
			break;
		case "survey.my":
		    System.out.println("설문 요청 수신");
		    this.outResult = new SurveyController().execute(request, response);
		    break;
		case "delete.my":
			System.out.println("회원탈퇴 신청 요청 수신");
			this.outResult = new DeleteController().execute(request, response);
			break;
		case "matching.my":
			System.out.println("매칭 정보 조회 수신");
			this.outResult = new MatchingController().execute(request, response);
			break;
		case "log.my":
			System.out.println("결제 정보 조회 수신");
			this.outResult = new PayLogController().execute(request, response);
			break;
		default:
			System.out.println("[Warn] 매칭되는 target이 없음: " + target);
			break;
		}
		return outResult;
	}
	
	private String extractTargetPath(String requestUri) {
		String[] splitedPaths = requestUri.split("/");
		String   target = splitedPaths[splitedPaths.length - 1];
		return target;
	}
}
