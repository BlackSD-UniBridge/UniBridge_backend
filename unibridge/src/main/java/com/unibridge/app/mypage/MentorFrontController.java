package com.unibridge.app.mypage;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.unibridge.app.Execute;
import com.unibridge.app.Result;
import com.unibridge.app.member.controller.DeleteController;
import com.unibridge.app.member.controller.UpdateController;
import com.unibridge.app.mypage.survey.controller.SurveyController;
import com.unibridge.app.mypage.mentoring.controller.MentoringFrontController;

public class MentorFrontController implements Execute {
    Result outResult = new Result();
    
    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String requestURI = request.getRequestURI();
        // 쿼리 스트링(?, &)이 있을 경우를 대비해 순수 파일명만 추출
        String target = requestURI.substring(requestURI.lastIndexOf("/") + 1);
        
        System.out.println("[Log] MentorFrontController 수신 URI: " + requestURI);
        System.out.println("[Log] 추출된 Target: " + target);

        // 이제 정확한 파일명으로 switch-case를 태웁니다.
        switch (target) {
            case "myPage.my":
                System.out.println("계정관리 요청 수신");
                this.outResult = new UpdateController().execute(request, response);
                break;
                
            case "survey.my":
                System.out.println("설문 요청 수신");
                this.outResult = new SurveyController().execute(request, response);
                break;
                
            case "delete.my":
                System.out.println("회원탈퇴 신청 요청 수신");
                this.outResult = new DeleteController().execute(request, response);
                break;

            // 멘토링 관련은 'mentoring'이라는 키워드가 포함되어 있는지로 별도 분기
            default:
                if (target.contains("mentoring")) {
                    System.out.println("[Log] 멘토링 관련 요청 -> MentoringFrontController 이동");
                    this.outResult = new MentoringFrontController().execute(request, response);
                } else {
                    System.out.println("[Warn] 매칭되는 target이 없음: " + target);
                }
                break;
        }
        
        return outResult;
    }
}