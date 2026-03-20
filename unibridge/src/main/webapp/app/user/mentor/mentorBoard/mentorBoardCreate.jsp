<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">

<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>UniBridge - 게시판 글 작성</title>
  <link
    href="https://fonts.googleapis.com/css2?family=Playfair+Display:wght@600&family=Noto+Sans+KR:wght@300;400;500;700&display=swap"
    rel="stylesheet" />
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/user/mentor/mentorBoard/mentorBoardCreate.css" />
</head>
<body>
<%@ include file="/app/user/header.jsp"%>
  <div class="container">
    <form id="write-form"
      action="${pageContext.request.contextPath}/mentor/mentorBoard/MentorBoardWrite.mob"
      method="post" enctype="multipart/form-data">
      <h1>글쓰기</h1>
      <div class="form-group">
        <label for="mentorBoardCreateSubject">제목</label>
        <input type="text" id="mentorBoardCreateSubject" name="MentorBoardTitle" required />
      </div>
      <div class="form-group">
        <label for="author">작성자</label>
        <div class="writer">
          <c:out value="${sessionScope.loginUser.memberId}" />
        </div>
      </div>
      <div class="form-group">
        <label for="mentorBoardCreateContent">내용</label>
        <textarea id="mentorBoardCreateContent" name="MentorBoardContent" required></textarea>
      </div>
      <div class="btn-group">
        <button type="submit" class="submit-btn">작성 완료</button>
        <button type="button" class="cancle-btn">취소</button>
      </div>
    </form>
  </div>
<script>
  const contextPath = "${pageContext.request.contextPath}";
</script>
<script src="${pageContext.request.contextPath}/assets/js/user/mentor/mentorBoard/mentorBoardCreate.js"></script>

</body>
</html>
