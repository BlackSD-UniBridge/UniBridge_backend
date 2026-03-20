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
import com.oreilly.servlet.multipart.MultipartParser;
import com.oreilly.servlet.multipart.ParamPart;
import com.oreilly.servlet.multipart.Part;

public class MentorBoardUpdateOkController implements Execute {

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		MentorBoardDAO mentorBoardDAO = new MentorBoardDAO();
		MentorBoardDTO mentorBoardDTO = new MentorBoardDTO();
		Result result = new Result();

		final int FILE_SIZE = 1024 * 1024 * 5;

		MultipartParser parser = new MultipartParser(request, FILE_SIZE);
		parser.setEncoding("utf-8");
		System.out.println("MultipartParser 초기화 완료");

		Part part;
		while ((part = parser.readNextPart()) != null) {
			System.out.println("Part : " + part.getClass().getSimpleName());

			if (part.isParam()) {
				ParamPart paramPart = (ParamPart) part;
				String paramName = paramPart.getName();
				String paramValue = paramPart.getStringValue();

				System.out.println("파라미터 : " + paramName + " = " + paramValue);

				if ("MentorBoardNumber".equals(paramName)) {
					mentorBoardDTO.setMentorBoardNumber(Integer.parseInt(paramValue));
				} else if ("MentorBoardTitle".equals(paramName)) {
					mentorBoardDTO.setBoardTitle(paramValue);
				} else if ("MentorBoardContent".equals(paramName)) {
					mentorBoardDTO.setBoardContent(paramValue);
				}
			}
		}

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
