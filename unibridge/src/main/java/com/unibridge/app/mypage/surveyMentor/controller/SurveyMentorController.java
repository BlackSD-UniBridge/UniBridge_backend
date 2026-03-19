package com.unibridge.app.mypage.surveyMentor.controller;

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
import com.unibridge.app.member.dao.MemberDAO;
import com.unibridge.app.member.dto.MemberDTO;
import com.unibridge.app.mypage.survey.dao.SurveyDAO;
import com.unibridge.app.mypage.surveyMentor.dto.SurveyMentorDTO;

public class SurveyMentorController implements Execute{
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
		// 기존 MentorController 예시처럼 세션 정보를 미리 채우거나 
//         페이지 이동 설정을 수행합니다.
		// 1. 세션에서 로그인 유저 객체 꺼내기
		HttpSession session = request.getSession(false);
		MemberDTO loginUser = (session != null) ? (MemberDTO) session.getAttribute("loginUser") : null;

		// 2. 객체에서 유형(memberType) 정보 가져오기
		// DB의 최신 상태를 반영하고 싶다면 앞서 만든 memberDAO.getMemberTypeByNum(loginUser.getMemberNumber())를 쓰세요.
		String type = loginUser.getMemberType(); 
		String path = "";

		// 3. 유형별 FrontController 요청 경로 지정
		if ("MENTOR".equals(type)) {
		    path = "/auth/mentor/survey.my";
		    outResult.setRedirect(true);
		} 
		else if ("MENTEE".equals(type)) {
		    path = "/auth/mentee/survey.my";
		    outResult.setRedirect(true);
		} 
		else {
		    // NODECIDED (미정)
		    path = "/auth/undecided/survey.my";
		    outResult.setRedirect(true);
		}

		// 4. 최종 경로 설정 (ContextPath 포함)
		outResult.setPath(request.getContextPath() + path);
        
	}

	private void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		SurveyDAO surveyDAO = new SurveyDAO();
        SurveyMentorDTO mentorDTO = new SurveyMentorDTO();
        FileDTO fileDTO = null;

        // 1. 파일 저장 경로 설정 및 폴더 체크
        final String UPLOAD_PATH = request.getServletContext().getRealPath("/") + "upload/";
        File uploadDir = new File(UPLOAD_PATH);
        // 폴더가 없으면 생성 (이 로직이 Not a directory 에러를 방지합니다)
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
            System.out.println("[LOG] 업로드 폴더를 생성했습니다: " + UPLOAD_PATH);
        }

        // 2. MultipartRequest 생성
        final int FILE_SIZE = 100 * 1024 * 1024; // 100MB 
        MultipartRequest multi = new MultipartRequest(request, UPLOAD_PATH, FILE_SIZE, "UTF-8", new DefaultFileRenamePolicy());

        // 3. 세션 정보 확인 (로그인한 유저의 정보 가져오기)
        HttpSession session = request.getSession(false);
        MemberDTO loginUser = (session != null) ? (MemberDTO) session.getAttribute("loginUser") : null;
        
        int memberNumber = loginUser.getMemberNumber();
        
        // 👉 무조건 41 사용
//        int memberNumber = 42;
//        System.out.println("[LOG] 임시 memberNumber 사용: 42");
        
        // 4. 파일 데이터 수집
        String filesystemName = multi.getFilesystemName("surveyFile");

        if (filesystemName != null) {
            fileDTO = new FileDTO();

            String originalName = multi.getOriginalFileName("surveyFile");

            fileDTO.setFileName(filesystemName);
            fileDTO.setFileOriginalName(originalName);
            fileDTO.setFilePath("/upload/" + filesystemName);

            // 확장자 추출 (필수)
            String extension = "none";

            if (originalName != null && originalName.contains(".")) {
                extension = originalName.substring(originalName.lastIndexOf(".") + 1);
            }

            fileDTO.setFileExtension(extension);

            // 파일 크기 (권장)
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

        // 5. 멘토 설문 데이터 수집 (SurveyDTO 상속 필드 포함)
        mentorDTO.setMemberNumber(memberNumber); // 부모 클래스 필드
        mentorDTO.setSurveyType("MENTOR");       // 부모 클래스 필드   
        
        // 멘토 상세 정보 (SurveyMentorDTO 전용 필드)
        mentorDTO.setGradSchool(multi.getParameter("gradSchool"));
        mentorDTO.setGradDepart(multi.getParameter("gradDepart"));
        
        // 학점(grad_score)은 DB에서 NUMBER 타입이므로 파싱 필요
        String gradScoreStr = multi.getParameter("gradScore");
        if(gradScoreStr != null && !gradScoreStr.isEmpty()) {
        	mentorDTO.setGradScore(Double.parseDouble(gradScoreStr));
        }
        
        // 과목 번호 (subject_number)
        String subjectNumStr = multi.getParameter("subjectNumber");
        if(subjectNumStr != null && !subjectNumStr.isEmpty()) {
        	mentorDTO.setSubjectNumber(Integer.parseInt(subjectNumStr));
        }
        
        //모든 필드 세팅 후 로그 출력
        System.out.println("=====[LOG] 세션 멤버 번호: " + memberNumber + "=====");
        System.out.println("=====[LOG] 수집 완료 DTO: " + mentorDTO.toString() + "=====");

        // 6. DAO 호출 (DB 저장 - 트랜잭션 처리됨)
        surveyDAO.insertMentorSurvey(mentorDTO, fileDTO);

        // 7. 결과 반환 (Redirect로 마이페이지 메인 이동) - 임지(추후 마이페이지 계정관리 이동예정)
        // 1. 세션에서 로그인 유저 정보(객체) 가져오기
        session = request.getSession(false);
        MemberDTO sessionUser = (session != null) ? (MemberDTO) session.getAttribute("loginUser") : null;

        MemberDAO memberDAO = new MemberDAO();
        String memberType = memberDAO.getMemberTypeByNum(sessionUser.getMemberNumber());

        // 타입에 따른 페이지 분기 및 리다이렉트 설정
        String path = "";

        if ("MENTEE".equals(memberType)) {
            path = "/app/user/mentee/myPage/myPage.jsp";
            outResult.setRedirect(false); // 멘티는 포워딩
        } 
        else if ("MENTOR".equals(memberType)) {
            path = "/app/user/mentor/myPage/myPage.jsp";
            outResult.setRedirect(true);  // 멘토는 리다이렉트
        } 
        else {
            // NODECIDED (미정)
            path = "/app/user/undecided/myPage/myPage.jsp";
            outResult.setRedirect(true);
        }

        // 4. 최종 경로 설정
        if (outResult.isRedirect()) {
            outResult.setPath(request.getContextPath() + path);
        } else {
            outResult.setPath(path);
        }
		
	}
	

}
