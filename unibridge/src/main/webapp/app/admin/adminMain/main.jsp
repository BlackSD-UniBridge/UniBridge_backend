<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>UniBridge 관리자 메인</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/admin/adminMain/main.css" />
</head>
<body>
  <div class="admin-main-page">

    <!-- 사이드 메뉴 -->
    <aside class="side-menu">
      <div class="side-logo">
        <img src="${pageContext.request.contextPath}/assets/img/UniBridge.png" alt="UniBridge 로고" />
      </div>

      <nav class="side-nav">

        <!-- 공지사항 관리 -->
        <a href="${pageContext.request.contextPath}/app/admin/adminNotice/noticeList.admin" class="side-link">
          <span class="side-icon" aria-hidden="true">
            <img src="${pageContext.request.contextPath}/assets/img/admin/공지사항.png" alt="공지사항 아이콘" />
          </span>
          <span>공지사항 관리</span>
        </a>

        <!-- 게시판 관리 -->
        <a href="${pageContext.request.contextPath}/app/admin/adminBoard/menteeBoard/menteeBoardList.admin" class="side-link">
          <span class="side-icon" aria-hidden="true">
            <img src="${pageContext.request.contextPath}/assets/img/admin/게시판.png" alt="게시판 아이콘" />
          </span>
          <span>게시판 관리</span>
        </a>

        <!-- 학습보고서 관리 -->
        <a href="${pageContext.request.contextPath}/app/admin/adminReport/report.admin" class="side-link">
          <span class="side-icon" aria-hidden="true">
            <img src="${pageContext.request.contextPath}/assets/img/admin/학습보고서 아이콘.png" alt="학습보고서 아이콘" />
          </span>
          <span>학습보고서 관리</span>
        </a>

        <!-- 유저 관리 -->
        <a href="${pageContext.request.contextPath}/app/admin/adminUserManagement/userMM.admin" class="side-link">
          <span class="side-icon" aria-hidden="true">
            <img src="${pageContext.request.contextPath}/assets/img/admin/유저 아이콘.png" alt="유저 아이콘" />
          </span>
          <span>유저 관리</span>
        </a>

        <!-- 매칭 관리 -->
        <a href="${pageContext.request.contextPath}/app/admin/adminMatching/matching.admin" class="side-link">
          <span class="side-icon" aria-hidden="true">
            <img src="${pageContext.request.contextPath}/assets/img/admin/매칭아이콘.jpg" alt="매칭 아이콘" />
          </span>
          <span>매칭 관리</span>
        </a>

      </nav>
    </aside>

    <!-- 대시보드 -->
    <main class="dashboard-wrap">

      <!-- 통계 카드 -->
      <section class="summary-section">
        <article class="summary-card">
          <h2>총 가입자 수</h2>
          <p class="summary-value" id="totalJoinCount"></p>
          <p class="summary-sub" id="joinSubText"></p>
        </article>

        <article class="summary-card">
          <h2>오늘 방문자 수</h2>
          <p class="summary-value" id="todayVisitCount"></p>
        </article>

        <article class="summary-card">
          <h2>매칭 수</h2>
          <p class="summary-value" id="matchingCount"></p>
        </article>
      </section>

      <!-- 테이블 섹션 -->
      <section class="table-section">

        <!-- 최근 게시글 -->
        <article class="board-card">
          <h2>최근 게시글</h2>
          <div class="table-head">
            <span>번호</span>
            <span>제목</span>
            <span>작성일</span>
          </div>
          <div class="table-body" id="recentBoardList"></div>
        </article>

        <!-- 최근 가입자 -->
        <article class="user-card">
          <h2>최근 가입자</h2>
          <div class="table-head">
            <span>이름</span>
            <span>구분</span>
            <span>가입일</span>
          </div>
          <div class="table-body" id="recentUserList"></div>
        </article>

      </section>
    </main>
  </div>


	<script>
		 const data = JSON.parse('${summaryDataJson}');
		 const recentBoards = JSON.parse('${recentBoardsJson}');
		 const recentUsers = JSON.parse('${recentUsersJson}');
	</script>

  <script src="${pageContext.request.contextPath}/assets/js/admin/adminMain/main.js"></script>
</body>
</html>

