<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>멘토 수정페이지</title>
    <link href="https://fonts.googleapis.com/css2?family=Playfair+Display:wght@600&family=Noto+Sans+KR:wght@300;400;500;700&display=swap" rel="stylesheet" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/header.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/footer.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/user/mentor/myPage/userManage/userModify.css">
    <script defer src="${pageContext.request.contextPath}/assets/js/user/mentor/myPage/userManage/userModify.js"></script>
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
                <div class="userImg">
                    <img src="${pageContext.request.contextPath}/assets/img/user/userProfile/ex1.png" alt="유저의 프로필 사진">
                    <button id="imgBtn">사진 변경</button>
                    <div class="errorMsg"></div>
                </div>
                <div class="modifyForm">
                    <div class="inputGroup">
                        <label>이름</label>
                        <label>홍길동</label>
                        <div class="spacer"></div>
                        <div class="spacer"></div>
                    </div>
    
                    <div class="inputGroup">
                        <label>아이디</label>
                        <label>hongdong11</label>
                        <div class="spacer"></div>
                        <div class="spacer"></div>
                    </div>

                    <div class="inputGroup">
                        <label>닉네임</label>
                        <input type="text" class="userInput">
                        <button class="duplication">중복확인</button>
                        <button class="change">변경</button>
                        <div class="errorMsg"></div>
                    </div>

                    <div class="inputGroup">
                        <label>변경할 비밀번호</label>
                        <input type="text" class="userInput">
                        <div class="spacer"></div>
                        <div class="spacer"></div>
                    </div>

                    <div class="inputGroup">
                        <label>비밀번호 확인</label>
                        <input type="password" class="userInput">
                        <button class="duplication">확인</button>
                        <button class="change">변경</button>
                        <div class="errorMsg"></div>
                    </div>

                    <div class="inputGroup">
                        <label>전화번호</label>
                        <input type="text" class="userInput">
                        <button class="authBtn">인증번호전송</button>
                        <div class="spacer2"></div>
                        <div class="errorMsg"></div>
                    </div>

                    <div class="inputGroup">
                        <label>인증번호</label>
                        <input type="text" class="userInput">
                        <button class="duplication">확인</button>
                        <button class="change">전호번호변경</button>
                        <div class="errorMsg"></div>
                    </div>

                    <div class="inputGroup">
                        <label>성별</label>
                        <div class="radio-group">
                            <span class="radioItem">
                                <span>남성</span> <input type="radio" value="M" name="role" class="radioUserType"> 
                            </span>
                            <span class="radioItem">
                                <span>여성</span> <input type="radio" value="F" name="role" class="radioUserType"> 
                            </span>
                            <span class="radioItem">
                                <span>선택안함</span> <input type="radio" value="No" name="role" class="radioUserType" checked> 
                            </span>
                        </div>
                        <button class="change">변경</button>
                    </div>
                </div>
            </div>
            <button class="userModifyBtn">완료</button>
        </main>

    </div>

    <script src="${pageContext.request.contextPath}/assets/js/header.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/footer.js"></script>
</body>
</html>