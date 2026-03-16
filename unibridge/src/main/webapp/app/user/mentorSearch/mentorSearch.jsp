<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>멘토 검색</title>
<link href="${pageContext.request.contextPath}/assets/css/fonts.css" rel="stylesheet">
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/user/mentorSearch/mentorSearch.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/header.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/footer.css">

<script>
    // [중요] 서버 데이터를 JS 배열로 변환
    const globalContextPath = "${pageContext.request.contextPath}";
    
    <%-- 오늘 날짜를 구하기 위한 자바 코드 --%>
    <%@ page import="java.util.Date" %>
    <%@ page import="java.text.SimpleDateFormat" %>
    <%
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        String today = sdf.format(new Date());
    %>

    const REAL_MENTORS = [
        <c:forEach var="m" items="${mentorList}" varStatus="status">
        {
            id: "${m.memberNumber}",
            name: "${m.memberNickname}",
            university: "${m.gradSchool}",
            major: "${m.gradDepart}",
            subject: "전체", 
            purpose: "유니브릿지에서 함께 성장할 멘티를 찾습니다.",
            <%-- 1. 날짜: 실제 등록일 컬럼이 없다면 오늘 날짜 또는 임의의 날짜로 처리 --%>
            date: "<%= today %>", 
            <%-- 2. 이미지: DB에 저장된 파일명이 있으면 해당 경로로, 없으면 기본 이미지(ex1.png)로 처리 --%>
            img: "${not empty m.memberProfile ? '/upload/' += m.memberProfile : '/assets/img/user/userProfile/ex1.png'}"
        }${not status.last ? ',' : ''}
        </c:forEach>
    ];
</script>

<script defer src="${pageContext.request.contextPath}/assets/js/user/mentorSearch/mentorPagenation.js"></script>
<script defer src="${pageContext.request.contextPath}/assets/js/header.js"></script>
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