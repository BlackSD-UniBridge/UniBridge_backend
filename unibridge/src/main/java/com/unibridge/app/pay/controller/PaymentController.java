package com.unibridge.app.pay.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.unibridge.app.Execute;
import com.unibridge.app.Result;

public class PaymentController implements Execute {
    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response) {
        Result result = new Result();
        HttpSession session = request.getSession();

        try {
            String mentorNumber = request.getParameter("memberNumber"); // 멘토 번호
            // 실제 서비스 시에는 매칭 테이블의 PK인 matchingNumber를 가져와야 함
            // 임시로 1L을 세션에 넣습니다.
            session.setAttribute("matchingNumber", 1L); 
            session.setAttribute("payAmount", "10000");

            request.setAttribute("mentorNumber", mentorNumber);
            request.setAttribute("totalAmount", 10000);

            result.setPath("/app/user/mentorSearch/payment/payment.jsp");
            result.setRedirect(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}