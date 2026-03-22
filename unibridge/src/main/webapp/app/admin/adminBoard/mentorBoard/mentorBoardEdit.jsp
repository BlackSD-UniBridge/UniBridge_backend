<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
  <title>게시판 관리 - 게시글 수정</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/admin/adminBoard/boardEdit.css" />
</head>
<body>

  <div id="header-wrap"></div>

  <form method="post" action="${pageContext.request.contextPath}/mentorBoardEditOk.admin" class="form-wrap">
    <h1 class="page-title">게시판 관리</h1>
    <h1 class="page-title">수정하기</h1>

    <!-- 수정하기 제목 행 -->
    <input type="hidden" name="boardNumber" value="${board.mentorboardNumber}">
    <div class="form-title-row" style="margin-top: 28px;">
      <span class="form-title-label">제목</span>
      <input
        type="text"
        id="inputTitle"
        class="form-title-input"
        value ="${board.boardTitle}"
        name="boardTitle"/>
    </div>

    <!-- 내용 입력 -->
    <textarea id="inputContent" class="form-content-area" name="boardContent">${board.boardContent}</textarea>

    <!-- 버튼 -->
    <div class="form-actions">
      <button type="submit" class="btn btn-blue" id="btnSave">저장</button>
      <button type="button" class="btn btn-red" id="btnEditCancel">취소</button>
    </div>
  </form>

<script>
  fetch("${pageContext.request.contextPath}/header/adminHeader.jsp")
    .then(res => res.text())
    .then(html => {
      document.getElementById("header-wrap").innerHTML = html;
      const s = document.createElement("script");
      s.src = "${pageContext.request.contextPath}/header/assets/adminHeader.js";
      document.body.appendChild(s);
    });
</script>
  <script>
  	const boardNumber = ${board.mentorboardNumber};
  </script>
  <script src="${pageContext.request.contextPath}/assets/js/admin/adminBoard/boardEdit.js"></script>
</body>
</html>
