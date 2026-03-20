package com.unibridge.app.mentorBoard.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.unibridge.app.Execute;
import com.unibridge.app.Result;
import com.unibridge.app.mentorBoard.dao.MentorBoardDAO;

public class MentorBoardDeleteOkController implements Execute {

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("===MentorBoardDeleteOkController===");

		MentorBoardDAO mentorBoardDAO = new MentorBoardDAO();
		Result result = new Result();

		int mentorBoardNumber = Integer.parseInt(request.getParameter("MentorBoardNumber"));

		// 댓글 먼저 삭제 (댓글 테이블이 있을 경우)
		mentorBoardDAO.deleteCommentByBoard(mentorBoardNumber);
		// 게시글 삭제
		mentorBoardDAO.deleteBoard(mentorBoardNumber);

		result.setPath(request.getContextPath() + "/mentor/mentorBoard/MentorBoardList.mob");
		result.setRedirect(true);

		return result;
	}
}
