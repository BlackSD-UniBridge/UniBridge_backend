<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>멘토 상세 정보</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/user/mentorSearch/mentorDetail.css">
<link href="${pageContext.request.contextPath}/assets/css/fonts.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/header.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/footer.css">

<script defer
	src="${pageContext.request.contextPath}/assets/js/user/mentorSearch/mentorDetail.js"></script>
<script defer
	src="${pageContext.request.contextPath}/assets/js/header.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/footer.js"></script>
</head>
<body>
	<%-- <jsp:include page="/app/common/header.jsp" /> --%>

	<main>
		<div id="mentorDetail">
			<div id="detailHead">
				<img
					src="${pageContext.request.contextPath}/assets/img/user/userProfile/ex1.png"
					alt="사진" id="profileImg">
				<div id="mentoExplan">
					<div class="mentoSpec">
						<div id="mentoName">아무개</div>
						<div id="mentoSubject">국어</div>
						<div id="mentorUniSchool">코딩대학교</div>
						<div id="mentoMajor">코딩학과</div>
					</div>
					<div id="mentoringPurpose">국어를 집중적으로 공부하여, 수능 최저를 맞출 수 있도록 도와
						드리겠습니다.</div>
				</div>
			</div>

			<div id="detailfoot">
				<div class="title">커리큘럼 소개</div>
				<div id="mentoringCurriculum">
					“아무개 대학교를 가기 위해 수능 국어 및 관련 학과 커리큘럼을 미리 배우는 멘토”<br> 올해 아무개 대학교를
					졸업했습니다.<br> 끝까지 열심히 참여할 학생이면 누구든지 신청 가능합니다
				</div>
			</div>
		</div>
		<div id="payment">
			<div id="paymenthead">
				<div class="mentoSpec">
					<div>국어</div>
					<div>코딩대학교</div>
					<div>코딩학과</div>
				</div>
				<div id="paymentTitle">수능 국어 맞춤 멘토링</div>
				<hr>
				<div id="startDay">멘토링 시작일</div>
				<div id="mentoringDay">2026.3.3(월)</div>
			</div>

			<div id="paymentfoot">
				<div id="price">
					<div>결제금액</div>
					<div>10,000원</div>
				</div>
				<button type="button" id="pay"
					onclick="location.href='${pageContext.request.contextPath}/pay/paymentOk.pay?memberNumber=${mentor.memberNumber}'">
					결제하기</button>
			</div>
		</div>
	</main>

	<%-- <jsp:include page="/app/common/footer.jsp" /> --%>
</body>
</html>