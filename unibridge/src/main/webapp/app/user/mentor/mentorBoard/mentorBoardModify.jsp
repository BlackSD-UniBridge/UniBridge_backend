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
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/user/mentor/mentorBoard/mentorBoardModify.css" />
</head>

<body>
<%@ include file="/app/user/header.jsp"%>
  <div id="container">
    <form id="modify-form"
      action="${pageContext.request.contextPath}/mentor/mentorBoard/MentorBoardUpdate.mob"
      method="post" enctype="multipart/form-data">

      <input type="hidden" name="MentorBoardNumber" value="${MentorBoard.mentorBoardNumber}" />
      <div class="mentorBoardModifyWrap">

        <div class="mentorBoardModifyHeader">
          <span class="mentorBoardModifyLabel">수정하기</span>
          <input type="text" id="mentorBoardModifySubject" name="MentorBoardTitle"
            class="mentorBoardModifySubjectInput" value="${MentorBoard.boardTitle}"
            placeholder="글제목" maxlength="50" />
          <div class="mentorBoardModifyMeta">
            <span class="mentorBoardModifyViews">조회수 <c:out value="${MentorBoard.boardClick}" /></span>
          </div>
        </div>

        <textarea id="mentorBoardModifyContent" name="MentorBoardContent"
          class="mentorBoardModifyContentTextarea"
          placeholder="수정할 글 내용"><c:out value="${MentorBoard.boardContent}" /></textarea>

        <div class="mentorBoardModifyFooter">
          <button type="button" class="mentorBoardModifyBackBtn" id="mentorBoardModifyBackBtn">수정 취소</button>
          <div class="mentorBoardModifyActionGroup">
            <button class="mentorBoardModifySubmitBtn" id="mentorBoardModifySubmitBtn">수정</button>
            <button class="mentorBoardModifyDeleteBtn" id="mentorBoardModifyDeleteBtn">삭제</button>
          </div>
        </div>

      </div>
    </form>
  </div>

  <script>
    const contextPath = "${pageContext.request.contextPath}";
  </script>
  <script src="${pageContext.request.contextPath}/assets/js/user/mentor/mentorBoard/mentorBoardModify.js"></script>

</body>
</html>
