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

public class MenteeBoardWriteOkController implements Execute {

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		
		MenteeBoardDTO MenteeBoardDTO = new MenteeBoardDTO();
		MenteeBoardDAO MenteeBoardDAO = new MenteeBoardDAO();
		Result result = new Result();
		
		//로그인 한 회원 정보 가져오기
		MemberDTO loginUser = (MemberDTO) request.getSession().getAttribute("loginUser");

		if (loginUser == null) {
		    System.out.println("오류 : 로그인 된 사용자가 없습니다");
		    response.sendRedirect(request.getContextPath() + "/signin.mem");
		    return null;
		}

		//게시글 정보 설정
		MenteeBoardDTO.setBoardTitle(request.getParameter("MenteeBoardTitle"));
        MenteeBoardDTO.setBoardContent(request.getParameter("MenteeBoardContent"));
		MenteeBoardDTO.setMemberNumber(loginUser.getMemberNumber());
		System.out.println("게시글 추가 - BoradDTO : " + MenteeBoardDTO);
				
		//게시글 추가
		int MenteeBoardNumber = MenteeBoardDAO.insertBoard(MenteeBoardDTO);
		System.out.println("생성된 게시글 번호 : " + MenteeBoardNumber);
		
		result.setPath(request.getContextPath() + "/mentee/menteeBoard/MenteeBoardList.meb");
		result.setRedirect(true);

		return result;
	}
	

}












