package com.unibridge.app.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.unibridge.app.Execute;
import com.unibridge.app.Result;
import com.unibridge.app.member.dao.MemberDAO;
import com.unibridge.app.member.dto.MemberDTO;

public class DeleteController implements Execute {

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

    // 탈퇴 페이지 이동
    private void doGet(HttpServletRequest request, HttpServletResponse response) {

    	HttpSession session = request.getSession();
        String role = (String) session.getAttribute("role");

        String path = "/app/user/undetermined/myPage/userDelete/userDelete.jsp"; // 기본값

        if ("mentor".equals(role)) {
            path = "/app/user/mentor/myPage/userDelete/userDelete.jsp";
        } else if ("mentee".equals(role)) {
            path = "/app/user/mentee/myPage/userDelete/userDelete.jsp";
        }

        System.out.println("현재 사용자 유형: " + role);
        System.out.println("이동 경로: " + path);

        outResult.setPath(path);
        outResult.setRedirect(false);
    }

    // 실제 탈퇴 처리
    private void doPost(HttpServletRequest request, HttpServletResponse response) {

        System.out.println("[DeleteController] POST - 회원 탈퇴 진행");

        HttpSession session = request.getSession();
        Object memberNumObj = session.getAttribute("memberNumber");

        if (memberNumObj == null) {
            System.out.println("[ERROR] 로그인 안됨");

            outResult.setRedirect(true);
            outResult.setPath(request.getContextPath() + "/member/login.me");
            return;
        }

        int memberNumber = (int) memberNumObj;

        // 🔥 DTO에 값 세팅
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setMemberId(request.getParameter("userId"));
        memberDTO.setMemberPw(request.getParameter("userPw"));
        memberDTO.setMemberPhone(request.getParameter("userPhone"));

        System.out.println("아이디: " + memberDTO.getMemberId());
        System.out.println("전화번호: " + memberDTO.getMemberPhone());

        MemberDAO memberDAO = new MemberDAO();

        // 검증
        boolean isValid = memberDAO.checkMember(memberDTO);

        if (!isValid) {
            System.out.println("[ERROR] 회원 정보 불일치");

            outResult.setRedirect(false);
            request.setAttribute("errorMsg", "정보가 일치하지 않습니다.");
            outResult.setPath("/app/user/undetermined/myPage/userDelete/userDelete.jsp");
            return;
        }

        // 탈퇴 처리
        memberDAO.deleteMember(memberNumber);

        // 세션 제거
        session.invalidate();

        // 메인 이동
        outResult.setRedirect(true);
        outResult.setPath(request.getContextPath() + "/main.main");
    }
}