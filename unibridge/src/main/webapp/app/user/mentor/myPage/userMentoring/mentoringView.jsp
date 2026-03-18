<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>멘토링 조회 - UniBridge</title>

<%-- CSS 경로들을 절대 경로로 수정하여 서버 환경에서 안전하게 불러옵니다 --%>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/fonts.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/user/mentor/myPage/userMentoing/mentoringView.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/header.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/footer.css">

<%-- JS 로드 --%>
<script defer
	src="${pageContext.request.contextPath}/assets/js/user/mentor/myPage/userMentoCreate/mentoringView.js"></script>
<script defer
	src="${pageContext.request.contextPath}/assets/js/header.js"></script>
<script defer
	src="${pageContext.request.contextPath}/assets/js/footer.js"></script>
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
					href="${pageContext.request.contextPath}/app/user/mentor/myPage/userMentoring/mentoringCreate.jsp"
					class="active">멘토링</a></li>
				<li><a
					href="${pageContext.request.contextPath}/app/user/mentor/myPage/userDelete/userDelete.jsp">회원
						탈퇴</a></li>
			</ul>
		</aside>

		<main>
			<div class="userManageTitle">
				<img
					src="${pageContext.request.contextPath}/frontend/assets/img/user/userMyPageImg/userMentoring.jpg"
					alt="멘토링 관리 아이콘">
				<div class="title">멘토링 관리</div>
			</div>

			<%-- 수정/삭제 처리를 위한 Form --%>
			<form method="post"
				action="${pageContext.request.contextPath}/auth/mentor/mentoringUpdate.my">
				<%-- 수정을 위해 데이터의 고유 ID(PK)를 숨겨서 전달: DTO의 internalId와 매칭 --%>
				<input type="hidden" name="mentoringId"
					value="${mentoring.internalId}">

				<div id="contentsMain">
					<div id="mentoringBackground">
						<div id="mentoringMain">
							<div id="mentoring">
								<div id="mentoringTopics">
									<div class="title">
										<span>학습 주제 및 목표</span>
									</div>
									<div class="subject">
										<label for="mentoringSubject">학습 과목 번호</label>
										<%-- subject -> subjectNumber 로 수정 --%>
										<input type="text" id="mentoringSubject"
											name="mentoringSubject" value="${mentoring.subjectNumber}"
											readonly>
									</div>

									<div class="subject">
										<label for="mentoringTitle">멘토링 주제</label>
										<%-- title -> mentoringTitle 로 수정 --%>
										<input type="text" id="mentoringTitle" name="mentoringTitle"
											value="${mentoring.mentoringTitle}" readonly>
									</div>

									<div id="purpose">
										<label for="mentoringPurpose">멘토링 목적</label>
										<%-- purpose -> mentoringGoal 로 수정 --%>
										<textarea id="mentoringPurpose" name="mentoringPurpose"
											readonly>${mentoring.mentoringGoal}</textarea>
									</div>
								</div>

								<div id="curriculum">
									<div id="text">
										<div>
											<label for="mentoringCurriculum">멘토링 커리큘럼 상세</label>
										</div>
										<%-- curriculum -> mentoringDetail 로 수정 --%>
										<textarea id="mentoringCurriculum" name="mentoringCurriculum"
											readonly>${mentoring.mentoringDetail}</textarea>
									</div>
									<div id="file">
										<div id="curriculumFileTitle">파일 첨부</div>
										<div id="curriculumFile">
											<c:choose>
												<c:when test="${not empty mentoring.fileNumber}">
													<%-- 현재 DTO에 파일 이름 필드가 따로 없다면 fileNumber를 출력하거나 
                                                 Mapper에서 JOIN을 통해 파일명을 가져와야 합니다. --%>
													<span>파일 번호: ${mentoring.fileNumber}</span>
												</c:when>
												<c:otherwise>첨부된 파일이 없습니다.</c:otherwise>
											</c:choose>
										</div>
									</div>
								</div>
							</div>

							<div id="profile">
								<a href="#"> <%-- 프로필 이미지는 현재 DTO에 없으므로 기본 이미지 노출 혹은 추후 추가 필요 --%>
									<img
									src="${pageContext.request.contextPath}/frontend/assets/img/user/userProfile/ex1.png"
									alt="멘토 프로필" id="profileImg"
									onerror="this.src='${pageContext.request.contextPath}/frontend/assets/img/user/userProfile/ex1.png'">
								</a>
							</div>
						</div>

						<div id="buttons">
							<button type="button" id="delBtn"
								onclick="location.href='${pageContext.request.contextPath}/auth/mentor/mentoringDelete.my?id=${mentoring.internalId}'">삭제</button>
							<button type="submit" id="modBtn">수정</button>
						</div>
					</div>
				</div>
			</form>
		</main>
	</div>

	<footer>
		<!-- <div class="footerContainer"></div> -->
	</footer>
</body>
</html>