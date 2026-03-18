package com.unibridge.app.mypage.mentoring.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.unibridge.app.Execute; // 인터페이스 구현
import com.unibridge.app.Result;

public class MentoringFrontController implements Execute {

    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String requestURI = request.getRequestURI();
        // 경로 추출 로직 (기존 MentorFrontController와 통일)
        String target = extractTargetPath(requestURI);
        Result result = null;

        try {
            switch (target) {
                case "mentoringWriteOk.my":
                    result = new MentoringWriteOkController().execute(request, response);
                    break;
                case "mentoringView.my":
                    result = new MentoringViewController().execute(request, response);
                    break;
                case "mentoringModify.my":
                	result = new MentoringModifyController().execute(request, response);
                	break;
                case "mentoringModifyOk.my":
                	result = new MentoringModifyOkController().execute(request, response);
                	break;
                case "mentoringDeleteOk.my":
                	result = new MentoringDeleteOkController().execute(request, response);
                	break;
                default:
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    private String extractTargetPath(String requestUri) {
        String[] splitedPaths = requestUri.split("/");
        return splitedPaths[splitedPaths.length - 1];
    }
}