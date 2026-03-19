<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>멘토링 수정 - UniBridge</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/fonts.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/user/mentor/myPage/userMentoring/mentoringForm.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/header.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/footer.css">
</head>
<body>
	<jsp:include page="/app/user/header.jsp" />

	<div class="mainContainer">
		<aside>
			<div class="myPageTitle">마이페이지</div>
			<ul>
				<li><a
					href="${pageContext.request.contextPath}/app/user/mentor/myPage/myPage.jsp">계정
						관리</a></li>
				<li><a
					href="${pageContext.request.contextPath}/app/user/mentor/myPage/userSurvey/userSurvey.jsp">설문
						조사</a></li>
				<li><a
					href="${pageContext.request.contextPath}/app/user/mentor/myPage/userMatching/userMatching.jsp">매칭
						정보</a></li>
				<li><a
					href="${pageContext.request.contextPath}/mvc/auth/mentor/myPage.my"
					class="active">멘토링</a></li>
				<li><a
					href="${pageContext.request.contextPath}/app/user/mentor/myPage/userDelete/userDelete.jsp">회원
						탈퇴</a></li>
			</ul>
		</aside>

		<main>
			<div class="userManageTitle">
				<img
					src="${pageContext.request.contextPath}/assets/img/user/userProfile/userIcon.png"
					alt="아이콘">
				<div class="title">멘토링 정보 수정</div>
			</div>

			<div id="contentsMain">
				<div id="mentoringBackground">
					<%-- [수정] action 경로 확인: .mo인지 .my인지 확인 필요 (일단 기존 흐름대로 유지) --%>
					<form
						action="${pageContext.request.contextPath}/mvc/auth/mentor/mentoringModifyOk.my"
						method="post" enctype="multipart/form-data">

						<%-- [수정] DTO 필드명에 맞춰 internalId 사용 --%>
						<input type="hidden" name="mentoringNumber"
							value="${mentoring.internalId}">

						<div id="mentoringMain">
							<div id="mentoring">
								<div id="mentoringTopics">
									<div class="subject">
										<label>학습 과목 번호</label> <input type="number"
											name="mentoringSubject" value="${mentoring.subjectNumber}"
											required>
									</div>
									<div class="title">
										<label>멘토링 제목</label> <input type="text" name="mentoringTitle"
											value="${mentoring.mentoringTitle}" required>
									</div>
									<div id="purpose">
										<label>수정할 목적</label>
										<%-- [수정] mentoringPurpose -> mentoringGoal --%>
										<textarea name="mentoringPurpose" required>${mentoring.mentoringGoal}</textarea>
									</div>
								</div>

								<div id="curriculum">
									<div id="text">
										<div>
											<label for="mentoringCurriculum">멘토링 커리큘럼 상세</label>
										</div>
										<%-- [수정] curriculum -> mentoringDetail --%>
										<textarea id="mentoringCurriculum" name="mentoringCurriculum"
											required>${mentoring.mentoringDetail}</textarea>
									</div>
									<div id="file">
										<div id="curriculumFileTitle">파일 수정 (기존 파일 존재)</div>
										<input type="file" id="curriculumFile" name="mentoringFile">
									</div>
								</div>
							</div>

							<div id="profile">
								<a href="#"> <img
									src="${pageContext.request.contextPath}/assets/img/user/userProfile/userIcon.png"
									alt="아이콘"> <img id="profileImg"
									src="${pageContext.request.contextPath}/upload/${not empty mentoring.fileName ? mentoring.fileName : 'default.png'}"
									onerror="this.src='${pageContext.request.contextPath}/assets/img/user/userProfile/ex1.png'"
									alt="프로필">
								</a>
							</div>
						</div>

						<div id="buttons">
							<button type="submit">수정 완료</button>
							<button type="button" style="color: #475569;"
								onclick="history.back()">취소</button>
							<button type="button" id="delBtn"
								style="border-color: #ff4d4d; color: #ff4d4d;"
								onclick="deleteMentoring(${mentoring.internalId})">삭제하기</button>
						</div>
					</form>
				</div>
			</div>
		</main>
	</div>

	<jsp:include page="/app/user/footer.jsp" />
	<script>
    function deleteMentoring(internalId) {
        if(confirm("정말로 이 멘토링을 삭제하시겠습니까?")) {
            location.href = "${pageContext.request.contextPath}/mvc/auth/mentor/mentoringDeleteOk.my?mentoringNumber=" + internalId;
        }
    }
</script>
</body>
</html>