package com.unibridge.app.learningReport.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.unibridge.app.Result;
import com.unibridge.app.learningReport.dao.LearningReportDAO;
import com.unibridge.app.learningReport.dto.LearningReportDTO;

public class ReportListOkController {
    public Result execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Result result = new Result();
        LearningReportDAO dao = new LearningReportDAO();
        
        // 1. 파라미터 수집 (날짜 검색 조건)
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");

        Map<String, Object> filters = new HashMap<>();
        filters.put("startDate", startDate);
        filters.put("endDate", endDate);

        // 2. DAO 호출 (주석 해제 및 실제 호출)
        // UB_LEARNING_REPORT 테이블에서 목록을 가져옵니다.
        List<LearningReportDTO> reportList = dao.selectReportList(filters);
        
        // (선택) 전체 개수 및 이번 주 작성 수 통계도 가져오려면 추가
        // Map<String, Object> status = dao.getReportStatus(1); // matchingNumber 예시로 1 전달
        
        // 3. 결과 전달 (JSP의 c:forEach에서 사용할 이름)
        request.setAttribute("reportList", reportList);
        // request.setAttribute("status", status); // 통계 데이터
        
        // 4. 이동 경로 설정 (실제 경로로 수정)
        result.setPath("/app/user/notice/report.jsp");
        result.setRedirect(false); 
        
        return result;
    }
}