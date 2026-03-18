<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>멘토 마이페이지</title>
    <link href="https://fonts.googleapis.com/css2?family=Playfair+Display:wght@600&family=Noto+Sans+KR:wght@300;400;500;700&display=swap" rel="stylesheet" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/header.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/footer.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/user/mentor/myPage/myPage.css" />
    <script src="${pageContext.request.contextPath}/assets/js/header.js" defer></script>
    <script src="${pageContext.request.contextPath}/assets/js/footer.js" defer></script>
</head>
<body>

    <jsp:include page="/app/user/header.jsp" />

    <div class="mainContainer">
        <aside>
            <div class="myPageTitle">마이페이지</div>
            <ul>
                <li><a href="${pageContext.request.contextPath}auth/mentor/myPage.my" class="active">계정 관리</a></li>
                <li><a href="${pageContext.request.contextPath}auth/mentor/survey.my">설문 조사</a></li>
                <li><a href="${pageContext.request.contextPath}auth/mentor/matching.my">매칭 정보</a></li>
                <li><a href="${pageContext.request.contextPath}/auth/mentor/mentoringCreate.my">멘토링</a></li>
                <li><a href="${pageContext.request.contextPath}auth/mentor/app/delete.my">회원 탈퇴</a></li>
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
                        <div class="userText">${sessionScope.memberId}</div>
                    </div>
                    <div class="userTextGroup">
                        <label for="">이름</label>
                        <div class="userText">${sessionScope.memberName}</div>
                    </div>
                    <div class="userTextGroup">
                        <label for="">닉네임</label>
                        <div class="userText">${sessionScope.memberNickname}</div>
                    </div>
                    <div class="userTextGroup">
                        <label for="">성별</label>
                        <div class="userText">${sessionScope.memberGender}</div>
                    </div>
                    <div class="userTextGroup">
                        <label for="">전화번호</label>
                        <div class="userText">${sessionScope.memberPhone}</div>
                    </div>
                </div>
            </div>

            <form action="${pageContext.request.contextPath}/app/user/mentor/myPage/userManage/userModifyCheck.jsp">
                <button class="userModifyBtn">수정</button>
            </form>
        </main>

    </div>


</body>
</html>