<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
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
	<div id="headerContainer"></div>
  <!-- ====== MAIN ====== -->
  <main class="mainWrap">

    <!-- 1. 광고 배너 -->
    <section class="adBanner">
      <div class="bannerContainer">
        <div class="bannerContent">
          <h1 class="bannerLogo">DACON</h1>
          <p class="bannerSubtitle">DATA TO VALUE</p>
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

          <!-- 카드 1 -->
          <div class="contestCard">
            <div class="contestCardThumbEmpty">
              <img src="${pageContext.request.contextPath}/assets/img/UniBridge.png" alt="이미지">
            </div>
            <p class="contestCardTitle">구조를 인공 슬라이 추천 AI 문제</p>
            <p class="contestCardDate">2025.08.03</p>
            <div class="contestCardTagList">
              <span class="contestCardTag contestCardTagActive">진행 대회</span>
              <span class="contestCardTag">딥러닝</span>
              <span class="contestCardTag">Physics AI</span>
              <span class="contestCardTag">Physic</span>
            </div>
          </div>

          <!-- 카드 2 -->
          <div class="contestCard">
            <div class="contestCardThumbEmpty">이미지</div>
            <p class="contestCardTitle">Almers 8기 : 모델 검증과 관리인 역방전</p>
            <p class="contestCardDate">2025.08.02</p>
            <div class="contestCardTagList">
              <span class="contestCardTag contestCardTagActive">진행 대회</span>
              <span class="contestCardTag">LG Almers</span>
              <span class="contestCardTag">예둠</span>
              <span class="contestCardTag">동고사용</span>
            </div>
          </div>

          <!-- 카드 3 -->
          <div class="contestCard">
            <div class="contestCardThumbEmpty">이미지</div>
            <p class="contestCardTitle">핵심 · 스텝 예발들을 위한 서비스 개발 경연</p>
            <p class="contestCardDate">2014.11.04</p>
            <div class="contestCardTagList">
              <span class="contestCardTag contestCardTagActive">진행 대회</span>
              <span class="contestCardTag">아이이관</span>
              <span class="contestCardTag">서비예 변화</span>
              <span class="contestCardTag">직상 관</span>
            </div>
          </div>

          <!-- 카드 4 -->
          <div class="contestCard">
            <div class="contestCardThumbEmpty">이미지</div>
            <p class="contestCardTitle">구조를 인공 슬라이 추천 AI 문제</p>
            <p class="contestCardDate">2025.08.22</p>
            <div class="contestCardTagList">
              <span class="contestCardTag contestCardTagActive">진행 대회</span>
              <span class="contestCardTag">딥러닝</span>
              <span class="contestCardTag">Physics AI</span>
              <span class="contestCardTag">Physic</span>
            </div>
          </div>

          <!-- 카드 5 -->
          <div class="contestCard">
            <div class="contestCardThumbEmpty">이미지</div>
            <p class="contestCardTitle">HAI하이미 - Hecto AI Challenge : 2</p>
            <p class="contestCardDate">2025.12.25</p>
            <div class="contestCardTagList">
              <span class="contestCardTag contestCardTagActive">진행 대회</span>
              <span class="contestCardTag">핵로 대회</span>
              <span class="contestCardTag">Physics AI</span>
              <span class="contestCardTag">핵 남앙</span>
            </div>
          </div>

        </div><%-- contestSliderTrack 닫힘 --%>
      </div><%-- contestSliderWrap 닫힘 --%>

    </section><%-- contestWrap 닫힘 --%>


    <!-- 추천 멘토 섹션 -->
    <section class="mentoRecommendWrap">
      <h2 class="mentoRecommendTitle">추천 멘토</h2>

      <div class="mentoRecommendList">

        <!-- 멘토 1 -->
        <div class="mentoRecommendCard" data-mento-id="1">
          <img class="mentoRecommendAvatar" src="${pageContext.request.contextPath}/assets/img/UniBridge.png" alt="멘토 프로필" />
          <p class="mentoRecommendName">한길대학교</p>
          <p class="mentoRecommendSchool">인공지능학과</p>
          <p class="mentoRecommendInfo">
            Python<br />
            오프라인 / 주 3회
          </p>
        </div>

        <!-- 멘토 2 -->
        <div class="mentoRecommendCard" data-mento-id="2">
          <img class="mentoRecommendAvatar" src="${pageContext.request.contextPath}/assets/img/mento2.jpg" alt="멘토 프로필" />
          <p class="mentoRecommendName">미래공과대학교</p>
          <p class="mentoRecommendSchool">컴퓨터소프트학과</p>
          <p class="mentoRecommendInfo">
            C언어<br />
            오프라인 / 주 2회
          </p>
        </div>

        <!-- 멘토 3 -->
        <div class="mentoRecommendCard" data-mento-id="3">
          <img class="mentoRecommendAvatar" src="${pageContext.request.contextPath}/assets/img/mento3.jpg" alt="멘토 프로필" />
          <p class="mentoRecommendName">유니굴대학교</p>
          <p class="mentoRecommendSchool">소프트웨어학부</p>
          <p class="mentoRecommendInfo">
            Java<br />
            오프라인 / 주 2회
          </p>
        </div>

        <!-- 멘토 4 -->
        <div class="mentoRecommendCard" data-mento-id="4">
          <img class="mentoRecommendAvatar" src="${pageContext.request.contextPath}/assets/img/mento4.jpg" alt="멘토 프로필" />
          <p class="mentoRecommendName">연세대 수학과</p>
          <p class="mentoRecommendSchool">수학과</p>
          <p class="mentoRecommendInfo">
            수학<br />
            오프라인 / 주 3회
          </p>
        </div>

        <!-- 멘토 5 -->
        <div class="mentoRecommendCard" data-mento-id="5">
          <img class="mentoRecommendAvatar" src="${pageContext.request.contextPath}/assets/img/mento5.jpg" alt="멘토 프로필" />
          <p class="mentoRecommendName">연세대 국어국문과</p>
          <p class="mentoRecommendSchool">국어국문학과</p>
          <p class="mentoRecommendInfo">
            국어<br />
            오프라인 / 주 3회
          </p>
        </div>

        <!-- 멘토 6 -->
        <div class="mentoRecommendCard" data-mento-id="6">
          <img class="mentoRecommendAvatar" src="${pageContext.request.contextPath}/assets/img/mento6.jpg" alt="멘토 프로필" />
          <p class="mentoRecommendName">연세대 수학과</p>
          <p class="mentoRecommendSchool">수학과</p>
          <p class="mentoRecommendInfo">
            과학<br />
            오프라인 / 주 3회
          </p>
        </div>

        <!-- 멘토 7 -->
        <div class="mentoRecommendCard" data-mento-id="7">
          <img class="mentoRecommendAvatar" src="${pageContext.request.contextPath}/assets/img/mento7.jpg" alt="멘토 프로필" />
          <p class="mentoRecommendName">연세대 수학과</p>
          <p class="mentoRecommendSchool">수학과</p>
          <p class="mentoRecommendInfo">
            수학<br />
            오프라인 / 주 3회
          </p>
        </div>

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

  <div id="footerContainer"></div>

  <%--
    원본 main.html:
      <script src="/frontend/assets/js/header.js">  → 경로 수정 (동적 렌더링 코드 제거된 버전)
      <script src="/frontend/assets/js/footer.js">  → 경로 수정 (빈 파일)
      <script src="/frontend/assets/js/main.js">    → 경로 수정
  --%>
  <script src="${pageContext.request.contextPath}/assets/js/user/header.js"></script>
  <script src="${pageContext.request.contextPath}/assets/js/user/footer.js"></script>
  <script src="${pageContext.request.contextPath}/assets/js/main.js"></script>

</body>

</html>
