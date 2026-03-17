<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Date"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>멘토 검색</title>
<link href="${pageContext.request.contextPath}/assets/css/fonts.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/user/mentorSearch/mentorSearch.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/header.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/footer.css">
<script>
    var globalContextPath = "${pageContext.request.contextPath}";
</script>
<script>
    // const 대신 var를 사용하여 전역 어디서든 접근 가능하게 합니다.
    var REAL_MENTORS = [
        <c:forEach var="m" items="${mentorList}" varStatus="status">
        {
            id: "${m.memberNumber}",
            name: "${m.memberName}",
            university: "${m.gradSchool}",
            major: "${m.gradDepart}",
            img: "${not empty m.file ? m.file : ''}", 
            date: "${m.createdAt}",
            subject: "입시/IT"
        }${not status.last ? ',' : ''}
        </c:forEach>
    ];
    var globalContextPath = "${pageContext.request.contextPath}";
    console.log("데이터 확인:", REAL_MENTORS);
</script>

<script
	src="${pageContext.request.contextPath}/assets/js/user/mentorSearch/mentorPagenation.js"></script>
<script defer
	src="${pageContext.request.contextPath}/assets/js/header.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/footer.js"></script>
</head>
<body>
	<main>
		<div id="mentoTitle">멘토</div>
		<div id="category">직무 카테고리</div>
		<hr>
		<nav>
			<ul>
				<li><a href="#" id="nowCategory">전체</a></li>
				<li><a href="#">국어</a></li>
				<li><a href="#">영어</a></li>
				<li><a href="#">수학</a></li>
				<li><a href="#">Java</a></li>
				<li><a href="#">Python</a></li>
			</ul>
		</nav>

		<%-- JS가 멘토 카드를 그려넣을 영역 --%>
		<div class="contents"></div>

		<%-- JS가 페이지 번호를 생성할 영역 --%>
		<div id="pageNumber">
			<ul></ul>
		</div>
	</main>
</body>
</html>