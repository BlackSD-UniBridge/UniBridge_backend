package com.unibridge.app.noticeBoard.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.unibridge.app.Execute;
import com.unibridge.app.Result;
import com.unibridge.app.noticeBoard.dao.NoticeBoardDAO;
import com.unibridge.app.noticeBoard.dto.NoticeBoardListDTO;

public class NoticeBoardReadOkController implements Execute {

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("게시글 상세 페이지 이동 시작");
		
		Result result = new Result();
		
		//noticeBoardNumber가 빈 문자열이거나 null인 경우
		String noticeBoardNumberStr = request.getParameter("boardNumber");
		if(noticeBoardNumberStr == null || noticeBoardNumberStr.trim().isEmpty()) {
			System.out.println("noticeBoardNumber 값이 없습니다");
			result.setPath("/app/user/common/noticeBoardList.jsp");
			result.setRedirect(true);
			return result;
		}
		
		int noticeBoardNumber = Integer.parseInt(noticeBoardNumberStr);
		NoticeBoardDAO noticeBoardDAO = new NoticeBoardDAO();
		
		// DB에서 게시글 가져오기
		NoticeBoardListDTO noticeBoardListDTO = noticeBoardDAO.selectBoard(noticeBoardNumber);
		
//		FileDAO fileDAO = new FileDAO(); 아직 구현 불가
		
		// 게시글이 존재하지 않을 경우 목록으로 리다이렉트
		if (noticeBoardListDTO == null) {
			System.out.println("존재하지 않는 게시물 : " + noticeBoardNumber);
			result.setPath("/app/user/common/noticeBoardList.jsp");
			result.setRedirect(true);
			return result;
		}
				
//				//첨부파일 가져오기
//				List<FileDTO> files = fileDAO.select(noticeBoardNumber); //추후 파일 DAO확인 후 수정
//				System.out.println("==파일 확인==");
//				System.out.println(files);
//				System.out.println("===========");
				
				//첨부파일 붙이기
//				noticeBoardListDTO.setFiles(files);
		
		//조회시 무조건 조회수 증가
		Integer loginMemberNumber = (Integer) request.getSession().getAttribute("memberNumber");
		if (loginMemberNumber == null || loginMemberNumber != noticeBoardListDTO.getMemberNumber()) {
			noticeBoardDAO.updateReadCount(noticeBoardNumber);
		}
		
		request.setAttribute("noticeBoard", noticeBoardListDTO);
		result.setPath("/app/user/common/noticeBoardRead.jsp");
		result.setRedirect(false);
		
		return result;
	}
	
}


















