package com.unibridge.app.mypage.matching.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.unibridge.app.Execute;
import com.unibridge.app.Result;

public class MatchingController implements Execute {

    private Result outResult = new Result();

    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String method = request.getMethod().toUpperCase();

        switch (method) {
            case "GET":
                doGet(request, response);
                break;
            case "POST":
                doPost(request, response);
                break;
        }

        return outResult;
    }

    // 매칭 정보 페이지 이동
    private void doGet(HttpServletRequest request, HttpServletResponse response) {

        System.out.println("[MatchingController] GET - 매칭 정보 페이지");

        HttpSession session = request.getSession();
        String role = (String) session.getAttribute("role");

        String path = "/app/user/undetermined/myPage/userMatching/userMatching.jsp"; // 기본값

        if ("mentor".equals(role)) {
            path = "/app/user/mentor/myPage/userMatching/userMatching.jsp";
        } else if ("mentee".equals(role)) {
            path = "/app/user/mentee/myPage/userMatching/userMatching.jsp";
        }

        System.out.println("현재 사용자 유형: " + role);
        System.out.println("이동 경로: " + path);

        outResult.setPath(path);
        outResult.setRedirect(false);
    }

    // 매칭 취소 처리 (추후 사용) - 버튼 기능
    private void doPost(HttpServletRequest request, HttpServletResponse response) {

        System.out.println("[MatchingController] POST - 매칭 취소 처리");

        // 예: 취소 사유 받기
        String reason = request.getParameter("cancelReason");

        System.out.println("취소 사유: " + reason);

        // 👉 TODO: DAO 호출해서 매칭 취소 처리

        // 완료 후 다시 매칭 페이지로
        outResult.setRedirect(true);
        outResult.setPath(request.getContextPath() + "/auth/mentor/matching.my");
    }
}