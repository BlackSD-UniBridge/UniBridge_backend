<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>멘토링 등록 - UniBridge</title>
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
					href="${pageContext.request.contextPath}/app/user/mentor/myPage/userMentoing/mentoringCreate.jsp"
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
				<div class="title">멘토링 관리</div>
			</div>

			<div id="contentsMain">
				<div id="mentoringBackground">
					<form
						action="${pageContext.request.contextPath}/mentoringWriteOk.mo"
						method="post" enctype="multipart/form-data">
						<div id="mentoringMain">
							<div id="mentoring">
								<div id="mentoringTopics">
									<div class="subject">
										<label>학습 과목</label> <select name="mentoringSubject">
											<option value="JAVA">JAVA</option>
											<option value="Python">Python</option>
										</select>
									</div>
									<div class="title">
										<label>멘토링 주제</label> <input type="text" name="mentoringTitle"
											placeholder="제목을 입력하세요">
									</div>
									<div id="purpose">
										<label>멘토링 목적</label>
										<textarea name="mentoringPurpose" placeholder="목적을 입력하세요"></textarea>
									</div>
								</div>

								<div id="curriculum">
									<div id="text">
										<div>
											<label for="mentoringCurriculum">멘토링 커리큘럼 상세</label>
										</div>
										<textarea id="mentoringCurriculum" name="mentoringCurriculum"></textarea>
									</div>
									<div id="file">
										<div id="curriculumFileTitle">파일 첨부</div>
										<input type="file" id="curriculumFile" name="mentoringFile">
									</div>
								</div>
							</div>

							<div id="profile">
								<a href="#"> <img
									src="${pageContext.request.contextPath}/assets/img/user/userProfile/userIcon.png"
									alt="아이콘"> <img id="profileImg"
									src="${pageContext.request.contextPath}/assets/img/user/userProfile/ex1.png"
									alt="프로필이미지">
								</a>
							</div>
						</div>

						<div id="buttons">
							<button type="submit">등록</button>
							<button type="reset">취소</button>
						</div>
					</form>
				</div>
			</div>
		</main>
	</div>

	<jsp:include page="/app/user/footer.jsp" />
</body>
</html>