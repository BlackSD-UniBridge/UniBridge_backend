<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>멘토 매칭 내역 없음</title>
    <link href="https://fonts.googleapis.com/css2?family=Playfair+Display:wght@600&family=Noto+Sans+KR:wght@300;400;500;700&display=swap" rel="stylesheet" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/header.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/footer.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/user/mentor/myPage/userMatching/nonMatching.css">
</head>
<body>
    
    <jsp:include page="/app/user/header.jsp" />

    <div class="mainContainer">
        
        <aside>
            <div class="myPageTitle">마이페이지</div>
            <ul>
                <li><a href="${pageContext.request.contextPath}/mvc/auth/mentor/myPage.my" >계정 관리</a></li>
                <li><a href="${pageContext.request.contextPath}/mvc/auth/mentor/survey.my">설문 조사</a></li>
                <li><a href="${pageContext.request.contextPath}/mvc/auth/mentor/matching.my" class="active">매칭 정보</a></li>
                <li><a href="${pageContext.request.contextPath}/mvc/auth/mentor/mentoringCreate.my">멘토링</a></li>
                <li><a href="${pageContext.request.contextPath}/mvc/auth/mentor/app/delete.my">회원 탈퇴</a></li>
            </ul>
        </aside>
        <main>
            <div class="userManageTitle">
                <img src="${pageContext.request.contextPath}/assets/img/user/userProfile/pay.png" alt="프로필 아이콘">
                <div class="title">결제 정보</div>
            </div>
            <div class="nonPayLog">
                매칭 내역이 없습니다.
            </div>
        </main>

    </div>

    <script src="${pageContext.request.contextPath}/assets/js/header.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/footer.js"></script>
</body>
</html>