<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>공지사항 확인</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/user/common/noticeBoardDetail.css" />
  </head>
  <body>
  <%@ include file="/app/user/header.jsp"%>
    <div class="headerContainer">
      <div class="announceDetailWrap">
              <!-- 제목 -->
      <div class="announceDetailHeader">
        <span class="announceDetailBadge">공지</span>
        <%-- ▼ noticeBoardTitle → boardTitle (NoticeBoardListDTO 필드명) --%>
        <h2 class="announceDetailTitle">
          <c:out value="${noticeBoard.boardTitle}" />
        </h2>
      </div>

      <!-- 메타 정보 -->
      <div class="announceDetailMeta">
        <span class="announceDetailAuthor">
          작성자 :
          <%-- ▼ memberId는 그대로 --%>
          <c:out value="${noticeBoard.memberId}" />
          &nbsp;|&nbsp;
          작성일 :
          <%-- ▼ noticeBoardDate → boardDate --%>
          <c:out value="${noticeBoard.boardDate}" />
        </span>
        <span class="announceDetailViews">
          조회수 :
          <%-- ▼ noticeBoardReadCount → boardReadCount --%>
          <c:out value="${noticeBoard.boardReadCount}" />
        </span>
      </div>

      <!-- 본문 -->
      <div class="announceDetailBody">
        <%-- ▼ noticeBoardContent → boardContent --%>
        <c:out value="${noticeBoard.boardContent}" />
      </div>

      <!-- 아마도 첨부파일 (추후 구현) -->
      <%--
      <c:forEach var="file" items="${noticeBoard.files}">
        <div class="img-box">
          <img src="${pageContext.request.contextPath}/upload/${file.fileSystemName}" />
        </div>
      </c:forEach>
      --%>

      <!-- 버튼 -->
      <div>
        <button type="button"
                class="announceDetailBackBtn list-btn"
                data-board-number="${noticeBoard.boardNumber}"
                data-member-number="${sessionScope.memberNumber}">
          목록
        </button>
        <c:if test="${sessionScope.memberNumber == noticeBoard.memberNumber}">
          <button type="button" class="modify-btn">수정</button>
          <button type="button" class="delete-btn">삭제</button>
        </c:if>
      </div>

    </div><%-- announceDetailWrap 닫힘 --%>

    <div id="footerContainer"></div>

    <script>
      let memberNumber = "${sessionScope.memberNumber}";
    </script>
    <%@ include file="/app/user/footer.jsp"%>
    <script src="${pageContext.request.contextPath}/assets/js/user/common/noticeBoardRead.js"></script>
  </body>
</html>