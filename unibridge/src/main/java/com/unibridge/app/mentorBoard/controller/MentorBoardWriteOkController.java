package com.unibridge.app.mentorBoard.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.unibridge.app.Execute;
import com.unibridge.app.Result;
import com.unibridge.app.member.dto.MemberDTO;
import com.unibridge.app.mentorBoard.dao.MentorBoardDAO;
import com.unibridge.app.mentorBoard.dto.MentorBoardDTO;

public class MentorBoardWriteOkController implements Execute {

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		
		MentorBoardDTO mentorBoardDTO = new MentorBoardDTO();
		MentorBoardDAO mentorBoardDAO = new MentorBoardDAO();
		Result result = new Result();

		MemberDTO loginUser = (MemberDTO) request.getSession().getAttribute("loginUser");
		if (loginUser == null) {
			System.out.println("오류 : 로그인 된 사용자가 없습니다");
			response.sendRedirect(request.getContextPath() + "/signin.mem");
			return null;
		}

		mentorBoardDTO.setBoardTitle(request.getParameter("MentorBoardTitle"));
		mentorBoardDTO.setBoardContent(request.getParameter("MentorBoardContent"));
		mentorBoardDTO.setMemberNumber(loginUser.getMemberNumber());
		System.out.println("게시글 추가 - BoardDTO : " + mentorBoardDTO);

		int mentorBoardNumber = mentorBoardDAO.insertBoard(mentorBoardDTO);
		System.out.println("생성된 게시글 번호 : " + mentorBoardNumber);

		result.setPath(request.getContextPath() + "/mentor/mentorBoard/MentorBoardList.mob");
		result.setRedirect(true);

		return result;
	}
}
