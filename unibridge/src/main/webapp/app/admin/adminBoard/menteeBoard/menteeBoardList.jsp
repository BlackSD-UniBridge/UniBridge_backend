<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
  <title>게시판 관리 - 멘티</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/admin/adminBoard/boardList.css" />
</head>
<body>

  <div id="header-wrap"></div>

  <div class="page-wrap">
    <h1 class="page-title">게시판 관리</h1>

    <!-- 탭 -->
    <div class="board-tabs">
      <a id="tab-mentee" href="${pageContext.request.contextPath}/app/admin/adminBoard/menteeBoard/menteeBoardList.admin" class="board-tab">멘티 게시판</a>
      <a id="tab-mentor" href="${pageContext.request.contextPath}/app/admin/adminBoard/mentorBoard/mentorBoardList.admin" class="board-tab">멘토 게시판</a>
    </div>

    <!-- 필터 행 -->
    <div class="filter-row">
      <div class="filter-date">
        <input type="date" class="input-date" id="dateFrom" />
        <span class="tilde">~</span>
        <input type="date" class="input-date" id="dateTo" />
      </div>
      <button class="btn" id="btnSearch">조회</button>
      <button class="btn btn-primary" id="btnWrite">+ 게시글 작성</button>
    </div>

    <!-- 테이블 -->
    <div class="board-table">
      <div class="board-table-head">
        <div class="col-no">번호</div>
        <div class="col-title">제목</div>
        <div class="col-author">작성자</div>
        <div class="col-date">작성일</div>
        <div class="col-views">조회수</div>
      </div>
      <div id="boardTableBody">
      
		<c:choose>
			<c:when test="${not empty boardList}">
			<%-- <c:when test="${not empty dateFrom && not empty dateTo }"> --%>
		      <c:forEach var="board" items="${boardList}">
		          <a class="board-table-row" href="${pageContext.request.contextPath}/app/admin/adminBoard/menteeBoard/menteeBoardDetail.admin?boardNumber=${board.menteeboardNumber}" >
		             <div class="col col-no">
		                <c:out value="${board.getMenteeboardNumber()}" />
		             </div>
		             <div class="col col-title">
		                <div>
		                   <c:out value="${board.getBoardTitle()}" />
		                </div>
		             </div>
		             <div class="col col-author text-muted">
		                <c:out value="${board.getWriteNickname() }" />
		             </div>
		             <div class="col col-date text-muted">
		                <c:out value="${board.getBoardDate() }" />
		             </div>
		             <div class="col col-views text-muted">
		                <c:out value="${board.getBoardClick() }" />
		             </div>
		          </a>
		       </c:forEach>
		   <%-- </c:when> --%>
		   </c:when>
		   <c:otherwise>
		      <div>
		         <div colspan="10" align="center">등록된 게시물이 없습니다.</div>
		      </div>
		   </c:otherwise>
		</c:choose>
    
      </div>
    </div>

    <!-- 페이지네이션 -->
    <div class="pagination" id="pagination">          
      <c:if test="${prev}">
      	<button onclick= 'location.href = "${pageContext.request.contextPath}/app/admin/adminBoard/menteeBoard/menteeBoardList.admin?page=${startPage - 1}"' class="page-btn page-btn-nav">&lsaquo;</button>
      </c:if>
      <c:set var="realStartPage" value="${startPage < 0 ? 0:startPage}" />
      <c:forEach var="i" begin="${realStartPage}" end="${endPage}">
      	<c:choose>
      		<c:when test="${!(i == page)}">
      			<button class="page-btn" onclick='location.href = "${pageContext.request.contextPath}/app/admin/adminBoard/menteeBoard/menteeBoardList.admin?page=${i}"' >
      				<c:out value="${i}" />
      			</button>
      		</c:when>
      		<c:otherwise>
      			<button onclick="#" class="page-btn" class="is-active">
      				<c:out value="${i}" />
      			</button>
      		</c:otherwise>
      	</c:choose>
      </c:forEach>
      <c:if test="${next}">
      	<button onclick='location.href = "${pageContext.request.contextPath}/app/admin/adminBoard/menteeBoard/menteeBoardList.admin?page=${endPage + 1}"' class="page-btn page-btn-nav">&gt;</button>
      </c:if>
	</div>
  </div>
  

	<script>
	  fetch("${pageContext.request.contextPath}/header/adminHeader.jsp")
	    .then(res => res.text())
	    .then(html => {
	      document.getElementById("header-wrap").innerHTML = html;
	      const s = document.createElement("script");
	      s.src = "${pageContext.request.contextPath}/assets/js/header/adminHeader.js";
	      document.body.appendChild(s);
	    });
	</script>
	<script> const contextPath = "${pageContext.request.contextPath}"; </script>
	<script src="${pageContext.request.contextPath}/assets/js/admin/adminBoard/boardList.js"></script>
</body>
</html>
