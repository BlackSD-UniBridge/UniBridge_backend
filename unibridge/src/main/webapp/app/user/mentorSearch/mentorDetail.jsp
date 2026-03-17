<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
						<%-- DTO에 존재하는 memberName 사용 --%>
						<div id="mentoName">${mentor.memberName}멘토님</div>

						<%-- DTO에 없는 subject는 주석 처리하거나 임시 텍스트 사용 --%>
						<div id="mentoSubject">전문 분야 준비 중</div>

						<div id="mentorUniSchool">학교: ${mentor.gradSchool}</div>
						<div id="mentoMajor">학과: ${mentor.gradDepart}</div>
					</div>
					<%-- 에러 유발 지점: mentoringContent가 DTO에 없으므로 주석 처리 --%>
					<%-- <div id="mentoringPurpose">${mentor.mentoringContent}</div> --%>
				</div>
			</div>

			<div id="detailfoot">
				<div class="title">커리큘럼 소개</div>
				<%-- 에러 유발 지점: mentoringCurriculum 주석 처리 --%>
				<div id="mentoringCurriculum">상세 커리큘럼을 준비 중입니다.</div>
			</div>
		</div>

		<%-- 1. 로그인한 유저의 상태가 'mentee'일 때만 결제 박스 전체를 보여줌 --%>
		<c:if test="${sessionScope.userStatus == 'mentee'}">
			<div id="payment">
				<div id="paymenthead">
					<div class="mentoSpec">
						<div>${mentor.subjectName}</div>
						<div>${mentor.universityName}</div>
						<div>${mentor.majorName}</div>
					</div>
					<div id="paymentTitle">${mentor.mentoringTitle}</div>
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
		</c:if>
	</main>

	<%-- <jsp:include page="/app/common/footer.jsp" /> --%>
</body>
</html>