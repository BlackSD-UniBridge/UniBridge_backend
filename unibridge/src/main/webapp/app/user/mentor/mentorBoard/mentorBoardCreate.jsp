<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">

<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>UniBridge - 게시판 글 작성</title>
  <link
    href="https://fonts.googleapis.com/css2?family=Playfair+Display:wght@600&family=Noto+Sans+KR:wght@300;400;500;700&display=swap"
    rel="stylesheet" />
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/header.css" />
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer.css" />
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/user/mentor/mentorBoard/mentorBoardCreate.css" />
</head>

<body>

  <!-- 헤더 -->
  <div id="headerContainer"></div>

  <div class="pageContent">
    <div class="mentorBoardCreateWrap">

      <h1 class="mentorBoardCreateTitle">게시판 글 작성</h1>

      <!-- 제목 입력 -->
      <input type="text" id="mentorBoardCreateSubject" class="mentorBoardCreateSubjectInput"
        placeholder="제목을 입력해주세요.(최대 50자)" maxlength="50" />

      <!-- 본문 입력 -->
      <textarea id="mentorBoardCreateContent" class="mentorBoardCreateContentTextarea"></textarea>

      <!-- 하단 버튼 -->
      <div class="mentorBoardCreateFooter">
        <button class="mentorBoardCreateBackBtn" id="mentorBoardCreateBackBtn">글목록</button>
        <button class="mentorBoardCreateSubmitBtn" id="mentorBoardCreateSubmitBtn">게시글 등록</button>
      </div>

    </div>
  </div>

  <div id="footerContainer"></div>

  <script src="${pageContext.request.contextPath}/assets/js/mentorHeader.js"></script>
  <script src="${pageContext.request.contextPath}/assets/js/footer.js"></script>
  <script src="${pageContext.request.contextPath}/assets/js/user/mentor/mentorBoard/mentorBoardCreate.js"></script>


</body>

</html>