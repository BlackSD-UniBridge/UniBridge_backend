package com.unibridge.app.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.unibridge.app.Execute;
import com.unibridge.app.Result;

public class UpdateController implements Execute{

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

	private void doGet(HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session = request.getSession();
        String role = (String) session.getAttribute("role");

        String path = "/app/user/undetermined/myPage/myPage.jsp"; // 기본값

        if ("mentor".equals(role)) {
            path = "/app/user/mentor/myPage/myPage.jsp";
        } else if ("mentee".equals(role)) {
            path = "/app/user/mentee/myPage/myPage.jsp";
        }

        System.out.println("현재 사용자 유형: " + role);
        System.out.println("이동 경로: " + path);

        outResult.setPath(path);
        outResult.setRedirect(false);
		
	}

	//인증 장치 -> 수정 페이지 넘어가게 별도 설계
	private void doPost(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

}
