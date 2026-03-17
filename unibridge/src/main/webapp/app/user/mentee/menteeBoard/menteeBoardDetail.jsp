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
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/user/mentee/menteeBoard/menteeBoardDetail.css" />

</head>
<body>

  <!-- 헤더 -->
  <div id="headerContainer"></div>

  <div class="pageContent">
    <div class="menteeBoardDetailWrap">

      <!-- 제목 -->
      <div class="menteeBoardDetailHeader">
        <span class="menteeBoardDetailBadge">제목</span>
        <h1 class="menteeBoardDetailTitle">글제목</h1>
      </div>

      <!-- 메타 정보 -->
      <div class="menteeBoardDetailMeta">
        <span class="menteeBoardDetailAuthor">작성자</span>
        <div class="menteeBoardDetailMetaRight">
          <span class="menteeBoardDetailViews">조회수 0</span>
          <span class="menteeBoardDetailCommentCount">댓글 0</span>
        </div>
      </div>

      <!-- 본문 -->
      <div class="menteeBoardDetailBody">
        글 내용
      </div>

      <!-- 글목록 / 수정 / 삭제 -->
      <div class="menteeBoardDetailBtnWrap">
        <button class="menteeBoardDetailBackBtn" id="menteeBoardDetailBackBtn">글목록</button>
        <div class="menteeBoardDetailActionGroup">
          <button class="menteeBoardDetailEditBtn" id="menteeBoardDetailEditBtn">수정</button>
          <button class="menteeBoardDetailDeleteBtn" id="menteeBoardDetailDeleteBtn">삭제</button>
        </div>
      </div>

      <!-- 댓글 입력 -->
      <div class="menteeBoardDetailCommentForm">
        <div class="menteeBoardDetailCommentNic">회원 닉네임</div>
        <textarea
          id="menteeBoardDetailCommentInput"
          class="menteeBoardDetailCommentInput"
          placeholder="댓글내용"
        ></textarea>
        <button class="menteeBoardDetailCommentSubmitBtn" id="menteeBoardDetailCommentSubmitBtn">등록</button>
      </div>

      <!-- 댓글 목록 -->
      <div class="menteeBoardDetailCommentList">

        <!-- 다른 사람 댓글 -->
        <div class="menteeBoardDetailCommentItem" data-comment-id="1">
          <div class="menteeBoardDetailCommentItemTop">
            <span class="menteeBoardDetailCommentDate">작성날짜 작성시간</span>
          </div>
          <div class="menteeBoardDetailCommentBody">
            <span class="menteeBoardDetailCommentNicname">닉네임</span>
            <span class="menteeBoardDetailCommentText">다른 사람 댓글내용</span>
          </div>
        </div>

        <!-- 본인 댓글 -->
        <div class="menteeBoardDetailCommentItem" data-comment-id="2">
          <div class="menteeBoardDetailCommentItemTop">
            <span class="menteeBoardDetailCommentDate">작성날짜 작성시간</span>
          </div>
          <div class="menteeBoardDetailCommentBody">
            <span class="menteeBoardDetailCommentNicname">닉네임(본인)</span>
            <span class="menteeBoardDetailCommentText">내가 등록한 댓글내용</span>
            <div class="menteeBoardDetailCommentActions">
              <span class="menteeBoardDetailCommentNewBadge">현재</span>
              <button class="menteeBoardDetailCommentEditBtn">수정</button>
              <button class="menteeBoardDetailCommentDeleteBtn">삭제</button>
            </div>
          </div>
        </div>

        <!-- 다른 사람 댓글 -->
        <div class="menteeBoardDetailCommentItem" data-comment-id="3">
          <div class="menteeBoardDetailCommentItemTop">
            <span class="menteeBoardDetailCommentDate">작성날짜 작성시간</span>
          </div>
          <div class="menteeBoardDetailCommentBody">
            <span class="menteeBoardDetailCommentNicname">닉네임</span>
            <span class="menteeBoardDetailCommentText">다른 사람 댓글내용</span>
          </div>
        </div>

      </div>

    </div>
  </div>

  <div id="footerContainer"></div>

  <script src="${pageContext.request.contextPath}/assets/js/header.js"></script>
  <script src="${pageContext.request.contextPath}/assets/js/footer.js"></script>
  <script src="${pageContext.request.contextPath}/assets/js/user/mentee/menteeBoard/menteeBoardDetail.js"></script>


</body>
</html>