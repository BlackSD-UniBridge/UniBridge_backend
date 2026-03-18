<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html lang="ko">

<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>UniBridge</title>
  <link
    href="https://fonts.googleapis.com/css2?family=Playfair+Display:wght@600&family=Noto+Sans+KR:wght@300;400;500;700&display=swap"
    rel="stylesheet" />
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/user/header.css" />
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/user/footer.css" />
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/main.css" />
</head>

<body>

  <%-- header.js의 renderHeader() + #headerContainer 대체 --%>
	<%@ include file="/app/user/header.jsp" %>
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
                  <div class="contestCardThumbEmpty">
                    <img src="${pageContext.request.contextPath}/assets/img/UniBridge.png" alt="대회 이미지" />
                  </div>
                  <p class="contestCardTitle">${contest.title}</p>
                  <p class="contestCardDate">
                    <fmt:formatDate value="${contest.endDate}" pattern="yyyy.MM.dd" />
                  </p>
                  <div class="contestCardTagList">
                    <span class="contestCardTag contestCardTagActive">${contest.status}</span>
                    <span class="contestCardTag">${contest.hostCompany}</span>
                    <%-- techStack이 쉼표로 구분된 문자열이면 split 후 태그 생성 --%>
                    <c:if test="${not empty contest.techStack}">
                      <c:forEach var="tag" items="${fn:split(contest.techStack, ',')}">
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
                  <img src="${pageContext.request.contextPath}/assets/img/UniBridge.png" alt="이미지" />
                </div>
                <p class="contestCardTitle">등록된 대회가 없습니다.</p>
                <p class="contestCardDate">-</p>
                <div class="contestCardTagList">
                  <span class="contestCardTag">준비중</span>
                </div>
              </div>
            </c:otherwise>
          </c:choose>

        </div><%-- contestSliderTrack 닫힘 --%>
      </div><%-- contestSliderWrap 닫힘 --%>

    </section><%-- contestWrap 닫힘 --%>


    <!-- 추천 멘토 섹션 -->
    <section class="mentoRecommendWrap">
      <h2 class="mentoRecommendTitle">추천 멘토</h2>

      <div class="mentoRecommendList">

        <c:choose>
          <c:when test="${not empty mentorCardList}">
            <c:forEach var="mentor" items="${mentorCardList}">
              <div class="mentoRecommendCard" data-mento-id="${mentor.mentorNumber}">
                <%-- 프로필 이미지: member_profile(파일번호)가 있으면 파일 경로로, 없으면 기본 이미지 --%>
                <c:choose>
                  <c:when test="${mentor.memberProfile > 0}">
                    <img class="mentoRecommendAvatar"
                         src="${pageContext.request.contextPath}/assets/img/profile/${mentor.memberProfile}"
                         alt="${mentor.memberNickname} 프로필" />
                  </c:when>
                  <c:otherwise>
                    <img class="mentoRecommendAvatar"
                         src="${pageContext.request.contextPath}/assets/img/UniBridge.png"
                         alt="기본 프로필" />
                  </c:otherwise>
                </c:choose>
                <p class="mentoRecommendName">${mentor.memberNickname}</p>
                <p class="mentoRecommendSchool">${mentor.subjectName}</p>
                <p class="mentoRecommendInfo">
                  ${mentor.mentoringTitle}<br />
                  ${mentor.mentoringGoal}
                </p>
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

          <!-- 카드 1 -->
          <div class="jobBannerCard" data-job-id="1">
            <img class="jobBannerCardThumb" src="${pageContext.request.contextPath}/assets/img/UniBridge.png" alt="취업 썸네일" />
            <p class="jobBannerCardTitle">구조를 인공 슬라이 추천 AI 문제</p>
            <p class="jobBannerCardDate">2025.08.03</p>
            <div class="jobBannerCardTagList">
              <span class="jobBannerCardTag active">항상 대회</span>
              <span class="jobBannerCardTag">딥러닝</span>
              <span class="jobBannerCardTag">Physics AI</span>
              <span class="jobBannerCardTag">Physic</span>
            </div>
          </div>

          <!-- 카드 2 -->
          <div class="jobBannerCard" data-job-id="2">
            <img class="jobBannerCardThumb" src="${pageContext.request.contextPath}/assets/img/job2.jpg" alt="취업 썸네일" />
            <p class="jobBannerCardTitle">Almers 8기 : 모델 검증과 관리인 역방전</p>
            <p class="jobBannerCardDate">2026.02.02</p>
            <div class="jobBannerCardTagList">
              <span class="jobBannerCardTag active">항상 대회</span>
              <span class="jobBannerCardTag">LG Almers</span>
              <span class="jobBannerCardTag">채용</span>
              <span class="jobBannerCardTag">탐고사용</span>
            </div>
          </div>

          <!-- 카드 3 -->
          <div class="jobBannerCard" data-job-id="3">
            <img class="jobBannerCardThumb" src="${pageContext.request.contextPath}/assets/img/job3.jpg" alt="취업 썸네일" />
            <p class="jobBannerCardTitle">핵심 · 스텝 예발들을 위한 서비스 개발 경연</p>
            <p class="jobBannerCardDate">2026.01.04</p>
            <div class="jobBannerCardTagList">
              <span class="jobBannerCardTag active">항상 대회</span>
              <span class="jobBannerCardTag">아이이관</span>
              <span class="jobBannerCardTag">서비예 변화</span>
              <span class="jobBannerCardTag">직상 관</span>
            </div>
          </div>

          <!-- 카드 4 -->
          <div class="jobBannerCard" data-job-id="4">
            <img class="jobBannerCardThumb" src="${pageContext.request.contextPath}/assets/img/job4.jpg" alt="취업 썸네일" />
            <p class="jobBannerCardTitle">구조를 인공 슬라이 추천 AI 문제</p>
            <p class="jobBannerCardDate">2026.06.03</p>
            <div class="jobBannerCardTagList">
              <span class="jobBannerCardTag active">항상 대회</span>
              <span class="jobBannerCardTag">딥러닝</span>
              <span class="jobBannerCardTag">Physics AI</span>
              <span class="jobBannerCardTag">Physic</span>
            </div>
          </div>

          <!-- 카드 5 -->
          <div class="jobBannerCard" data-job-id="5">
            <img class="jobBannerCardThumb" src="${pageContext.request.contextPath}/assets/img/job5.jpg" alt="취업 썸네일" />
            <p class="jobBannerCardTitle">HAI하이미 - Hecto AI Challenge : 2</p>
            <p class="jobBannerCardDate">2025.12.29</p>
            <div class="jobBannerCardTagList">
              <span class="jobBannerCardTag active">항상 대회</span>
              <span class="jobBannerCardTag">채용</span>
              <span class="jobBannerCardTag">딥러닝고사용</span>
              <span class="jobBannerCardTag">책 비전</span>
            </div>
          </div>

          <!-- 카드 6 -->
          <div class="jobBannerCard" data-job-id="6">
            <img class="jobBannerCardThumb" src="${pageContext.request.contextPath}/assets/img/job6.jpg" alt="취업 썸네일" />
            <p class="jobBannerCardTitle">빅데이터 취업 공모전 2025</p>
            <p class="jobBannerCardDate">2025.09.15</p>
            <div class="jobBannerCardTagList">
              <span class="jobBannerCardTag active">항상 대회</span>
              <span class="jobBannerCardTag">빅데이터</span>
              <span class="jobBannerCardTag">분석</span>
            </div>
          </div>

          <!-- 카드 7 -->
          <div class="jobBannerCard" data-job-id="7">
            <img class="jobBannerCardThumb" src="${pageContext.request.contextPath}/assets/img/job7.jpg" alt="취업 썸네일" />
            <p class="jobBannerCardTitle">AI 개발자 채용 연계 프로그램</p>
            <p class="jobBannerCardDate">2025.10.01</p>
            <div class="jobBannerCardTagList">
              <span class="jobBannerCardTag active">항상 대회</span>
              <span class="jobBannerCardTag">AI</span>
              <span class="jobBannerCardTag">채용</span>
            </div>
          </div>

        </div><%-- jobBannerSliderTrack 닫힘 --%>
      </div><%-- jobBannerSliderWrap 닫힘 --%>

    </section><%-- jobBannerWrap 닫힘 --%>

  </main>

  <%@ include file="/app/user/footer.jsp" %>

  <script src="${pageContext.request.contextPath}/assets/js/user/header.js"></script>
  <script src="${pageContext.request.contextPath}/assets/js/user/footer.js"></script>
  <script src="${pageContext.request.contextPath}/assets/js/main.js"></script>

</body>

</html>
