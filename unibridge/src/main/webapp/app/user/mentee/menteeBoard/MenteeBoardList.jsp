<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">

<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>UniBridge - 멘티 게시판</title>
  <link
    href="https://fonts.googleapis.com/css2?family=Playfair+Display:wght@600&family=Noto+Sans+KR:wght@300;400;500;700&display=swap"
    rel="stylesheet" />
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/user/mentee/menteeBoard/menteeBoardList.css" />
</head>

<body>
    <%@ include file="/app/user/header.jsp"%>

<div class="container">
      <div class="write-btn-wrap">
        <!-- 글쓰기 페이지 이동 처리 -->
        <a href="${pageContext.request.contextPath}/mentee/menteeBoard/MenteeBoardWrite.meb" class="write-btn">글쓰기</a>
      </div>

      <!-- 게시글 목록 -->
      <div class="board-list">
        <div class="board-header">
          <span class="no">번호</span>
          <span class="title">제목</span>
          <span class="author">작성자</span>
          <span class="date">날짜</span>
          <span class="hit">조회수</span>
        </div>

         <div class="board-body">
            <c:choose>
               <c:when test="${not empty MenteeBoardList}">
                  <c:forEach var="board" items="${MenteeBoardList}">
                      <div class="board-row">
                         <div class="board-item no">
                            <c:out value="${board.menteeBoardNumber}" />
                         </div>
                         <div class="board-item title">
                            <a href="${pageContext.request.contextPath}/mentee/menteeBoard/MenteeBoardRead.meb?MenteeBoardNumber=${board.menteeBoardNumber}">
                               <c:out value="${board.boardTitle}" />
                            </a>
                         </div>
                         <div class="board-item author">
                            <c:out value="${board.memberId}" />
                         </div>
                         <div class="board-item date">
                            <c:out value="${board.boardDate}" />
                         </div>
                         <div class="board-item hit">
                            <c:out value="${board.boardClick}" />
                         </div>
                      </div>
                   </c:forEach>
               </c:when>
               <c:otherwise>
                  <div>
                     <div colspan="5" align="center">등록된 게시물이 없습니다.</div>
                  </div>
               </c:otherwise>
            </c:choose>
         </div>
      </div>
      <div class="pagination">
        <ul>
          <!-- 페이징 처리 -->
<!--           <li><a href="#" class="prev">&lt;</a></li>
          <li><a href="#" class="active">1</a></li>
          <li><a href="#">2</a></li>
          <li><a href="#">3</a></li>
          <li><a href="#">4</a></li>
          <li><a href="#">5</a></li>
          <li><a href="#" class="next">&gt;</a></li> -->
          
          <c:if test="${prev}">
          	<li><a href="${pageContext.request.contextPath}/mentee/menteeBoard/MenteeBoardList.meb?page=${startPage - 1}" class="prev">&lt;</a></li>
          </c:if>
          <c:set var="realStartPage" value="${startPage < 1 ? 1:startPage}" />
          <c:forEach var="i" begin="${realStartPage}" end="${endPage}">
          	<c:choose>
          <c:when test="${i == page}">
            <li><a href="#" class="active"><c:out value="${i}" /></a></li>
          </c:when>
          <c:otherwise>
            <li><a href="${pageContext.request.contextPath}/mentee/menteeBoard/MenteeBoardList.meb?page=${i}"><c:out value="${i}" /></a></li>
          </c:otherwise>
        </c:choose>
      </c:forEach>
      <c:if test="${next}">
        <li><a href="${pageContext.request.contextPath}/mentee/menteeBoard/MenteeBoardList.meb?page=${endPage + 1}" class="next">&gt;</a></li>
      </c:if>
    </ul>
  </div>
</div>
	<script>
		let memberNumber = "${sessionScope.memberNumber}";
		const contextPath = "${pageContext.request.contextPath}";
	</script>
  <script src="${pageContext.request.contextPath}/assets/js/user/mentee/menteeBoard/menteeBoardList.js"></script>

</body>

</html>