package com.unibridge.app.mypage.delete.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.unibridge.app.Execute;
import com.unibridge.app.Result;
import com.unibridge.app.mypage.delete.dao.MemberDeleteDAO;
import com.unibridge.app.mypage.delete.dto.MemberDeleteDTO;

public class MentorDeleteController implements Execute {
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
		outResult.setPath(request.getContextPath() + "/app/user/mentor/myPage/userDelete/userDelete.jsp");
	}
	
	private void doPost(HttpServletRequest request, HttpServletResponse response) {
		MemberDeleteDAO deleteDAO = new MemberDeleteDAO();
		
		String userId = request.getParameter("userId");
		String userPw = request.getParameter("userPw");
		MemberDeleteDTO deleteDTO = new MemberDeleteDTO(userId, userPw);
		
		int result = deleteDAO.deleteUser(deleteDTO);
		if (result > 0) {
			/* main 창으로 이동해야하나 아직 미개발 */
			outResult.setRedirect(true);
			outResult.setPath(request.getContextPath() + "/app/user/mentor/myPage/userDelete/userDelete.jsp");
			
			/* 회원 탈퇴를 하였으니, 로그인 세션을 만료시켜야함. */
			HttpSession session = request.getSession();
			session.invalidate();
		} else {
			request.setAttribute("loginError", "아이디 정보가 맞지 않습니다. 다시 확인해주세요.");
			outResult.setRedirect(false);
			outResult.setPath("/app/user/mentor/myPage/userDelete/userDelete.jsp");
		}
	}
}
