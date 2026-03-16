<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>멘토링 생성 - UniBridge</title>
  
  <%-- 공통 CSS 및 폰트 경로 (ContextPath 적용) --%>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/fonts.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/user/mentor/myPage/userMentoCreate/mentoringCreate.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/header.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/footer.css">
  
  <%-- JS 로드 --%>
  <script defer src="${pageContext.request.contextPath}/assets/js/user/mentor/myPage/userMentoCreate/mentoringCreate.js"></script>
  <script defer src="${pageContext.request.contextPath}/assets/js/header.js"></script>
  <script defer src="${pageContext.request.contextPath}/assets/js/footer.js"></script>
</head>
<body>
  <header>
    <div id="headerContainer"></div>
  </header>
  
  <div class="mainContainer">
    <aside>
        <div class="myPageTitle">마이페이지</div>
        <ul>
            <%-- href 경로도 서블릿 URL 패턴(.pay)에 맞춰 수정이 필요할 수 있습니다 --%>
            <li><a href="${pageContext.request.contextPath}/app/user/mentor/myPage.jsp">계정 관리</a></li>
            <li><a href="${pageContext.request.contextPath}/app/user/mentor/userSurvey.jsp">설문 조사</a></li>
            <li><a href="${pageContext.request.contextPath}/app/user/mentor/userMatching.jsp">매칭 정보</a></li>
            <li><a href="${pageContext.request.contextPath}/app/user/mentor/mentoring.jsp" class="active">멘토링</a></li>
            <li><a href="${pageContext.request.contextPath}/app/user/mentor/userDelete.jsp">회원 탈퇴</a></li>
        </ul>
    </aside>

    <main>
      <div class="userManageTitle">
          <img src="${pageContext.request.contextPath}/frontend/assets/img/user/userMyPageImg/userMentoring.jpg" alt="프로필 아이콘">
          <div class="title">멘토링 관리</div>
      </div>

      <%-- 데이터를 DB에 전송하기 위한 설정: POST 방식과 multipart/form-data --%>
      <form action="${pageContext.request.contextPath}/mentoringWriteOk.pay" method="post" enctype="multipart/form-data">  
        <div id="contentsMain">
          <div id="mentoringBackground">
            <div id="mentoringMain">
              <div id="mentoring">
                <div id="mentoringTopics">
                  <div class="title">
                    <span>학습 주제 및 목표</span>
                  </div>

                  <div class="subject">
                    <label for="mentoringSubject">학습 과목</label>
                    <select id="mentoringSubject" name="mentoringSubject">
                      <option value="none" disabled selected hidden>과목명을 선택해주세요.</option>
                      <option value="korea">국어</option>
                      <option value="english">영어</option>
                      <option value="math">수학</option>
                      <option value="C">C</option>
                      <option value="JAVA">JAVA</option>
                      <option value="C++">C++</option>
                      <option value="Python">Python</option>
                      <option value="game">게임 제작</option>
                    </select>
                    <div class="error">과목명을 선택해주세요.</div>
                  </div>

                  <div class="subject">
                    <label for="mentoringTitle">멘토링 주제</label>
                    <input type="text" id="mentoringTitle" name="mentoringTitle" placeholder="제목을 입력해주세요.">
                    <div class="error">제목을 입력해주세요.</div>
                  </div>

                  <div id="purpose">
                    <label for="mentoringPurpose">멘토링 목적</label> 
                    <textarea id="mentoringPurpose" name="mentoringPurpose" placeholder="내용을 입력해주세요."></textarea>
                    <div class="error">내용을 입력해주세요.</div>
                  </div>
                </div>
                
                <div id="curriculum">
                  <div id="text">
                    <div>
                      <label for="mentoringCurriculum">멘토링 커리큘럼 상세</label>
                      <div class="error">내용을 입력해주세요.</div>
                    </div>
                    <textarea id="mentoringCurriculum" name="mentoringCurriculum"></textarea>
                  </div>
                  <div id="file">
                    <div id="curriculumFileTitle">파일 첨부</div>
                    <input type="file" id="curriculumFile" name="mentoringCurriculumFile">
                  </div>
                </div>
              </div>

              <div id="profile">
                <a href="${pageContext.request.contextPath}/app/user/mentor/myPage.jsp">
                  <img src="${pageContext.request.contextPath}/frontend/assets/img/user/userMyPageImg/userManage.jpg" alt="계정관리 아이콘">
                  <div id="manage">계정관리</div>
                </a>
                <div class="error">본인 사진을 추가해 주세요</div>
              </div>
            </div>
            <div id="buttons">
              <button type="submit">등록</button>
            </div>
          </div>
        </div>
      </form>
    </main>
  </div>
  <%-- 푸터는 JSP include 또는 동일한 contextPath 적용 --%>
  <footer>
    <div class="footerContainer"></div>  
  </footer>
</body>
</html>