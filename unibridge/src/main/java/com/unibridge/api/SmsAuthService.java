package com.unibridge.api;

import java.io.BufferedReader;
import java.io.InputStreamReader;
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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.google.gson.JsonObject;
import com.unibridge.app.pay.controller.ConfigReader;

/**
 * 휴대폰 문자 인증 전용 서비스 클래스
 */
public class SmsAuthService {

	/**
	 * [기능] 인증번호 생성 및 문자 발송 [과정] 1. 파라미터로 넘어온 핸드폰 번호의 유효성(형식)을 검사합니다. 2. 6자리의
	 * 난수(인증코드)를 생성합니다. 3. 추후 검증을 위해 생성된 코드를 사용자의 세션(Session)에 저장합니다. 4. 솔라피 API를
	 * 호출하여 실제로 문자를 발송합니다.
	 */
	public boolean sendSms(HttpServletRequest request, String phoneNumber) {
		// [Step 1] 전화번호 형식 검증 (010으로 시작하는 11자리 숫자)
		if (phoneNumber == null || !phoneNumber.matches("^010\\d{8}$")) {
			return false;
		}

		// [Step 2] 6자리 랜덤 인증번호 생성
		String authCode = String.format("%06d", new Random().nextInt(1000000));

		// [Step 3] 세션에 인증번호 저장 (30분 기본 유지)
		HttpSession session = request.getSession();
		session.setAttribute("serverAuthCode", authCode);

		// [Step 4] 외부 API(Solapi)를 통한 실제 발송 처리
		return callSolapi(phoneNumber, authCode);
	}

	/**
	 * [기능] 사용자가 입력한 인증번호 검증 [과정] 1. 세션에 저장해두었던 '서버측 인증번호'를 꺼내옵니다. 2. 보안을 위해 한 번 확인된
	 * 세션의 인증번호는 즉시 삭제합니다. (재시도 방지) 3. 사용자가 입력한 값과 서버의 값이 일치하는지 비교합니다. 4. 일치할 경우 세션에
	 * '인증완료 플래그'를 저장하고 결과를 반환합니다.
	 */
	public String verifyCode(HttpServletRequest request, String userCode) {
		HttpSession session = request.getSession();

		// [Step 1] 세션에서 원본 인증번호 획득
		String serverCode = (String) session.getAttribute("serverAuthCode");

		// [Step 2] 보안 처리: 확인 절차 시작과 동시에 세션 데이터 파기 (단 1회만 비교 가능)
		session.removeAttribute("serverAuthCode");

		// [Step 3] 값 비교 및 최종 처리
		if (serverCode != null && serverCode.equals(userCode)) {
			// [Step 4] 인증 성공 시, 회원가입 완료 전까지 유효한 인증 플래그 생성
			session.setAttribute("isPhoneVerified", true);
			return "verified";
		}

		return "invalid";
	}

	/**
	 * [기능] 솔라피 API 호출 (실제 문자 전송 통신) [과정] 1. ConfigReader를 통해 API Key 등 설정 정보를
	 * 읽어옵니다. 2. API 보안을 위한 HMAC-SHA256 시그니처를 생성합니다. 3. Solapi 규격에 맞는 JSON 데이터(수신번호,
	 * 발신번호, 본문)를 생성합니다. 4. HTTP POST 연결을 통해 데이터를 전송하고 응답 코드(200 OK)를 확인합니다.
	 */
	private boolean callSolapi(String to, String code) {
		try {
			// 1. 설정값 로드
			String apiKey = ConfigReader.getProperty("api.apiKey");
			String apiSecret = ConfigReader.getProperty("api.apiSecret");
			String from = ConfigReader.getProperty("api.fromNumber");

			// 2. 인증 시그니처 생성 (Solapi 보안 요구사항)
			String salt = UUID.randomUUID().toString().replaceAll("-", "");
			String date = ZonedDateTime.now(ZoneId.of("Asia/Seoul")).format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);

			Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
			sha256_HMAC.init(new SecretKeySpec(apiSecret.getBytes(StandardCharsets.UTF_8), "HmacSHA256"));
			String signature = String.format("%064x",
					new java.math.BigInteger(1, sha256_HMAC.doFinal((date + salt).getBytes(StandardCharsets.UTF_8))));

			// ================= [콘솔 출력 시작] =================
			System.out.println("\n========== [Solapi 전송 데이터 확인] ==========");
			System.out.println("1. 생성된 인증번호: " + code);
			System.out.println("2. API Key: " + apiKey);
			System.out.println("3. Signature: " + signature);
			System.out.println("4. Salt: " + salt);
			System.out.println("5. Date: " + date);
			System.out.println("6. 발신번호(From): " + from);
			System.out.println("7. 수신번호(To): " + to);
			System.out.println("==============================================\n");
			// ================= [콘솔 출력 끝] =================

			// 3. API 서버 연결 설정
			URL url = new URL("https://api.solapi.com/messages/v4/send");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Authorization",
					"HMAC-SHA256 apiKey=" + apiKey + ", date=" + date + ", salt=" + salt + ", signature=" + signature);
			conn.setRequestProperty("Content-Type", "application/json; charset=utf-8");
			conn.setDoOutput(true);

			// 4. 전송 데이터(JSON) 구성
			JsonObject msg = new JsonObject();
			msg.addProperty("to", to);
			msg.addProperty("from", from);
			msg.addProperty("text", "[UniBridge] 인증번호는 [" + code + "] 입니다.");

			JsonObject root = new JsonObject();
			root.add("message", msg);

			// 5. 실제 데이터 전송
			try (OutputStream os = conn.getOutputStream()) {
				os.write(root.toString().getBytes(StandardCharsets.UTF_8));
			}

			int responseCode = conn.getResponseCode();
			System.out.println("[Solapi 결과] HTTP 응답 코드: " + responseCode);

			if (responseCode != 200) {
				// 에러 발생 시 서버가 보내준 상세 메시지 읽기
				try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getErrorStream()))) {
					String line;
					while ((line = br.readLine()) != null) {
						// 상세한 에러 사유를 출력
						System.out.println("[Solapi Error Details]: " + line);
					}
				}
			}

			// 6. 결과 반환 (HTTP 200 성공 여부)
			return conn.getResponseCode() == 200;
		} catch (Exception e) {
			System.out.println("[Error] Solapi 발송 중 예외 발생!");
			e.printStackTrace(); // 콘솔에 찍히는 에러 내용을 반드시 확인해야 합니다.
			return false;
		}
	}
}