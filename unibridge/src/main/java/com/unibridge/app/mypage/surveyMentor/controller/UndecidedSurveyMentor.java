package com.unibridge.app.mypage.surveyMentor.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.oreilly.servlet.MultipartRequest;
import com.unibridge.app.Execute;
import com.unibridge.app.Result;
import com.unibridge.app.file.dto.FileDTO;
import com.unibridge.app.member.dto.MemberDTO;
import com.unibridge.app.mypage.survey.dao.SurveyDAO;
import com.unibridge.app.mypage.surveyMentor.dto.SurveyMentorDTO;

public class UndecidedSurveyMentor implements Execute {
    private MultipartRequest multi;
    private Result outResult = new Result();

    public UndecidedSurveyMentor(MultipartRequest multi) {
        this.multi = multi;
    }

    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        MemberDTO loginUser = (MemberDTO) session.getAttribute("loginUser");

        SurveyMentorDTO mentorDTO = new SurveyMentorDTO();
        mentorDTO.setMemberNumber(loginUser.getMemberNumber());
        mentorDTO.setSurveyType("MENTOR");
        mentorDTO.setGradSchool(multi.getParameter("gradSchool"));
        mentorDTO.setGradDepart(multi.getParameter("gradDepart"));
        
        String scoreStr = multi.getParameter("gradScore");
        mentorDTO.setGradScore((scoreStr != null && !scoreStr.isEmpty()) ? Double.parseDouble(scoreStr) : 0.0);

        String subjectStr = multi.getParameter("subjectNumber");
        mentorDTO.setSubjectNumber((subjectStr != null && !subjectStr.isEmpty()) ? Integer.parseInt(subjectStr) : 0);

        FileDTO fileDTO = null;
        if (multi.getFilesystemName("surveyFile") != null) {
            fileDTO = new FileDTO();
            String fileName = multi.getFilesystemName("surveyFile");
            fileDTO.setFileName(fileName);
            fileDTO.setFileOriginalName(multi.getOriginalFileName("surveyFile"));
            fileDTO.setFilePath("/upload/" + fileName);
            fileDTO.setFileExtension(fileName.substring(fileName.lastIndexOf(".") + 1));
            fileDTO.setFileSize(multi.getFile("surveyFile").length());
        }

        new SurveyDAO().insertMentorSurvey(mentorDTO, fileDTO);

        outResult.setRedirect(true);
        outResult.setPath(request.getContextPath() + "/mvc/auth/undecided/survey.my");
        return outResult;
    }
}