package com.unibridge.app.mypage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.unibridge.app.Execute;
import com.unibridge.app.Result;
import com.unibridge.app.member.controller.UndecidedDeleteController;
import com.unibridge.app.member.controller.UndecidedMangeController;
import com.unibridge.app.member.controller.UndecidedUpdateOkController;
import com.unibridge.app.member.controller.UndecidedVerifyController;
import com.unibridge.app.member.controller.UndecidedVerifySubmitController;
import com.unibridge.app.mypage.survey.controller.UndecidedSurveyController;

public class UndecidedFrontController implements Execute{
	Result outResult = new Result();

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 미정 컨트롤러
		String requestURI = request.getRequestURI();
		String target = extractTargetPath(requestURI);
		
		System.out.println("===UndecidedFrontController===");
		
		switch (target) {
		case  "myPage.my": // 마이페이지
			System.out.println("[Log] 결과: UndecidedMangeController 실행 시도...");
			this.outResult = new UndecidedMangeController().execute(request, response);
			System.out.println("[Log] 결과: UndecidedMangeController 실행 완료!");
			break;
			
		case "verify.my": // 1. 인증 페이지로 '단순 이동'
	        System.out.println("[Log] 인증 페이지 이동 처리");
	        outResult = new Result();
	        outResult.setPath("/app/user/undetermined/myPage/userManage/userModifyCheck.jsp");
	        outResult.setRedirect(false); // forward 방식
	        break;
	        
		case "verifyAction.my": // 인증 로직 처리
			System.out.println("[Log] 결과: UndecidedVerifyController 실행 시도...");
		    this.outResult = new UndecidedVerifyController().execute(request, response);
		    System.out.println("[Log] 결과: UndecidedVerifyController 실행 완료!");
		    break;
		    
		case "verifySubmit.my": // 3. 최종 비밀번호 및 인증 상태 체크 확인
	        System.out.println("[Log] 최종 수정 제출 검증 실행");
	        outResult = new UndecidedVerifySubmitController().execute(request, response);
	        break;
	        
		case "updateOk.my":
            // [추가] 실제 DB 데이터 수정 처리
            System.out.println("[Log] 결과: UndecidedUpdateOkController 실행...");
            outResult = new UndecidedUpdateOkController().execute(request, response);
            System.out.println("[Log] 결과: UndecidedUpdateOkController 실행완료!");
            break;
            
		case "finishUpdate.my": // 수정 완료 단순 이동 처리
            System.out.println("[Log] 결과: 수정 완료 후 마이페이지 메인으로 리다이렉트");
            this.outResult = new Result();
            // JSP가 아닌 '컨트롤러'를 호출해서 데이터를 새로고침함
            this.outResult.setPath(request.getContextPath() + "/auth/undecided/myPage.my");
            this.outResult.setRedirect(true); 
            break;
            
		case "survey.my": // 설문조사
			System.out.println("[Log] 결과: UndecidedSurveyController 실행 시도...");
		    this.outResult = new UndecidedSurveyController().execute(request, response);
		    System.out.println("[Log] 결과: UndecidedSurveyController 실행 완료!");
		    break;
		    
		case "delete.my": //회원탈퇴
			System.out.println("[Log] 결과: UndecidedDeleteController 실행 시도...");
			this.outResult = new UndecidedDeleteController().execute(request, response);
			System.out.println("[Log] 결과: UndecidedDeleteController 실행 완료!");
			break;
			
		default:
			System.out.println("[Warn] 매칭되는 target이 없음: " + target);
			break;
		}
		return outResult;
	}
	
	private String extractTargetPath(String requestUri) {
		if (requestUri == null) return "";
	    
	    // 마지막 슬래시(/) 다음의 문자열(예: myPage.my)만 추출
	    return requestUri.substring(requestUri.lastIndexOf("/") + 1);
	}

}
