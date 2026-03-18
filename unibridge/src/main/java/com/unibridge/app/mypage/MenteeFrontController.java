package com.unibridge.app.mypage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.unibridge.app.Execute;
import com.unibridge.app.Result;
<<<<<<< HEAD
import com.unibridge.app.member.controller.DeleteController;
import com.unibridge.app.member.controller.UpdateController;
import com.unibridge.app.mypage.matching.controller.MatchingController;
import com.unibridge.app.mypage.matching.controller.PayLogController;
import com.unibridge.app.mypage.survey.controller.SurveyController;
=======
import com.unibridge.app.mypage.surveyMentee.controller.SurveyMenteeController;
import com.unibridge.app.mypage.delete.controller.MenteeDeleteController;
import com.unibridge.app.mypage.delete.controller.MentorDeleteController;
>>>>>>> 2dcfaea6b47442a48a168dc7e8f723285f026411

public class MenteeFrontController implements Execute {
	Result outResult = new Result();
	
	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String requestURI = request.getRequestURI();
		String target = extractTargetPath(requestURI);
		
		switch (target) {
<<<<<<< HEAD
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
=======
		case  "myPage/delete.my":
		case "/myPage/delete.my":
			this.outResult = new MenteeDeleteController().execute(request, response);
			break;
		case "survey.my":
		case "/survey.my":
			System.out.println("멘티 설문 등록 요청 수신");
			outResult = new SurveyMenteeController().execute(request, response);
>>>>>>> 2dcfaea6b47442a48a168dc7e8f723285f026411
		default:
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
