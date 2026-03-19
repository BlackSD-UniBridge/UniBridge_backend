<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">

<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>UniBridge - 게시판 글 작성</title>
  <link
    href="https://fonts.googleapis.com/css2?family=Playfair+Display:wght@600&family=Noto+Sans+KR:wght@300;400;500;700&display=swap"
    rel="stylesheet" />
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/user/mentee/menteeBoard/menteeBoardCreate.css" />
</head>
<body>
<%@ include file="/app/user/header.jsp"%>
	<div class="container">
		<!-- 작성완료 경로 처리하기 -->
		<form id="write-form"
			action="${pageContext.request.contextPath}/mentee/menteeBoard/MenteeBoardWrite.meb"
			method="post" enctype="multipart/form-data">
			<h1>글쓰기</h1>
			<div class="form-group">
				<label for="menteeBoardCreateSubject">제목</label> 
				<input type="text" id="menteeBoardCreateSubject"
					name="MenteeBoardTitle" required />
			</div>
			<div class="form-group">
				<label for="author">작성자</label>
				<!-- 작성자 서버 연결시 로그인한 회원 아이디로 수정하기 -->
				<!-- <div class="writer">홍길동</dv> -->
				<div class="writer">
					<c:out value="${sessionScope.memberId}" />
				</div>
			</div>
			<div class="form-group">
				<label for="menteeBoardCreateContent">내용</label>
				<textarea id="menteeBoardCreateContent" name="MenteeBoardContent" required></textarea>
			</div>
			
			<div class="btn-group">
				<button type="submit" class="submit-btn">작성 완료</button>
				<!-- 취소 버튼 js로 처리하기 -->
				<button type="button" class="cancle-btn">취소</button>
			</div>
		</form>
	</div>
<%@ include file="/app/user/footer.jsp"%>
<script>
  const contextPath = "${pageContext.request.contextPath}";
</script>
  <script src="${pageContext.request.contextPath}/assets/js/user/mentee/menteeBoard/menteeBoardCreate.js"></script>

</body>

</html>