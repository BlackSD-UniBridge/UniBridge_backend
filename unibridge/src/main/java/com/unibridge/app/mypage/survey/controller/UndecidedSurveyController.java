package com.unibridge.app.mypage.survey.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.unibridge.app.Execute;
import com.unibridge.app.Result;
import com.unibridge.app.member.dto.MemberDTO;
import com.unibridge.app.mypage.survey.dao.SurveyDAO;
import com.unibridge.app.mypage.surveyMentee.controller.UndecidedSurveyMentee;
import com.unibridge.app.mypage.surveyMentee.dao.SurveyMenteeDAO;
import com.unibridge.app.mypage.surveyMentee.dto.SurveyMenteeDTO;
import com.unibridge.app.mypage.surveyMentor.controller.UndecidedSurveyMentor;
import com.unibridge.app.mypage.surveyMentor.dao.SurveyMentorDAO;
import com.unibridge.app.mypage.surveyMentor.dto.SurveyMentorDTO;

public class UndecidedSurveyController implements Execute {
    
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
        // 1. 세션에서 로그인 정보 가져오기 및 로그인 체크
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("loginUser") == null) {
            outResult.setPath(request.getContextPath() + "/mvc/auth/index.main");
            outResult.setRedirect(true);
            return;
        }
        
        MemberDTO loginUser = (MemberDTO) session.getAttribute("loginUser");
        int memberNumber = loginUser.getMemberNumber();

        // 2. 설문 작성 여부 확인 
        SurveyDAO surveyDao = new SurveyDAO();
        int surveyCount = surveyDao.checkSurveyExists(memberNumber); // 기존에 만드신 메서드 활용

        if (surveyCount == 0) {
            System.out.println("[이동] 설문 기록 없음 (Count: 0) -> 최초 설문 페이지");
            outResult.setPath("/app/user/undetermined/myPage/userSurvey/firstUserSurvey.jsp");
            outResult.setRedirect(false);
            return;
        }

        // 2. 작성한 적이 있다면 DAO를 통해 유형(MENTEE/MENTOR) 가져오기
//        SurveyDAO surveyDao = new SurveyDAO();
        String surveyType = surveyDao.getSurveyType(memberNumber);
        System.out.println("[DEBUG] 조회된 설문 유형: " + surveyType);

        if ("MENTEE".equals(surveyType)) {
            // 멘티 상세 데이터 조회 후 이동
            SurveyMenteeDAO menteeDao = new SurveyMenteeDAO();
            SurveyMenteeDTO menteeSurvey = menteeDao.selectMenteeSurvey(memberNumber);
            request.setAttribute("survey", menteeSurvey);
            
            if ("F".equals(menteeSurvey.getSurveyApproval()) && menteeSurvey.getSurveyRejReason() != null) {
                outResult.setPath("/app/user/undetermined/myPage/userSurvey/userRefusal.jsp");
            } else {
                outResult.setPath("/app/user/undetermined/myPage/userSurvey/menteeUserSurvey.jsp");
            }

        } else if ("MENTOR".equals(surveyType)) {
            // 멘토 상세 데이터 조회 후 이동
            SurveyMentorDAO mentorDao = new SurveyMentorDAO();
            SurveyMentorDTO mentorSurvey = mentorDao.selectMentorSurvey(memberNumber);
            request.setAttribute("survey", mentorSurvey);

            if ("F".equals(mentorSurvey.getSurveyApproval()) && mentorSurvey.getSurveyRejReason() != null) {
                outResult.setPath("/app/user/undetermined/myPage/userSurvey/userRefusal.jsp");
            } else {
                outResult.setPath("/app/user/undetermined/myPage/userSurvey/mentorUserSurvey.jsp");
            }
        }

        outResult.setRedirect(false);
    }

    private void doPost(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("[UndecidedSurveyController] POST - 진입");
        
        try {
            // 1. MultipartRequest를 생성해야 내부 파라미터(role 등)를 읽을 수 있습니다.
        	String UPLOAD_PATH = "C:/upload/survey/";
//            String UPLOAD_PATH = request.getServletContext().getRealPath("/") + "upload/";
            
            
            MultipartRequest multi = new MultipartRequest(
                request, UPLOAD_PATH, 100*1024*1024, "UTF-8", new DefaultFileRenamePolicy()
            );

            // 2. 이제 getParameter가 작동합니다.
            String role = multi.getParameter("role"); 
            System.out.println("[DEBUG] 사용자가 선택한 역할: " + role);

            // 3. 분기 처리
            if ("mentor".equals(role)) {
                this.outResult = new UndecidedSurveyMentor(multi).execute(request, response);
            } else {
                this.outResult = new UndecidedSurveyMentee(multi).execute(request, response);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}