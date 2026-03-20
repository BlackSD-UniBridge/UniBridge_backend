<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html lang="ko">

<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>UniBridge</title>
<link
	href="https://fonts.googleapis.com/css2?family=Playfair+Display:wght@600&family=Noto+Sans+KR:wght@300;400;500;700&display=swap"
	rel="stylesheet" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/main.css" />

<script src="${pageContext.request.contextPath}/assets/js/main.js"></script>

</head>

<body>

	<%-- header.js의 renderHeader() + #headerContainer 대체 --%>
	<%@ include file="/app/user/header.jsp"%>
	<!-- ====== MAIN ====== -->
	<main class="mainWrap">

		<!-- 1. 광고 배너 -->
		<section class="adBanner">
			<div class="bannerContainer">
				<div class="bannerContent">
					<h1 class="bannerLogo">UNIBRIDGE</h1>
					<p class="bannerSubtitle">멘토와 멘티를 잇는 다리</p>
				</div>
			</div>
		</section>

		<!-- 진행중인 대회 -->
		<section class="contestWrap">

			<div class="contestHeader">
				<h2 class="contestTitle">진행중인 대회</h2>
				<div class="contestNavGroup">
					<button class="contestNavBtn" id="contestPrevBtn">&#8249;</button>
					<button class="contestNavBtn" id="contestNextBtn">&#8250;</button>
				</div>
			</div>

			<div class="contestSliderWrap">
				<div class="contestSliderTrack" id="contestSliderTrack">

					<%-- DB 데이터가 있을 경우 동적 렌더링 --%>
					<c:choose>
						<c:when test="${not empty contestList}">
							<c:forEach var="contest" items="${contestList}">
								<div class="contestCard">
									<div class="contestCardThumbEmpty"
										onclick="location.href=`${pageContext.request.contextPath}/common/noticeBoardReadOk.ntb?contestNumber=${contest.contestNumber}`"
										style="cursor:pointer;">
										<img
											src="${pageContext.request.contextPath}/assets/img/UniBridge.png"
											alt="대회 이미지" />
									</div>
									<p class="contestCardTitle">${contest.title}</p>
									<p class="contestCardDate">
										<fmt:formatDate value="${contest.endDate}"
											pattern="yyyy.MM.dd" />
									</p>
									<div class="contestCardTagList">
										<span class="contestCardTag contestCardTagActive">${contest.status}</span>
										<span class="contestCardTag">${contest.hostCompany}</span>
										<%-- techStack이 쉼표로 구분된 문자열이면 split 후 태그 생성 --%>
										<c:if test="${not empty contest.techStack}">
											<c:forEach var="tag"
												items="${fn:split(contest.techStack, ',')}">
												<span class="contestCardTag">${tag}</span>
											</c:forEach>
										</c:if>
									</div>
								</div>
							</c:forEach>
						</c:when>

						<%-- DB 데이터 없을 경우 더미 카드 표시 --%>
						<c:otherwise>
							<div class="contestCard">
								<div class="contestCardThumbEmpty">
									<img
										src="${pageContext.request.contextPath}/assets/img/UniBridge.png"
										alt="이미지" />
								</div>
								<p class="contestCardTitle">등록된 대회가 없습니다.</p>
								<p class="contestCardDate">-</p>
								<div class="contestCardTagList">
									<span class="contestCardTag">준비중</span>
								</div>
							</div>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</section>


		<!-- 추천 멘토 섹션 -->
		<section class="mentoRecommendWrap">
			<h2 class="mentoRecommendTitle">추천 멘토</h2>

			<div class="mentoRecommendList">

				<c:choose>
					<c:when test="${not empty mentorCardList}">
						<c:forEach var="mentor" items="${mentorCardList}">
						    <div class="mentoRecommendCard" onclick="location.href='${pageContext.request.contextPath}/mentor/mentorDetailOk.sch?memberNumber=${mentor.mentorNumber}'">
						        <div class="mentoRecommendAvatar">
						            <c:choose>
						                <c:when test="${not empty mentor.fileName}">
						                    <img src="${pageContext.request.contextPath}/upload/${mentor.fileName}" alt="프로필">
						                </c:when>
						                <c:otherwise>
						                    <img src="${pageContext.request.contextPath}/static/img/default_avatar.png" alt="기본프로필">
						                </c:otherwise>
						            </c:choose>
						        </div>
						        <div class="mentoRecommendInfo">
						            <p class="mentoRecommendName">${mentor.memberNickname} 멘토</p>
						            <p class="mentoRecommendSchool">${mentor.gradSchool} ${mentor.gradDepart}</p>
						            <p class="mentoRecommendTitle">${mentor.mentoringTitle}</p>
						            <div class="mentoRecommendTags">
						                <span class="tag">${mentor.subjectName}</span>
						            </div>
						        </div>
						    </div>
						</c:forEach>
					</c:when>

					<c:otherwise>
						<div class="mentoRecommendCard">
							<img class="mentoRecommendAvatar"
								src="${pageContext.request.contextPath}/assets/img/UniBridge.png"
								alt="기본 프로필" />
							<p class="mentoRecommendName">멘토 없음</p>
							<p class="mentoRecommendSchool">-</p>
							<p class="mentoRecommendInfo">등록된 멘토가 없습니다.</p>
						</div>
					</c:otherwise>
				</c:choose>

			</div>
		</section>

		<!-- 취업 섹션 -->
		<section class="jobBannerWrap">
			<div class="jobBannerHeader">
				<h2 class="jobBannerTitle">취업</h2>
				<div class="jobBannerNavGroup">
					<button class="jobBannerNavBtn" id="jobBannerPrev">&#8249;</button>
					<button class="jobBannerNavBtn" id="jobBannerNext">&#8250;</button>
				</div>
			</div>
			<div class="jobBannerSliderWrap">
				<div class="jobBannerSliderTrack" id="jobBannerTrack">

					<c:choose>
						<c:when test="${not empty companyList}">
							<c:forEach var="company" items="${companyList}">
								<%-- employmentUrl이 있으면 클릭 시 해당 채용 페이지로 이동 --%>
								<div class="jobBannerCard"
									data-job-id="${company.employmentId}"
									<c:if test="${not empty company.employmentUrl}">
										style="cursor:pointer;"
									</c:if>>

									<%-- 로고 이미지 (emplymentLog 컬럼): 없으면 기본 이미지 표시 --%>
									<c:choose>
										<c:when test="${not empty company.employmentLog}">
											<img class="jobBannerCardThumb"
												src="${pageContext.request.contextPath}/upload/${company.employmentLog}"
												alt="${company.companyName}" />
										</c:when>
										<c:otherwise>
											<img class="jobBannerCardThumb"
												src="${pageContext.request.contextPath}/assets/img/UniBridge.png"
												alt="기업 이미지" />
										</c:otherwise>
									</c:choose>

									<%-- 채용 공고 제목: employmentTitle 없으면 companyName 표시 --%>
									<p class="jobBannerCardTitle">
										<c:choose>
											<c:when test="${not empty company.employmentTitle}">
												${company.employmentTitle}
											</c:when>
											<c:otherwise>
												${company.companyName}
											</c:otherwise>
										</c:choose>
									</p>

									<%-- 태그: 지역, 경력, 학력 --%>
									<div class="jobBannerCardTagList">
										<c:if test="${not empty company.employmentLocation}">
											<span class="jobBannerCardTag active">${company.employmentLocation}</span>
										</c:if>
										<c:if test="${not empty company.employmentCareer}">
											<span class="jobBannerCardTag">${company.employmentCareer}</span>
										</c:if>
										<c:if test="${not empty company.employmentEducation}">
											<span class="jobBannerCardTag">${company.employmentEducation}</span>
										</c:if>
									</div>
								</div>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<div class="jobBannerCard">
								<img class="jobBannerCardThumb"
									src="${pageContext.request.contextPath}/assets/img/UniBridge.png"
									alt="취업 썸네일" />
								<p class="jobBannerCardTitle">등록된 채용 정보가 없습니다.</p>
								<div class="jobBannerCardTagList">
									<span class="jobBannerCardTag">준비중</span>
								</div>
							</div>
						</c:otherwise>
					</c:choose>

				</div>
			</div>
		</section>
		<%-- jobBannerWrap 닫힘 --%>

	</main>

	<%@ include file="/app/user/footer.jsp"%>

</body>

</html>
