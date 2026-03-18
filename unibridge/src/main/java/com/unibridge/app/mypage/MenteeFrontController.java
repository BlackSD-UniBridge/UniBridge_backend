package com.unibridge.app.mypage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.unibridge.app.Execute;
import com.unibridge.app.Result;
import com.unibridge.app.mypage.surveyMentee.controller.SurveyMenteeController;
import com.unibridge.app.mypage.delete.controller.MenteeDeleteController;
import com.unibridge.app.mypage.delete.controller.MentorDeleteController;

public class MenteeFrontController implements Execute {
	Result outResult = new Result();
	
	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String requestURI = request.getRequestURI();
		String target = extractTargetPath(requestURI);
		switch (target) {
		case  "myPage/delete.my":
		case "/myPage/delete.my":
			this.outResult = new MenteeDeleteController().execute(request, response);
			break;
		case "survey.my":
		case "/survey.my":
			System.out.println("멘티 설문 등록 요청 수신");
			outResult = new SurveyMenteeController().execute(request, response);
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
