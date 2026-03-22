<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 관리 - 멘토 게시글</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/admin/adminBoard/boardDetail.css" />
<script defer src="${pageContext.request.contextPath}/assets/js/admin/adminBoard/boardDetail.js"></script>
</head>
<body>

  <div id="header-wrap"></div>

  <div class="detail-wrap">
    <h1 class="page-title">게시판 관리</h1>

    <!-- 제목 + 메타 -->
    <div class="detail-header">
      <div class="detail-title-row">
        <span class="detail-title-label">제목)</span>
        <span class="detail-title-text">${board.boardTitle}</span>
      </div>
      <div class="detail-meta">
        <span>${board.boardDate}</span>
        <span>조회수 ${board.boardClick}</span>
        <span>댓글 0</span>
      </div>
    </div>

    <!-- 본문 -->
    <div class="detail-content-box">${board.boardContent}</div>

    <!-- 작성자 + 삭제 -->
    <div class="detail-author-row">
      <span class="detail-author">작성자 : ${board.getWriteNickname()}</span>
      <div id="postActions" style="display:flex; gap:12px;"></div>
    </div>

    <!-- 댓글 -->
    <div class="comment-list" id="commentList"></div>
  </div>

<script>
  fetch("${pageContext.request.contextPath}/header/adminHeader.jsp")
    .then(res => res.text())
    .then(html => {
      document.getElementById("header-wrap").innerHTML = html;
      const s = document.createElement("script");
      s.src = "${pageContext.request.contextPath}/assets/js/adminHeader.js";
      document.body.appendChild(s);
    });
</script>
	<script>
		const writeNumber = ${board.writeNumber};
		const sessionNumber = ${loginMemberNumber};
		const boardNumber = ${board.mentorboardNumber};
	</script>
  
</body>
</html>
