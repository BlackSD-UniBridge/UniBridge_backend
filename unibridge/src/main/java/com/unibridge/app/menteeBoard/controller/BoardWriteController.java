package com.example.app.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.app.Execute;
import com.example.app.Result;
import com.example.app.member.dao.MemberDAO;

public class BoardWriteController implements Execute {

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("게시글 작성 페이지 컨트롤러 이동 완료");
		MemberDAO memberDAO = new MemberDAO();
		Result result = new Result();
		HttpSession session = request.getSession();
		Integer memberNumber = (Integer)session.getAttribute("memberNumber");
		String path = null;		//path = "/app/board/boardWrite.jsp"; 이걸로 대신 사용
								//request.setAttribute("memberId", memberDAO.getMemberId(memberNumber));
		
		if(memberNumber == null) {		//나는 안쓰는 if문
			path = "/app/member/login.jsp";
		}else {
			path = "/app/board/boardWrite.jsp";
			request.setAttribute("memberId", memberDAO.getMemberId(memberNumber));
		}
		
		result.setPath(path);
		result.setRedirect(false);
		
		return result;
	}
	
}









