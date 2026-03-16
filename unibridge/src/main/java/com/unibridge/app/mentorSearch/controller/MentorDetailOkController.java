package com.unibridge.app.mentorSearch.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.unibridge.app.Execute;
import com.unibridge.app.Result;
import com.unibridge.app.mentorSearch.dao.MentorSearchDAO;
import com.unibridge.app.mentorSearch.dto.MentorSearchDTO;

public class MentorDetailOkController implements Execute{

	@Override
    public Result execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // 1. 파라미터 받기
        String memberNumberStr = request.getParameter("memberNumber");
        long memberNumber = Long.parseLong(memberNumberStr);
        
        MentorSearchDAO dao = new MentorSearchDAO();
        Result result = new Result();
        
        // 2. 해당 멘토 정보 한 건 조회
        MentorSearchDTO mentor = dao.selectMentorDetail(memberNumber);
        
        // 3. 데이터 바인딩
        request.setAttribute("mentor", mentor);
        
        // 4. 결과 설정 (경로 설정 및 포워드 방식 결정)
        result.setPath("/app/user/mentorSearch/mentorDetail.jsp");
        result.setRedirect(false); // 데이터를 담아서 가야 하므로 Forward 방식인 false 설정
        
        return result; // 수정된 포인트: Result 객체 리턴
    }
}

