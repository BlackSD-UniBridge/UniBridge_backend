package com.unibridge.app.mypage.surveyMentee.controller;

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

        new SurveyDAO().insertMenteeSurvey(menteeDTO, fileDTO);

        outResult.setRedirect(true);
        outResult.setPath(request.getContextPath() + "/mvc/auth/undecided/survey.my");
        return outResult;
    }
}