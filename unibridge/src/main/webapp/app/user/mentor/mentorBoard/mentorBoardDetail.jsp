<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>UniBridge - 게시판 상세</title>
  <link
    href="https://fonts.googleapis.com/css2?family=Playfair+Display:wght@600&family=Noto+Sans+KR:wght@300;400;500;700&display=swap"
    rel="stylesheet" />
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/user/mentor/mentorBoard/mentorBoardDetail.css" />
</head>
<body>
  <%@ include file="/app/user/header.jsp"%>

  <div class="container">
    <div class="view-wrap">
      <div class="view-title">
        <h1><c:out value="${MentorBoard.boardTitle}" /></h1>
      </div>
      <div class="view-info">
        <div class="info-title">
          <div class="title-wrap">
            <span class="writer">작성자</span>
            <div class="content-writer"><c:out value="${MentorBoard.memberId}" /></div>
          </div>
          <div class="title-wrap">
            <span class="date">작성일</span>
            <div class="content-date"><c:out value="${MentorBoard.boardDate}" /></div>
          </div>
          <div class="title-wrap">
            <span class="hit">조회수</span>
            <div class="content-hit"><c:out value="${MentorBoard.boardClick}" /></div>
          </div>
        </div>
      </div>

      <div class="view-content">
        <c:out value="${MentorBoard.boardContent}" />
      </div>

      <div class="btn-group">
        <button type="button" class="list-btn"
          data-board-number="${MentorBoard.mentorBoardNumber}"
          data-member-number="${sessionScope.loginUser.memberNumber}">목록</button>
        <c:if test="${sessionScope.loginUser.memberNumber == MentorBoard.memberNumber}">
          <button type="button" class="modify-btn">수정</button>
          <button type="button" class="delete-btn">삭제</button>
        </c:if>
      </div>
    </div>
  </div>

  <script>
    let memberNumber = "${sessionScope.loginUser.memberNumber}";
    const contextPath = "${pageContext.request.contextPath}";
  </script>
  <script src="${pageContext.request.contextPath}/assets/js/user/mentor/mentorBoard/mentorBoardDetail.js"></script>

</body>
</html>
