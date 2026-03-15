<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
  
  <script defer src="${pageContext.request.contextPath}/assets/js/user/mentorSearch/mentorPagenation.js"></script>
  <script defer src="${pageContext.request.contextPath}/assets/js/user/mentorSearch/categorySearch.js"></script>
  <script defer src="${pageContext.request.contextPath}/assets/js/header.js"></script>
  <script src="${pageContext.request.contextPath}/assets/js/footer.js"></script>
</head>
<body>
 <%--  <jsp:include page="/app/common/header.jsp" /> --%>

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
        <li><a href="#">C언어</a></li>
        <li><a href="#">Java</a></li>
        <li><a href="#">C++</a></li>
        <li><a href="#">Python</a></li>
        <li><a href="#">게임</a></li>
      </ul>
    </nav>

    <div class="contents">
      <div class="mentoList">
        </div>
      <div class="mentoList"></div>
      <div class="mentoList"></div>
    </div>

    <div id="pageNumber">
      <ul>
        <li id="left">&lt;</li>
        <li>10</li>
        <li id="right">&gt;</li>
      </ul>
    </div>
  </main>

<%--   <jsp:include page="/app/common/footer.jsp" /> --%>
</body>
</html>