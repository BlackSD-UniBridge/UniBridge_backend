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
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/footer.css" />
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/user/mentee/menteeBoard/menteeBoardCreate.css" />
</head>

<body>

  <!-- 헤더 -->
  <div id="headerContainer"></div>

  <div class="pageContent">
    <div class="menteeBoardCreateWrap">

      <h1 class="menteeBoardCreateTitle">게시판 글 작성</h1>

      <!-- 제목 입력 -->
      <input type="text" id="menteeBoardCreateSubject" class="menteeBoardCreateSubjectInput"
        placeholder="제목을 입력해주세요.(최대 50자)" maxlength="50" />

      <!-- 본문 입력 -->
      <textarea id="menteeBoardCreateContent" class="menteeBoardCreateContentTextarea"></textarea>

      <!-- 하단 버튼 -->
      <div class="menteeBoardCreateFooter">
        <button class="menteeBoardCreateBackBtn" id="menteeBoardCreateBackBtn">글목록</button>
        <button class="menteeBoardCreateSubmitBtn" id="menteeBoardCreateSubmitBtn">게시글 등록</button>
      </div>

    </div>
  </div>

  <div id="footerContainer"></div>

  <script src="${pageContext.request.contextPath}/assets/js/header.js"></script>
  <script src="${pageContext.request.contextPath}/assets/js/footer.js"></script>
  <script src="${pageContext.request.contextPath}/assets/js/user/mentee/menteeBoard/menteeBoardCreate.js"></script>

</body>

</html>