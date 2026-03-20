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

public class UndecidedMangeController implements Execute{

	private Result outResult = new Result();
	
	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String method = request.getMethod().toUpperCase();
		System.out.println("미정 마이페이지로 들어옴");

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
		
		System.out.println("멘토/멘티 -- 회원 정보 출력");
		
		HttpSession session = request.getSession();
		MemberDTO loginUser = (MemberDTO) session.getAttribute("loginUser");
	    System.out.println("MenteeMange컨트롤러 : " + loginUser.getMemberNumber());


	    // DAO로 회원정보 조회
	    MemberDAO memberDAO = new MemberDAO();
	    Map<String, Object> member = memberDAO.selectMember(loginUser.getMemberNumber());
	    System.out.println("DB에서 가져온 데이터: " + member.toString()); // 여기서 Key 이름을 확인!

	    request.setAttribute("member", member);
	    
	    // outResult로 forward 설정
	    outResult.setPath("/app/user/undecied/myPage/myPage.jsp");
	    outResult.setRedirect(false); // forward 처리
	    
	    System.out.println("request.getContextPath() :" + request.getContextPath());
	    
	}

	//인증 장치 -> 수정 페이지 넘어가게 별도 설계
	private void doPost(HttpServletRequest request, HttpServletResponse response) {
		
		// 수정 페이지로 이동
		outResult.setPath(request.getContextPath()+"/auth/undecied/verify.my"); // 컨텍스트 패스 없이 시도 (FrontController가 처리하도록)
	    outResult.setRedirect(true);
		System.out.println("인증 페이지 이동");
	}

}
