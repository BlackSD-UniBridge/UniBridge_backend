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

public class MentorBoardUpdateOkController implements Execute {

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		MentorBoardDAO mentorBoardDAO = new MentorBoardDAO();
		MentorBoardDTO mentorBoardDTO = new MentorBoardDTO();
		Result result = new Result();
		
		mentorBoardDTO.setMentorBoardNumber(Integer.parseInt(request.getParameter("MentorBoardNumber")));
        mentorBoardDTO.setBoardTitle(request.getParameter("MentorBoardTitle"));
        mentorBoardDTO.setBoardContent(request.getParameter("MentorBoardContent"));

		MemberDTO loginUser = (MemberDTO) request.getSession().getAttribute("loginUser");
		if (loginUser == null) {
			response.sendRedirect(request.getContextPath() + "/signin.mem");
			return null;
		}
		
		mentorBoardDTO.setMemberNumber(loginUser.getMemberNumber());
		mentorBoardDAO.updateBoard(mentorBoardDTO);
		System.out.println("게시글 수정 완료");

		result.setPath(request.getContextPath() + "/mentor/mentorBoard/MentorBoardList.mob");
		result.setRedirect(true);

		return result;
	}
}
