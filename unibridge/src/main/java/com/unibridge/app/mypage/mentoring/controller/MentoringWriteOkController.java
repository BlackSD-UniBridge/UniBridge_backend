package com.unibridge.app.mypage.mentoring.controller;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.unibridge.app.Execute;
import com.unibridge.app.Result;
import com.unibridge.app.mypage.mentoring.dao.MentoringDAO;
import com.unibridge.app.mypage.mentoring.dto.MentoringDTO;

public class MentoringWriteOkController implements Execute {
	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("=========================================");
		System.out.println("[Log] MentoringWriteOkController 시작");
		
		MentoringDAO mentoringDAO = new MentoringDAO();
		MentoringDTO mentoringDTO = new MentoringDTO();
		Result result = new Result();
		
		// 세션에서 로그인한 유저의 번호 가져오기
		HttpSession session = request.getSession();
		// 로그인 시 세션에 저장한 키값이 "memberNumber"라고 가정합니다.
		Integer loginUserNumber = (Integer) session.getAttribute("memberNumber");

		// 1. 파일 업로드 경로 설정
		String uploadPath = request.getServletContext().getRealPath("/") + "upload/";
		int fileSize = 1024 * 1024 * 5; // 5MB

		try {
			// 2. 폴더 생성
			File uploadDir = new File(uploadPath);
			if (!uploadDir.exists()) {
				uploadDir.mkdirs();
			}

			// 3. 파일 업로드 실행 (MultipartRequest 생성)
			MultipartRequest multi = new MultipartRequest(request, uploadPath, fileSize, "UTF-8",
					new DefaultFileRenamePolicy());

			// 4. 데이터 수집
			String title = multi.getParameter("mentoringTitle");
			String subject = multi.getParameter("mentoringSubject"); // 과목 ID (숫자)
			String goal = multi.getParameter("mentoringPurpose");    // 목적
			String detail = multi.getParameter("mentoringCurriculum"); // 상세 내용
			String systemFileName = multi.getFilesystemName("mentoringFile"); // 실제 저장된 파일명

			// 5. DTO 데이터 세팅
			// 로그인 체크 (로그인 안 되어 있으면 21번으로 임시 세팅하거나 에러 처리)
			if (loginUserNumber == null) {
				System.out.println("[Warn] 세션에 유저 정보가 없음 - 테스트용 21번 세팅");
				mentoringDTO.setMentorNumber(21); 
			} else {
				mentoringDTO.setMentorNumber(loginUserNumber);
			}

			mentoringDTO.setSubjectNumber(Integer.parseInt(subject));
			mentoringDTO.setMentoringTitle(title);
			mentoringDTO.setMentoringGoal(goal);
			mentoringDTO.setMentoringDetail(detail);
			
			// 파일 번호 처리: 현재 DB가 FK 구조이므로 실제 파일 테이블 insert가 선행되어야 하나,
			// 우선 데이터 확인을 위해 1번(기본값) 혹은 파일명을 활용하도록 세팅
			mentoringDTO.setFileNumber(1); 

			// 6. DB Insert 실행 (단 한 번만 호출!)
			System.out.println("[Step 6] DB Insert 실행 중...");
			mentoringDAO.insert(mentoringDTO); 
			
			// selectKey에 의해 DTO에 담긴 생성된 PK 값 가져오기
			int createdId = mentoringDTO.getInternalId();
			System.out.println("[Step 6] DB Insert 성공! 생성된 ID: " + createdId);

			// 7. 성공 시 상세페이지로 이동
			// 파라미터명은 MentoringViewController에서 받는 이름과 일치해야 함 (mentoringNumber)
			String successPath = "/auth/mentor/mentoringView.my?mentoringNumber=" + createdId;
			result.setPath(successPath);
			result.setRedirect(true);

		} catch (Exception e) {
			System.out.println("[Error] 예외 발생: " + e.getMessage());
			e.printStackTrace();
			// 에러 시 마이페이지로 이동
			result.setPath("/auth/mentor/myPage.my");
			result.setRedirect(true);
		}

		System.out.println("[Log] MentoringWriteOkController 종료");
		System.out.println("=========================================");
		return result;
	}
}