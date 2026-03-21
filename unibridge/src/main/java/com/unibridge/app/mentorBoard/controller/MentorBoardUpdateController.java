package com.unibridge.app.mentorBoard.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.unibridge.app.Execute;
import com.unibridge.app.Result;
import com.unibridge.app.mentorBoard.dao.MentorBoardDAO;

public class MentorBoardUpdateController implements Execute {

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("===MentorBoardUpdateController 실행===");
		int mentorBoardNumber = Integer.valueOf(request.getParameter("MentorBoardNumber"));
		MentorBoardDAO mentorBoardDAO = new MentorBoardDAO();
		Result result = new Result();
		System.out.println("MentorBoardNumber 값 : " + mentorBoardNumber);
		request.setAttribute("MentorBoard", mentorBoardDAO.selectBoard(mentorBoardNumber));

		result.setPath("/app/user/mentor/mentorBoard/mentorBoardModify.jsp");
		result.setRedirect(false);

		return result;
	}
}
