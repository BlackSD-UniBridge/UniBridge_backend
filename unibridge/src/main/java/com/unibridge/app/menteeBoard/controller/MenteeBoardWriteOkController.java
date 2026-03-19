package com.unibridge.app.menteeBoard.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.unibridge.app.Execute;
import com.unibridge.app.Result;
import com.unibridge.app.member.dto.MemberDTO;
import com.unibridge.app.menteeBoard.dao.MenteeBoardDAO;
import com.unibridge.app.menteeBoard.dto.MenteeBoardDTO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class MenteeBoardWriteOkController implements Execute {

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

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

		Integer memberNumber = loginUser.getMemberNumber();
		
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
		MenteeBoardDTO.setBoardTitle(multipartRequest.getParameter("MenteeBoardTitle"));
		MenteeBoardDTO.setBoardContent(multipartRequest.getParameter("MenteeBoardContent"));
		MenteeBoardDTO.setMemberNumber(memberNumber);
		System.out.println("게시글 추가 - BoradDTO : " + MenteeBoardDTO);
				
		//게시글 추가
		int MenteeBoardNumber = MenteeBoardDAO.insertBoard(MenteeBoardDTO);
		System.out.println("생성된 게시글 번호 : " + MenteeBoardNumber);
		
		result.setPath("/app/user/mentee/menteeBoard/MenteeBoardList.meb");
		result.setRedirect(true);
		
		return result;
	}
	

}












