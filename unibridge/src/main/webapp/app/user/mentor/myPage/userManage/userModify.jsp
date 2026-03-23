<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>멘토 수정페이지</title>
    <link href="https://fonts.googleapis.com/css2?family=Playfair+Display:wght@600&family=Noto+Sans+KR:wght@300;400;500;700&display=swap" rel="stylesheet" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/user/mentor/myPage/userManage/userModify.css">
    <script>
	    window.contextPath = "${pageContext.request.contextPath}";
	    // 서버에서 전달받은 업데이트 상태를 전역 변수로 저장
	    window.SERVER_UPDATE_STATUS = "${updateStatus}";
	</script>
    <script defer src="${pageContext.request.contextPath}/assets/js/user/mentor/myPage/userManage/userModify.js"></script>
</head>
<body>
    <jsp:include page="/app/user/header.jsp" />
    
    <div class="mainContainer">
        <aside>
            <div class="myPageTitle">마이페이지</div>
            <ul>
                <li><a href="${pageContext.request.contextPath}/mvc/auth/mentor/myPage.my" class="active">계정 관리</a></li>
                <li><a href="${pageContext.request.contextPath}/mvc/auth/mentor/survey.my">설문 조사</a></li>
                <li><a href="${pageContext.request.contextPath}/mvc/auth/mentor/matching.my">매칭 정보</a></li>
                <li><a href="${pageContext.request.contextPath}/mvc/auth/mentor/mentoring.my">멘토링</a></li>
                <li><a href="${pageContext.request.contextPath}/mvc/auth/mentor/delete.my">회원 탈퇴</a></li>
            </ul>
        </aside>
        <main>
            <div class="userManageTitle">
                <img src="${pageContext.request.contextPath}/assets/img/user/userProfile/userIcon.png" alt="프로필 아이콘">
                <div class="title">계정관리</div>
            </div>
            <div class="userMain">
                <div class="userImg">
				    <img src="/upload/profile/${member.MEMBER_IMG != null ? member.MEMBER_IMG : 'default.png'}?t=<%=System.currentTimeMillis()%>" 
					     alt="유저의 프로필 사진" 
					     id="profileDisplay"
					     onerror="this.onerror=null; this.src='${pageContext.request.contextPath}/assets/img/user/userProfile/default.png';">
				    
				    <form action="${pageContext.request.contextPath}/mvc/auth/mentor/updateOk.my" method="post" enctype="multipart/form-data" id="imageForm">
				        <input type="hidden" name="updateType" value="profileImg">
				        <input type="file" name="profileFile" id="profileFileInput" style="display: none;" accept="image/*">
				        <button type="button" id="imgBtn" onclick="document.getElementById('profileFileInput').click();">사진 변경</button>
				    </form>
				    <div class="errorMsg" id="imageError"></div>
				</div>
                <div class="modifyForm">
                    <div class="inputGroup">
                        <label>이름</label>
                        <label>${member.MEMBER_NAME}</label>
                        <div class="spacer"></div>
                        <div class="spacer"></div>
                    </div>
    
                    <div class="inputGroup">
                        <label>아이디</label>
                        <label>${member.MEMBER_ID}</label>
                        <div class="spacer"></div>
                        <div class="spacer"></div>
                    </div>

                    <form action="${pageContext.request.contextPath}/mvc/auth/mentor/updateOk.my" method="post" id="nickForm">
					    <input type="hidden" name="updateType" value="nickname">
					    <div class="inputGroup">
					        <label>닉네임</label>
					        <input type="text" class="userInput" name="memberNickname" id="memberNickname" value="${member.MEMBER_NICKNAME}">
					        <button type="button" class="duplication" id="nickCheckBtn" onclick="checkNick()">중복확인</button>
					        <button type="submit" class="change">변경</button> 
					        <div class="errorMsg" id="nickErrorMsg"></div> 
					    </div>
					</form>
					
					<form action="${pageContext.request.contextPath}/mvc/auth/mentor/updateOk.my" method="post" id="pwForm">
					    <input type="hidden" name="updateType" value="password">
					    <div class="inputGroup">
					        <label>변경할 비밀번호</label>
					        <input type="password" class="userInput" name="newPw" id="newPw">
					        <div class="errorMsg"></div>
					    </div>
					    <div class="inputGroup">
					        <label>비밀번호 확인</label>
					        <input type="password" class="userInput" name="newPwConfirm" id="newPwConfirm">
					        <button type="button" class="duplication" onclick="checkPwMatch()">확인</button>
					        <button type="submit" class="change">변경</button>
					        <div class="errorMsg" id="pwErrorMsg"></div>
					    </div>
					</form>

                    <form action="${pageContext.request.contextPath}/mvc/auth/mentor/updateOk.my" method="post" id="phoneForm">
					    <input type="hidden" name="updateType" value="phone">
					    
					    <div class="inputGroup">
					        <label>전화번호</label>
					        <input type="text" class="userInput" name="memberPhone" id="memberPhone" value="${member.MEMBER_PHONE}">
					        <button type="button" class="authBtn" onclick="sendSms()">인증번호전송</button>
					        <div class="spacer"></div>
					        <div class="errorMsg" id="phoneSendError" style="color: red;">${phoneError}</div>
					    </div>
					
					    <div class="inputGroup">
					        <label>인증번호</label>
					        <input type="text" class="userInput" id="authCodeInput">
					        <button type="button" class="duplication" onclick="verifyCode()">확인</button>
					        <button type="submit" class="change">전화번호변경</button>
					        <div class="errorMsg" id="authCodeError"></div>
					    </div>
					</form>

                    <form action="${pageContext.request.contextPath}/mvc/auth/mentor/updateOk.my" method="post">
					    <input type="hidden" name="updateType" value="gender"> <div class="inputGroup">
					        <label>성별</label>
					        <div class="radio-group">
					            <input type="radio" value="M" name="memberGender" ${member.MEMBER_GENDER == 'M' ? 'checked' : ''}> 남성
					            <input type="radio" value="W" name="memberGender" ${member.MEMBER_GENDER == 'W' ? 'checked' : ''}> 여성
					            <input type="radio" value="N" name="memberGender" ${member.MEMBER_GENDER == 'N' ? 'checked' : ''}> 미정
					        </div>
					        <button type="submit" class="change">변경</button> </div>
					</form>
                </div>
            </div>
            <button class="userModifyBtn">완료</button>
        </main>
        <script>
		    // 외부 JS 파일에서 사용할 수 있도록 전역 변수에 할당
		    const contextPath = "${pageContext.request.contextPath}";
		    const SERVER_UPDATE_STATUS = "${updateStatus}";
		</script>
    </div>
</body>
</html>