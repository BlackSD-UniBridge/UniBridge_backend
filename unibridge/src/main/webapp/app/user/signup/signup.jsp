<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>회원가입</title>
    <link href="https://fonts.googleapis.com/css2?family=Playfair+Display:wght@600&family=Noto+Sans+KR:wght@300;400;500;700&display=swap" rel="stylesheet" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/user/signup/signup.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/header.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/footer.css" />
    <script defer src="${pageContext.request.contextPath}/assets/js/user/signup/signup.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/header.js" defer></script>
    <script src="${pageContext.request.contextPath}/assets/js/footer.js" defer></script>
</head>
<body>
    <div id="headerContainer">
    	<jsp:include page="/app/user/header.jsp"/>
    </div>
    <main>
        <div class="content">
            <div class="signUpBox">
                <div class="signUpTitle">회원가입</div>
                <form action="${pageContext.request.contextPath}/signup.mem" method="post" id="signUpForm">
                    <div class="inputGroup">
                        <label for="userId">아이디</label>
                        <input type="text" name="userId" class="userInput">
                        <button class="duplication">중복확인</button>
                    </div>
                    <div class="inputGroup">
                        <label for="userPw">비밀번호</label>
                        <input type="password" name="userPw" class="userInput">
                        <div class="spacer"></div>
                    </div>
                    <div class="inputGroup">
                        <label for="userPw2">비밀번호 확인</label>
                        <input type="password" name="userPw2" class="userInput">
                        <button class="duplication">확인</button>
                    </div>
                    <div class="inputGroup">
                        <label for="userNamme">이름</label>
                        <input type="text" name="userName" class="userInput">
                        <div class="spacer"></div>
                    </div>
                    <div class="inputGroup">
                        <label for="userNickname">닉네임</label>
                        <input type="text" name="userNickname" class="userInput">
                        <button class="duplication">중복확인</button>
                    </div>
                    <div class="inputGroup">
                        <label for="userPhone">전화번호</label>
                        <input type="text" name="userPhone" class="userInput">
                        <button class="duplication">인증발송</button>
                    </div>
                    <div class="inputGroup">
                        <label>인증번호</label>
                        <input type="text" class="userInput">
                        <button class="duplication">인증확인</button>
                    </div>
                    <div class="inputGroup">
                        <label>성별</label>
                        남성 <input type='radio' name='userGender' value='male' class="genderBtn" />
                        여성 <input type='radio' name='userGender' value='female' class="genderBtn"/>
                        선택안함 <input type='radio' name='userGender' value='none' class="genderBtn" checked />
                    </div>
                    <button type="submit" class="signUpBtn">회원가입</button>
                </form>
            </div>
        </div>
    </main>
</body>
</html>