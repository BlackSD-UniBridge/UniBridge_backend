<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 관리 - 멘토 게시글</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/admin/adminBoard/boardDetail.css" />
<script defer
	src="${pageContext.request.contextPath}/assets/js/admin/adminBoard/boardDetail.js"></script>
</head>
<body>

	<div id="header-wrap"></div>

	<div class="detail-wrap">
		<h1 class="page-title">게시판 관리</h1>

		<div class="detail-header">
			<div class="detail-title-row">
				<span class="detail-title-label">제목)</span> <span
					class="detail-title-text">${board.boardTitle}</span>
			</div>
		</div>

		<div class="detail-meta">
			<span>작성자 ${board.getWriteNickname()}</span> <span>작성일
				${board.boardDate}</span> <span>조회수 ${board.boardClick}</span> <span>댓글
				${commentList.size()}</span>
		</div>

		<div class="detail-content-box">${board.boardContent}</div>

		<div class="detail-actions">
			<button class="btn" onclick="history.back()">목록</button>
			<div id="postActions" style="display: flex; gap: 12px;"></div>
		</div>

		<hr class="section-divider" />

		<div class="comment-list" id="commentList">
			<c:choose>
				<c:when test="${not empty commentList}">
					<c:forEach var="comment" items="${commentList}">
						<div class="comment-item" data-cid="${comment.mentorComNumber}">
							<div class="comment-body">
								<div class="comment-nickname">${comment.memberNickname}</div>
								<div class="comment-content">${comment.mentorComContent}</div>
							</div>
							<div class="comment-date">${comment.mentorComDate}</div>
							<button class="btn btn-red"
								onclick="deleteComment(${comment.mentorComNumber})">삭제</button>
						</div>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<p style="text-align: center; color: #888; padding: 20px;">등록된
						댓글이 없습니다.</p>
				</c:otherwise>
			</c:choose>
		</div>
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
		// 하단 스크립트 변수들도 멘토 게시판 프로퍼티에 맞게 설정
		const writeNumber = ${board.writeNumber};
		const sessionNumber = ${loginMemberNumber};
		const boardNumber = ${board.mentorboardNumber}; 
	</script>

</body>
</html>