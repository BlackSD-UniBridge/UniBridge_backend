package com.unibridge.app.mypage.matching.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.unibridge.app.Execute;
import com.unibridge.app.Result;
import com.unibridge.app.pay.dao.PaymentDAO;
import com.unibridge.app.pay.dto.PaymentDTO;

public class PayLogController implements Execute{
	
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

	    Long memberNumber = (Long) request.getSession().getAttribute("memberNumber");
	    //임시 값 결제 내역 O
//	    memberNumber = 39L;
	    
	    //임시 값 결제 내역 X
//	    memberNumber = 1L;

	    if (memberNumber == null) {
	        outResult.setPath("/app/user/signin/signin.jsp");
	        outResult.setRedirect(true);
	        return;
	    }

	    PaymentDAO dao = new PaymentDAO();
	    PaymentDTO payLog = dao.selectLatestPaymentByMember(memberNumber);

	    // 핵심 분기
	    if (payLog == null) {
	        // 결제 내역 없음
	        outResult.setPath("/app/user/mentee/myPage/userPayLog/notPayLog.jsp");
	        outResult.setRedirect(false);
	        return;
	    }

	    // 결제 내역 있음
	    request.setAttribute("payLog", payLog);

	    outResult.setPath("/app/user/mentee/myPage/userPayLog/payLog.jsp");
	    outResult.setRedirect(false);
	}

	private void doPost(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}


}
