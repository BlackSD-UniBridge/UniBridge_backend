package com.unibridge.app.mentorSearch.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.unibridge.app.mentorSearch.dao.MentorSearchDAO;
import com.unibridge.app.mentorSearch.dto.MentorSearchDTO;

public class MentorSearchOkController {
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MentorSearchDAO dao = new MentorSearchDAO();
		List<MentorSearchDTO> mentorList = dao.selectAllMentors();

		// 데이터 유무를 리퀘스트에 담기 전 마지막 확인
		request.setAttribute("mentorList", mentorList);
		System.out.println(">>> [LOG] Controller: request에 mentorList 세팅 완료");

		request.getRequestDispatcher("/app/user/mentorSearch/mentorSearch.jsp").forward(request, response);
	}

}
