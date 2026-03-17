package com.unibridge.app.mentorSearch.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.unibridge.app.Execute;
import com.unibridge.app.Result;
import com.unibridge.app.mentorSearch.dao.MentorSearchDAO;
import com.unibridge.app.mentorSearch.dto.MentorSearchDTO;

public class MentorDetailOkController implements Execute {

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    
	    // 세션에서 로그인 정보 확인 (예: memberNumber가 세션에 저장되어 있다고 가정)
		// 1. 세션에서 로그인된 회원 번호 확인
	    Object loginNum = request.getSession().getAttribute("memberNumber");
	    
	    Result result = new Result();

	    // 2. 로그인 여부 검사
//	    if (loginNum == null) {
//	        // 로그인이 안 되어 있다면 로그인 페이지로 강제 이동
//	        // (contextPath는 프로젝트 기본 경로를 자동으로 잡아줍니다)
//	        result.setPath(request.getContextPath() + "/member/login.me"); 
//	        result.setRedirect(true); // 주소창을 로그인 페이지로 바꾸기 위해 true 설정
//	        return result; // 이후 로직(DB 조회 등)을 실행하지 않고 바로 종료
//	    }

	    // --- 여기서부터는 로그인된 사용자만 실행됨 ---
	    
	    // 3. 파라미터 받기 및 상세 정보 조회
	    String memberNumberStr = request.getParameter("memberNumber");
	    long memberNumber = Long.parseLong(memberNumberStr);
	    
	    MentorSearchDAO dao = new MentorSearchDAO();
	    MentorSearchDTO mentor = dao.selectMentorDetail(memberNumber);
	    
	    // 4. 데이터 바인딩 및 페이지 이동 설정
	    request.setAttribute("mentor", mentor);
	    result.setPath("/app/user/mentorSearch/mentorDetail.jsp");
	    result.setRedirect(false); // 데이터를 가지고 JSP로 가야 하므로 forward(false)
	    
	    return result;
	}
}
