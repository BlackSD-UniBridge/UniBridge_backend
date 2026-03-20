<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>UniBridge 관리자 로그인</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/admin/adminLogin/login.css" />
  <script  defer src="${pageContext.request.contextPath}/assets/js/admin/adminLogin/login.js"></script>
</head>
<body>
  <main class="login-page">
    <section class="login-card">
      <div class="login-top-icon" aria-hidden="true">
        <svg viewBox="0 0 24 24" fill="none">
          <rect x="5" y="10" width="14" height="10" rx="2" stroke="currentColor" stroke-width="1.8"/>
          <path d="M8 10V7.8C8 5.7 9.7 4 11.8 4h.4C14.3 4 16 5.7 16 7.8V10" stroke="currentColor" stroke-width="1.8" stroke-linecap="round"/>
          <circle cx="12" cy="14.6" r="1.2" fill="currentColor"/>
          <path d="M12 15.8V17.2" stroke="currentColor" stroke-width="1.6" stroke-linecap="round"/>
        </svg>
      </div>

      <h1 class="login-title">관리자 로그인</h1>
      <p class="login-subtitle">ADMIN PANEL</p>
      <div class="login-divider"></div>

      <form class="login-form" id="loginForm" method="post" action="${pageContext.request.contextPath}/loginOk.admin">
        <div class="input-wrap">
          <span class="input-icon" aria-hidden="true">
            <svg viewBox="0 0 24 24" fill="none">
              <circle cx="12" cy="8" r="4" stroke="currentColor" stroke-width="1.8"/>
              <path d="M5 19c1.4-3 4-4.5 7-4.5S17.6 16 19 19" stroke="currentColor" stroke-width="1.8" stroke-linecap="round"/>
            </svg>
          </span>
          <input type="text" id="adminId" name="adminId" placeholder="아이디를 입력하세요" autocomplete="username"/>
        </div>

        <div class="input-wrap">
          <span class="input-icon" aria-hidden="true">
            <svg viewBox="0 0 24 24" fill="none">
              <rect x="6" y="10" width="12" height="10" rx="2" stroke="currentColor" stroke-width="1.8"/>
              <path d="M9 10V8a3 3 0 116 0v2" stroke="currentColor" stroke-width="1.8" stroke-linecap="round"/>
              <circle cx="12" cy="15" r="1.1" fill="currentColor"/>
              <path d="M12 16.2V17.2" stroke="currentColor" stroke-width="1.4" stroke-linecap="round"/>
            </svg>
          </span>
          <input type="password" id="adminPw" name="adminPw"  placeholder="비밀번호를 입력하세요"/>
        </div>

        <p class="error-text" id="errorText"></p>

        <button type="submit" class="login-btn">로그인</button>
      </form>
    </section>
  </main>


</body>
</html>