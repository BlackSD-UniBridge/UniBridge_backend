<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>멘티 회원탈퇴</title>
    <link href="https://fonts.googleapis.com/css2?family=Playfair+Display:wght@600&family=Noto+Sans+KR:wght@300;400;500;700&display=swap" rel="stylesheet" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/header.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/footer.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/user/mentee/myPage/userDelete/userDelete.css">
    <script defer src="${pageContext.request.contextPath}/assets/js/user/mentee/myPage/userDelete/userDelete.js"></script>
</head>
<body>
    
    <div id="headerContainer"></div>

    <div class="mainContainer">
        <aside>
            <div class="myPageTitle">마이페이지</div>
            <ul>
                <li><a href="${pageContext.request.contextPath}/app/user/mentee/myPage/myPage.jsp" >계정 관리</a></li>
                <li><a href="${pageContext.request.contextPath}/app/user/mentee/myPage/userSurvey/userSurvey.jsp">설문 조사</a></li>
                <li><a href="${pageContext.request.contextPath}/app/user/mentee/myPage/userPayLog/payLog.jsp">결제 정보</a></li>
                <li><a href="${pageContext.request.contextPath}/app/user/mentee/myPage/userMatching/userMatching.jsp" >매칭 정보</a></li>
                <li><a href="${pageContext.request.contextPath}/app/user/mentee/myPage/userDelete/userDelete.jsp" class="active">회원탈퇴</a></li>
            </ul>
        </aside>
        <main>
            <div class="userManageTitle">
                <img src="${pageContext.request.contextPath}/assets/img/user/userProfile/userDelete.png" alt="회원탈퇴 아이콘">
                <div class="title">회원탈퇴</div>
            </div>
            <div class="userMsg">
                <ul>
                    <li>회원님이 탈퇴를 신청하신 경우, 추가적인 매칭은 진행되지 않습니다.</li>
                    <li>회원님이 탈퇴하더라도 기존 매칭 상대방이 회원 자격을 유지하고 있는 경우, 해당 매칭과 관련하여 작성·제공된 학습보고서는 내부 운영 정책에 따라 보관됩니다.</li>
                    <li>매칭이 진행 중인 상태에서 탈퇴를 희망하는 경우, 매칭은 자동으로 취소되지 않으므로, 반드시 매칭 취소 절차를 완료한 후 탈퇴를 신청하여야 합니다.</li>
                </ul>
            </div>
            <div class="userInputBox">
                <form id="deleteForm" action="">
                    <div class="inputGroupContainer">
                        <div class="inputGroup">
                            <label>아이디</label>
                            <input type="text" id="userId" class="userInput">
                            <div class="spacer"></div>
                        </div>
                        <div class="errorMsg" id="loginError"></div>
                    </div>
                    
                    <div class="inputGroupContainer">
                        <div class="inputGroup">
                            <label>비밀번호</label>
                            <input type="password" id="userPw" class="userInput">
                            <div class="spacer"></div>
                        </div>
                        <div class="spacer" id="space"></div>
                    </div>

                    <div class="inputGroupContainer">
                        <div class="inputGroup">
                            <label>전화번호</label>
                            <input type="text" id="userPhone" class="userInput">
                            <button type="button" class="duplication" id="sendSms">인증 번호 전송</button>
                        </div>
                        <div class="spacer" id="space"></div>
                    </div>

                    <div class="inputGroupContainer">
                        <div class="inputGroup">
                            <label>인증번호</label>
                            <input type="text" id="authCode" class="userInput">
                            <button type="button" class="duplication" id="verifySms">인증 확인</button>
                        </div>
                        <div class="errorMsg" id="authError"></div>
                    </div>

                    <button type="submit" class="userDeleteBtn" id="submitBtn" disabled>탈퇴 신청</button>
                </form>
            </div>
        </main>
    </div>

    <script src="${pageContext.request.contextPath}/assets/js/header.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/footer.js"></script>
</body>
</html>