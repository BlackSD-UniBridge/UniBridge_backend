<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>멘티 매칭 정보</title>
	<link href="https://fonts.googleapis.com/css2?family=Playfair+Display:wght@600&family=Noto+Sans+KR:wght@300;400;500;700&display=swap" rel="stylesheet" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/header.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/user/mentor/myPage/userMatching/userMatching.css">
	<script defer src="${pageContext.request.contextPath}/assets/js/user/mentee/myPage/userMatching/userMatching.js"></script>
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
				<img src="${pageContext.request.contextPath}/assets/img/user/userProfile/matching.png" alt="프로필 아이콘">
				<div class="title">매칭 정보</div>
			</div>

			<div class="container">
				<c:forEach var="matching" items="${matchingList}">
					<%-- 1. 매칭 카드 (기본 화면) --%>
					<div class="userTypeBox">
						<div class="mentoring">
							<div class="userText">
								<label>멘토 이름</label> 
								<label>${matching.mentorName}</label>
							</div>
							<div class="userText">
								<label>멘티 이름</label> 
								<label>${matching.menteeName}</label>
							</div>
							<div class="userText">
								<label>멘토링 과목</label> 
								<label>${matching.subjectName}</label>
							</div>
							<div class="userText">
							    <label>멘토링 시작일</label> 
							    <label>
							        <%-- 1. 문자열을 날짜 객체로 변환 (pattern은 DB에서 넘어오는 형식과 일치해야 함) --%>
							        <fmt:parseDate value="${matching.matchingStart}" var="parsedMatchingStart" pattern="yyyy-MM-dd HH:mm:ss" />
							        <%-- 2. 변환된 객체를 원하는 형식으로 출력 --%>
							        <fmt:formatDate value="${parsedMatchingStart}" pattern="yyyy/MM/dd" />
							    </label>
							</div>
							<div class="userText">
	                            <label>학습보고서</label>
	                            <form method="post" action="${pageContext.request.contextPath}/mvc/auth/report.rep">
		                            <button class="mentoringCheck" id="reportBtn">학습보고서 확인</button>
	                            </form>
                        	</div>
						</div>

						<button type="button" class="matchingCancel"
							onclick="openCancelModal('${matching.matchinNumber}')">매칭 취소 신청</button>
					</div>

					<%-- 2. 매칭 취소 모달창 (카드 내 데이터와 동일하게 출력) --%>
					<div id="matchingModal_${matching.matchinNumber}" class="matingCancel" style="display: none;">
						<div class="cancelBox">
							<button type="button" class="closeBtn" onclick="closeCancelModal('${matching.matchinNumber}')">
								<img src="${pageContext.request.contextPath}/assets/img/user/userProfile/close.png" alt="닫기">
							</button>
							
							<div class="cacelTitle">매칭 취소 신청서</div>
							
							<div class="cancelModalBox">
								<form id="cancelForm_${matching.matchinNumber}" method="post"
									action="${pageContext.request.contextPath}/mvc/auth/mentee/matching.my">
									
									<input type="hidden" name="matchinNumber" value="${matching.matchinNumber}">

									<div class="infoGrid">
										<div class="printRow">
											<label>멘토 이름</label>
											<div class="mentoringText">${matching.mentorName}</div>
										</div>
										<div class="printRow">
											<label>멘티 이름</label>
											<div class="mentoringText">${matching.menteeName}</div>
										</div>
										<div class="printRow">
											<label>멘토링 과목</label>
											<div class="mentoringText">${matching.subjectName}</div>
										</div>
										<div class="printRow">
										    <label>시작일</label>
										    <div class="mentoringText">
										        <fmt:parseDate value="${matching.payDate}" var="parsedPayDate" pattern="yyyy-MM-dd HH:mm:ss" />
										        <fmt:formatDate value="${parsedPayDate}" pattern="yyyy/MM/dd" />
										    </div>
										</div>
									</div>

									<div class="cencelInputBox">
										<div class="contextTitle">매칭취소 사유</div>
										<textarea name="matchingCanReason" class="cencelIput"
											maxlength="1024" placeholder="취소 사유를 입력해주세요"></textarea>
									</div>

									<div class="cancelFooter">
										<button type="button" class="submitBtn"
											onclick="submitCancel('${matching.matchinNumber}')">취소 신청</button>
										<button type="button" class="cancelBtn"
											onclick="closeCancelModal('${matching.matchinNumber}')">닫기</button>
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