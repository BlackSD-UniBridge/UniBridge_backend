<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- JSTL 사용을 위해 상단에 추가 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>학습 보고서 목록</title>

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/header.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/footer.css" />
	<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/user/notice/popup.css" />
	<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/user/notice/report.css" />
	<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/user/notice/calendar.css" />

<script src="${pageContext.request.contextPath}/assets/js/header.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/footer.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/footer.js"></script>
</head>
<body>
	<%@ include file="/app/user/header.jsp"%>
	<div id="root">

		<div class="main-container">

			<div class="filter-container"></div>

			<div class="list-container">
				<aside class="nav-container">
					<div class="cur-status-container">
						<div class="cur-status-title-container">
							<div class="cur-status-title-container__inner">
								<span>전체 현황</span>
							</div>
						</div>
						<div class="cur-status-content-container">
							<div class="cur-status-content-container__inner">
								<div class="content-container">
									<div class="status-title">
										<div class="title">전체 학습일지</div>
										<div class="value">${status.TOTAL_COUNT != null ? status.TOTAL_COUNT : 0}</div>
									</div>
									<div class="status-content">
										<span>현재 등록된 멘토·멘티 학습일지 총 개수</span>
									</div>
								</div>
								<div class="content-container">
									<div class="status-title">
										<div class="title">이번 주 작성 수</div>
										<div class="value">${status.WEEKLY_COUNT != null ? status.WEEKLY_COUNT : 0}</div>
									</div>
									<div class="status-content">
										<span>이번 주에 작성된 학습일지 개수</span>
									</div>
								</div>
							</div>
						</div>
					</div>
				</aside>

				<main class="list-container__inner">
					<div class="list-title-container">
						<div class="list-main-title">학습일지 목록</div>
						<div class="list-sub-title">멘토·멘티 학습일지를 날짜, 상태, 성취도 기준으로 확인할
							수 있습니다.</div>
					</div>

					<%-- 목록 출력 부분 --%>
					<ul class="list-content-container">
						<c:choose>
							<c:when test="${not empty reportList}">
								<c:forEach var="report" items="${reportList}">
									<%-- JS에서 찾기 쉽게 class와 data-id를 설정합니다 --%>
									<li class="list-learning-content"
										data-id="${report.learningReportNumber}">

										<div class="report-name">
											<%-- 서버에서 넘어온 제목 데이터 출력 --%>
											${report.subjectTitle}
										</div>

										<div class="actions">
											<%-- JS의 .target.closest(".detail")이 이 버튼을 인식합니다 --%>
											<button type="button" class="detail">상세보기</button>

											s
											<%-- 수정 권한이 있는 경우만 버튼 노출 (필요 시) --%>
											<button type="button" class="modify">수정</button>
										</div>

									</li>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<li class="no-data-msg">등록된 학습 보고서가 없습니다.</li>
							</c:otherwise>
						</c:choose>
					</ul>
				</main>
			</div>
		</div>
		<div class="popup-container">
			<div class="dim"></div>
		</div>
		<%@ include file="/app/user/footer.jsp"%>
	</div>
</body>
</html>