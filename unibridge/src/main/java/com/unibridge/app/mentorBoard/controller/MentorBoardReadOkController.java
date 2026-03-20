package com.unibridge.app.mentorBoard.controller;

import java.io.IOException;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.unibridge.app.Execute;
import com.unibridge.app.Result;
import com.unibridge.app.member.dto.MemberDTO;
import com.unibridge.app.mentorBoard.dao.MentorBoardDAO;
import com.unibridge.app.mentorBoard.dto.MentorBoardListDTO;

public class MentorBoardReadOkController implements Execute {

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("게시글 상세 페이지 이동 완료");

		Result result = new Result();

		String mentorBoardNumberStr = request.getParameter("MentorBoardNumber");
		if (mentorBoardNumberStr == null || mentorBoardNumberStr.trim().isEmpty()) {
			System.out.println("MentorBoardNumber 값이 없습니다");
			result.setPath(request.getContextPath() + "/mentor/mentorBoard/MentorBoardList.mob");
			result.setRedirect(true);
			return result;
		}

		int mentorBoardNumber = Integer.parseInt(mentorBoardNumberStr);
		MentorBoardDAO mentorBoardDAO = new MentorBoardDAO();
		MentorBoardListDTO mentorBoardListDTO = mentorBoardDAO.selectBoard(mentorBoardNumber);

		if (mentorBoardListDTO == null) {
			System.out.println("존재하지 않는 게시물입니다." + mentorBoardNumber);
			result.setPath(request.getContextPath() + "/mentor/mentorBoard/MentorBoardList.mob");
			result.setRedirect(true);
			return result;
		}

		MemberDTO loginUser = (MemberDTO) request.getSession().getAttribute("loginUser");
		Integer loginMemberNumber = (loginUser != null) ? loginUser.getMemberNumber() : null;
		System.out.println("로그인 한 멤버 번호 : " + loginMemberNumber);

		int mentorBoardWriterNumber = mentorBoardListDTO.getMemberNumber();
		System.out.println("현재 게시글 작성자 번호 : " + mentorBoardWriterNumber);

		if (!Objects.equals(loginMemberNumber, mentorBoardWriterNumber)) {
			mentorBoardDAO.updateClick(mentorBoardNumber);
		}

		request.setAttribute("MentorBoard", mentorBoardListDTO);
		result.setPath("/app/user/mentor/mentorBoard/MentorBoardDetail.jsp");
		result.setRedirect(false);

		return result;
	}
}
