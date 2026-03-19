package com.example.app.board.controller;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.app.Execute;
import com.example.app.Result;
import com.example.app.board.dao.BoardDAO;
import com.example.app.board.dto.BoardDTO;
import com.example.app.file.dao.FileDAO;
import com.example.app.file.dto.FileDTO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class BoardWriteOkController implements Execute {

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		BoardDTO boardDTO = new BoardDTO();
		BoardDAO boardDAO = new BoardDAO();
		FileDAO fileDAO = new FileDAO();
		Result result = new Result();
		
		//로그인 한 회원 정보 가져오기
		Integer memberNumber = (Integer)request.getSession().getAttribute("memberNumber");
		
		System.out.println("현재 로그인한 회원 번호 : " + memberNumber);
		if(memberNumber == null) {
			System.out.println("오류 : 로그인 된 사용자가 없습니다");
			response.sendRedirect("login.jsp");
			return null;
		}
		
		//파일 업로드 환경 설정
		final String UPLOAD_PATH = request.getSession().getServletContext().getRealPath("/") + "upload/";
		final int FILE_SIZE = 1024 * 1024 * 5; //5MB
		System.out.println("파일 업로드 경로 : " + UPLOAD_PATH);
		
		File uploadDir = new File(UPLOAD_PATH);
		if(!uploadDir.exists()) {
			uploadDir.mkdirs();
		}
		
		//MultipartRequest를 이용한 데이터 파싱
		MultipartRequest multipartRequest = new MultipartRequest(request, UPLOAD_PATH, FILE_SIZE, "utf-8", 
				new DefaultFileRenamePolicy()); 
		
		//게시글 정보 설정
		boardDTO.setBoardTitle(multipartRequest.getParameter("boardTitle"));
		boardDTO.setBoardContent(multipartRequest.getParameter("boardContent"));
		boardDTO.setMemberNumber(memberNumber);
		System.out.println("게시글 추가 - BoradDTO : " + boardDTO);
				
		//게시글 추가
		int boardNumber = boardDAO.insertBoard(boardDTO);
		System.out.println("생성된 게시글 번호 : " + boardNumber);
		
		//파일 업로드 처리
		//Enumeration : java.util 패키지에 포함된 인터페이스, Itrator와 비슷한 역할을 함
		Enumeration<String> fileNames = multipartRequest.getFileNames();
		while(fileNames.hasMoreElements()) {
			FileDTO fileDTO = new FileDTO();
			String name = fileNames.nextElement();
			String fileSystemName = multipartRequest.getFilesystemName(name);
			String fileOriginalName = multipartRequest.getOriginalFileName(name);
			
			if(fileSystemName == null) {
				continue;
			}
			
			fileDTO.setFileSystemName(fileSystemName);
			fileDTO.setFileOriginalName(fileOriginalName);
			fileDTO.setBoardNumber(boardNumber);
			
			System.out.println("업로드 된 파일 정보 : " + fileDTO);
			fileDAO.insert(fileDTO);
		}
		result.setPath("/board/boardListOk.bo");
		result.setRedirect(true);
		
		return result;
	}
	

}












