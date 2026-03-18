package com.unibridge.app.mypage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.unibridge.app.Execute;
import com.unibridge.app.Result;
import com.unibridge.app.mypage.surveyMentee.controller.SurveyMenteeOkController;

public class MenteeFrontController implements Execute {
	Result outResult = new Result();
	
	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String requestURI = request.getRequestURI();
		String target = extractTargetPath(requestURI);
		switch (target) {
		case "surveyMenteeOk.my":
		case "/surveyMenteeOk.my":
			System.out.println("멘티 설문 등록 요청 수신");
			outResult = new SurveyMenteeOkController().execute(request, response);
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
