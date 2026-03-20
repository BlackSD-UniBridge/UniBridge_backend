<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>멘티 매칭 정보</title>
<link
	href="https://fonts.googleapis.com/css2?family=Playfair+Display:wght@600&family=Noto+Sans+KR:wght@300;400;500;700&display=swap"
	rel="stylesheet" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/header.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/user/mentor/myPage/userMatching/userMatching.css">
<script defer
	src="${pageContext.request.contextPath}/assets/js/user/mentee/myPage/userMatching/userMatching.js"></script>
</head>
<body>

	<jsp:include page="/app/user/header.jsp" />
    <div class="mainContainer">
        <aside>
            <div class="myPageTitle">마이페이지</div>
            <ul>
                <li><a href="${pageContext.request.contextPath}/mvc/auth/mentee/myPage.my">계정 관리</a></li>
                <li><a href="${pageContext.request.contextPath}/mvc/auth/mentee/survey.my">설문 조사</a></li>
                <li><a href="${pageContext.request.contextPath}/mvc/auth/mentee/log.my">결제 정보</a></li>
                <li><a href="${pageContext.request.contextPath}/mvc/auth/mentee/matching.my" class="active">매칭 정보</a></li>
                <li><a href="${pageContext.request.contextPath}/mvc/auth/mentee/delete.my">회원 탈퇴</a></li>
            </ul>
        </aside>

		<main>
			<div class="userManageTitle">
				<img
					src="${pageContext.request.contextPath}/assets/img/user/userProfile/matching.png"
					alt="프로필 아이콘">
				<div class="title">매칭 정보</div>
			</div>

			<div class="container">
				<c:forEach var="matching" items="${matchingList}">
					<fmt:parseDate value="${matching.matchingStart}" var="parsedDate"
						pattern="yyyy-MM-dd HH:mm:ss" />

					<div class="userTypeBox">
						<div class="mentoring">
							<div class="userText">
								<label>결제 번호</label> <label> <c:choose>
										<c:when test="${matching.payId > 0}">${matching.payId}</c:when>
										<partial>이전 내역 혹은 미결제</partial>
									</c:choose>
								</label>
							</div>
							<div class="userText">
								<label>멘토 이름</label> <label><c:out
										value="${matching.mentorName}" /></label>
							</div>
							<div class="userText">
								<label>멘티 이름</label> <label><c:out
										value="${matching.menteeName}" /></label>
							</div>
							<div class="userText">
								<label>멘토링 과목</label> <label><c:out
										value="${matching.subjectName}" /></label>
							</div>
							<div class="userText">
								<label>멘토링 시작일</label> <label><fmt:formatDate
										value="${parsedDate}" pattern="yyyy/MM/dd" /></label>
							</div>
						</div>

						<button type="button" class="matchingCancel"
							onclick="openCancelModal('${matching.matchingNumber}')">매칭
							취소 신청</button>
					</div>

					<div id="matchingModal_${matching.matchingNumber}"
						class="matingCancel" style="display: none;">
						<div class="cancelBox">
							<button type="button" class="closeBtn"
								onclick="closeCancelModal('${matching.matchingNumber}')">
								<img
									src="${pageContext.request.contextPath}/assets/img/user/userProfile/close.png"
									alt="닫기">
							</button>
							<div class="cacelTitle">매칭 취소 신청서</div>
							<div class="cancelModalBox">
								<form id="cancelForm_${matching.matchingNumber}" method="post"
									action="${pageContext.request.contextPath}/mvc/auth/mentee/matching.my">
									<input type="hidden" name="matchingNumber"
										value="${matching.matchingNumber}">

									<div class="infoGrid">
										<div class="printRow">
											<label>멘토링 과목</label>
											<div class="mentoringText">
												<c:out value="${matching.subjectName}" />
											</div>
										</div>
										<div class="printRow">
											<label>결제 번호</label>
											<div class="mentoringText">${matching.payId}</div>
										</div>
									</div>

									<div class="cencelInputBox">
										<div class="contextTitle">매칭취소 사유</div>
										<textarea name="matchingCanReason" class="cencelIput"
											maxlength="1024" placeholder="취소 사유를 입력해주세요"></textarea>
									</div>

									<div class="cancelFooter">
										<button type="button" class="submitBtn"
											onclick="submitCancel('${matching.matchingNumber}')">취소
											신청</button>
										<button type="button" class="cancelBtn"
											onclick="closeCancelModal('${matching.matchingNumber}')">닫기</button>
									</div>
								</form>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
		</main>
	</div>
</body>
</html>