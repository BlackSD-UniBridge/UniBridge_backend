package com.unibridge.app.mypage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.unibridge.app.Execute;
import com.unibridge.app.Result;
import com.unibridge.app.member.controller.MentorDeleteController;
import com.unibridge.app.member.controller.MentorMangeController;
import com.unibridge.app.member.controller.MentorSurveyController;
import com.unibridge.app.member.controller.MentorUpdateOkController;
import com.unibridge.app.member.controller.MentorVerifyController;
import com.unibridge.app.mypage.matching.controller.MentorMatchingController;
import com.unibridge.app.mypage.mentoring.controller.MentoringFrontController;

public class MentorFrontController implements Execute {
	Result outResult = new Result();
	
	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 멘토 컨트롤러
		String requestURI = request.getRequestURI();
		String target = extractTargetPath(requestURI);
		System.out.println(target + "확인");
		System.out.println("===MentorFrontController===");
		
		switch (target) {
		case  "myPage.my": // 마이페이지
			System.out.println("[Log] 결과: MentorMangeController 실행 시도...");
			this.outResult = new MentorMangeController().execute(request, response);
			System.out.println("[Log] 결과: MentorMangeController 실행 완료!");
			break;
		case "verify.my": // 인증 로직 처리
			System.out.println("[Log] 결과: MentorVerifyController 실행 시도...");
		    this.outResult = new MentorVerifyController().execute(request, response);
		    System.out.println("[Log] 결과: MentorVerifyController 실행 완료!");
		    break;
		case "updateOk.my":
            // [추가] 실제 DB 데이터 수정 처리
            System.out.println("[Log] 결과: MentorUpdateOkController 실행...");
            outResult = new MentorUpdateOkController().execute(request, response);
            System.out.println("[Log] 결과: MentorUpdateOkController 실행완료!");
            break;
		case "finishUpdate.my": // 수정 완료 단순 이동 처리
            System.out.println("[Log] 결과: 수정 완료 후 마이페이지 메인으로 리다이렉트");
            this.outResult = new Result();
            // JSP가 아닌 '컨트롤러'를 호출해서 데이터를 새로고침함
            this.outResult.setPath(request.getContextPath() + "/auth/mentor/myPage.my");
            this.outResult.setRedirect(true); 
            break;
		case "survey.my": // 설문조사
			System.out.println("[Log] 결과: MentorSurveyController 실행 시도...");
		    this.outResult = new MentorSurveyController().execute(request, response);
		    System.out.println("[Log] 결과: MentorSurveyController 실행 완료!");
		    break;
		case "delete.my": //회원탈퇴
			System.out.println("[Log] 결과: MentorDeleteController 실행 시도...");
			this.outResult = new MentorDeleteController().execute(request, response);
			System.out.println("[Log] 결과: MentorDeleteController 실행 완료!");
			break;
		case "matching.my": // 매칭정보
			System.out.println("[Log] 결과: MatchingController 실행 시도...");
			this.outResult = new MentorMatchingController().execute(request, response);
			System.out.println("[Log] 결과: MatchingController 실행 완료!");
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
