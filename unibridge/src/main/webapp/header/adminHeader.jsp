<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/adminHeader.css" />

<header class="admin-header">
  <a href="${pageContext.request.contextPath}/app/admin/adminMain/main.admin" class="admin-header__logo" data-link="main">
     <img id="header-logo" src="${pageContext.request.contextPath}/assets/img/UniBridge.png" alt="UniBridge" />
  </a>

    <nav class="admin-header__nav">
      <a href="${pageContext.request.contextPath}/app/admin/adminNotice/noticeList.admin" class="admin-header__menu" data-link="notice">공지사항</a>
      <a href="${pageContext.request.contextPath}/app/admin/adminBoard/menteeBoard/menteeBoardList.admin" class="admin-header__menu" data-link="board">게시판</a>
      <a href="${pageContext.request.contextPath}/app/admin/adminReport/report.admin" class="admin-header__menu" data-link="report">학습보고서</a>
      <a href="${pageContext.request.contextPath}/app/admin/adminUserManagement/userMM.admin" class="admin-header__menu" data-link="user">유저관리</a>
      <a href="${pageContext.request.contextPath}/app/admin/adminMatching/matching.admin" class="admin-header__menu" data-link="matching">매칭</a>
      <a href="${pageContext.request.contextPath}/app/admin/adminLogin/login.admin" class="admin-header__menu admin-header__menu--logout" data-link="logout">로그아웃</a>
    </nav>
  </header>

  <script src="${pageContext.request.contextPath}/assets/js/adminHeader.js"></script>
