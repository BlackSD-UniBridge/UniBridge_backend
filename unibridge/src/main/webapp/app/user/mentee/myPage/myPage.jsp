<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>멘티 마이페이지</title>
    <link href="https://fonts.googleapis.com/css2?family=Playfair+Display:wght@600&family=Noto+Sans+KR:wght@300;400;500;700&display=swap" rel="stylesheet" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/header.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/footer.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/user/mentee/myPage/myPage.css">
</head>
<body>

    <div id="headerContainer"></div>

    <div class="mainContainer">
        <aside>
            <div class="myPageTitle">마이페이지</div>
            <ul>
                <li><a href="${pageContext.request.contextPath}/app/user/mentee/myPage/myPage.jsp" class="active">계정 관리</a></li>
                <li><a href="${pageContext.request.contextPath}/app/user/mentee/myPage/userSurvey/userSurvey.jsp">설문 조사</a></li>
                <li><a href="${pageContext.request.contextPath}/app/user/mentee/myPage/userPayLog/payLog.jsp">결제 정보</a></li>
                <li><a href="${pageContext.request.contextPath}/app/user/mentee/myPage/userMatching/userMatching.jsp">매칭 정보</a></li>
                <li><a href="${pageContext.request.contextPath}/app/user/mentee/myPage/userDelete/userDelete.jsp">회원 탈퇴</a></li>
            </ul>
        </aside>

        <main>
            <div class="userManageTitle">
                <img src="${pageContext.request.contextPath}/assets/img/user/userProfile/userIcon.png" alt="프로필 아이콘">
                <div class="title">계정관리</div>
            </div>

            <div class="userMain">
                <img src="${pageContext.request.contextPath}/assets/img/user/userProfile/ex1.png" alt="유저의 프로필 사진">
                <div class="userProfile">
                    <div class="userTextGroup">
                        <label for="">아이디</label>
                        <div class="userText">hongdong11</div>
                    </div>
                    <div class="userTextGroup">
                        <label for="">이름</label>
                        <div class="userText">홍길동</div>
                    </div>
                    <div class="userTextGroup">
                        <label for="">닉네임</label>
                        <div class="userText">홍길동</div>
                    </div>
                    <div class="userTextGroup">
                        <label for="">성별</label>
                        <div class="userText">남성</div>
                    </div>
                    <div class="userTextGroup">
                        <label for="">전화번호</label>
                        <div class="userText">010-1234-5678</div>
                    </div>
                </div>
            </div>

            <form action="${pageContext.request.contextPath}/app/user/mentee/myPage/userManage/userModifyCheck.jsp">
                <button class="userModifyBtn">수정</button>
            </form>
        </main>

    </div>

    <script src="${pageContext.request.contextPath}/assets/js/header.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/footer.js"></script>
    
</body>
</html>