package com.unibridge.app.member.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.UUID;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.unibridge.app.Execute;
import com.unibridge.app.Result;
import com.unibridge.app.pay.controller.ConfigReader;

public class MentorVerifyActionController implements Execute{
	
	
	@Override
    public Result execute(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
		String mode = request.getParameter("mode");
        response.setContentType("text/plain; charset=utf-8");

        if ("send".equals(mode)) {
            // 1. 인증번호 발송 처리
            doSendSms(request, response);
        } else if ("check".equals(mode)) {
            // 2. 인증번호 일치 확인 (JSON 처리)
            doVerifyCode(request, response);
        }

        // AJAX 요청이므로 Result(페이지 이동 정보)를 반환하지 않음
        return null;

	}
	// [1] 문자 발송 로직
    private void doSendSms(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	String phoneNumber = request.getParameter("phoneNumber");
        
        // 서버측 검증: 010으로 시작하는 숫자 11자리만 허용
        if (phoneNumber == null || !phoneNumber.matches("^010\\d{8}$")) {
            response.getWriter().write("invalid_format");
            return;
        }

        String authCode = String.format("%06d", new Random().nextInt(1000000));
        
        HttpSession session = request.getSession();
        session.setAttribute("serverAuthCode", authCode);

        boolean isSent = requestSendApi(phoneNumber, authCode);
        response.getWriter().write(isSent ? "success" : "fail");
    }

    // [2] 인증번호 확인 로직 (JSON 수신)
    private void doVerifyCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = request.getReader()) {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        }

        Gson gson = new Gson();
        JsonObject jsonBody = gson.fromJson(sb.toString(), JsonObject.class);
        String userCode = jsonBody.get("authCode").getAsString();
        
        HttpSession session = request.getSession();
        
        // 1. 세션에서 코드를 꺼냅니다.
        String serverCode = (String) session.getAttribute("serverAuthCode");
        System.out.println("serverCode : "+ serverCode);

        // 2. [핵심] 꺼내자마자 세션에서 삭제합니다. (딱 한 번만 비교 가능하게 함)
        session.removeAttribute("serverAuthCode"); 

        // 3. 비교 로직 진행
        if (serverCode != null && serverCode.equals(userCode)) {
            // 인증 성공 시에만 '인증완료' 플래그를 세션에 남깁니다.
            session.setAttribute("isPhoneVerified", true);
            response.getWriter().write("verified");
        } else {
            // 코드가 틀렸거나, 이미 삭제되었거나, 세션이 만료된 경우
            response.getWriter().write("invalid");
        }
    }

    // 솔라피 API 호출 메서드 (동일 로직)
    private boolean requestSendApi(String to, String code) {
	        try {
	            String apiKey = ConfigReader.getProperty("solapi.api.key"); //
	            String apiSecret = ConfigReader.getProperty("solapi.api.secret"); //
	            String from = ConfigReader.getProperty("solapi.from.number"); //
	            
	            // 로그 출력으로 값 확인 (운영 시에는 삭제)
	            System.out.println("[Debug] API Key: " + apiKey);
	            System.out.println("[Debug] From Number: " + from);
	
	            String salt = UUID.randomUUID().toString().replaceAll("-", ""); //
	            String date = ZonedDateTime.now(ZoneId.of("Asia/Seoul")).format(DateTimeFormatter.ISO_OFFSET_DATE_TIME); //
	            
	            Mac sha256_HMAC = Mac.getInstance("HmacSHA256"); //
	            sha256_HMAC.init(new SecretKeySpec(apiSecret.getBytes(StandardCharsets.UTF_8), "HmacSHA256")); //
	            String signature = String.format("%064x", new java.math.BigInteger(1, sha256_HMAC.doFinal((date + salt).getBytes(StandardCharsets.UTF_8)))); //
	
	            URL url = new URL("https://api.solapi.com/messages/v4/send"); //
	            HttpURLConnection conn = (HttpURLConnection) url.openConnection(); //
	            conn.setRequestMethod("POST"); //
	            conn.setRequestProperty("Authorization", "HMAC-SHA256 apiKey=" + apiKey + ", date=" + date + ", salt=" + salt + ", signature=" + signature); //
	            conn.setRequestProperty("Content-Type", "application/json; charset=utf-8"); //
	            conn.setDoOutput(true); //
	
	            JsonObject msg = new JsonObject(); //
	            msg.addProperty("to", to); //
	            msg.addProperty("from", from); //
	            msg.addProperty("text", "[UniBridge] 인증번호는 [" + code + "] 입니다."); //
	            
	            JsonObject root = new JsonObject(); //
	            root.add("message", msg); //
	
	            try (OutputStream os = conn.getOutputStream()) { //
	                os.write(root.toString().getBytes(StandardCharsets.UTF_8)); //
	            }
	            
	            int responseCode = conn.getResponseCode();
	            System.out.println("[Debug] Solapi Response Code: " + responseCode);
	            
	            // 200이 아니면 실패 원인을 응답 본문에서 읽어와야 함
	            return responseCode == 200;
	//            return conn.getResponseCode() == 200; 
	        } catch (Exception e) {
	            e.printStackTrace();
	            return false;
	        }
    	}
    }