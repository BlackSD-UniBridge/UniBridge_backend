package com.unibridge.app.mypage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.unibridge.app.Execute;
import com.unibridge.app.Result;
import com.unibridge.app.mypage.delete.controller.MentorDeleteController;
import com.unibridge.app.mypage.entrypoint.controller.MentorController;
import com.unibridge.app.mypage.surveyMentor.controller.SurveyMentorController;


public class MentorFrontController implements Execute {
	Result outResult = new Result();
	
	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String requestURI = request.getRequestURI();
		String target = extractTargetPath(requestURI);
		switch (target) {
		case  "myPage.my":
		case "/myPage.my":
			this.outResult = new MentorController().execute(request, response);
			break;
		case  "delete.my":
		case "/delete.my":
			this.outResult = new MentorDeleteController().execute(request, response);
			break;
		case "survey.my":
		case "/survey.my":
			System.out.println("멘토 설문 등록 요청 수신");
			this.outResult = new SurveyMentorController().execute(request, response);
			break;
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
