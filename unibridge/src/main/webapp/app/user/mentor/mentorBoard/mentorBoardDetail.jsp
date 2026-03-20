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
      
      <!-- ── 댓글 입력 폼 ── -->
      <div class="comment-wrap">
        <form class="comment-form"
              action="${pageContext.request.contextPath}/mentor/mentorBoard/MentorBoardCommentWrite.mob"
              method="post">
          <input type="hidden" name="mentorBoardId" value="${MentorBoard.mentorBoardNumber}" />
          <div class="comment-input-row">
            <span class="comment-writer-label">
              <c:choose>
                <c:when test="${not empty sessionScope.loginUser}">
                  <c:out value="${sessionScope.loginUser.memberNickname}" />
                </c:when>
                <c:otherwise>회원 닉네임</c:otherwise>
              </c:choose>
            </span>
            <textarea class="comment-input" name="mentorComContent"
                      placeholder="댓글내용" rows="2"></textarea>
            <button type="submit" class="comment-submit-btn">등록</button>
          </div>
        </form>
      </div>

      <!-- ── 댓글 목록 ── -->
      <div class="comment-list-wrap">
        <c:choose>
          <c:when test="${not empty commentList}">
            <c:forEach var="comment" items="${commentList}">
              <div class="comment-item">
                <div class="comment-item-top">
                  <%-- 본인 댓글이면 (본인) 표시 --%>
                  <span class="comment-nickname">
                    <c:out value="${comment.memberNickname}" />
                    <c:if test="${sessionScope.loginUser.memberNumber == comment.memberNumber}">
                      <span class="comment-mine-badge">(본인)</span>
                    </c:if>
                  </span>
                  <span class="comment-content"><c:out value="${comment.mentorComContent}" /></span>
                  <span class="comment-date"><c:out value="${comment.mentorComDate}" /></span>
                </div>

                <%-- 본인 댓글에만 수정/삭제 버튼 표시 --%>
                <c:if test="${sessionScope.loginUser.memberNumber == comment.memberNumber}">
                  <div class="comment-btn-group">
                    <button type="button" class="comment-modify-btn"
                            data-com-id="${comment.mentorComId}"
                            data-board-id="${MentorBoard.mentorBoardNumber}"
                            data-content="${comment.mentorComContent}">수정</button>
                    <button type="button" class="comment-delete-btn"
                            data-com-id="${comment.mentorComId}"
                            data-board-id="${MentorBoard.mentorBoardNumber}">삭제</button>
                  </div>
                </c:if>
              </div>
            </c:forEach>
          </c:when>
          <c:otherwise>
            <div class="comment-empty">등록된 댓글이 없습니다.</div>
          </c:otherwise>
        </c:choose>
      </div>

      <!-- ── 댓글 수정 폼 (hidden, JS로 표시) ── -->
      <form id="comment-update-form"
            action="${pageContext.request.contextPath}/mentor/mentorBoard/MentorBoardCommentUpdate.mob"
            method="post" style="display:none;">
        <input type="hidden" id="updateComId"    name="mentorComId" />
        <input type="hidden" id="updateBoardId"  name="mentorBoardId" />
        <input type="hidden" id="updateContent"  name="mentorComContent" />
      </form>

      <!-- ── 댓글 삭제 폼 (hidden, JS로 submit) ── -->
      <form id="comment-delete-form"
            action="${pageContext.request.contextPath}/mentor/mentorBoard/MentorBoardCommentDelete.mob"
            method="post" style="display:none;">
        <input type="hidden" id="deleteComId"   name="mentorComId" />
        <input type="hidden" id="deleteBoardId" name="mentorBoardId" />
      </form>
      
    </div>
  </div>

  <script>
    let memberNumber = "${sessionScope.loginUser.memberNumber}";
    const contextPath = "${pageContext.request.contextPath}";
  </script>
  <script src="${pageContext.request.contextPath}/assets/js/user/mentor/mentorBoard/mentorBoardDetail.js"></script>

</body>
</html>
