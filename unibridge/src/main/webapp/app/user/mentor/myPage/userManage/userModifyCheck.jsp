<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>멘토 인증페이지</title>
    <link href="https://fonts.googleapis.com/css2?family=Playfair+Display:wght@600&family=Noto+Sans+KR:wght@300;400;500;700&display=swap" rel="stylesheet" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/header.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/footer.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/user/mentor/myPage/userManage/userModifyCheck.css">
    <script defer src="${pageContext.request.contextPath}/assets/js/user/mentor/myPage/userManage/userModifyCheck.js"></script>
</head>
<body>

    <div id="headerContainer"></div>

    <div class="mainContainer">
        <aside>
            <div class="myPageTitle">마이페이지</div>
            <ul>
                <li><a href="${pageContext.request.contextPath}/app/user/mentor/myPage/myPage.jsp" class="active">계정 관리</a></li>
                <li><a href="${pageContext.request.contextPath}/app/user/mentor/myPage/userSurvey/userSurvey.jsp">설문 조사</a></li>
                <li><a href="${pageContext.request.contextPath}/app/user/mentor/myPage/userMatching/userMatching.jsp">매칭 정보</a></li>
                <li><a href="${pageContext.request.contextPath}/app/user/mentor/myPage/userMentoCreate/mentoringCreate.jsp">멘토링</a></li>
                <li><a href="${pageContext.request.contextPath}/app/user/mentor/myPage/userDelete/userDelete.jsp">회원 탈퇴</a></li>
            </ul>
        </aside>
        <main>
            <div class="userManageTitle">
                <img src="${pageContext.request.contextPath}/assets/img/user/userProfile/userIcon.png" alt="프로필 아이콘">
                <div class="title">계정관리</div>
            </div>
            <div class="userMain">
                <div class="modifyForm">
                    <form action="" id="goUserModify">
                        <div class="inputGroup">
                            <label>현재 비밀번호</label>
                            <input type="text" class="userInput">
                            <div class="spacer"></div>
                        </div>
        
                        <div class="inputGroup">
                            <label>전화번호</label>
                            <input type="text" class="userInput">
                            <button class="duplication">인증 번호 전송</button>
                        </div>
    
                        <div class="inputGroup">
                            <label>인증번호</label>
                            <input type="text" class="userInput">
                            <button class="duplication">인증 확인</button>
                        </div>
                    </form>
                </div>
            </div>
            
            <button class="userModifyBtn" form="goUserModify">정보 수정</button>

        </main>

    </div>

    <script src="${pageContext.request.contextPath}/assets/js/header.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/footer.js"></script>
</body>
</html>