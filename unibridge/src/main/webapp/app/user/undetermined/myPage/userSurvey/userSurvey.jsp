<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>미정 유저 설문조사</title>
    <link href="https://fonts.googleapis.com/css2?family=Playfair+Display:wght@600&family=Noto+Sans+KR:wght@300;400;500;700&display=swap" rel="stylesheet" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/header.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/footer.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/user/undetermined/myPage/userSurvey/userSurvey.css">
    <script defer src="${pageContext.request.contextPath}/assets/js/user/undetermined/myPage/userSurvey/userSurvey.js"></script>
</head>
<body>
    
    <div id="headerContainer"></div>

    <div class="mainContainer">
        <aside>
            <div class="myPageTitle">마이페이지</div>
            <ul>
                <li><a href="${pageContext.request.contextPath}/app/user/undetermined/myPage/myPage.jsp" >계정 관리</a></li>
                <li><a href="${pageContext.request.contextPath}/app/user/undetermined/myPage/userSurvey/userSurvey.jsp" class="active">설문 조사</a></li>
                <li><a href="${pageContext.request.contextPath}/app/user/undetermined/myPage/userDelete/userDelete.jsp">회원 탈퇴</a></li>
            </ul>
        </aside>
        <main>
            <div class="userManageTitle">
                <img src="${pageContext.request.contextPath}/assets/img/user/userMyPageImg/userManage.jpg" alt="프로필 아이콘">
                <div class="title">설문조사</div>
            </div>
            <div class="userTypeBox">
                <div class="userText">
                    <label>멘토/멘티</label>
                    <div class="userType">미정</div>
                </div>
            </div>
            <button id="userWriteBtn">작성</button>
            <div id="surveyModal" class="modal">
                <div class="modalContent">
                    <button class="closeBtn"><img src="${pageContext.request.contextPath}/assets/img/user/userProfile/close.png" alt=""></button>
                    <div class="surveyTitle">설문 조사</div>
                    <div class="modalBox">
                        <form id="surveyForm" method="post" enctype="multipart/form-data">
                            <div class="inputRow">
                                <label>멘토/멘티</label>
                                <div class="radioGroup">
                                    <label class="radioItem">
                                        <span>멘토</span> <input type="radio" value="mentor" name="role" class="radioUserType"> 
                                    </label>
                                    <label class="radioItem">
                                        <span>멘티</span> <input type="radio" value="mentee" name="role" class="radioUserType" checked> 
                                    </label>
                                </div>
                            </div>

                            <div id="mentorContent" class="mentorContentList" style="display: none;">
                                <div class="inputRow">
                                    <label>대학</label>
                                    <input type="text" name="gradSchool" class="modalInput">
                                </div>
                                <div class="inputRow">
                                    <label>전공</label>
                                    <input type="text" name="gradDepart" class="modalInput">
                                </div>
                                <div class="inputRow">
                                    <label>졸업학점</label>
                                    <input type="text" name="gradScore" class="modalInput">
                                </div>
                                <div class="inputRow">
                                    <label>교육과목</label>
                                    <select name="subjectNumber" class="modalSelect">
                                        <option value="" disabled selected>선택해 주세요</option>
                                        <option value="1">국어</option>
                                        <option value="2">영어</option>
                                        <option value="3">수학</option>
                                        <option value="4">C언어</option>
                                        <option value="5">JAVA</option>
                                        <option value="6">C++</option>
                                        <option value="7">Python</option>
                                        <option value="8">게임</option>
                                    </select>
                                </div>
                            </div>

                            <div id="menteeContent" class="menteeContentList" style="display: block;">
                                <div class="inputRow">
                                    <label>희망 과목</label>
                                    <select name="subjectNumber" class="modalSelect">
                                        <option value="" disabled selected>선택해 주세요</option>
                                        <option value="1">국어</option>
                                        <option value="2">영어</option>
                                        <option value="3">수학</option>
                                        <option value="4">C언어</option>
                                        <option value="5">JAVA</option>
                                        <option value="6">C++</option>
                                        <option value="7">Python</option>
                                        <option value="8">게임</option>
                                    </select>
                                </div>
                                <div class="inputRow">
                                    <label>학교</label>
                                    <input type="text" name="menteeSchool" class="modalInput">
                                </div>
                                <div class="inputRow">
                                    <label>학년</label>
                                    <input type="text" name="menteeGrade" class="modalInput">
                                </div>
                                <div class="inputRow">
                                    <label>희망 대학</label>
                                    <input type="text" name="menteeHopeuni" class="modalInput">
                                </div>
                                <div class="inputRow">
                                    <label>희망 전공</label>
                                    <input type="text" name="menteeHopemajor" class="modalInput">
                                </div>
                            </div>

                            <div class="fileAttachSection">
                                <p class="fileLabel">파일 첨부</p>
                                <div class="fileInputWrapper">
                                    <input type="file" id="surveyFile" name="surveyFile" accept=".pdf, .xlsx, .xls, .doc, .docx, .jpg, .png" onchange="updateFileName()">
                                    <div class="fakeFileInput">
                                        <label for="surveyFile" id="fileSelector" class="fileSelectBtn">파일 선택</label>
                                        <label for="surveyFile" id="fileInfoDisplay" class="fileInfoActive" style="display: none;">
                                            <img src="/frontend/assets/img/user/file-icon.png" alt="파일" class="fileIcon">
                                            <span id="fileNameDisplay" class="fileNameText"></span>
                                        </label>
                                    </div>
                                </div>
                            </div>
    
                            <div class="modalFooter">
                                <button type="button" class="submitBtn" id="submitBtn">작성 완료</button>
                                <button type="button" class="cancelBtn" id="closeModalBtn">취소</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </main>

    </div>

    <script src="${pageContext.request.contextPath}/assets/js/header.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/footer.js"></script>
    <script>const contextPath = "${pageContext.request.contextPath}";</script>
</body>
</html>