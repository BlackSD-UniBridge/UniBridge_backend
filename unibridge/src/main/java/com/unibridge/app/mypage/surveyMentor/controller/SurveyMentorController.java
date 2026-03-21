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
import com.unibridge.app.member.dto.MemberDTO;
import com.unibridge.app.mypage.survey.dao.SurveyDAO;
import com.unibridge.app.mypage.surveyMentor.dto.SurveyMentorDTO;

public class SurveyMentorController implements Execute{
	private Result outResult = new Result();

	@Override
    public Result execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String UPLOAD_PATH = request.getServletContext().getRealPath("/") + "upload/";
        File uploadDir = new File(UPLOAD_PATH);
        if (!uploadDir.exists()) uploadDir.mkdirs();
        
        HttpSession session = request.getSession();
		MemberDTO loginUser = (MemberDTO) session.getAttribute("loginUser");

        // 1. MultipartRequest 생성 (여기서 데이터가 파싱됩니다)
        MultipartRequest multi = new MultipartRequest(request, UPLOAD_PATH, 100*1024*1024, "UTF-8", new DefaultFileRenamePolicy());

        // 2. 파싱된 multi 객체에서 role 값을 꺼냅니다. (드디어 null이 아님!)
        String role = multi.getParameter("role");
        System.out.println("[LOG] 전달된 role: " + role);

        SurveyDAO surveyDAO = new SurveyDAO();
        SurveyMentorDTO mentorDTO = new SurveyMentorDTO();
        
        // 3. 데이터 수집 및 DTO 세팅
        // 2. 데이터 수집 및 DTO 세팅 (멘토 필드에 맞게 수정)
        mentorDTO.setMemberNumber(loginUser.getMemberNumber());
        mentorDTO.setSurveyType("MENTOR"); // 멘토 컨트롤러이므로 고정 또는 role 사용
        
        // JSP의 <input name="gradSchool"> 등과 매칭
        mentorDTO.setGradSchool(multi.getParameter("gradSchool"));
        mentorDTO.setGradDepart(multi.getParameter("gradDepart"));
        
        // 학점(double) 변환 방어 코드
        String scoreStr = multi.getParameter("gradScore");
        if (scoreStr != null && !scoreStr.isEmpty()) {
            mentorDTO.setGradScore(Double.parseDouble(scoreStr));
        }

        // 과목번호(int) 변환 방어 코드
        String subjectStr = multi.getParameter("subjectNumber");
        if (subjectStr != null && !subjectStr.isEmpty()) {
            mentorDTO.setSubjectNumber(Integer.parseInt(subjectStr));
        }

        // 3. 파일 처리
        FileDTO fileDTO = null;
        if (multi.getFilesystemName("surveyFile") != null) {
            fileDTO = new FileDTO();
            fileDTO.setFileName(multi.getFilesystemName("surveyFile"));
            fileDTO.setFileOriginalName(multi.getOriginalFileName("surveyFile"));
            fileDTO.setFilePath("/upload/" + multi.getFilesystemName("surveyFile"));
            fileDTO.setFileExtension(fileDTO.getFileName().substring(fileDTO.getFileName().lastIndexOf(".") + 1));
            fileDTO.setFileSize(multi.getFile("surveyFile").length());
        }

        // 4. DAO 호출
        surveyDAO.insertMentorSurvey(mentorDTO, fileDTO);

        // 5. 결과 설정
        outResult.setRedirect(true);
        outResult.setPath(request.getContextPath() + "/mvc/auth/mentor/survey.my"); 
        return outResult;
    }
	

}
