<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/global.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/fonts.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/admin/adminReport/report.css">

  <script src="${pageContext.request.contextPath}/assets/js/admin/adminReport/report.js" defer></script>
  
  <script>window.contextPath = "${pageContext.request.contextPath}";</script>
  <title>Document</title>
</head>
<body>
  <div id="root">
    <div class="root-container">
      <div id="header-wrap">
      	<jsp:include page="/header/adminHeader.jsp"/>
      </div>
      
      <main class="main-container">
        <div class="title-container">
          <div class="title">학습 보고서</div>
          <div class="date-selector-container">
            <div class="left-button button">&lt;</div>
            <div class="selected-year">2026</div>
            <div class="right-button button">&gt;</div>
          </div>
        </div>
        <div class="content-container">
          <div class="content-container__inner">
            <ul class="month-selector">
              <li class="month-item">1월</li>
              <li class="month-item">2월</li>
              <li class="month-item">3월</li>
              <li class="month-item">4월</li>
              <li class="month-item">5월</li>
              <li class="month-item">6월</li>
              <li class="month-item">7월</li>
              <li class="month-item">8월</li>
              <li class="month-item">9월</li>
              <li class="month-item">10월</li>
              <li class="month-item">11월</li>
              <li class="month-item">12월</li>
            </ul>
            <div class="vertical-line"></div>
            <ul class="subject-selector">
              <li class="subject-item all"><img src="${pageContext.request.contextPath}/assets/img/admin/adminNoticeImg/AllSubjectIcon.png"></li>
              <li class="subject-item korean"><img src="${pageContext.request.contextPath}/assets/img/admin/adminNoticeImg/subjectKoreanIcon.png"></li>
              <li class="subject-item english"><img src="${pageContext.request.contextPath}/assets/img/admin/adminNoticeImg/subjectEnglishIcon.png"></li>
              <li class="subject-item math"><img src="${pageContext.request.contextPath}/assets/img/admin/adminNoticeImg/subjectMathIcon.png"></li>
              <li class="subject-item c"><img src="${pageContext.request.contextPath}/assets/img/admin/adminNoticeImg/subjectCIcon.png"></li>
              <li class="subject-item java"><img src="${pageContext.request.contextPath}/assets/img/admin/adminNoticeImg/subjectJavaIcon.png"></li>
              <li class="subject-item cpp"><img src="${pageContext.request.contextPath}/assets/img/admin/adminNoticeImg/subjectCPPIcon.png"></li>
              <li class="subject-item python"><img src="${pageContext.request.contextPath}/assets/img/admin/adminNoticeImg/subjectPythonIcon.png"></li>
              <li class="subject-item game-programming"><img src="${pageContext.request.contextPath}/assets/img/admin/adminNoticeImg/subjectGameProIcon.png"></li>
            </ul>
            <div class="report-container">
              <div class="report-container__inner">
                <ul class="reports-per-week">
                </ul>
              </div>
            </div>
          </div>
        </div>
      </main>
    </div>
  </div>
</body>
</html>