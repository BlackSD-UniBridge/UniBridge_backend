package com.unibridge.app.mentorBoard.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.unibridge.app.Execute;
import com.unibridge.app.Result;
import com.unibridge.app.member.dto.MemberDTO;
import com.unibridge.app.mentorBoard.dao.MentorBoardDAO;
import com.unibridge.app.mentorBoard.dto.MentorBoardDTO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class MentorBoardWriteOkController implements Execute {

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		MentorBoardDTO mentorBoardDTO = new MentorBoardDTO();
		MentorBoardDAO mentorBoardDAO = new MentorBoardDAO();
		Result result = new Result();

		MemberDTO loginUser = (MemberDTO) request.getSession().getAttribute("loginUser");
		if (loginUser == null) {
			System.out.println("오류 : 로그인 된 사용자가 없습니다");
			response.sendRedirect(request.getContextPath() + "/signin.mem");
			return null;
		}

		Integer memberNumber = loginUser.getMemberNumber();

		final String UPLOAD_PATH = request.getSession().getServletContext().getRealPath("/") + "upload/";
		final int FILE_SIZE = 1024 * 1024 * 5;
		System.out.println("파일 업로드 경로 : " + UPLOAD_PATH);

		File uploadDir = new File(UPLOAD_PATH);
		if (!uploadDir.exists()) {
			uploadDir.mkdirs();
		}

		MultipartRequest multipartRequest = new MultipartRequest(request, UPLOAD_PATH, FILE_SIZE, "utf-8",
				new DefaultFileRenamePolicy());

		mentorBoardDTO.setBoardTitle(multipartRequest.getParameter("MentorBoardTitle"));
		mentorBoardDTO.setBoardContent(multipartRequest.getParameter("MentorBoardContent"));
		mentorBoardDTO.setMemberNumber(memberNumber);
		System.out.println("게시글 추가 - BoardDTO : " + mentorBoardDTO);

		int mentorBoardNumber = mentorBoardDAO.insertBoard(mentorBoardDTO);
		System.out.println("생성된 게시글 번호 : " + mentorBoardNumber);

		result.setPath(request.getContextPath() + "/mentor/mentorBoard/mentorBoardList.mob");
		result.setRedirect(true);

		return result;
	}
}
