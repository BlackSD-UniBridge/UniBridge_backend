package com.unibridge.app.learningReport.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.unibridge.app.Result;

public class LearningReportFrontController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doProcess(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doProcess(request, response);
    }

    protected void doProcess(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String requestURI = request.getRequestURI();
        String contextPath = request.getContextPath();
        String target = requestURI.substring(contextPath.length());

        System.out.println("\n========= [LearningReport FrontController] =========");
        System.out.println("Target: " + target);

        Result result = null;

        try {
            switch (target) {
                // 1. 목록 조회 (검색 포함)
                case "/mvc/auth/reportList.rep":
                    System.out.println("[Log] ReportListOkController 실행");
                    result = new ReportListOkController().execute(request, response);
                    break;

                // 2. 상세보기 (AJAX 처리)
                case "/mvc/auth/reportDetailOk.rep":
                    System.out.println("[Log] ReportDetailOkController 실행");
                    // AJAX 응답은 result가 null일 수 있으므로 내부에서 처리
                    new ReportDetailOkController().execute(request, response);
                    break;

                default:
                    System.out.println("[Warn] 알 수 없는 경로: " + target);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 전송 처리
        if (result != null) {
            if (result.isRedirect()) {
                response.sendRedirect(result.getPath());
            } else {
                request.getRequestDispatcher(result.getPath()).forward(request, response);
            }
        }
        System.out.println("========= [End] =========\n");
    }
}