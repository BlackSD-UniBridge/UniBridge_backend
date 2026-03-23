<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>유저 상세</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/admin/adminUserManagement/userDetail/userModal.css" />
  <script type="text/javascript">
  	window.contextPath = "${pageContext.request.contextPath}";
  </script>
</head>
<body>

  <div id="header-wrap"></div>

  <div class="detail-wrap">
    <div class="detail-inner">

      <!-- 왼쪽: 프로필 이미지 + 설문조사 확인 -->
      <div class="profile-col">
        <div class="profile-avatar">
          <img src="${pageContext.request.contextPath}/assets/img/admin/유저 아이콘.png" alt="유저 아이콘" />
        </div>
        <button class="btn-survey" id="btnSurvey">설문조사 확인</button>
      </div>

      <!-- 오른쪽: 유저 정보 -->
      <div class="info-col">
        <div class="info-name">홍길순</div>

        <div class="info-type-row">
          유형 :
          <span class="type-badge" id="userType">${memberDetail.memberType}</span>
        </div>

        <div class="info-status-row">상태 :&nbsp;&nbsp;${memberDetail.memberState}</div>

        <div class="info-fields">
          <div class="info-field">닉네임 : ${memberDetail.memberNickname}</div>
          <div class="info-field">아이디 :&nbsp;&nbsp;${memberDetail.memberId}</div>
          <div class="info-field">성별 :&nbsp;&nbsp;${memberDetail.memberGender}</div>
          <div class="info-field">전화번호 : ${memberDetail.memberPhone}</div>
        </div>

        <div class="detail-actions">
          <button class="btn btn-red" id="btnWithdraw">회원퇴출</button>
        </div>
      </div>

    </div>
  </div>

  <!-- ========================
       설문조사 모달 (멘토)
  ======================== -->
  <div class="modal-overlay" id="modalSurveyMentor">
    <div class="modal">
      <button class="modal-close" id="closeSurveyMentor">&times;</button>
      <h2 class="modal-title">설문조사 (멘토)</h2>
      <div class="survey-fields">
        <div class="survey-row">
          <span class="survey-label">대학</span>
          <!-- 백엔드 연동 시 data-field="university" 값으로 교체 -->
          <span class="survey-value" data-field="university">${memberDetail.mtrGradSchool}</span>
        </div>
        <div class="survey-row">
          <span class="survey-label">전공</span>
          <span class="survey-value" data-field="major">${memberDetail.mtrGradDepart}</span>
        </div>
        <div class="survey-row">
          <span class="survey-label">졸업학점</span>
          <span class="survey-value" data-field="gpa">${memberDetail.mtrGradScore}점</span>
        </div>
        <div class="survey-row">
          <span class="survey-label">교육과목</span>
          <span class="survey-value" data-field="subject">${memberDetail.subjectName}</span>
        </div>
        <div class="survey-row survey-row--file">
          <span class="survey-label">첨부파일</span>
        </div>
        <!-- 백엔드 연동 시 data-file 값으로 파일명 교체 -->
        <div class="survey-file-box" data-field="file">ex) 졸업증명서.pdf</div>
      </div>
    </div>
  </div>

  <!-- ========================
       설문조사 모달 (멘티)
  ======================== -->
  <div class="modal-overlay" id="modalSurveyMentee">
    <div class="modal">
      <button class="modal-close" id="closeSurveyMentee">&times;</button>
      <h2 class="modal-title">설문조사 (멘티)</h2>
      <div class="survey-fields">
        <div class="survey-row">
          <span class="survey-label">학교</span>
          <span class="survey-value" data-field="school">${memberDetail.mteSchool}</span>
        </div>
        <div class="survey-row">
          <span class="survey-label">학년</span>
          <span class="survey-value" data-field="grade">${memberDetail.mteGrade}학년</span>
        </div>
        <div class="survey-row">
          <span class="survey-label">희망대학</span>
          <span class="survey-value" data-field="hopeUniv">${memberDetail.mteHopeUni}</span>
        </div>
        <div class="survey-row">
          <span class="survey-label">희망과목</span>
          <span class="survey-value" data-field="hopeSubject">${memberDetail.mteHopeMajor}</span>
        </div>
        <div class="survey-row survey-row--file">
          <span class="survey-label">첨부파일</span>
        </div>
        <div class="survey-file-box" data-field="file">ex) 고등학교증명서.pdf</div>
      </div>
    </div>
  </div>

  <script>
    fetch("${pageContext.request.contextPath}/header/adminHeader.jsp")
      .then(res => res.text())
      .then(html => {
        document.getElementById("header-wrap").innerHTML = html;
        const s = document.createElement("script");
        s.src = "${pageContext.request.contextPath}/header/adminHeader.js";
        document.body.appendChild(s);
      });
  </script>
  <script src="${pageContext.request.contextPath}/assets/js/admin/adminUserManagement/userDetail/userDetail.js"></script>
</body>
</html>
