package com.unibridge.app.pay.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.unibridge.app.Execute;
import com.unibridge.app.Result;
import com.unibridge.app.pay.dao.PaymentDAO;
import com.unibridge.app.pay.dto.PaymentDTO;

public class PaymentFinishController implements Execute {

    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        Result result = new Result();
        HttpSession session = request.getSession();
        
        String pgToken = request.getParameter("pg_token");
        String tid = (String) session.getAttribute("tid");
        String secretKey = "SECRET_KEY " + ConfigReader.getProperty("kakao.secret.key");
        
        try {
            // 카카오 승인 API 호출
            URL url = new URL("https://open-api.kakaopay.com/online/v1/payment/approve");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Authorization", secretKey);
            conn.setRequestProperty("Content-type", "application/json;charset=utf-8");
            conn.setDoOutput(true);

            String jsonParams = String.format("{"
                    + "\"cid\":\"TC0ONETIME\","
                    + "\"tid\":\"%s\","
                    + "\"partner_order_id\":\"1001\","
                    + "\"partner_user_id\":\"unibridge\","
                    + "\"pg_token\":\"%s\""
                    + "}", tid, pgToken);

            try (OutputStream out = conn.getOutputStream()) {
                out.write(jsonParams.getBytes("utf-8"));
            }

            if (conn.getResponseCode() == 200) {
                // 승인 성공 시 DB 저장
                PaymentDAO paymentDAO = new PaymentDAO();
                PaymentDTO payInfo = new PaymentDTO();

                payInfo.setPayAmount("10000"); 
                payInfo.setPayMethod("카카오페이");
                payInfo.setPayStatus("결제완료");
                
                // JSP의 fmt:parseDate와 형식을 맞추기 위해 String으로 세팅
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                payInfo.setPayDate(sdf.format(new Date()));

                // DB Insert (selectKey에 의해 payId가 payInfo에 자동 세팅됨)
                paymentDAO.insertPayment(payInfo); 

                // 결과 페이지로 데이터 전달
                request.setAttribute("payInfo", payInfo);
                session.removeAttribute("tid");

                result.setPath("/app/user/mentorSearch/payment/paymentFinish.jsp");
                result.setRedirect(false);

            } else {
                result.setPath("/index.main");
                result.setRedirect(true);
            }

        } catch (Exception e) {
            e.printStackTrace();
            result.setPath("/index.main");
            result.setRedirect(true);
        }

        return result;
    }
}