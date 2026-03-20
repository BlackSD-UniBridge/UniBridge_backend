package com.unibridge.app.menteeBoard.controller;

import java.io.IOException;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.unibridge.app.Execute;
import com.unibridge.app.Result;
import com.unibridge.app.member.dto.MemberDTO;
import com.unibridge.app.menteeBoard.dao.MenteeBoardDAO;
import com.unibridge.app.menteeBoard.dto.MenteeBoardListDTO;

public class MenteeBoardReadOkController implements Execute {

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("게시글 상세 페이지 이동 완료");
		
		Result result = new Result();
		
		//MenteeBoardNumber가 빈 문자열이거나 null인 경우
		String MenteeBoardNumberStr = request.getParameter("MenteeBoardNumber");
		if(MenteeBoardNumberStr == null || MenteeBoardNumberStr.trim().isEmpty()) {
			System.out.println("MenteeBoardNumber 값이 없습니다");
			result.setPath("/app/user/mentee/menteeBoard/MenteeBoardList.jsp"); //게시글 목록 페이지로 리다이렉트
			result.setRedirect(true);
			return result;
		}
		
		int MenteeBoardNumber = Integer.parseInt(MenteeBoardNumberStr);
		
		MenteeBoardDAO MenteeBoardDAO = new MenteeBoardDAO();
		
		//DB에서 게시글 가져오기
		MenteeBoardListDTO MenteeBoardListDTO = MenteeBoardDAO.selectBoard(MenteeBoardNumber);
		
		//게시글이 존재하지 않을 경우
		if(MenteeBoardListDTO == null) {
			System.out.println("존재하지 않는 게시물입니다." + MenteeBoardNumber);
			result.setPath(request.getContextPath() + "/app/user/mentee/menteeBoard/MenteeBoardList.meb");
			result.setRedirect(true);
			return result;
		}
		
		//로그인 한 사용자 번호 가져오기
		MemberDTO loginUser = (MemberDTO) request.getSession().getAttribute("loginUser");
		Integer loginMemberNumber = (loginUser != null) ? loginUser.getMemberNumber() : null;
		System.out.println("로그인 한 멤버 번호 : " + loginMemberNumber);
		
		//현재 게시글 작성자 번호 가져오기
		int MenteeBoardWriterNumber = MenteeBoardListDTO.getMemberNumber();
		System.out.println("현재 게시글 작성자 번호 : " + MenteeBoardWriterNumber);
		
		//로그인한 사용자가 작성자가 아닐 때만 조회 수 증가
		if(!Objects.equals(loginMemberNumber, MenteeBoardWriterNumber)) {
			MenteeBoardDAO.updateClick(MenteeBoardNumber);
		}
		
		request.setAttribute("MenteeBoard", MenteeBoardListDTO);
		result.setPath("/app/user/mentee/menteeBoard/MenteeBoardDetail.jsp");
		result.setRedirect(false);
		
		return result;
	}
	
}


















