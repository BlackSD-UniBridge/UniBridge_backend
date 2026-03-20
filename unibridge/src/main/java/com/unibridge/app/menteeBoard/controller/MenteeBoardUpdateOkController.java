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
import com.oreilly.servlet.multipart.MultipartParser;
import com.oreilly.servlet.multipart.ParamPart;
import com.oreilly.servlet.multipart.Part;

public class MenteeBoardUpdateOkController implements Execute {

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		MenteeBoardDAO MenteeBoardDAO = new MenteeBoardDAO();
		MenteeBoardDTO MenteeBoardDTO = new MenteeBoardDTO();
		Result result = new Result();
		
		final int FILE_SIZE = 1024 * 1024 * 5;
		// 1024 byte = 1KB
		// 1024 KB = 1MB * 5 = 5MB
		 
		// MultipartParser 실행
		MultipartParser parser = new MultipartParser(request, FILE_SIZE);
		parser.setEncoding("UTF-8");
		System.out.println("MultipartParser 초기화 완료");
 
		// 파일, 텍스트 데이터 처리
		Part part; // multipart 요청안에 들어있는 각 조각을 의미(menteeBoardNumber, menteeBoardTitle, menteeBoardContent)
		while ((part = parser.readNextPart()) != null) {
			System.out.println("Part : " + part.getClass().getSimpleName());
 
			if (part.isParam()) { // 현재 part가 일반 텍스트 데이터인지 확인
				// 텍스트 파라미터 처리
				ParamPart paramPart = (ParamPart) part; // part는 부모타입 Part로 선언, 텍스트전용메소드는 ParamPart에 있기 때문에 다운캐스팅
				String paramName = paramPart.getName(); // input의 name 속성값
				String paramValue = paramPart.getStringValue(); // 사용자가 실제로 입력한 값
 
				System.out.println("파라미터 : " + paramName + " = " + paramValue);
 
				if ("MenteeBoardNumber".equals(paramName)) {
					MenteeBoardDTO.setMenteeBoardNumber(Integer.parseInt(paramValue)); // dto에 게시글 번호 저장
				} else if ("MenteeBoardTitle".equals(paramName)) {
					MenteeBoardDTO.setBoardTitle(paramValue); // dto에 title 제목 저장
				} else if ("MenteeBoardContent".equals(paramName)) {
					MenteeBoardDTO.setBoardContent(paramValue); // dto에 내용 저장
				}
			}
		}
 
		// 게시글 업데이트 실행
		MemberDTO loginUser = (MemberDTO) request.getSession().getAttribute("loginUser");
		if (loginUser == null) {
		    response.sendRedirect(request.getContextPath() + "/signin.jsp");
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
