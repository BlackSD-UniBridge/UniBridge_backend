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

                  <li class="report-content">
                    <div class="title">3월 3일(월)</div>
                    <div class="vertical-line"></div>
                    <ul class="reports">
                      <li class="report-item">
                        <div class="mentor-mentee">
                          <div class="user-info mentor">
                            <div class="user-icon"><img src ="${pageContext.request.contextPath}/assets/img/admin/adminNoticeImg/userIcon.png"></div>
                            <div class="user-desc">
                              <span>멘토</span>
                              <span class="mentor-name">멘토 이름 1</span>
                            </div>
                          </div>
                          <div class="user-info mentee">
                            <div class="user-icon"><img src ="${pageContext.request.contextPath}/assets/img/admin/adminNoticeImg/userIcon.png"></div>
                            <div class="user-desc">
                              <span>멘티</span>
                              <span class="mentee-name">멘티 이름 1</span>
                            </div>
                          </div>
                        </div>
                        <div class="detail-button">상세</div>
                      </li>

                      <li class="report-item">
                        <div class="mentor-mentee">
                          <div class="user-info mentor">
                            <div class="user-icon"><img src ="${pageContext.request.contextPath}/assets/img/admin/adminNoticeImg/userIcon.png"></div>
                            <div class="user-desc">
                              <span>멘토</span>
                              <span class="mentor-name">멘토 이름 2</span>
                            </div>
                          </div>
                          <div class="user-info mentee">
                            <div class="user-icon"><img src ="${pageContext.request.contextPath}/assets/img/admin/adminNoticeImg/userIcon.png"></div>
                            <div class="user-desc">
                              <span>멘티</span>
                              <span class="mentee-name">멘티 이름 2</span>
                            </div>
                          </div>
                        </div>
                        <div class="detail-button">상세</div>
                      </li>
                    </ul>
                  </li>

                  <li class="report-content">
                    <div class="report-content__inner">
                      <div class="title">3월 2일(일)</div>
                      <div class="vertical-line"></div>
                      <ul class="reports">
                      <li class="report-item">
                        <div class="mentor-mentee">
                          <div class="user-info mentor">
                            <div class="user-icon"><img src ="${pageContext.request.contextPath}/assets/img/admin/adminNoticeImg/userIcon.png"></div>
                            <div class="user-desc">
                              <span>멘토</span>
                              <span class="mentor-name">멘토 이름 1</span>
                            </div>
                          </div>
                          <div class="user-info mentee">
                            <div class="user-icon"><img src ="${pageContext.request.contextPath}/assets/img/admin/adminNoticeImg/userIcon.png"></div>
                            <div class="user-desc">
                              <span>멘티</span>
                              <span class="mentee-name">멘티 이름 1</span>
                            </div>
                          </div>
                        </div>
                        <div class="detail-button">상세</div>
                      </li>

                      <li class="report-item">
                        <div class="mentor-mentee">
                          <div class="user-info mentor">
                            <div class="user-icon"><img src ="${pageContext.request.contextPath}/assets/img/admin/adminNoticeImg/userIcon.png"></div>
                            <div class="user-desc">
                              <span>멘토</span>
                              <span class="mentor-name">멘토 이름 2</span>
                            </div>
                          </div>
                          <div class="user-info mentee">
                            <div class="user-icon"><img src ="${pageContext.request.contextPath}/assets/img/admin/adminNoticeImg/userIcon.png"></div>
                            <div class="user-desc">
                              <span>멘티</span>
                              <span class="mentee-name">멘티 이름 2</span>
                            </div>
                          </div>
                        </div>
                        <div class="detail-button">상세</div>
                      </li>
                      </ul>
                    </div>
                  </li>
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