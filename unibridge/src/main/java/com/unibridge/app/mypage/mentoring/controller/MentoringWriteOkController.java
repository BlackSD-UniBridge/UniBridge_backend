package com.unibridge.app.mypage.mentoring.controller;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;

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

		System.out.println("[Log] MentoringWriteOkController 시작");

		MentoringDAO mentoringDAO = new MentoringDAO();
		MentoringDTO mentoringDTO = new MentoringDTO();
		Result result = new Result();

		HttpSession session = request.getSession();
		Object loginUserObj = session.getAttribute("memberNumber");
		long mentorNumber = Long.parseLong(String.valueOf(loginUserObj != null ? loginUserObj : 21L));

		// 1. 저장 경로 설정 (mentoring 폴더 추가)
		String uploadPath = request.getServletContext().getRealPath("/") + "upload/mentoring/";
		int fileSize = 1024 * 1024 * 10; // 10MB로 상향

		try {
			File uploadDir = new File(uploadPath);
			if (!uploadDir.exists()) {
				uploadDir.mkdirs();
			}

			// 2. 파일 업로드 실행 (이 시점에 파일이 서버에 저장됨)
			MultipartRequest multi = new MultipartRequest(request, uploadPath, fileSize, "UTF-8",
					new DefaultFileRenamePolicy());

			// 3. 파일명 변경 로직 적용
			Enumeration<String> files = multi.getFileNames();
			String finalFileName = "";

			if (files.hasMoreElements()) {
				String name = files.nextElement();
				String systemName = multi.getFilesystemName(name); // 서버에 저장된 이름
				String originalName = multi.getOriginalFileName(name); // 실제 파일명

				if (systemName != null) {
					// 규칙: 현재시간_멤버번호_원본이름 (파일명 중복 방지 및 규칙 준수)
					finalFileName = System.currentTimeMillis() + "_" + mentorNumber + "_" + originalName;

					File oldFile = new File(uploadPath + systemName);
					File newFile = new File(uploadPath + finalFileName);
					oldFile.renameTo(newFile); // 파일명 변경 실행
					System.out.println("[Log] 파일명 변경 완료: " + finalFileName);
				}
			}

			// 4. 데이터 세팅
			mentoringDTO.setMentorNumber(mentorNumber);
			mentoringDTO.setSubjectNumber(Integer.parseInt(multi.getParameter("mentoringSubject")));
			mentoringDTO.setMentoringTitle(multi.getParameter("mentoringTitle"));
			mentoringDTO.setMentoringGoal(multi.getParameter("mentoringPurpose"));
			mentoringDTO.setMentoringDetail(multi.getParameter("mentoringCurriculum"));
			mentoringDTO.setFileName(finalFileName); // 가공된 파일명을 DTO에 저장

			// 5. 중복 체크 및 DB Insert
			int existingCount = mentoringDAO.checkAlreadyExists(mentorNumber);
			if (existingCount > 0) {
				result.setPath(request.getContextPath() + "/mvc/auth/mentor/myPage.my?error=already_exists");
				result.setRedirect(true);
				return result;
			}

			mentoringDAO.insert(mentoringDTO); // Mapper에서 UB_FILE과 UB_MENTORING 동시 처리 권장

			long createdNumber = mentoringDTO.getMentoringNumber();
			result.setPath(request.getContextPath() + "/mvc/auth/mentor/mentoring.my?type=view&mentoringNumber="
					+ createdNumber);
			result.setRedirect(true);
			return result;

		} catch (Exception e) {
			e.printStackTrace();
			result.setPath(request.getContextPath() + "/mvc/auth/mentor/myPage.my");
			result.setRedirect(true);
			return result;
		}
	}
}