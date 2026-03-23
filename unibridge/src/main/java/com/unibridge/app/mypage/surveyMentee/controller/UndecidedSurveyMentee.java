package com.unibridge.app.mypage.surveyMentee.controller;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.oreilly.servlet.MultipartRequest;
import com.unibridge.app.Execute;
import com.unibridge.app.Result;
import com.unibridge.app.file.dto.FileDTO;
import com.unibridge.app.member.dao.MemberDAO;
import com.unibridge.app.member.dto.MemberDTO;
import com.unibridge.app.mypage.survey.dao.SurveyDAO;
import com.unibridge.app.mypage.surveyMentee.dto.SurveyMenteeDTO;

public class UndecidedSurveyMentee implements Execute {
    private MultipartRequest multi;
    private Result outResult = new Result();

    public UndecidedSurveyMentee(MultipartRequest multi) {
        this.multi = multi;
    }

    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        MemberDTO loginUser = (MemberDTO) session.getAttribute("loginUser");

        SurveyMenteeDTO menteeDTO = new SurveyMenteeDTO();
        menteeDTO.setMemberNumber(loginUser.getMemberNumber());
        menteeDTO.setSurveyType("MENTEE");
        menteeDTO.setMenteeSchool(multi.getParameter("menteeSchool"));
        
        String gradeStr = multi.getParameter("menteeGrade");
        menteeDTO.setMenteeGrade(gradeStr != null ? Integer.parseInt(gradeStr) : 0);
        menteeDTO.setMenteeHopeuni(multi.getParameter("menteeHopeuni"));
        menteeDTO.setMenteeHopemajor(multi.getParameter("menteeHopemajor"));
        
        String subjectStr = multi.getParameter("subjectNumber");
        menteeDTO.setSubjectNumber(subjectStr != null ? Integer.parseInt(subjectStr) : 0);

        // 파일 처리 및 DTO 조립 로직
        MemberDAO memberDAO = new MemberDAO();
        FileDTO fileDTO = null;
        String originalName = multi.getOriginalFileName("surveyFile");

        if (originalName != null) {
            File oldFile = multi.getFile("surveyFile");
            String extension = originalName.substring(originalName.lastIndexOf("."));
            
            // 파일명 규칙: survey_회원번호_현재시간.확장자
            String newFileName = "survey_" + loginUser.getMemberNumber() + "_" + System.currentTimeMillis() + extension;
            
            // 저장 경로 (C:/upload/survey/)
            String savePath = "C:/upload/survey/";
            File newFile = new File(savePath, newFileName);
            
            // 파일 이름 변경 실행
            if (oldFile.renameTo(newFile)) {
                fileDTO = new FileDTO();
                fileDTO.setFileName(newFileName);              // 서버 저장명
                fileDTO.setFileOriginalName(originalName);     // 실제 원본명
                fileDTO.setFileExtension(extension.replace(".", ""));
                fileDTO.setFileSize(newFile.length());
                fileDTO.setFilePath("/upload/survey/" + newFileName); // 가상 경로
                
                System.out.println("[DEBUG] 멘티 파일명 변경 완료: " + newFileName);
            }
        }

        new SurveyDAO().insertMenteeSurvey(menteeDTO, fileDTO);
        
        int generatedSurveyNumber = menteeDTO.getSurveyNumber(); 
        memberDAO.updateMemberSurveyNumber(loginUser.getMemberNumber(), generatedSurveyNumber);
        
        System.out.println("[LOG] 멘토 설문 등록 성공 (회원번호: " + loginUser.getMemberNumber() + ")");

        outResult.setRedirect(true);
        outResult.setPath(request.getContextPath() + "/mvc/auth/undecided/survey.my");
        return outResult;
    }
}