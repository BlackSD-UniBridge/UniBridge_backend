<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>미정 회원정보 변경전 확인</title>
    <link href="https://fonts.googleapis.com/css2?family=Playfair+Display:wght@600&family=Noto+Sans+KR:wght@300;400;500;700&display=swap" rel="stylesheet" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/header.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/footer.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/user/undetermined/myPage/userManage/userModifyCheck.css">
    <script defer src="${pageContext.request.contextPath}/assets/js/user/undetermined/myPage/userManage/userModifyCheck.js"></script>
</head>
<body>
    
    <jsp:include page="/app/user/header.jsp" />

    <div class="mainContainer">
        <aside>
            <div class="myPageTitle">마이페이지</div>
            <ul>
                <li><a href="${pageContext.request.contextPath}/mvc/auth/undecided/myPage.my" class="active">계정 관리</a></li>
                <li><a href="${pageContext.request.contextPath}/mvc/auth/undecided/survey.my">설문 조사</a></li>
                <li><a href="${pageContext.request.contextPath}/mvc/auth/undecided/delete.my">회원 탈퇴</a></li>
            </ul>
        </aside>
        <main>
            <div class="userManageTitle">
                <img src="${pageContext.request.contextPath}/assets/img/user/userProfile/userIcon.png" alt="프로필 아이콘">
                <div class="title">계정관리</div>
            </div>
            <div class="userMain">
                <div class="modifyForm">
                    <form action="${pageContext.request.contextPath}/mvc/auth/undecided/verifySubmit.my" method="POST" id="goUserModify">

                        <%-- 현재 비밀번호: spacer로 버튼 자리 확보 → input 너비 통일 --%>
                        <div class="inputGroup">
                            <label>현재 비밀번호</label>
                            <div class="inputRow">
                                <input type="password" name="password" class="userInput" required>
                                <div class="spacer"></div>
                            </div>
                            <c:if test="${not empty pwError}">
                                <div class="errorText">${pwError}</div>
                            </c:if>
                        </div>

                        <%-- ✅ 전화번호: 입력창 + 버튼 한 줄, 에러는 inputRow 바로 아래 --%>
                        <div class="inputGroup">
                            <label>전화번호</label>
                            <div class="inputRow">
                                <input type="tel" name="phoneNumber" class="userInput" id="phoneNum"
                                       placeholder="휴대폰 번호 (숫자만 입력)" required>
                                <button type="button" class="duplication" id="sendSmsBtn">인증번호전송</button>
                            </div>
                            <c:if test="${not empty authError}">
                                <div class="errorText">${authError}</div>
                            </c:if>
                        </div>

                        <%-- 인증번호: 입력창 + 버튼 한 줄, 에러는 inputRow 바로 아래 --%>
                        <div class="inputGroup">
                            <label>인증번호</label>
                            <div class="inputRow">
                                <input type="text" name="authCode" class="userInput" id="authNum"
                                       placeholder="인증번호 입력">
                                <button type="button" class="duplication" id="verifySmsBtn">인증확인</button>
                            </div>
                            <div id="verifyFailMsg" class="errorText" style="display: none;">인증에 실패하였습니다.</div>
                        </div>
                    </form>
                </div>
            </div>
            <button type="submit" form="goUserModify" class="userModifyBtn">정보 수정</button>
        </main>

    </div>
</body>
</html>