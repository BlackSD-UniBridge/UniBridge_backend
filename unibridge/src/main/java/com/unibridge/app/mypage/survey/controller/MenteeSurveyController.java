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
		
		if (session == null || session.getAttribute("loginUser") == null) {
	        outResult.setPath(request.getContextPath() + "/mvc/auth/index.main");
	        outResult.setRedirect(true);
	        return;
	    }
		
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
		    System.out.println("회원의 타입 : "+ loginUser.getMemberType());
		    
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
		        
		        // 승인 상태가 'F'(거부)이고, 거부 사유가 있는 경우에만 이동
		        if ("F".equals(survey.getSurveyApproval()) && survey.getSurveyRejReason() != null) {
		            System.out.println("[이동] 거부 사유가 발견되어 거부 안내 페이지로 이동합니다.");
		            outResult.setPath("/app/user/mentee/myPage/userSurvey/userRefusal.jsp");
		            outResult.setRedirect(false); // 포워딩
		            return; // 중요: 리턴을 해줘야 아래의 기본 경로 세팅이 실행되지 않음
		        }
		        
		    } else {
		        System.out.println("해당 회원의 설문 데이터가 존재하지 않습니다.");
		    }
		}

		// 결과 화면(JSP)으로 포워딩
        outResult.setPath("/app/user/mentee/myPage/userSurvey/userSurvey.jsp");
        outResult.setRedirect(false);
	   
	}

	private void doPost(HttpServletRequest request, HttpServletResponse response) {
	    System.out.println("[MenteeSurveyController] POST - 진입");
	    
	    // multipart 데이터이므로 여기서 getParameter는 무조건 null입니다.
	    // 따라서 바로 전용 컨트롤러를 실행하여 그 안에서 처리하도록 합니다.
	    try {
	        // 현재 페이지의 유저 타입에 따라 기본적으로 멘티 컨트롤러를 실행하거나
	        // 로직을 단순화하여 멘티 컨트롤러 내에서 분기 처리를 하도록 유도합니다.
	        outResult = new SurveyMenteeController().execute(request, response);
	    } catch (Exception e) {
	        e.printStackTrace();
	        System.out.println("멘티 설문조사 에러 발생");
	    }
	}

}
