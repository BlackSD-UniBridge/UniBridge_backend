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
import com.unibridge.app.mypage.surveyMentor.dao.SurveyMentorDAO;
import com.unibridge.app.mypage.surveyMentor.dto.SurveyMentorDTO;

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
		
		//멘티 회원 설문조사 이동
		HttpSession session = request.getSession();
		MemberDTO loginUser = (MemberDTO) session.getAttribute("loginUser");
		
		int memberNumber = loginUser.getMemberNumber();
		String memberType = loginUser.getMemberType();
		
	    System.out.println("설문조사 회원번호 : " + memberNumber +" 회원 유형 : " + memberType);
	    	
	    SurveyMenteeDAO menteeDAO = new SurveyMenteeDAO();
	    SurveyMentorDAO mentorDAO = new SurveyMentorDAO();

	    if ("MENTEE".equals(memberType)) {

	        SurveyMenteeDTO survey = menteeDAO.selectMenteeSurvey(memberNumber);

	        request.setAttribute("survey", survey);

	        outResult.setPath("/app/user/mentee/myPage/userSurvey/userSurvey.jsp");
	        outResult.setRedirect(false);

	    } else if ("MENTOR".equals(memberType)) {

	        SurveyMentorDTO survey = mentorDAO.selectMentorSurvey(memberNumber);

	        request.setAttribute("survey", survey);

	        outResult.setPath("/app/user/mentor/myPage/userSurvey/userSurvey.jsp");
	        outResult.setRedirect(false);

	    } else {

	        outResult.setPath("/app/user/undetermined/myPage/userSurvey/userSurvey.jsp");
	        outResult.setRedirect(false);
	    }
	   
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
