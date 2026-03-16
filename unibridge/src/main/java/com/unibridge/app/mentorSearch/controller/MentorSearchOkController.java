package com.unibridge.app.mentorSearch.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.unibridge.app.mentorSearch.dao.MentorSearchDAO;
import com.unibridge.app.mentorSearch.dto.MentorSearchDTO;

public class MentorSearchOkController {
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MentorSearchDAO dao = new MentorSearchDAO();
        
        // 1. DB에서 조인된 멘토 리스트 가져오기
        List<MentorSearchDTO> mentorList = dao.selectAllMentors();
        
        // 2. JSP에서 사용하기 위해 request 객체에 저장
        request.setAttribute("mentorList", mentorList);
        
        // 3. 데이터를 가지고 mentorSearch.jsp로 이동
        request.getRequestDispatcher("/app/user/mentorSearch/mentorSearch.jsp").forward(request, response);
    }

}
