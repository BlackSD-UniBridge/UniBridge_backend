<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>UniBridge - 게시판 상세</title>
  <link
    href="https://fonts.googleapis.com/css2?family=Playfair+Display:wght@600&family=Noto+Sans+KR:wght@300;400;500;700&display=swap"
    rel="stylesheet" />
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/header.css" />
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/footer.css" />
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/user/mentor/mentorBoard/mentorBoardDetail.css" />
</head>


<body>

  <!-- 헤더 -->
  <div id="headerContainer"></div>

  <div class="pageContent">
    <div class="mentorBoardDetailWrap">

      <!-- 제목 -->
      <div class="mentorBoardDetailHeader">
        <span class="mentorBoardDetailBadge">제목</span>
        <h1 class="mentorBoardDetailTitle">글제목</h1>
      </div>

      <!-- 메타 정보 -->
      <div class="mentorBoardDetailMeta">
        <span class="mentorBoardDetailAuthor">작성자</span>
        <div class="mentorBoardDetailMetaRight">
          <span class="mentorBoardDetailViews">조회수 0</span>
          <span class="mentorBoardDetailCommentCount">댓글 0</span>
        </div>
      </div>

      <!-- 본문 -->
      <div class="mentorBoardDetailBody">
        글 내용
      </div>

      <!-- 글목록 / 수정 / 삭제 -->
      <div class="mentorBoardDetailBtnWrap">
        <button class="mentorBoardDetailBackBtn" id="mentorBoardDetailBackBtn">글목록</button>
        <div class="mentorBoardDetailActionGroup">
          <button class="mentorBoardDetailEditBtn" id="mentorBoardDetailEditBtn">수정</button>
          <button class="mentorBoardDetailDeleteBtn" id="mentorBoardDetailDeleteBtn">삭제</button>
        </div>
      </div>

      <!-- 댓글 입력 -->
      <div class="mentorBoardDetailCommentForm">
        <span class="mentorBoardDetailCommentNic">회원 닉네임</span>
        <textarea id="mentorBoardDetailCommentInput" class="mentorBoardDetailCommentInput"
          placeholder="댓글내용"></textarea>
        <button class="mentorBoardDetailCommentSubmitBtn" id="mentorBoardDetailCommentSubmitBtn">등록</button>
      </div>

      <!-- 댓글 목록 -->
      <div class="mentorBoardDetailCommentList">

        <!-- 다른 사람 댓글 -->
        <div class="mentorBoardDetailCommentItem" data-comment-id="1">
          <div class="mentorBoardDetailCommentItemTop">
            <span class="mentorBoardDetailCommentDate">작성날짜 작성시간</span>
          </div>
          <div class="mentorBoardDetailCommentBody">
            <span class="mentorBoardDetailCommentNicname">닉네임</span>
            <span class="mentorBoardDetailCommentText">다른 사람 댓글내용</span>
          </div>
        </div>

        <!-- 본인 댓글 -->
        <div class="mentorBoardDetailCommentItem" data-comment-id="2">
          <div class="mentorBoardDetailCommentItemTop">
            <span class="mentorBoardDetailCommentDate">작성날짜 작성시간</span>
          </div>
          <div class="mentorBoardDetailCommentBody">
            <span class="mentorBoardDetailCommentNicname">닉네임(본인)</span>
            <span class="mentorBoardDetailCommentText">내가 등록한 댓글내용</span>
            <div class="mentorBoardDetailCommentActions">
              <span class="mentorBoardDetailCommentNewBadge">현재</span>
              <button class="mentorBoardDetailCommentEditBtn">수정</button>
              <button class="mentorBoardDetailCommentDeleteBtn">삭제</button>
            </div>
          </div>
        </div>

        <!-- 다른 사람 댓글 -->
        <div class="mentorBoardDetailCommentItem" data-comment-id="3">
          <div class="mentorBoardDetailCommentItemTop">
            <span class="mentorBoardDetailCommentDate">작성날짜 작성시간</span>
          </div>
          <div class="mentorBoardDetailCommentBody">
            <span class="mentorBoardDetailCommentNicname">닉네임</span>
            <span class="mentorBoardDetailCommentText">다른 사람 댓글내용</span>
          </div>
        </div>

      </div>

    </div>
  </div>

  <div id="footerContainer"></div>

  <script src="${pageContext.request.contextPath}/assets/js/header.js"></script>
  <script src="${pageContext.request.contextPath}/assets/js/footer.js"></script>
  <script src="${pageContext.request.contextPath}/assets/js/user/mentor/mentorBoard/mentorBoardDetail.js"></script>

</body>

</html>