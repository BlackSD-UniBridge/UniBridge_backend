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

public class SigninController implements Execute {
	MemberDAO memberDAO = new MemberDAO();
	Result outResult = new Result();
	
	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String method = request.getMethod().toUpperCase();
		switch (method) {
		case "GET":
			this.doGet(request, response);
			break;
		case "POST":
			this.doPost(request, response);
			break;
		default:
			break;
		}
		return outResult;
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		outResult.setRedirect(true);
		outResult.setPath(request.getContextPath() + "/app/user/signin/signin.jsp");
	}
	
	private void doPost(HttpServletRequest request, HttpServletResponse response) {
		String memberId = (String)request.getParameter("userId");
		String memberPw = (String)request.getParameter("userPw");
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setMemberId(memberId);
		memberDTO.setMemberPw(memberPw);
		
		MemberDTO dtoResult = memberDAO.memberLogin(memberDTO);
		if (dtoResult != null) {
			/* 로그인 성공 */
			request.setAttribute("loginError", "로그인 성공");
			outResult.setRedirect(true);
			outResult.setPath(request.getContextPath() + "/index.main");
			
			
			HttpSession oldSession = request.getSession(false);
			if (oldSession != null) {
				oldSession.invalidate();
			}
			
			HttpSession newSession = request.getSession(true);
			newSession.setAttribute("loginUser", dtoResult);
			newSession.setMaxInactiveInterval(30 * 60); // 30분 지속
			
		} else {
			/* 로그인 실패 */
			request.setAttribute("loginError", "아이디 또는 비밀번호가 옳바르지 않습니다.");
			outResult.setRedirect(false);
			outResult.setPath("/app/user/signin/signin.jsp");
		}
	}
}
