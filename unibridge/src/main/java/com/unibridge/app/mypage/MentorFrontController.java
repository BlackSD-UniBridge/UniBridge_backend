package com.unibridge.app.mypage;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.unibridge.app.Execute;
import com.unibridge.app.Result;
import com.unibridge.app.member.controller.MatchingController;
import com.unibridge.app.member.controller.MentorDeleteController;
import com.unibridge.app.member.controller.MentorMangeController;
import com.unibridge.app.member.controller.MentorSurveyController;
import com.unibridge.app.member.controller.MentorUpdateOkController;
import com.unibridge.app.member.controller.MentorVerifyActionController;
import com.unibridge.app.member.controller.MentorVerifySubmitController;
import com.unibridge.app.member.controller.MentorwDeleteOkController;
import com.unibridge.app.mypage.mentoring.controller.MentoringFrontController;

public class MentorFrontController implements Execute {

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Result outResult = new Result();

		// 멘토 컨트롤러
		String requestURI = request.getRequestURI();
		String target = extractTargetPath(requestURI);
		
		System.out.println(target + "확인");
		System.out.println("===MentorFrontController===");

		switch (target) {
		case  "myPage.my": // 마이페이지
			System.out.println("[Log] 결과: MenteeMangeController 실행 시도...");
			outResult = new MentorMangeController().execute(request, response);
			System.out.println("[Log] 결과: MenteeMangeController 실행 완료!");
			break;
			
		case "verify.my": // 1. 인증 페이지로 '단순 이동'
	        System.out.println("[Log] 인증 페이지 이동 처리");
	        outResult = new Result();
	        outResult.setPath("/app/user/mentor/myPage/userManage/userModifyCheck.jsp");
	        outResult.setRedirect(false); // forward 방식
	        break;

	    case "verifyAction.my": // 2. 인증번호 발송 및 AJAX 검증 (SOLAPI 연동)
	        System.out.println("[Log] 인증 AJAX 액션 실행");
	        // 기존 MenteeVerifyController의 AJAX 로직만 담당하는 컨트롤러로 연결
	        outResult = new MentorVerifyActionController().execute(request, response);
	        break;

	    case "verifySubmit.my": // 3. 최종 비밀번호 및 인증 상태 체크 확인
	        System.out.println("[Log] 최종 수정 제출 검증 실행");
	        outResult = new MentorVerifySubmitController().execute(request, response);
	        break;
	        
		case "updateOk.my": // 회원 정보 수정 페이지
            // [추가] 실제 DB 데이터 수정 처리
            System.out.println("[Log] 결과: MenteeUpdateOkController 실행...");
            outResult = new MentorUpdateOkController().execute(request, response);
            System.out.println("[Log] 결과: MenteeUpdateOkController 실행완료!");
            break;
            
		case "finishUpdate.my": // 수정 완료 단순 이동 처리
            System.out.println("[Log] 결과: 수정 완료 후 마이페이지 메인으로 리다이렉트");
            outResult = new Result();
            // JSP가 아닌 '컨트롤러'를 호출해서 데이터를 새로고침함
            outResult.setPath(request.getContextPath() + "/auth/mentor/myPage.my");
            outResult.setRedirect(true); 
            break;
            
		case "survey.my": // 설문조사
			System.out.println("[Log] 결과: MenteeSurveyController 실행 시도...");
		    outResult = new MentorSurveyController().execute(request, response);
		    System.out.println("[Log] 결과: MenteeSurveyController 실행 완료!");
		    break;
		    
		case "delete.my": // 회원탈퇴 페이지 이동 및 AJAX 인증 처리 전용
			System.out.println("[Log] 결과: MenteeDeleteController 실행 시도...");
			outResult = new MentorDeleteController().execute(request, response);
			System.out.println("[Log] 결과: MenteeDeleteController 실행 완료!");
			break;
			
		case "deleteOk.my": // 최종 탈퇴 버튼 클릭 시 (비밀번호 & 매칭상태 최종 확인)
		    System.out.println("[Log] 결과: MenteeDeleteOkController 실행 시도...");
		    outResult = new MentorwDeleteOkController().execute(request, response);
		    System.out.println("[Log] 결과: MenteeDeleteOkController 실행 완료!");
		    break;
		    
		case "matching.my": // 매칭정보
			System.out.println("[Log] 결과: MatchingController 실행 시도...");
			outResult = new MatchingController().execute(request, response);
			System.out.println("[Log] 결과: MatchingController 실행 완료!");
			break;
		case "mentoring.my": //멘토링
			System.out.println("[Log] 멘토링 진입 -> MentoringFrontController로 위임");
			outResult = new MentoringFrontController().execute(request, response);
			break;
			
		default:
			System.out.println("[Warn] 매칭되는 target이 없음: " + target);
			break;
		}
		return outResult;
	}

	private String extractTargetPath(String requestUri) {
		String[] splitedPaths = requestUri.split("/");
		String target = splitedPaths[splitedPaths.length - 1];
		return target;
	}

}
