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
              <!-- 임시 작성일 -->
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
          </div>
          <!-- <div class="info-content">
            <span class="content-writer">작성자</span>
            <span class="content-date">작성일</span>
            <span class="content-hit">조회수</span>
          </div> -->
        </div>
        <!-- 임시 내용 -->
        <div class="view-content">
        	<c:out value="${MenteeBoard.boardContent}" />
        </div>
        <!-- 임시 첨부 파일 -->
        <%-- <div class="view-attach">
        	<c:forEach var="file" items="${board.files}" >
        		<div class="img-box">
        			<img src="${pageContext.request.contextPath}/upload/${file.fileSystemName}" />
        			
        			<!-- 다운로드 버튼 -->
        			<!--  다운로드 받기 위해서는 시스템 이름이 필요하고 사용자에게 파일을 줄 때는 오리지널 네임으로 줘야한다 -->
        			<a href="${pageContext.request.contextPath}/file/download.file?fileSystemName="${file.fileOriginalName}">
        				<div class="download-btn">
        					<svg viewBox="0 0 14 14" xmlns="http://www.w3.org/2000/svg">
								<path fill-rule="evenodd" clip-rule="evenodd"
							</svg>
        				</div>
        			
        			</a>
        			
        		</div>
        	</c:forEach>
        </div> --%>
        <div class="btn-group">
            <!-- 각 버튼 처리 경로 js로 수정하기 -->
            <button type="button" class="list-btn" data-board-number="${MenteeBoard.menteeBoardNumber}" data-member-number="${sessionScope.loginUser.memberNumber}">목록</button>
            <!-- 수정/삭제 버튼(로그인한 사용자가 작성자인 경우에만 표시) -->
            <c:if test="${sessionScope.loginUser.memberNumber == MenteeBoard.memberNumber}">
                <button type="button" class="modify-btn">수정</button>
          		<button type="button" class="delete-btn">삭제</button>
            </c:if>
        </div>
        
        <!--  댓글 -->
      </div>
    </div>
    <script>
    	let memberNumber = "${sessionScope.loginUser.memberNumber}";
	  const contextPath = "${pageContext.request.contextPath}";
    </script>
  <script src="${pageContext.request.contextPath}/assets/js/user/mentee/menteeBoard/menteeBoardDetail.js"></script>


</body>
</html>