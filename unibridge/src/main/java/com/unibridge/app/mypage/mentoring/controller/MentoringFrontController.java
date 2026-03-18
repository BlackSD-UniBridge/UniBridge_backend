package com.unibridge.app.mypage.mentoring.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.unibridge.app.Execute;
import com.unibridge.app.Result;

public class MentoringFrontController implements Execute {

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String requestURI = request.getRequestURI();
		String target = extractTargetPath(requestURI);
		Result result = null;

		// [추가] 현재 어떤 요청이 MentoringFC까지 도달했는지 확인용 로그
		System.out.println("[Log] MentoringFrontController 도달 - target: " + target);

		try {
			switch (target) {
			case "mentoringCreate.my":
				System.out.println("[Log] 분기: 등록 페이지 이동");
				result = new Result();
				result.setPath("/app/user/mentor/myPage/userMentoing/mentoringCreate.jsp");
				result.setRedirect(false); // forward 방식
				break;
			case "mentoringWriteOk.my":
				System.out.println("[Log] 분기: 등록 실행");
				result = new MentoringWriteOkController().execute(request, response);
				break;
			case "mentoringView.my":
				System.out.println("[Log] 분기: 상세보기 실행");
				result = new MentoringViewController().execute(request, response);
				break;
			case "mentoringModify.my":
				System.out.println("[Log] 분기: 수정페이지 로드");
				result = new MentoringModifyController().execute(request, response);
				break;
			case "mentoringModifyOk.my":
				System.out.println("[Log] 분기: 수정 실행");
				result = new MentoringModifyOkController().execute(request, response);
				break;
			case "mentoringDeleteOk.my":
				System.out.println("[Log] 분기: 삭제 실행");
				result = new MentoringDeleteOkController().execute(request, response);
				break;

			default:
				// [추가] 매칭되는 케이스가 없을 때 로그
				System.out.println("[Warn] MentoringForntController 내 매칭되는 케이스 없음: " + target);
				break;
			}
		} catch (Exception e) {
			System.out.println("[Error] MentoringFrontController 예외 발생!");
			e.printStackTrace();
		}
		return result;
	}

	private String extractTargetPath(String requestUri) {
		if (requestUri == null || !requestUri.contains("/")) {
			return "";
		}
		String[] splitedPaths = requestUri.split("/");
		return splitedPaths[splitedPaths.length - 1];
	}
}