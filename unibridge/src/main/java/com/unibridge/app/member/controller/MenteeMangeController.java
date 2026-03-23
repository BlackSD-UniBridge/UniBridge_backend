package com.unibridge.app.member.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.unibridge.app.Execute;
import com.unibridge.app.Result;
import com.unibridge.app.member.dao.MemberDAO;
import com.unibridge.app.member.dto.MemberDTO;

public class MenteeMangeController implements Execute {

	private Result outResult = new Result();

    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
		System.out.println("--------------MenteeMangeController-------------");

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
		
		System.out.println("멘티 -- 회원 정보 출력");
		
		HttpSession session = request.getSession();
		MemberDTO loginUser = (MemberDTO) session.getAttribute("loginUser");
	    System.out.println("MenteeMange컨트롤러 : " + loginUser.getMemberNumber());


	    // DAO로 회원정보 조회
	    MemberDAO memberDAO = new MemberDAO();
	    Map<String, Object> member = memberDAO.selectMemberDetail(loginUser.getMemberNumber());
	    System.out.println("마이페이지 출력 데이터: " + member.toString());
	    request.setAttribute("member", member);
	    
	    // outResult로 forward 설정
	    outResult.setPath("/app/user/mentee/myPage/myPage.jsp");
	    outResult.setRedirect(false); // forward 처리
	    
	    System.out.println("request.getContextPath() :" + request.getContextPath());
		
	}

	private void doPost(HttpServletRequest request, HttpServletResponse response) {
		
		// 수정 페이지로 이동
		outResult.setPath(request.getContextPath()+"/mvc/auth/mentee/verify.my"); // 컨텍스트 패스 없이 시도 (FrontController가 처리하도록)
	    outResult.setRedirect(true);
		System.out.println("인증 페이지 이동");
		
	}

}
