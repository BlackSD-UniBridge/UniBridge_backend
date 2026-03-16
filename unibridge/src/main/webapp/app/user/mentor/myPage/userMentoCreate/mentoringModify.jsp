<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>멘토링 수정 - UniBridge</title>
  
  <%-- 공통 CSS 및 폰트 경로 --%>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/fonts.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/user/mentor/myPage/userMentoCreate/mentoringModify.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/header.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/footer.css">
  
  <%-- JS 로드 --%>
  <script defer src="${pageContext.request.contextPath}/assets/js/user/mentor/myPage/userMentoCreate/mentoringModify.js"></script>
  <script defer src="${pageContext.request.contextPath}/assets/js/header.js"></script>
  <script defer src="${pageContext.request.contextPath}/assets/js/footer.js"></script>
</head>
<body>
  <header><!--<div id="headerContainer"></div> --></header>

  <div class="mainContainer">
    <aside>
        <div class="myPageTitle">마이페이지</div>
        <ul>
            <li><a href="${pageContext.request.contextPath}/app/user/mentor/myPage.pay">계정 관리</a></li>
            <li><a href="${pageContext.request.contextPath}/app/user/mentor/userSurvey.pay">설문 조사</a></li>
            <li><a href="${pageContext.request.contextPath}/app/user/mentor/userMatching.pay">매칭 정보</a></li>
            <li><a href="${pageContext.request.contextPath}/app/user/mentor/mentoring.pay" class="active">멘토링</a></li>
            <li><a href="${pageContext.request.contextPath}/app/user/mentor/userDelete.pay">회원 탈퇴</a></li>
        </ul>
    </aside>

    <main>
      <div class="userManageTitle">
        <img src="${pageContext.request.contextPath}/frontend/assets/img/user/userMyPageImg/userMentoring.jpg" alt="멘토링 관리 아이콘">
        <div class="title">멘토링 관리</div>
      </div>

      <%-- 수정 완료 처리를 위한 Form. 파일 업로드가 있다면 enctype 필수 --%>
      <form method="post" action="${pageContext.request.contextPath}/mentoringUpdateOk.pay" enctype="multipart/form-data">
        <%-- 수정을 위해 어떤 게시글인지 알려주는 고유 ID --%>
        <input type="hidden" name="mentoringId" value="${mentoring.id}">
        
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
                    <input type="text" id="mentoringSubject" name="mentoringSubject" 
                           value="${mentoring.subject}">
                  </div>

                  <div class="subject">
                    <label for="mentoringTitle">멘토링 주제</label>
                    <input type="text" id="mentoringTitle" name="mentoringTitle" 
                           value="${mentoring.title}">
                  </div>

                  <div id="purpose">
                    <label for="mentoringPurpose">멘토링 목적</label> 
                    <textarea id="mentoringPurpose" name="mentoringPurpose">${mentoring.purpose}</textarea>
                  </div>
                </div>

                <div id="curriculum">
                  <div id="text">
                    <div>
                      <label for="mentoringCurriculum">멘토링 커리큘럼 상세</label>
                    </div>
                    <textarea id="mentoringCurriculum" name="mentoringCurriculum">${mentoring.curriculum}</textarea>
                  </div>
                  <div id="file">
                    <div id="curriculumFileTitle">파일 첨부</div>
                    <%-- 기존 파일 정보 표시 --%>
                    <div style="font-size: 0.8rem; color: #666; margin-bottom: 5px;">
                      기존 파일: ${not empty mentoring.fileName ? mentoring.fileName : '없음'}
                    </div>
                    <input type="file" id="curriculumFile" name="mentoringCurriculumFile">
                  </div>
                </div>
              </div>

              <div id="profile">
                <a href="#">
                  <img src="${pageContext.request.contextPath}/upload/${mentoring.profileImg}" 
                       alt="멘토 프로필" id="profileImg" 
                       onerror="this.src='${pageContext.request.contextPath}/frontend/assets/img/user/userProfile/ex1.png'">
                </a>
              </div>
            </div>
            <div id="buttons">
              <button type="button" id="cancel" onclick="history.back()">취소</button>
              <button type="submit">확인</button>
            </div>
          </div>
        </div>
      </form>
    </main>
  </div>
  <footer>
    <!-- <div class="footerContainer"></div>   -->
  </footer>
</body>
</html>