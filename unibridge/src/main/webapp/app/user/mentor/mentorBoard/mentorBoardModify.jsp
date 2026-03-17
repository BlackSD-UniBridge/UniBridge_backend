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
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/user/mentor/mentorBoard/mentorBoardModify.css" />
</head>

<body>

  <!-- 헤더 -->
  <div id="headerContainer"></div>

  <div class="pageContent">
    <div class="mentorBoardModifyWrap">

      <!-- 제목 영역 -->
      <div class="mentorBoardModifyHeader">
        <span class="mentorBoardModifyLabel">수정하기</span>

        <input type="text" id="mentorBoardModifySubject" class="mentorBoardModifySubjectInput" placeholder="글제목"
          maxlength="50" />
        <!-- 메타 정보 -->
        <div class="mentorBoardModifyMeta">
          <span class="mentorBoardModifyViews">조회수 0</span>
          <span class="mentorBoardModifyCommentCount">댓글 0</span>
        </div>
      </div>

      <!-- 본문 입력 -->
      <textarea id="mentorBoardModifyContent" class="mentorBoardModifyContentTextarea"
        placeholder="수정할 글 내용"></textarea>

      <!-- 하단 버튼 -->
      <div class="mentorBoardModifyFooter">
        <button class="mentorBoardModifyBackBtn" id="mentorBoardModifyBackBtn">수정 취소</button>
        <div class="mentorBoardModifyActionGroup">
          <button class="mentorBoardModifySubmitBtn" id="mentorBoardModifySubmitBtn">수정</button>
          <button class="mentorBoardModifyDeleteBtn" id="mentorBoardModifyDeleteBtn">삭제</button>
        </div>
      </div>

    </div>
  </div>

  <div id="footerContainer"></div>

  <script src="${pageContext.request.contextPath}/assets/js/header.js"></script>
  <script src="${pageContext.request.contextPath}/assets/js/footer.js"></script>
  <script src="${pageContext.request.contextPath}/assets/js/user/mentor/mentorBoard/mentorBoardModify.js"></script>


</body>

</html>