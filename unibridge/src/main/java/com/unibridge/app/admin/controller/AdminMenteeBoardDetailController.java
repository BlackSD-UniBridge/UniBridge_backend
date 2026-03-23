package com.unibridge.app.admin.controller;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.unibridge.app.Execute;
import com.unibridge.app.Result;
import com.unibridge.app.admin.dao.AdMenteeBoardDAO;
import com.unibridge.app.admin.dto.AdMenteeBoardCommentDTO;
import com.unibridge.app.admin.dto.AdMenteeBoardDTO;
import com.unibridge.app.admin.dto.AdMenteeBoardListDTO;
import com.unibridge.app.file.dao.FileDAO;
import com.unibridge.app.file.dto.FileDTO;

public class AdminMenteeBoardDetailController implements Execute {

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("게시글 상세 페이지 이동 완료");
		Result result = new Result();
		
		//boardNumber가 빈 문자열이거나 null인 경우
		String boardNumberStr = request.getParameter("boardNumber");
		if(boardNumberStr == null || boardNumberStr.trim().isEmpty()) {
			System.out.println("boardNumber 값이 없습니다");
			result.setPath("/app/admin/adminBoard/menteeBoard/menteeBoardList.jsp"); //게시글 목록 페이지로 리다이렉트
			result.setRedirect(true);
			return result;
		}
		
		int boardNumber = Integer.parseInt(boardNumberStr);
		
		AdMenteeBoardDAO boardDAO = new AdMenteeBoardDAO();
		FileDAO fileDAO = new FileDAO();
		
		//DB에서 게시글 가져오기
		AdMenteeBoardDTO boardDTO = boardDAO.selectPage(boardNumber);
		
		System.out.println("boardTitle : " + boardDTO.getBoardTitle());
		System.out.println("boardContent : " + boardDTO.getBoardContent());
		System.out.println("WriteNumber : " + boardDTO.getWriteNumber());
		System.out.println("boardTitle : " + boardDTO.getBoardTitle());
		
		//게시글이 존재하지 않을 경우
		if(boardDTO == null) {
			System.out.println("존재하지 않는 게시물입니다." + boardNumber);
			result.setPath("/app/board/boardList.jsp");
			result.setRedirect(true);
			return result;
		}
		
		// 해당 게시글으 ㅣ댓글 리스트 가져오기
		List<AdMenteeBoardCommentDTO> commentList = boardDAO.selectComments(boardNumber);
		
		//로그인 한 사용자 번호 가져오기
		Integer loginMemberNumber = (Integer) request.getSession().getAttribute("adminNumber");
		System.out.println("로그인 한 멤버 번호 : " + loginMemberNumber);
		
		request.setAttribute("sessionNumber", request.getSession().getAttribute("adminNumber"));
		
		//현재 게시글 작성자 번호 가져오기
		int writeNumber = boardDTO.getWriteNumber();
		System.out.println("현재 게시글 작성자 번호 : " + writeNumber);
		
		
		request.setAttribute("board", boardDTO);
		request.setAttribute("commentList", commentList);
		request.setAttribute("loginMemberNumber",loginMemberNumber);
		result.setPath("/app/admin/adminBoard/menteeBoard/menteeBoardDetail.jsp");
		result.setRedirect(false);
		
		return result;
	}

}
