package com.unibridge.app.pay.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class PaymentOkController {

    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
        	// properties에서 키 값 가져오기
            String secretKey = "SECRET_KEY " + ConfigReader.getProperty("kakao.secret.key");
            
            String cid = ConfigReader.getProperty("kakao.cid");

            HttpSession session = request.getSession();
            String tid = (String) session.getAttribute("tid");
            String pgToken = request.getParameter("pg_token");

            System.out.println(">>> [승인 단계] TID: " + tid);
            System.out.println(">>> [승인 단계] PG_TOKEN: " + pgToken);

            URL url = new URL("https://open-api.kakaopay.com/online/v1/payment/approve");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            
            conn.setRequestMethod("POST");
            
            conn.setRequestProperty("Authorization", secretKey);
            conn.setRequestProperty("Content-type", "application/json;charset=utf-8");
            conn.setDoOutput(true);

            // 승인 요청에 필요한 JSON 데이터
            String jsonParams = "{"
                    + "\"cid\":\"" + cid + "\","
                    + "\"tid\":\"" + tid + "\","
                    + "\"partner_order_id\":\"1001\","
                    + "\"partner_user_id\":\"unibridge\","
                    + "\"pg_token\":\"" + pgToken + "\""
                    + "}";

            try (OutputStream out = conn.getOutputStream()) {
                out.write(jsonParams.getBytes("utf-8"));
            }

            int code = conn.getResponseCode();
            System.out.println(">>> [승인 응답 코드]: " + code);

            if (code == 200) {
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
                String line;
                StringBuilder sb = new StringBuilder();
                while ((line = br.readLine()) != null) { sb.append(line); }
                
                System.out.println(">>> [결제 최종 성공 데이터]: " + sb.toString());
                
                session.removeAttribute("tid");
                
                System.out.println(">>> [8] 모든 처리 완료. 화면 이동 시작");
                
                // [수정] 실제 파일 경로와 이름(Finsih 오타 주의)을 정확히 입력합니다.
                String movePath = request.getContextPath() + "/app/user/mentorSearch/payment/paymentFinish.jsp";
                response.sendRedirect(movePath);
            } else {
                // [추가] 400, 500 에러 발생 시 상세 내용을 브라우저에 출력합니다.
                BufferedReader errorBr = new BufferedReader(new InputStreamReader(conn.getErrorStream(), "utf-8"));
                String errorMsg = errorBr.readLine();
                
                System.err.println(">>> [승인 에러 상세]: " + errorMsg);
                
                response.setContentType("text/html; charset=UTF-8");
                response.getWriter().print("<script>alert('승인 에러: " + errorMsg + "'); history.back();</script>");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}