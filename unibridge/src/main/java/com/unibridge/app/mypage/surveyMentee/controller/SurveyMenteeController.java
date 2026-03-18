package com.unibridge.app.mypage.surveyMentee.controller;

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
import com.unibridge.app.file.dto.FileDTO;
import com.unibridge.app.mypage.survey.dao.SurveyDAO;
import com.unibridge.app.mypage.surveyMentee.dto.SurveyMenteeDTO;

public class SurveyMenteeController implements Execute{
	private Result outResult = new Result();
	
	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String method = request.getMethod().toUpperCase();
        
        switch (method) {
            case "GET":
                this.doGet(request, response);
                break;
            case "POST":
                this.doPost(request, response);
                break;
        }
        return outResult;
	}

	private void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		outResult.setRedirect(false); // 포워딩 방식
        outResult.setPath("/app/user/mentee/myPage/userSurvey/userSurvey.jsp");
		
	}

	private void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		SurveyDAO surveyDAO = new SurveyDAO();
        SurveyMenteeDTO menteeDTO = new SurveyMenteeDTO();
        FileDTO fileDTO = null;

        // 1. 파일 저장 경로 설정 및 폴더 체크
        final String UPLOAD_PATH = request.getServletContext().getRealPath("/") + "upload/";
        File uploadDir = new File(UPLOAD_PATH);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
            System.out.println("[LOG] 업로드 폴더를 생성했습니다: " + UPLOAD_PATH);
        }

        // 2. MultipartRequest 생성 (파일 업로드 실행)
        final int FILE_SIZE = 100 * 1024 * 1024; // 500MB
        MultipartRequest multi = new MultipartRequest(request, UPLOAD_PATH, FILE_SIZE, "UTF-8", new DefaultFileRenamePolicy());

        // 3. 세션 정보 확인
        HttpSession session = request.getSession();
        Object memberNumObj = session.getAttribute("memberNumber");
        
        // 👉 무조건 41 사용
        int memberNumber = 43;
        System.out.println("[LOG] 임시 memberNumber 사용: 43");
        
      //테스트를 위한 주석처리 -> 테스트 해제시 주석 해제 
//        if (memberNumObj == null) {
//            outResult.setRedirect(true);
//            outResult.setPath(request.getContextPath() + "/member/login.me");
//            return;
//        }

        // 4. 파일 데이터 수집
        String filesystemName = multi.getFilesystemName("surveyFile");

        if (filesystemName != null) {
            fileDTO = new FileDTO();

            String originalName = multi.getOriginalFileName("surveyFile");

            fileDTO.setFileName(filesystemName);
            fileDTO.setFileOriginalName(originalName);
            fileDTO.setFilePath("/upload/" + filesystemName);

            // 🔥 확장자 추출 (필수)
            String extension = "none";

            if (originalName != null && originalName.contains(".")) {
                extension = originalName.substring(originalName.lastIndexOf(".") + 1);
            }

            fileDTO.setFileExtension(extension);

            // 🔥 파일 크기 (권장)
            File file = multi.getFile("surveyFile");
            if (file != null) {
                fileDTO.setFileSize(file.length());
            }

            System.out.println("[LOG] 파일 업로드됨");
            System.out.println("파일명: " + originalName);
            System.out.println("확장자: " + fileDTO.getFileExtension());
        } else {
            System.out.println("[LOG] 파일 업로드 없음");
        }

        // 5. 멘티 설문 데이터 수집 (상속 필드 포함)
//      int memberNumber = (int)memberNumObj; //테스트 해제시 주석처리 해제할 것
        menteeDTO.setMemberNumber(memberNumber);
        menteeDTO.setSurveyType("MENTEE");
        menteeDTO.setMenteeSchool(multi.getParameter("menteeSchool"));
        menteeDTO.setMenteeHopeuni(multi.getParameter("menteeHopeuni"));
        menteeDTO.setMenteeHopemajor(multi.getParameter("menteeHopemajor"));

        // 숫자형 데이터 파싱 및 예외 처리
        try {
            String gradeStr = multi.getParameter("menteeGrade");
            if(gradeStr != null && !gradeStr.isEmpty()) {
                menteeDTO.setMenteeGrade(Integer.parseInt(gradeStr));
            }
            
            String subjectNumStr = multi.getParameter("subjectNumber");
            if(subjectNumStr != null && !subjectNumStr.isEmpty()) {
                menteeDTO.setSubjectNumber(Integer.parseInt(subjectNumStr));
            }
        } catch (NumberFormatException e) {
            System.out.println("[ERROR] 숫자 파싱 오류 발생");
            e.printStackTrace();
        }

        System.out.println("=====[LOG] 세션 멤버 번호: " + memberNumber + "=====");
        System.out.println("=====[LOG] 수집 완료 DTO: " + menteeDTO.toString() + "=====");

        // 6. DAO 호출 (멘티 전용 메서드)
        surveyDAO.insertMenteeSurvey(menteeDTO, fileDTO);

        // 7. 결과 반환
        session = request.getSession();
        String role = (String) session.getAttribute("role");

        String path = "/auth/undecided/mypage.my"; // 기본값

        if ("mentor".equals(role)) {
            path = "/auth/mentor/mypage.my";
        } else if ("mentee".equals(role)) {
            path = "/auth/mentee/mypage.my";
        }

        // redirect 설정
        outResult.setRedirect(true);
        outResult.setPath(request.getContextPath() + path);
		
	}
	
}
