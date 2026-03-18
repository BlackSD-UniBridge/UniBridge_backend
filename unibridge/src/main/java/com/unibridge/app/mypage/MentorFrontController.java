package com.unibridge.app.mypage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.unibridge.app.Execute;
import com.unibridge.app.Result;
import com.unibridge.app.member.controller.DeleteController;
import com.unibridge.app.member.controller.UpdateController;
import com.unibridge.app.mypage.survey.controller.SurveyController;
import com.unibridge.app.mypage.mentoring.controller.MentoringFrontController;

public class MentorFrontController implements Execute {
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
        // 멘토링 관련 요청들을 모두 MentoringFrontController로 토스
        case "mentoringCreate.my":
        case "mentoringWriteOk.my":
        case "mentoringView.my":
        case "mentoringModify.my":
        case "mentoringModifyOk.my":
        case "mentoringDeleteOk.my":
            System.out.println("[Log] 멘토링 관련 요청 -> MentoringFrontController로 이동");
            this.outResult = new MentoringFrontController().execute(request, response);
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
