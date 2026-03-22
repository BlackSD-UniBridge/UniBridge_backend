package com.unibridge.app.mentorBoard.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.unibridge.app.Execute;
import com.unibridge.app.Result;
import com.unibridge.app.member.dto.MemberDTO;

public class MentorBoardWriteController implements Execute {

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("게시글 작성 페이지 컨트롤러 이동 완료");
		Result result = new Result();

		MemberDTO loginUser = (MemberDTO) request.getSession().getAttribute("loginUser");
		if (loginUser == null) {
			result.setPath(request.getContextPath() + "/signin.mem");
			result.setRedirect(true);
			return result;
		}

		result.setPath("/app/user/mentor/mentorBoard/mentorBoardCreate.jsp");
		result.setRedirect(false);

		return result;
	}
}
