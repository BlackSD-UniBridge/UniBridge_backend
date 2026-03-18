<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>${mentor.memberName}멘토 상세 정보</title>
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

	<main
		style="display: flex; justify-content: center; gap: 40px; padding: 50px;">
		<div id="mentorDetail">
			<div id="detailHead">
				<img
					src="${pageContext.request.contextPath}/assets/img/user/userProfile/ex1.png"
					alt="사진" id="profileImg">
				<div id="mentoExplan">
					<div class="mentoSpec">
						<div id="mentoName">${mentor.memberName}멘토님</div>
						<div id="mentorUniSchool">${mentor.gradSchool}</div>
						<div id="mentoMajor">${mentor.gradDepart}</div>
					</div>
				</div>
			</div>

			<div id="detailfoot">
				<div class="title">커리큘럼 소개</div>
				<div id="mentoringCurriculum">
					<p>안녕하세요, ${mentor.memberName} 멘토입니다.</p>
					<p>${mentor.gradSchool}에서전공한 지식을 바탕으로 성심껏 도와드리겠습니다.</p>
				</div>
			</div>
		</div>

		<%-- 2. 결제 영역 (멘티일 때만 활성화) --%>
		<div id="payment"
			style="width: 300px; border: 1px solid #ddd; padding: 20px; border-radius: 10px; height: fit-content;">
			<c:choose>
				<%-- 세션의 memberType이 MENTEE인 경우 --%>
				<c:when test="${sessionScope.memberType eq 'MENTEE'}">
					<div id="paymenthead">
						<div class="mentoSpec">
							<div style="font-weight: bold; color: #007bff;">${mentor.gradSchool}</div>
						</div>
						<div id="paymentTitle" style="font-size: 18px; margin: 10px 0;">멘토링
							1회권</div>
						<hr>
						<div id="startDay">예상 시작일</div>
						<div id="mentoringDay">2026.3.25(수)</div>
					</div>

					<div id="paymentfoot" style="margin-top: 20px;">
						<div id="price"
							style="display: flex; justify-content: space-between; margin-bottom: 15px;">
							<div>결제금액</div>
							<div style="font-weight: bold; color: #ff5252;">10,000원</div>
						</div>
						<%-- 결제 페이지 컨트롤러(.pay)로 이동 --%>
						<button type="button" id="pay"
							onclick="location.href='${pageContext.request.contextPath}/pay/payment.pay?mentorNumber=${mentor.memberNumber}'"
							style="width: 100%; padding: 10px; background: #007bff; color: white; border: none; border-radius: 5px; cursor: pointer;">
							결제하기</button>
					</div>
				</c:when>

				<%-- 로그인은 되어있으나 멘티가 아닌 경우 (멘토 등) --%>
				<c:when test="${not empty sessionScope.memberNumber}">
					<div style="text-align: center; color: #666;">
						<p>
							멘토링 신청은<br>멘티 계정으로만 가능합니다.
						</p>
					</div>
				</c:when>

				<%-- 로그인하지 않은 경우 --%>
				<c:otherwise>
					<div style="text-align: center; color: #666;">
						<p>
							로그인 후<br>멘토링을 신청할 수 있습니다.
						</p>
						<button type="button"
							onclick="location.href='${pageContext.request.contextPath}/signin.mem'"
							style="width: 100%; padding: 10px; background: #333; color: white; border: none; border-radius: 5px; cursor: pointer;">
							로그인 하러가기</button>
					</div>
				</c:otherwise>
			</c:choose>
		</div>
	</main>

	<jsp:include page="/app/user/footer.jsp" />
</body>
</html>