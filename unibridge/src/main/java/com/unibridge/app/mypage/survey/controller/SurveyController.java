package com.unibridge.app.mypage.survey.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.unibridge.app.Execute;
import com.unibridge.app.Result;
import com.unibridge.app.mypage.surveyMentor.controller.SurveyMentorController;
import com.unibridge.app.mypage.surveyMentee.controller.SurveyMenteeController;

public class SurveyController implements Execute {

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

    // 설문 페이지 이동
    private void doGet(HttpServletRequest request, HttpServletResponse response) {

    	System.out.println("[SurveyController] GET - 설문 페이지 이동");

        HttpSession session = request.getSession();
        String memberType = (String) session.getAttribute("memberType");

        String path = "/app/user/undetermined/myPage/userSurvey/userSurvey.jsp"; // 기본값

        if ("MENTOR".equals(memberType)) {
            path = "/app/user/mentor/myPage/userSurvey/userSurvey.jsp";
        } else if ("MENTEE".equals(memberType)) {
            path = "/app/user/mentee/myPage/userSurvey/userSurvey.jsp";
        } else if ("NODECIDED".equals(memberType)) {
            path = "/app/user/undetermined/myPage/userSurvey/userSurvey.jsp";
        }

        System.out.println("현재 memberType: " + memberType);
        System.out.println("이동 경로: " + path);

        outResult.setPath(path);
        outResult.setRedirect(false);
    }

    // 설문 처리
    private void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("[SurveyController] POST - 설문 처리");

        String role = request.getParameter("role");

        System.out.println("선택된 role: " + role);

        if ("mentor".equals(role)) {
            outResult = new SurveyMentorController().execute(request, response);
        } 
        else if ("mentee".equals(role)) {
            outResult = new SurveyMenteeController().execute(request, response);
        } 
        else {
            System.out.println("[ERROR] role 값 이상");

            outResult.setPath(request.getContextPath() + "/index.main");
            outResult.setRedirect(true);
        }
    }
}