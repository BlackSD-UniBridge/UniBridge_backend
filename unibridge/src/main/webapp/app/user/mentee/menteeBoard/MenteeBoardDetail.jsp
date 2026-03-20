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

  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/user/mentee/menteeBoard/menteeBoardDetail.css" />

</head>
<body>
   <%@ include file="/app/user/header.jsp"%>

  <!-- 헤더 -->
    <div class="container">
      <div class="view-wrap">
        <div class="view-title">
          <h1>
          	<c:out value="${MenteeBoard.boardTitle}" />
          </h1>
        </div>
        <div class="view-info">
          <div class="info-title">
            <div class="title-wrap">
              <span class="writer">작성자</span>
              <!-- 임시 작성자 -->
              <div class="content-writer">
              	<c:out value="${MenteeBoard.memberId}" />
              </div>
            </div>
            <div class="title-wrap">
              <span class="date">작성일</span>
              <div class="content-date">
              	<c:out value="${MenteeBoard.boardDate}" />
              </div>
            </div>
            <div class="title-wrap">
              <span class="hit">조회수</span>
              <!-- 임시 조회수 -->
              <div class="content-hit">
              	<c:out value="${MenteeBoard.boardClick}" />
              </div>
            </div>
            <div class="title-wrap">
            <span class="hit">댓글</span>
            <div class="content-hit"><c:out value="${commentTotal}" /></div>
          </div>
          </div>
        </div>
        
        <!-- 임시 내용 -->
        <div class="view-content">
        	<c:out value="${MenteeBoard.boardContent}" />
        </div>
        <div class="btn-group">
            <!-- 각 버튼 처리 경로 js로 수정하기 -->
            <button type="button" class="list-btn" data-board-number="${MenteeBoard.menteeBoardNumber}" data-member-number="${sessionScope.loginUser.memberNumber}">목록</button>
            <!-- 수정/삭제 버튼(로그인한 사용자가 작성자인 경우에만 표시) -->
            <c:if test="${sessionScope.loginUser.memberNumber == MenteeBoard.memberNumber}">
                <button type="button" class="modify-btn">수정</button>
          		<button type="button" class="delete-btn">삭제</button>
            </c:if>
        </div>
        
        <!-- ── 댓글 입력 폼 ── -->
      <div class="comment-wrap">
        <form class="comment-form"
              action="${pageContext.request.contextPath}/mentee/menteeBoard/MenteeBoardCommentWrite.meb"
              method="post">
          <input type="hidden" name="menteeBoardId" value="${MenteeBoard.menteeBoardNumber}" />
          <div class="comment-input-row">
            <span class="comment-writer-label">
              <c:choose>
                <c:when test="${not empty sessionScope.loginUser}">
                  <c:out value="${sessionScope.loginUser.memberNickname}" />
                </c:when>
                <c:otherwise>회원 닉네임</c:otherwise>
              </c:choose>
            </span>
            <textarea class="comment-input" name="menteeComContent"
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
                  <span class="comment-nickname">
                    <c:out value="${comment.memberNickname}" />
                    <c:if test="${sessionScope.loginUser.memberNumber == comment.memberNumber}">
                      <span class="comment-mine-badge">(본인)</span>
                    </c:if>
                  </span>
                  <span class="comment-content"><c:out value="${comment.menteeComContent}" /></span>
                  <span class="comment-date"><c:out value="${comment.menteeComDate}" /></span>
                </div>
                <c:if test="${sessionScope.loginUser.memberNumber == comment.memberNumber}">
                  <div class="comment-btn-group">
                    <button type="button" class="comment-modify-btn"
                            data-com-id="${comment.menteeComId}"
                            data-board-id="${MenteeBoard.menteeBoardNumber}"
                            data-content="${comment.menteeComContent}">수정</button>
                    <button type="button" class="comment-delete-btn"
                            data-com-id="${comment.menteeComId}"
                            data-board-id="${MenteeBoard.menteeBoardNumber}">삭제</button>
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

      <!-- ── 댓글 수정 폼 (hidden) ── -->
      <form id="comment-update-form"
            action="${pageContext.request.contextPath}/mentee/menteeBoard/MenteeBoardCommentUpdate.meb"
            method="post" style="display:none;">
        <input type="hidden" id="updateComId"   name="menteeComId"      />
        <input type="hidden" id="updateBoardId" name="menteeBoardId"    />
        <input type="hidden" id="updateContent" name="menteeComContent" />
      </form>

      <!-- ── 댓글 삭제 폼 (hidden) ── -->
      <form id="comment-delete-form"
            action="${pageContext.request.contextPath}/mentee/menteeBoard/MenteeBoardCommentDelete.meb"
            method="post" style="display:none;">
        <input type="hidden" id="deleteComId"   name="menteeComId"   />
        <input type="hidden" id="deleteBoardId" name="menteeBoardId" />
      </form>
      
      </div>
    </div>
    <script>
    	let memberNumber = "${sessionScope.loginUser.memberNumber}";
	  const contextPath = "${pageContext.request.contextPath}";
    </script>
  <script src="${pageContext.request.contextPath}/assets/js/user/mentee/menteeBoard/menteeBoardDetail.js"></script>


</body>
</html>