package com.unibridge.app.mypage.entrypoint.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.unibridge.app.Execute;
import com.unibridge.app.Result;
import com.unibridge.app.member.dto.MemberDTO;
import com.unibridge.app.mypage.entrypoint.dao.MentorDAO;

public class MentorController implements Execute {
	MentorDAO memberDAO = new MentorDAO();
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
	
	private void doGet(HttpServletRequest request, HttpServletResponse response) {
		outResult.setRedirect(true);
		outResult.setPath(request.getContextPath() + "/app/user/mentor/myPage/myPage.jsp");
		

//		HttpSession session = request.getSession();
//		session.setAttribute("memberId", "user1");
//		session.setAttribute("memberName", "노진구");
//		session.setAttribute("memberNickname", "미닉1");
//		session.setAttribute("memberPhone", "010-1-01");
//		session.setAttribute("memberGender", "남성");
//		session.setAttribute("memberType", "미정");
//		session.setAttribute("memberState", "계정 활성화");
	}
	
	private void doPost(HttpServletRequest request, HttpServletResponse response) {
	}
}
