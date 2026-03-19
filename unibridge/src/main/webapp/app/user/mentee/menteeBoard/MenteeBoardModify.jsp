<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">

<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>UniBridge - 게시글 수정</title>
  <link
    href="https://fonts.googleapis.com/css2?family=Playfair+Display:wght@600&family=Noto+Sans+KR:wght@300;400;500;700&display=swap"
    rel="stylesheet" />
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/user/mentee/menteeBoard/menteeBoardModify.css" />

</head>

<body>
<%@ include file="/app/user/header.jsp"%>
  <!-- 헤더 -->
  <div id="container">
  <form id="modify-form"
      action="${pageContext.request.contextPath}/mentee/menteeBoard/MenteeBoardUpdate.meb"
      method="post" enctype="multipart/form-data">
      
      <input type="hidden" name="MenteeBoardNumber" value="${MenteeBoard.menteeBoardNumber}" />
	  <div class="menteeBoardModifyWrap">
	
	      <!-- 제목 영역 -->
	      <div class="menteeBoardModifyHeader">
	        <span class="menteeBoardModifyLabel">수정하기</span>
	
	        <input type="text" id="menteeBoardModifySubject" name="MenteeBoardTitle"
	        class="menteeBoardModifySubjectInput" value="${MenteeBoard.boardTitle}"
	        placeholder="글제목" maxlength="50" />
	        <!-- 메타 정보 -->
	        <div class="menteeBoardModifyMeta">
	          <span class="menteeBoardModifyViews">조회수 <c:out value="${MenteeBoard.boardClick}" /></span>
	          <%-- <span class="menteeBoardModifyCommentCount">댓글 <c:out value="${MenteeBoard.boardClick}" /></span> --%>
	        </div>
	      </div>
	
	      <!-- 본문 입력 -->
	      <textarea id="menteeBoardModifyContent" name="MenteeBoardContent"
	      class="menteeBoardModifyContentTextarea"
	        placeholder="수정할 글 내용"><c:out value="${MenteeBoard.boardContent}" /></textarea>
	
	      <!-- 하단 버튼 -->
	      <div class="menteeBoardModifyFooter">
	        <button type="button" class="menteeBoardModifyBackBtn" id="menteeBoardModifyBackBtn">수정 취소</button>
	        <div class="menteeBoardModifyActionGroup">
	          <button class="menteeBoardModifySubmitBtn" id="menteeBoardModifySubmitBtn">수정</button>
	          <button class="menteeBoardModifyDeleteBtn" id="menteeBoardModifyDeleteBtn">삭제</button>
	        </div>
	      </div>
	
	    </div>
    </form>
  </div>

  <div id="footerContainer"></div>
  <script>
	  const contextPath = "${pageContext.request.contextPath}";
	</script>

  <script src="${pageContext.request.contextPath}/assets/js/user/mentee/menteeBoard/menteeBoardModify.js"></script>

</body>

</html>