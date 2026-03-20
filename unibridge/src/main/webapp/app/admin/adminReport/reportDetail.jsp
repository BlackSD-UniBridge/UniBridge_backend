<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/global.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/fonts.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/admin/adminReport/reportDetail.css">
  
  <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/admin/adminReport/reportDetail.js" defer></script>
  <title>Document</title>
</head>
<body>
  <div id="root">
    <div class="root-container">
      <div id="header-wrap">
      	<jsp:include page="/header/adminHeader.jsp"/>
      </div>
      <div class="content-container">
        <div class="title-container">
          <span>학습보고서</span>
        </div>
        <div class="detail-container">
          <div class="detail-container__inner">
            <div class="detail-config-container">
              <div class="config-title">학습 주제 및 목표</div>
              <div class="config-subject-name-container">
                <div class="config-title__inner">학습 과목</div>
                <div class="config-content__inner">${subjectName}</div>
              </div>
              <div class="config-subject-topic-container">
                <div class="config-title__inner">학습일지 주제</div>
                <div class="config-content__inner">${sessionScope.curLearningReport.getLrSubjectTitle()}</div>
              </div>
              <div class="config-subject-summary-container">
                <div class="config-title__inner">학습일지 요약</div>
                <div class="config-content__inner">${sessionScope.curLearningReport.getLrSubjectSummary()}</div>
              </div>
            </div>
            <div class="detail-content-container">
              <div class="detail-content-container__inner">
                <div class="detail-content-title">학습보고서 상세 내용</div>
                <div class="detail-content-textbox">${sessionScope.curLearningReport.getLrSubjectContent()}</div>
              </div>
            </div>
            <form class="button-container" action="${pageContext.request.contextPath}/admin/reportDelete.admin" method="POST">
              <input name="reportNumber" value="${sessionScope.curLearningReport.getLrReportNumber()}" style="display: none;">
              <button class="delete-button" type="submit">삭제 </button>
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>
  <script>    
    window.contextPath = "${pageContext.request.contextPath}";
    sessionStorage.setItem("subjectName", "${subjectName}");
    sessionStorage.setItem("currentLeaningReport", JSON.stringify(${curLearningReport}));
  </script>
</body>
</html>