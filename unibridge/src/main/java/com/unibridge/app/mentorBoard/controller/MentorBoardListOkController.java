package com.unibridge.app.mentorBoard.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.unibridge.app.Execute;
import com.unibridge.app.Result;
import com.unibridge.app.mentorBoard.dao.MentorBoardDAO;
import com.unibridge.app.mentorBoard.dto.MentorBoardListDTO;

public class MentorBoardListOkController implements Execute {

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("===MentorBoardListOkController 실행===");
		MentorBoardDAO mentorBoardDAO = new MentorBoardDAO();
		Result result = new Result();

		String temp = request.getParameter("page");
		int page = (temp == null) ? 1 : Integer.valueOf(temp);
		int rowCount = 10;
		int pageCount = 5;

		int startRow = (page - 1) * rowCount + 1;
		int endRow = startRow + rowCount - 1;

		Map<String, Integer> pageMap = new HashMap<>();
		pageMap.put("startRow", startRow);
		pageMap.put("endRow", endRow);

		List<MentorBoardListDTO> mentorBoardList = mentorBoardDAO.selectAll(pageMap);
		request.setAttribute("MentorBoardList", mentorBoardList);

		int total = mentorBoardDAO.getTotal();
		int realEndPage = (int) (Math.ceil(total / (double) rowCount));
		int endPage = (int) (Math.ceil(page / (double) pageCount) * pageCount);
		int startPage = endPage - (pageCount - 1);
		endPage = Math.min(endPage, realEndPage);

		boolean prev = startPage > 1;
		boolean next = endPage < realEndPage;

		request.setAttribute("page", page);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("prev", prev);
		request.setAttribute("next", next);

		System.out.println("======페이징 정보 확인======");
		System.out.println("pageMap : " + pageMap);
		System.out.println("MentorBoardList : " + mentorBoardList);
		System.out.println("startPage : " + startPage + ", endPage : " + endPage + ", prev : " + prev + ", next : " + next);
		System.out.println("=========================");

		result.setPath("/app/user/mentor/mentorBoard/mentorBoardList.jsp");
		result.setRedirect(false);

		return result;
	}
}
