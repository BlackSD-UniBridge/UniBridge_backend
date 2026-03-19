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

public class MentorMangeController implements Execute{

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
		HttpSession session = request.getSession();
	    Integer memberNumber = (Integer) session.getAttribute("memberNumber");
	    System.out.println("MentorMange컨트롤러 : " + memberNumber);
	    //임시 처리 테스트
//	    memberNumber = 13;
	    
	    // 로그인 체크
	    if (memberNumber == null) {
	        outResult.setPath("/app/user/signin/signin.jsp");
	        outResult.setRedirect(true); // redirect 처리
	        return;
	    }

	    // DAO로 회원정보 조회
	    MemberDAO memberDAO = new MemberDAO();
	    Map<String, Object> member = memberDAO.selectMember(memberNumber);
	    System.out.println("DB에서 가져온 데이터: " + member.toString()); // 여기서 Key 이름을 확인!

	    request.setAttribute("member", member);
	    
	    // outResult로 forward 설정
	    outResult.setPath("/app/user/mentor/myPage/myPage.jsp");
	    outResult.setRedirect(false); // forward 처리
		
	}

	private void doPost(HttpServletRequest request, HttpServletResponse response) {
		
		// 수정 페이지로 이동
	    outResult.setPath("/app/user/mentor/myPage/userManage/userModifyCheck.jsp");
	    outResult.setRedirect(false); // forward
		
	}

}
