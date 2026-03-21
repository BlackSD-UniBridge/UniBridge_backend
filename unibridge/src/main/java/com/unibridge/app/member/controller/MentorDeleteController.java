package com.unibridge.app.member.controller;

import java.io.IOException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.unibridge.app.Execute;
import com.unibridge.app.Result;

public class MentorDeleteController implements Execute{

	@Override
    public Result execute(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
    	System.out.println("===============MentorDeleteController================");
    	
        String method = request.getMethod().toUpperCase();

        if ("GET".equals(method)) {
        	System.out.println("get 방식 접근");
        	
            // 회원탈퇴 페이지로 이동
            Result result = new Result();
            result.setPath("/app/user/mentor/myPage/userDelete/userDelete.jsp");
            result.setRedirect(false);
            return result;
        } else {
        	System.out.println("post 방식 접근");
        	
            // POST 방식: AJAX 인증 처리
            String mode = request.getParameter("mode");
            response.setContentType("text/plain; charset=utf-8");

            if ("send".equals(mode)) {
                doSendSms(request, response);
            } else if ("check".equals(mode)) {
                doVerifyCode(request, response);
            }
            return null; // AJAX 응답이므로 페이지 이동 없음
        }
    }

    private void doSendSms(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String phoneNumber = request.getParameter("phoneNumber");
        if (phoneNumber == null || !phoneNumber.matches("^010\\d{8}$")) {
            response.getWriter().write("invalid_format");
            return;
        }

        String authCode = String.format("%06d", new Random().nextInt(1000000));
        request.getSession().setAttribute("deleteAuthCode", authCode);

        // 실제 문자 발송 API (기존 소스 활용)
        // boolean isSent = requestSendApi(phoneNumber, authCode);
        System.out.println("[DEBUG] 생성된 인증번호: " + authCode); // 테스트용
        response.getWriter().write("success"); 
    }

    private void doVerifyCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        JsonObject jsonBody = new Gson().fromJson(request.getReader(), JsonObject.class);
        String userCode = jsonBody.get("authCode").getAsString();
        
        HttpSession session = request.getSession();
        String serverCode = (String) session.getAttribute("deleteAuthCode");

        if (serverCode != null && serverCode.equals(userCode)) {
        	// 1. 인증 완료 마크 저장 (최종 탈퇴 처리를 위해 필요)
            session.setAttribute("isDeleteVerified", true); 
            
            // 2. ★ 사용이 끝난 인증번호는 즉시 삭제 ★
            session.removeAttribute("deleteAuthCode"); 
            
            response.getWriter().write("verified");
        } else {
            response.getWriter().write("invalid");
        }
    }

}
