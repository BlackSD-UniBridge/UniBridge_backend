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
      <div class="announceDetailWrap">
              <!-- 제목 -->
      <div class="announceDetailHeader">
        <span class="announceDetailBadge">
          <c:choose>
            <c:when test="${not empty noticeBoard.boardType}">
              <c:out value="${noticeBoard.boardType}" />
            </c:when>
            <c:otherwise>공지</c:otherwise>
          </c:choose>
        </span>
        <h2 class="announceDetailTitle">
          <c:out value="${noticeBoard.boardTitle}" />
        </h2>
      </div>

      <!-- 메타 정보 -->
      <div class="announceDetailMeta">
        <span class="announceDetailAuthor">
          작성자 :
          <%-- ▼ memberId는 그대로 --%>
          <c:out value="${noticeBoard.boardUserId}" />
          &nbsp;|&nbsp;
          작성일 :
          <%-- ▼ noticeBoardDate → boardDate --%>
          <c:out value="${noticeBoard.boardDate}" />
        </span>
        <span class="announceDetailViews">
          조회수 :
          <%-- ▼ noticeBoardClick → boardClick --%>
          <c:out value="${noticeBoard.boardClick}" />
        </span>
      </div>

      <!-- 본문 -->
      <div class="announceDetailBody">
        <%-- ▼ noticeBoardContent → boardContent --%>
        <c:out value="${noticeBoard.boardContent}" />
      </div>

      <!-- 아마도 첨부파일 (추후 구현) -->
      <c:if test="${not empty attachedFile}">
        <div class="announceDetailFileWrap">
          <span class="announceDetailFileLabel">첨부파일</span>
          <a class="announceDetailFileLink"
             href="${pageContext.request.contextPath}/file/download.file?fileNumber=${attachedFile.fileNumber}"
             download="${attachedFile.fileOriginalName}">
            <%-- 📎 아이콘 + 원본 파일명 표시 --%>
            &#128206; <c:out value="${attachedFile.fileOriginalName}" />
            <span class="announceDetailFileSize">
              <%-- 파일 크기를 KB 단위로 표시 --%>
              (<c:out value="${attachedFile.fileSize / 1024}" />KB)
            </span>
          </a>
        </div>
      </c:if>

      <!-- 버튼 -->
      <div class="announceDetailBtnWrap">
        <button type="button"
                class="announceDetailBackBtn list-btn"
                data-board-number="${noticeBoard.noticeBoardNumber}"> 목록
        </button>

    </div><%-- announceDetailWrap 닫힘 --%>

    <div id="footerContainer"></div>

    <script src="${pageContext.request.contextPath}/assets/js/user/common/noticeBoardRead.js"></script>
  </body>
</html>