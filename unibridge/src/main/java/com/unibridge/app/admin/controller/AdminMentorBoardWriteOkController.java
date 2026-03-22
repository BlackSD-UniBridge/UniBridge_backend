package com.unibridge.app.admin.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.unibridge.app.Execute;
import com.unibridge.app.Result;
import com.unibridge.app.admin.dao.AdMentorBoardDAO;
import com.unibridge.app.admin.dto.AdMentorBoardDTO;

public class AdminMentorBoardWriteOkController implements Execute {

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		AdMentorBoardDTO boardDTO = new AdMentorBoardDTO();
		AdMentorBoardDAO boardDAO = new AdMentorBoardDAO();
		Result result = new Result();
		
		//로그인 한 회원 정보 가져오기
		Integer adminNumber = (Integer)request.getSession().getAttribute("adminNumber");
		
		System.out.println("현재 로그인한 관리자 번호 : " + adminNumber);
		if(adminNumber == null) {
			System.out.println("오류 : 로그인 된 사용자가 없습니다");
			response.sendRedirect("login.admin");
			return null;
		}
		
		
		//게시글 정보 설정
		boardDTO.setBoardTitle(request.getParameter("boardTitle"));
		boardDTO.setBoardContent(request.getParameter("boardContent"));
		boardDTO.setWriteNumber(adminNumber);
		System.out.println("게시글 추가 - BoradDTO : " + boardDTO);
				
		//게시글 추가
		int boardNumber = boardDAO.insertBoard(boardDTO);
		System.out.println("생성된 게시글 번호 : " + boardNumber);
		
		
		result.setPath(request.getContextPath()+"/app/admin/adminBoard/mentorBoard/mentorBoardList.admin");
		result.setRedirect(true);
		
		return result;

	}

}
