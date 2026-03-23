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
					href="${pageContext.request.contextPath}/mvc/auth/mentor/myPage.my">계정
						관리</a></li>
				<li><a
					href="${pageContext.request.contextPath}/mvc/auth/mentor/survey.my">설문
						조사</a></li>
				<li><a
					href="${pageContext.request.contextPath}/mvc/auth/mentor/matching.my">매칭
						정보</a></li>
				<li><a
					href="${pageContext.request.contextPath}/mvc/auth/mentor/mentoring.my"
					class="active">멘토링</a></li>
				<li><a
					href="${pageContext.request.contextPath}/mvc/auth/mentor/app/delete.my">회원
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
						action="${pageContext.request.contextPath}/mvc/auth/mentor/mentoring.my?type=modifyOk"
						method="post" enctype="multipart/form-data">
						<input type="hidden" name="mentoringNumber"
							value="${mentoring.mentoringNumber}">

						<%-- [수정] DTO 필드명에 맞춰 internalId 사용 --%>
						<input type="hidden" name="mentoringNumber"
							value="${mentoring.mentoringNumber}">

						<div id="mentoringMain">
							<div id="mentoring">
								<div id="mentoringTopics">
									<div class="subject">
										<label>학습 과목</label> <select name="mentoringSubject"
											id="mentoringSubject">
											<option value="none">선택하세요</option>
											<option value="1"
												${mentoring.subjectNumber == 1 ? 'selected' : ''}>자바</option>
											<option value="2"
												${mentoring.subjectNumber == 2 ? 'selected' : ''}>파이썬</option>
											<option value="3"
												${mentoring.subjectNumber == 3 ? 'selected' : ''}>국어</option>
											<option value="4"
												${mentoring.subjectNumber == 4 ? 'selected' : ''}>수학</option>
											<option value="5"
												${mentoring.subjectNumber == 5 ? 'selected' : ''}>C++</option>
											<option value="6"
												${mentoring.subjectNumber == 6 ? 'selected' : ''}>C언어</option>
											<option value="7"
												${mentoring.subjectNumber == 7 ? 'selected' : ''}>영어</option>
											<option value="8"
												${mentoring.subjectNumber == 8 ? 'selected' : ''}>게임</option>
										</select>
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
										<div id="curriculumFileTitle">커리큘럼 파일 수정</div>
										<div class="file-info-area">
											<c:choose>
												<c:when test="${not empty mentoring.fileName}">
													<p>
														현재 등록된 파일: <strong>${mentoring.fileName}</strong>
													</p>
												</c:when>
												<c:otherwise>
													<p>등록된 파일이 없습니다.</p>
												</c:otherwise>
											</c:choose>
										</div>
										<input type="file" id="curriculumFile" name="mentoringFile"
											accept=".pdf, .doc, .docx">
										<p class="file-notice">* 새로 업로드하면 기존 파일은 교체됩니다.</p>
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
							<button type="button" onclick="history.back()">취소</button>
						</div>
					</form>
				</div>
			</div>
		</main>
	</div>

	<jsp:include page="/app/user/footer.jsp" />
	<script>
		function deleteMentoring(internalId) {
			if (confirm("정말로 이 멘토링을 삭제하시겠습니까?")) {
				location.href = "${pageContext.request.contextPath}/mvc/auth/mentor/mentoringDeleteOk.my?mentoringNumber="
						+ mentoringNumber;
			}
		}
	</script>
</body>
</html>