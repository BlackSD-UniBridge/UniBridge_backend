<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>멘티 매칭 정보</title>
    <link href="https://fonts.googleapis.com/css2?family=Playfair+Display:wght@600&family=Noto+Sans+KR:wght@300;400;500;700&display=swap" rel="stylesheet" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/header.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/footer.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/user/mentee/myPage/userMatching/userMatching.css">
    <script defer src="${pageContext.request.contextPath}/assets/js/user/mentee/myPage/userMatching/userMatching.js"></script>
</head>
<body>
    
    <jsp:include page="/app/user/header.jsp" />

    <div class="mainContainer">
        <aside>
            <div class="myPageTitle">마이페이지</div>
            <ul>
                <li><a href="${pageContext.request.contextPath}/auth/mentee/myPage.my" >계정 관리</a></li>
                <li><a href="${pageContext.request.contextPath}/auth/mentee/survey.my">설문 조사</a></li>
                <li><a href="${pageContext.request.contextPath}/auth/mentee/log.my">결제 정보</a></li>
                <li><a href="${pageContext.request.contextPath}/auth/mentee/matching.my" class="active">매칭 정보</a></li>
                <li><a href="${pageContext.request.contextPath}/auth/mentee/delete.my">회원 탈퇴</a></li>
            </ul>
        </aside>
        <main>
            <div class="userManageTitle">
                <img src="${pageContext.request.contextPath}/assets/img/user/userProfile/matching.png" alt="프로필 아이콘">
                <div class="title">매칭 정보</div>
            </div>
            <div class="container"> 
                <div class="userTypeBox">
                    <div class="mentoring">
                        <div class="userText">
                            <label>멘토 이름</label>
                            <label>홍길동</label>
                        </div>
                        <div class="userText">
                            <label>멘티 이름</label>
                            <label>장발장</label>
                        </div>
                        <div class="userText">
                            <label>멘토링 과목</label>
                            <label>Java</label>
                        </div>
                        <div class="userText">
                            <label>멘토링 시작일</label>
                            <label>2025/02/28</label>
                        </div>
                        <div class="userText">
                            <label>학습보고서</label>
                            <button class="mentoringCheck" id="reportBtn">학습보고서 확인</button>
                        </div>
                    </div>
                    <button type="submit" class="matchingCancel" id="openModalBtn">매칭 취소 신청</button>
                </div>
            </div>
            <div id="matchingModal" class="matingCancel">
                <div class="cancelBox">
                    <button class="closeBtn"><img src="${pageContext.request.contextPath}/assets/img/user/userProfile/close.png" alt=""></button>
                    <div class="cacelTitle">매칭 취소 신청서</div>
                    <div class="cancelModalBox">
                        <form action="${pageContext.request.contextPath}/auth/mentor/matching.my" method="post">
                            <div class="infoGrid">
                                <div class="printRow">
                                    <label>멘토 이름</label>
                                    <div class="mentoringText">홍길동</div>
                                </div>
                                <div class="printRow">
                                    <label>멘티 이름</label>
                                    <div class="mentoringText">장발장</div>
                                </div>
                                <div class="printRow">
                                    <label>멘토링 과목</label>
                                    <div class="mentoringText">Java</div>
                                </div>
                                <div class="printRow">
                                    <label>시작일</label>
                                    <div class="mentoringText">2025/02/28</div>
                                </div>
                            </div>

                            <div class="cencelInputBox">
                                <div class="contextTitle">매칭취소 사유</div>
                                <textarea name="cancelReason" class="cencelIput"></textarea>
                            </div>

                            <div class="cancelFooter">
                                <button type="submit" class="submitBtn" id="sumbitBtn">취소 신청</button>
                                <button type="button" class="cancelBtn" id="closeModalBtn">취소</button>
                            </div>
                        </form>
                        
                    </div>
                </div>
            </div>
        </main>

    </div>

    <script src="${pageContext.request.contextPath}/assets/js/header.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/footer.js"></script>
</body>
</html>