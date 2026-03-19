package com.unibridge.app.learningReport.controller;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson; // 1. import 추가
import com.unibridge.app.learningReport.dao.LearningReportDAO;
import com.unibridge.app.learningReport.dto.LearningReportDTO;

public class ReportDetailOkController {
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        // 1. 파라미터 수집
        int learningReportNumber = Integer.parseInt(request.getParameter("reportId"));

        // 2. DAO 호출 (DB 데이터 가져오기)
        LearningReportDAO dao = new LearningReportDAO();
        LearningReportDTO dto = dao.selectReportDetail(learningReportNumber);

        // 3. 응답 설정
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        // 4. Gson을 이용한 변환
        Gson gson = new Gson();
        // dto 객체 안에 들어있는 모든 데이터를 자동으로 {"subjectTitle":"...", "reportWeek":1 ...} 형태로 만듭니다.
        String json = gson.toJson(dto);
        
        // 5. 출력
        out.print(json);
        out.close();
    }
}