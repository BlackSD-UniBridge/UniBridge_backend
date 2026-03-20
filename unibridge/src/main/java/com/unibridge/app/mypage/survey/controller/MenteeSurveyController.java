package com.unibridge.app.mypage.survey.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.unibridge.app.Execute;
import com.unibridge.app.Result;
import com.unibridge.app.member.dto.MemberDTO;
import com.unibridge.app.mypage.surveyMentee.controller.SurveyMenteeController;
import com.unibridge.app.mypage.surveyMentee.dao.SurveyMenteeDAO;
import com.unibridge.app.mypage.surveyMentee.dto.SurveyMenteeDTO;
import com.unibridge.app.mypage.surveyMentor.controller.SurveyMentorController;

public class MenteeSurveyController implements Execute {

	private Result outResult = new Result();

    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String method = request.getMethod().toUpperCase();

        switch (method) {
            case "GET":
                doGet(request, response);
                break;
            case "POST":
                doPost(request, response);
                break;
        }

        return outResult;
    }

	private void doGet(HttpServletRequest request, HttpServletResponse response) {
		
		// 세션에서 로그인 정보 가져오기
		HttpSession session = request.getSession(false);
		MemberDTO loginUser = (MemberDTO) session.getAttribute("loginUser");

		if (loginUser != null) {
		    int memberNumber = loginUser.getMemberNumber();
		    System.out.println("조회할 회원 번호: " + memberNumber);

		    // DAO 생성 및 데이터 조회
		    SurveyMenteeDAO dao = new SurveyMenteeDAO();
		    // surveyMapper.xml의 selectMenteeSurvey 실행
		    SurveyMenteeDTO survey = dao.selectMenteeSurvey(memberNumber);
		    
		    // 사용자 유형 정보를 request에 추가 (예: "mentee" 또는 "mentor")
	        // DTO의 필드명이 다를 경우 해당 getter로 수정하세요.
	        request.setAttribute("userRole", loginUser.getMemberType());
		    
		    
		    // 콘솔 출력용 코드
		    System.out.println("------------------------------------------");
		    if (survey != null) {
		        System.out.println("[조회 결과] 설문 데이터가 존재합니다.");
		        System.out.println(survey.toString()); // 전체 데이터 출력
		        System.out.println("학교명: " + survey.getMenteeSchool()); // 개별 필드 출력
		    } else {
		        System.out.println("[조회 결과] 해당 회원(" + memberNumber + ")의 설문 데이터가 없습니다.");
		    }
		    System.out.println("------------------------------------------");

		    // 조회된 결과가 있다면 request에 저장
		    if (survey != null) {
		        // JSP의 <c:if test="${not empty survey}">에서 사용할 이름 "survey"와 일치해야 함
		        request.setAttribute("survey", survey);
		        System.out.println("설문 데이터 조회 성공: " + survey.getMenteeSchool());
		    } else {
		        System.out.println("해당 회원의 설문 데이터가 존재하지 않습니다.");
		    }
		}

		// 결과 화면(JSP)으로 포워딩
        outResult.setPath("/app/user/mentee/myPage/userSurvey/userSurvey.jsp");
        outResult.setRedirect(false);
	   
	}

	private void doPost(HttpServletRequest request, HttpServletResponse response) {
		
		System.out.println("[SurveyController] POST - 설문 처리");

        String role = request.getParameter("role");

        System.out.println("선택된 role: " + role);

        if ("mentor".equals(role)) {
            try {
				outResult = new SurveyMentorController().execute(request, response);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        } 
        else if ("mentee".equals(role)) {
            try {
				outResult = new SurveyMenteeController().execute(request, response);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        } 
        else {
            System.out.println("[ERROR] role 값 이상");

            outResult.setPath(request.getContextPath() + "/index.main");
            outResult.setRedirect(true);
        }
		
	}

}
