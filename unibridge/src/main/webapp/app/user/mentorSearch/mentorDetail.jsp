<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>${mentor.memberName}멘토상세정보</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/user/mentorSearch/mentorDetail.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/header.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/footer.css">

<script defer
	src="${pageContext.request.contextPath}/assets/js/user/mentorSearch/mentorDetail.js"></script>
<script defer
	src="${pageContext.request.contextPath}/assets/js/header.js"></script>
</head>
<body>
	<jsp:include page="/app/user/header.jsp" />

	<main id="mentorMainContainer">
		<div id="mentorDetail">
			<%-- 상단 헤더: 프로필 이미지 및 기본 정보 --%>
			<div id="detailHead">
				<img
					src="${pageContext.request.contextPath}/display.file?fileName=${mentor.fileName != null ? mentor.fileName : 'default.png'}&t=<%=System.currentTimeMillis()%>"
					alt="프로필 사진" id="profileImg"
					onerror="this.onerror=null; this.src='${pageContext.request.contextPath}/assets/img/user/userProfile/default.png';">

				<div id="mentorInfo">
					<div id="mentorName">${mentor.memberName}멘토</div>
				</div>
				<div id="mentoExplan">
					<div class="mentoSpec">
						<div id="mentoName">${mentor.memberName}멘토님</div>
						<div id="mentorUniSchool">${mentor.gradSchool}</div>
						<div id="mentoMajor">${mentor.gradDepart}</div>
					</div>
					<%-- 멘토링 제목 영역 --%>
					<div id="mentoringTitleWrap">
						<h3 id="mentoringTitle">${mentor.mentoringTitle}</h3>
					</div>
				</div>
			</div>

			<%-- 상세 정보 영역 --%>
			<div id="detailBody">
				<%-- 멘토링 목표 --%>
				<div id="goalSection">
					<div class="section-title">멘토링 목표</div>
					<div id="mentoringGoal">
						<p>${mentor.mentoringGoal}</p>
					</div>
				</div>

				<%-- 커리큘럼 소개 --%>
				<div id="detailfoot">
					<div class="section-title">커리큘럼 소개</div>
					<div id="mentoringCurriculum">
						<p>안녕하세요, ${mentor.memberName} 멘토입니다.</p>
						<p>${mentor.gradSchool}에서전공한지식을바탕으로성심껏도와드리겠습니다.</p>
					</div>
				</div>
			</div>
		</div>

		<%-- 결제 영역 (Sticky 카드) --%>
		<div id="payment">
			<c:choose>
				<%-- 1. 멘티 계정인 경우 --%>
				<c:when test="${sessionScope.loginUser.memberType eq 'MENTEE'}">
					<div id="paymenthead">
						<div class="uniName">${mentor.gradSchool}</div>
						<div id="paymentTitle">멘토링 1회권</div>
						<hr>
						<div id="startDay">예상 시작일</div>
						<div id="mentoringDay">2026.3.25(수)</div>
					</div>

					<div id="paymentfoot">
						<c:choose>
							<c:when test="${isMatching}">
								<div class="matching-alert">
									현재 진행 중인 매칭이 있습니다.<br>기존 매칭 종료 후 신청 가능합니다.
								</div>
								<button type="button" class="btn-disabled" disabled>결제
									불가 (매칭 진행 중)</button>
							</c:when>
							<c:otherwise>
								<div id="price">
									<div>결제금액</div>
									<div class="price-amount">10,000원</div>
								</div>
								<button type="button" id="pay" class="btn-pay"
									onclick="location.href='${pageContext.request.contextPath}/pay/payment.pay?memberNumber=${mentor.memberNumber}'">
									결제하기</button>
							</c:otherwise>
						</c:choose>
					</div>
				</c:when>

				<%-- 2. 멘토 계정인 경우 --%>
				<c:when test="${not empty sessionScope.loginUser}">
					<div class="msg-box">
						<p>
							멘토링 신청은<br>멘티 계정으로만 가능합니다.
						</p>
					</div>
				</c:when>

				<%-- 3. 비로그인 상태 --%>
				<c:otherwise>
					<div class="msg-box">
						<p>
							로그인 후<br>멘토링을 신청할 수 있습니다.
						</p>
						<button type="button" class="btn-login"
							onclick="location.href='${pageContext.request.contextPath}/signin.mem'">
							로그인 하러가기</button>
					</div>
				</c:otherwise>
			</c:choose>
		</div>
	</main>

	<jsp:include page="/app/user/footer.jsp" />
</body>
</html>