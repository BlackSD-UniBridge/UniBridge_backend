<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://fonts.googleapis.com/css2?family=Playfair+Display:wght@600&family=Noto+Sans+KR:wght@300;400;500;700&display=swap" rel="stylesheet" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/header.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/footer.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/user/signin/signin.css">
    <script defer src="${pageContext.request.contextPath}/assets/js/header.js"></script>
    <script defer src="${pageContext.request.contextPath}/assets/js/footer.js"></script>
    <script defer src="${pageContext.request.contextPath}/assets/js/user/signin/signin.js"></script>
	<title>login</title>
</head>
<body>
    <div id="headerContainer">
    	<jsp:include page="/app/user/header.jsp"/>
    </div>
    <main>
        <div class="content">
            <div class="loginBox">
                <div class="loginTitle">로그인</div>
                <form action="${pageContext.request.contextPath}/signin.mem" method="post" id="login">
                    <div class="inputGroup">
                        <label for="userId">아이디</label>
                        <input type="text" id="userId" name="userId" class="userInput">
                    </div>
                    <div class="inputGroup">
                        <label for="userPw">비밀번호</label>
                        <input type="password" id="userPw" name="userPw" class="userInput">
                    </div>
                    <div id="errorMsg" class="errorMsg">${loginError}</div>
                    <button type="submit" class="loginBtn">로그인</button>
                </form>
                <a href="${pageContext.request.contextPath}/signup.mem">
                	<div class="signup">회원가입</div>
                </a>
            </div>
        </div>
    </main>
</body>
</html>