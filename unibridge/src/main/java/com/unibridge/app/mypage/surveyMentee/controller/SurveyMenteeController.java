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
import com.unibridge.app.member.dao.MemberDAO;
import com.unibridge.app.member.dto.MemberDTO;
import com.unibridge.app.mypage.survey.dao.SurveyDAO;
import com.unibridge.app.mypage.surveyMentee.dto.SurveyMenteeDTO;

public class SurveyMenteeController implements Execute {
    private Result outResult = new Result();

    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	//파일 저장 경로 설정 및 디렉토리 생성
    	String UPLOAD_PATH = "C:/upload/survey/";
        File uploadDir = new File(UPLOAD_PATH);
        if (!uploadDir.exists()) uploadDir.mkdirs();
        
        HttpSession session = request.getSession();
        MemberDTO loginUser = (MemberDTO) session.getAttribute("loginUser");
        if (loginUser == null) {
            outResult.setRedirect(true);
            outResult.setPath(request.getContextPath() + "/mvc/auth/lndex.main");
            return outResult;
        }

        // 1. MultipartRequest 생성 (여기서 데이터가 파싱됩니다)
        MultipartRequest multi = new MultipartRequest(request, UPLOAD_PATH, 100*1024*1024, "UTF-8", new DefaultFileRenamePolicy());

        // 2. 파싱된 multi 객체에서 role 값을 꺼냅니다. (드디어 null이 아님!)
        String role = multi.getParameter("role");
        System.out.println("[LOG] 전달된 role: " + role);
        
        SurveyDAO surveyDAO = new SurveyDAO();
        MemberDAO memberDAO = new MemberDAO();
        SurveyMenteeDTO menteeDTO = new SurveyMenteeDTO();

        
        // 3. 데이터 수집 (예외 방지 처리)
        try {
            menteeDTO.setMemberNumber(loginUser.getMemberNumber());
            menteeDTO.setSurveyType("MENTEE");
            menteeDTO.setMenteeSchool(multi.getParameter("menteeSchool"));
            
            // 값이 비어있을 경우를 대비해 기본값 처리
            String gradeStr = multi.getParameter("menteeGrade");
            menteeDTO.setMenteeGrade(gradeStr != null ? Integer.parseInt(gradeStr) : 0);
            
            menteeDTO.setMenteeHopeuni(multi.getParameter("menteeHopeuni"));
            menteeDTO.setMenteeHopemajor(multi.getParameter("menteeHopemajor"));
            
            String subjectStr = multi.getParameter("subjectNumber");
            menteeDTO.setSubjectNumber(subjectStr != null ? Integer.parseInt(subjectStr) : 0);
            
            // 4. 파일 처리 및 DAO 호출
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
                } else {
                	System.out.println("[DEBUG] 멘티 파일 업로드 없음");
                }
            }
            
            // 5. DB 저장
            surveyDAO.insertMenteeSurvey(menteeDTO, fileDTO);
            
            int generatedSurveyNumber = menteeDTO.getSurveyNumber(); 
            memberDAO.updateMemberSurveyNumber(loginUser.getMemberNumber(), generatedSurveyNumber);
            
            System.out.println("[LOG] 멘토 설문 등록 성공 (회원번호: " + loginUser.getMemberNumber() + ")");
            
        } catch (NumberFormatException e) {
            System.out.println("[Error] 숫자 형식 데이터 오류 발생");
        }

        outResult.setRedirect(true);
        outResult.setPath(request.getContextPath() + "/mvc/auth/mentee/survey.my");
        return outResult;
    }
}