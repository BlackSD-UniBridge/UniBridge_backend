package com.unibridge.app.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.unibridge.app.Execute;
import com.unibridge.app.Result;
import com.unibridge.app.member.dto.MemberDTO;

public class MenteeVerifySubmitController implements Execute {
    private Result outResult = new Result();

    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        // 1. 세션에서 로그인 유저 정보 및 휴대폰 인증 여부 확인
        MemberDTO loginUser = (MemberDTO) session.getAttribute("loginUser");
        Boolean isPhoneVerified = (Boolean) session.getAttribute("isPhoneVerified");
        
        // 2. JSP에서 전송된 입력값 가져오기
        String inputPw = request.getParameter("password");
        String inputPhone = request.getParameter("phoneNumber");

        // 3. 비밀번호 일치 검증
        if (loginUser == null || !loginUser.getMemberPw().equals(inputPw)) {
            request.setAttribute("pwError", "비밀번호가 일치하지 않습니다.");
            outResult.setPath("/app/user/mentee/myPage/userManage/userModifyCheck.jsp");
            outResult.setRedirect(false);
            return outResult;
        }

        // 4. 휴대폰 인증 완료 여부 검증
        if (isPhoneVerified == null || !isPhoneVerified) {
            request.setAttribute("authError", "휴대폰 인증이 완료되지 않았습니다.");
            outResult.setPath("/app/user/mentee/myPage/userManage/userModifyCheck.jsp");
            outResult.setRedirect(false);
            return outResult;
        }

        // 5. 모든 검증 성공 시: 수정 로직(UpdateOk)으로 데이터 전달
        request.setAttribute("newPhoneNumber", inputPhone);
        
        // 실제 DB 업데이트를 처리하는 컨트롤러로 이동
        outResult.setPath(request.getContextPath() + "/auth/mentee/updateOk.my");
        outResult.setRedirect(true); // 데이터를 가지고 가야 하므로 forward 방식 사용
        
        return outResult;
    }
}