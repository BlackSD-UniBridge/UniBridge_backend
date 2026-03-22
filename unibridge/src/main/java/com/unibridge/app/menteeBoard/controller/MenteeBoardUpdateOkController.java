package com.unibridge.app.menteeBoard.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.unibridge.app.Execute;
import com.unibridge.app.Result;
import com.unibridge.app.member.dto.MemberDTO;
import com.unibridge.app.menteeBoard.dao.MenteeBoardDAO;
import com.unibridge.app.menteeBoard.dto.MenteeBoardDTO;

public class MenteeBoardUpdateOkController implements Execute {

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		MenteeBoardDAO MenteeBoardDAO = new MenteeBoardDAO();
		MenteeBoardDTO MenteeBoardDTO = new MenteeBoardDTO();
		Result result = new Result();
		
		MenteeBoardDTO.setMenteeBoardNumber(Integer.parseInt(request.getParameter("MenteeBoardNumber")));
		MenteeBoardDTO.setBoardTitle(request.getParameter("MenteeBoardTitle"));
		MenteeBoardDTO.setBoardContent(request.getParameter("MenteeBoardContent"));
		
		// 게시글 업데이트 실행
		MemberDTO loginUser = (MemberDTO) request.getSession().getAttribute("loginUser");
		if (loginUser == null) {
		    response.sendRedirect(request.getContextPath() + "/signin.mem");
		    return null;
		}
		
		MenteeBoardDTO.setMemberNumber(loginUser.getMemberNumber());
		MenteeBoardDAO.updateBoard(MenteeBoardDTO);
		System.out.println("게시글 수정 완료");
 
		// 수정 완료 후 페이지 이동
		result.setPath(request.getContextPath() + "/mentee/menteeBoard/MenteeBoardList.meb");
		result.setRedirect(true);
 
		return result;
	}
 
}
