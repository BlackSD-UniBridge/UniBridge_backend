<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">

<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>UniBridge - 게시글 수정</title>
  <link
    href="https://fonts.googleapis.com/css2?family=Playfair+Display:wght@600&family=Noto+Sans+KR:wght@300;400;500;700&display=swap"
    rel="stylesheet" />
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/header.css" />
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/footer.css" />
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/user/mentee/menteeBoard/menteeBoardModify.css" />

</head>

<body>

  <!-- 헤더 -->
  <div id="headerContainer"></div>

  <div class="pageContent">
    <div class="menteeBoardModifyWrap">

      <!-- 제목 영역 -->
      <div class="menteeBoardModifyHeader">
        <span class="menteeBoardModifyLabel">수정하기</span>

        <input type="text" id="menteeBoardModifySubject" class="menteeBoardModifySubjectInput" placeholder="글제목"
          maxlength="50" />
        <!-- 메타 정보 -->
        <div class="menteeBoardModifyMeta">
          <span class="menteeBoardModifyViews">조회수 0</span>
          <span class="menteeBoardModifyCommentCount">댓글 0</span>
        </div>
      </div>

      <!-- 본문 입력 -->
      <textarea id="menteeBoardModifyContent" class="menteeBoardModifyContentTextarea"
        placeholder="수정할 글 내용"></textarea>

      <!-- 하단 버튼 -->
      <div class="menteeBoardModifyFooter">
        <button class="menteeBoardModifyBackBtn" id="menteeBoardModifyBackBtn">수정 취소</button>
        <div class="menteeBoardModifyActionGroup">
          <button class="menteeBoardModifySubmitBtn" id="menteeBoardModifySubmitBtn">수정</button>
          <button class="menteeBoardModifyDeleteBtn" id="menteeBoardModifyDeleteBtn">삭제</button>
        </div>
      </div>

    </div>
  </div>

  <div id="footerContainer"></div>

  <script src="${pageContext.request.contextPath}/assets/js/header.js"></script>
  <script src="${pageContext.request.contextPath}/assets/js/user/mentee/menteeBoard/menteeBoardModify.js"></script>

</body>

</html>