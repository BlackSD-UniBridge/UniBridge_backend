<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/global.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/fonts.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/admin/adminReport/reportList.css">
  <script src="${pageContext.request.contextPath}/assets/js/admin/adminReport/reportList.js" defer></script>

  <title>Document</title>
</head>
<body>
  <div id="root">
    <div class="root-container">
      <div id="header-wrap">
      	<jsp:include page="/header/adminHeader.jsp"/>
      </div>
      <main class="content-container">
        <div class="selector-container">
          <div class="date-container">
            <div class="date-content">주차</div>
            <div class="week-select-dropdown disabled">
              <ul class="week-selector">
              <li class="week-item">전체</li>
              	<c:forEach var="week" items="${sessionScope.learningReportWeeks}">
              		<li class="week-item">${week}주차</li>
              	</c:forEach>
              </ul>
            </div>
          </div>
          <div class="select-button">조회</div>
        </div>
        <ul class="list-container">
		  <c:forEach var="report" items="${sessionScope.learningReports}">
            <li class="lr-report" id="report-id-${report.getMatchingNumber()} }">
              <div class="lr-report-desc">
                <div class="lr-report-idx">${report.getLrReportWeek()}주차 ${report.getLrReportSession()}일차 - ${report.getLrSubjectTitle()}</div>
                <div class="lr-report-date">${report.getLrReportDate()}</div>
              </div>
              <div 
              	class="lr-report-submit-button"
              	onclick="window.location.href='${pageContext.request.contextPath}/reportDetail.admin?reportNumber=${report.getLrReportNumber()}'"
              >확인</div>
            </li>
    	  </c:forEach>
        </ul>
      </main>
    </div>
  </div>
  <script>
    window.contextPath = "${pageContext.request.contextPath}";
    sessionStorage.setItem("learningReports", JSON.stringify(${learningReports}));
    sessionStorage.setItem("learningReportWeeks", JSON.stringify(${learningReportWeeks}));
  </script>

</body>
</html>