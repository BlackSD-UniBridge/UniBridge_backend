package com.unibridge.app.mentorSearch.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.unibridge.app.Execute;
import com.unibridge.app.Result;
import com.unibridge.app.mentorSearch.dao.MentorSearchDAO;
import com.unibridge.app.mentorSearch.dto.MentorSearchDTO;

public class MentorSearchOkController implements Execute {
	
	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("멘토 검색 처리 시작");
		
		// 1. 결과 객체 생성
		Result result = new Result();
		
		// 2. DAO 및 서비스 로직 수행
		MentorSearchDAO dao = new MentorSearchDAO();
		List<MentorSearchDTO> mentorList = dao.selectAllMentors();

		// 3. 데이터 검증 (데이터가 없을 경우의 처리 - 선택 사항)
		if (mentorList == null || mentorList.isEmpty()) {
			System.out.println(">>> [LOG] 검색된 멘토 데이터가 없습니다.");
			// 필요하다면 리다이렉트 처리나 에러 페이지 설정 가능
		}

		// 4. 결과 데이터를 request 영역에 저장
		request.setAttribute("mentorList", mentorList);
		System.out.println(">>> [LOG] Controller: request에 mentorList 세팅 완료");

		// 5. 이동 경로 및 방식 설정
		result.setPath("/app/user/mentorSearch/mentorSearch.jsp");
		result.setRedirect(false);

		// 6. Result 객체 반환 (FrontController에서 나머지 처리)
		return result;
	}

}
