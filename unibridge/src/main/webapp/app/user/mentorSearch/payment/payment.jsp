<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>결제</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/user/mentorSearch/payment/payment.css">
<%-- <link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/header.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/footer.css">
<link href="${pageContext.request.contextPath}/assets/css/fonts.css"
	rel="stylesheet"> --%>
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
			<div class="title">결제 상세</div>
			<div id="payment">
				<div id="payWay">
					<div class="title">아무개</div>
					<hr>
					<div>카카오 페이 결제</div>
					<div id="kakaoPay">
						<input type="radio" name="kakao" id="kakao"> <label
							for="kakao">카카오페이머니</label>
					</div>
					<hr>
					<div class="title">결제정보</div>
					<hr>
					<div id="price">
						<div class="title">가격</div>
						<div class="title">10,000원</div>
					</div>
				</div>
			</div>
			<div>
				<button type="button" id="kakaoPayBtn">카카오 결제</button>
			</div>
		</div>
	</main>

	<%-- <jsp:include page="/app/common/footer.jsp" /> --%>
	<script src="${pageContext.request.contextPath}/assets/js/user/mentorSearch/payment/payment.js"></script>
</body>
</html>