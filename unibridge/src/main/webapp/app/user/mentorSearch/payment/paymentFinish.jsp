<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>결제 완료</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/user/mentorSearch/payment/payment.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/header.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/footer.css">
<script defer
	src="${pageContext.request.contextPath}/assets/js/user/mentorSearch/paymentFinish.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/footer.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/header.js"></script>
</head>
<body>
	<%-- <jsp:include page="/app/common/header.jsp" /> --%>

	<main>
		<div id="mentoringInfo">
			<img
				src="${pageContext.request.contextPath}/assets/img/user/userProfile/ex1.png"
				alt="사진">
			<div id="mentoringPurpose">국어를 집중적으로 공부하여, 수능 최저를 맞출 수 있도록 도와
				드리겠습니다.</div>
			<div id="price">10,000원</div>
		</div>

		<div id="pay">
			<div class="title">결제 완료</div>

			<div id="payment">
				<div id="payResult">
					<div class="title">결제 계좌</div>
					<div id="payAccount">카카오)123456-78-123456</div>
					<hr>
					<div class="title">결제 번호</div>
					<div id="payNumber">4520-0200-1900-4060</div>
					<hr>
					<div id="price">
						<div class="title">결제 가격</div>
						<div class="title">10,000원</div>
					</div>
				</div>
			</div>

			<div>
				<button type="button" onclick="location.href='main.me'">홈으로
					가기</button>
			</div>
		</div>
	</main>

	<%-- <jsp:include page="/app/common/footer.jsp" /> --%>
</body>
</html>