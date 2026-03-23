<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- 1. JSTL fmt 태그 라이브러리 추가 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
<script>
	var globalContextPath = "${pageContext.request.contextPath}";
</script>
</head>
<body>
	<jsp:include page="/app/user/header.jsp" />

	<main>
		<div id="mentoringInfo">
			<img
				src="${pageContext.request.contextPath}/display.file?fileName=${payInfo.fileName != null ? payInfo.fileName : 'default.png'}"
				alt="프로필 사진" id="profileImg"
				onerror="this.onerror=null; this.src='${pageContext.request.contextPath}/assets/img/user/userProfile/default.png';">
			<div id="mentoringPurpose">국어를 집중적으로 공부하여, 수능 최저를 맞출 수 있도록 도와
				드리겠습니다.</div>
		</div>

		<div id="pay">
			<div class="title">결제 완료</div>

			<div id="payment">
				<div id="payResult">
					<div class="title">결제 정보</div>
					<div id="payAccount">카카오페이</div>
					<hr>
					<div>주문 번호: ${payInfo.payId}</div>
					<div>결제 수단: ${payInfo.payMethod}</div>

					<%-- 금액 포맷팅 (쉼표 추가) --%>
					<div>
						결제 금액:
						<fmt:formatNumber value="${payInfo.payAmount}" type="number" />
						원
					</div>

					<%-- 날짜 포맷팅 --%>
					<div>
						결제 일시:
						<fmt:parseDate value="${payInfo.payDate}"
							pattern="yyyy-MM-dd HH:mm:ss" var="parsedDate" type="both" />
						<fmt:formatDate value="${parsedDate}"
							pattern="yyyy년 MM월 dd일 HH:mm:ss" />
					</div>

					<div id="payStatus">
						상태: <span style="font-weight: bold; color: blue;">${payInfo.payStatus}</span>
					</div>
				</div>
			</div>

			<div>
				<button type="button"
					onclick="location.href='${pageContext.request.contextPath}/index.main'">
					홈으로 가기</button>
			</div>
		</div>
	</main>

	<jsp:include page="/app/user/footer.jsp" />
</body>
</html>