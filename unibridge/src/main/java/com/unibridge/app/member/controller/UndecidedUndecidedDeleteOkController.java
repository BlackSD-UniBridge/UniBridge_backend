package com.unibridge.app.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.unibridge.app.Execute;
import com.unibridge.app.Result;
import com.unibridge.app.member.dao.MemberDAO;
import com.unibridge.app.member.dto.MemberDTO;

public class UndecidedUndecidedDeleteOkController implements Execute{

	 @Override
	    public Result execute(HttpServletRequest request, HttpServletResponse response) 
	            throws ServletException, IOException {
	        
	    	System.out.println("=======undeterminedDeleteOkController=======");
	    	
	        Result outResult = new Result();
	        HttpSession session = request.getSession();
	        MemberDTO loginUser = (MemberDTO) session.getAttribute("loginUser");

	        // 1. 휴대폰 인증 여부 세션 확인
	        Boolean isVerified = (Boolean) session.getAttribute("isDeleteVerified");
	        if (isVerified == null || !isVerified) {
	            request.setAttribute("authErrorMsg", "휴대폰 인증이 완료되지 않았습니다.");
	            return goBack(request, outResult);
	        }

	        // 2. 비밀번호 일치 확인
	        String inputPw = request.getParameter("userPw");
	        if (loginUser == null || !loginUser.getMemberPw().equals(inputPw)) {
	            request.setAttribute("loginErrorMsg", "비밀번호가 일치하지 않습니다.");
	            return goBack(request, outResult);
	        }

	        // 3. 매칭 진행 상태 확인 (핵심 로직)
	        MemberDAO memberDAO = new MemberDAO();
	        if (memberDAO.isMatchingActive(loginUser.getMemberNumber())) {
	            request.setAttribute("loginErrorMsg", "현재 매칭 중인 상태입니다. 매칭 취소 후 탈퇴해 주세요.");
	            return goBack(request, outResult);
	        }

	        // 4. 회원 상태 업데이트 (또는 삭제)
	        memberDAO.deleteMember(loginUser.getMemberNumber());
	        System.out.println("회원 탈퇴 완료!!");

	        // 5. 세션 종료 및 메인 이동
	        session.invalidate();
	        outResult.setRedirect(true);
	        outResult.setPath(request.getContextPath() + "/index.main");
	        
	        return outResult;
	    }

	    private Result goBack(HttpServletRequest request, Result result) {
	        result.setPath("/app/user/undetermined/myPage/userDelete/userDelete.jsp");
	        result.setRedirect(false);
	        return result;
	    }

}
