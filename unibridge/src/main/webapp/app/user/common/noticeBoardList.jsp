<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>공지사항 게시판</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/user/header.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/user/footer.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/user/common/noticeBoard.css" />
  </head>
  <body>
    <div id="headerContainer"></div>

    <div class="announceWrap">
      <h2 class="announceTitle">공지사항</h2>

      <!-- 게시글 목록 -->
      <div class="announceList">

        <!-- 헤더 행 -->
        <div class="announceHeaderRow">
          <span class="announceTh">번호</span>
          <span class="announceTh announceThCategory">분류</span>
          <span class="announceTh announceThTitle">제목</span>
          <span class="announceTh announceThDate">날짜</span>
          <span class="announceTh announceThAuthor">작성자</span>
          <span class="announceTh announceThViews">조회수</span>
        </div>

        <!-- 본문 행 -->
        <c:choose>
          <c:when test="${not empty noticeBoardList}">
            <c:forEach var="noticeBoard" items="${noticeBoardList}">
              <div class="announceRow"
                   onclick="location.href='${pageContext.request.contextPath}/noticeBoardRead.ntb?boardNumber=${noticeBoard.boardNumber}'">
                <span class="announceTd announceTdNum">
                  <c:out value="${noticeBoard.boardNumber}" />
                </span>
                <span class="announceTd">
                  <span class="announceCategoryBadge announce">공지</span>
                </span>
                <span class="announceTd announceTdTitle">
                  <c:out value="${noticeBoard.boardTitle}" />
                </span>
                <span class="announceTd announceTdDate">
                  <c:out value="${noticeBoard.boardDate}" />
                </span>
                <span class="announceTd announceTdAuthor">
                  <c:out value="${noticeBoard.memberId}" />
                </span>
                <span class="announceTd announceTdViews">
                  <c:out value="${noticeBoard.boardReadCount}" />
                </span>
              </div>
            </c:forEach>
          </c:when>
          <c:otherwise>
            <div class="announceRow">
              <span class="announceTd" style="flex:1; text-align:center;">등록된 게시물이 없습니다.</span>
            </div>
          </c:otherwise>
        </c:choose>

      </div><%-- announceList 닫힘 --%>

      <!-- 페이지네이션 -->
      <div class="announcePaginationWrap">
        <c:if test="${prev}">
          <button class="announcePageBtn"
                  onclick="location.href='${pageContext.request.contextPath}/noticeBoardList.ntb?page=${startPage - 1}'">
            &lt;
          </button>
        </c:if>

        <c:set var="realStartPage" value="${startPage < 1 ? 1 : startPage}" />
        <c:forEach var="i" begin="${realStartPage}" end="${endPage}">
          <c:choose>
            <c:when test="${i == page}">
              <button class="announcePageBtn active"><c:out value="${i}" /></button>
            </c:when>
            <c:otherwise>
              <button class="announcePageBtn"
                      onclick="location.href='${pageContext.request.contextPath}/noticeBoardList.ntb?page=${i}'">
                <c:out value="${i}" />
              </button>
            </c:otherwise>
          </c:choose>
        </c:forEach>

        <c:if test="${next}">
          <button class="announcePageBtn announcePageNext"
                  onclick="location.href='${pageContext.request.contextPath}/noticeBoardList.ntb?page=${endPage + 1}'">
            &gt;
          </button>
        </c:if>
      </div>

    </div><%-- announceWrap 닫힘 --%>

    <div id="footerContainer"></div>

    <script>
      let memberNumber = "${sessionScope.memberNumber}";
    </script>
    <script src="${pageContext.request.contextPath}/assets/js/user/header.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/user/footer.js"></script>
  </body>
</html>