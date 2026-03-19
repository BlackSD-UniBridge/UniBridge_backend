package com.example.app.board.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.app.Execute;
import com.example.app.Result;
import com.example.app.board.dao.BoardDAO;
import com.example.app.file.dao.FileDAO;
import com.example.app.file.dto.FileDTO;

public class BoardDeleteOkController implements Execute{

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("===BoardDeleteOkController===");
		
		BoardDAO boardDAO = new BoardDAO();
		FileDAO fileDAO = new FileDAO();
		Result result = new Result();
		
		//게시글 번호 받기
		int boardNumber = Integer.parseInt(request.getParameter("boardNumber"));
		
		//업로드 경로
		final String UPLOAD_PATH = request.getSession().getServletContext().getRealPath("/") + "upload/";
		
		List<FileDTO> files = fileDAO.select(boardNumber);
		System.out.println("파일 목록 확인 : " + files);
		
		//해당 게시글 파일 목록 조회
		for(FileDTO file : files) {
			File target = new File(UPLOAD_PATH, file.getFileSystemName());
			
			//실제 파일 삭제
			if(target.exists()) {
				target.delete();
			}
		}
		
		//파일 DB 삭제
		fileDAO.delete(boardNumber);
		
		//게시글 삭제
		boardDAO.deleteBoard(boardNumber);
		
		//이동
		result.setPath("/board/boardListOk.bo");
		result.setRedirect(true);
		
		return result;
	}
	

}











