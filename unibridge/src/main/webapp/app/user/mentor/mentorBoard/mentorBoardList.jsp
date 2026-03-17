<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>UniBridge - 멘토 게시판</title>
    <link
        href="https://fonts.googleapis.com/css2?family=Playfair+Display:wght@600&family=Noto+Sans+KR:wght@300;400;500;700&display=swap"
        rel="stylesheet" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/header.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/footer.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/user/mentor/mentorBoard/mentorBoardList.css" />

</head>

<body>

    <!-- 헤더 (멘터 헤더) -->
    <div id="headerContainer"></div>

    <div class="pageContent">
        <div class="mentorBoardWrap">

            <h1 class="mentorBoardTitle">멘터전용 게시판</h1>
            <div class="mentorBoardList">

                <!-- 헤더 행 -->
                <div class="mentorBoardHeader">
                    <div class="mentorBoardHeaderRow">
                        <div class="mentorBoardTh">번호</div>
                        <div class="mentorBoardTh mentorBoardThTitle">제목</div>
                        <div class="mentorBoardTh mentorBoardThDate">작성날짜 작성시간</div>
                        <div class="mentorBoardTh mentorBoardThAuthor">작성자</div>
                        <div class="mentorBoardTh mentorBoardThViews">조회수</div>
                    </div>
                </div>

                <!-- 본문 행 -->
                <div class="mentorBoardBody">

                    <div class="mentorBoardRow" data-board-id="1">
                        <div class="mentorBoardTd mentorBoardTdNum">1</div>
                        <div class="mentorBoardTd mentorBoardTdTitle">게시글제목</div>
                        <div class="mentorBoardTd mentorBoardTdDate">작성날짜 작성시간</div>
                        <div class="mentorBoardTd mentorBoardTdAuthor">작성자1</div>
                        <div class="mentorBoardTd mentorBoardTdViews">조회수 0</div>
                    </div>

                    <div class="mentorBoardRow" data-board-id="2">
                        <div class="mentorBoardTd mentorBoardTdNum">2</div>
                        <div class="mentorBoardTd mentorBoardTdTitle">게시글제목</div>
                        <div class="mentorBoardTd mentorBoardTdDate">작성날짜 작성시간</div>
                        <div class="mentorBoardTd mentorBoardTdAuthor">작성자1</div>
                        <div class="mentorBoardTd mentorBoardTdViews">조회수 0</div>
                    </div>

                    <div class="mentorBoardRow" data-board-id="3">
                        <div class="mentorBoardTd mentorBoardTdNum">3</div>
                        <div class="mentorBoardTd mentorBoardTdTitle">게시글제목</div>
                        <div class="mentorBoardTd mentorBoardTdDate">작성날짜 작성시간</div>
                        <div class="mentorBoardTd mentorBoardTdAuthor">작성자1</div>
                        <div class="mentorBoardTd mentorBoardTdViews">조회수 0</div>
                    </div>

                    <div class="mentorBoardRow" data-board-id="4">
                        <div class="mentorBoardTd mentorBoardTdNum">4</div>
                        <div class="mentorBoardTd mentorBoardTdTitle">게시글제목</div>
                        <div class="mentorBoardTd mentorBoardTdDate">작성날짜 작성시간</div>
                        <div class="mentorBoardTd mentorBoardTdAuthor">작성자1</div>
                        <div class="mentorBoardTd mentorBoardTdViews">조회수 0</div>
                    </div>

                    <div class="mentorBoardRow" data-board-id="5">
                        <div class="mentorBoardTd mentorBoardTdNum">5</div>
                        <div class="mentorBoardTd mentorBoardTdTitle">게시글제목</div>
                        <div class="mentorBoardTd mentorBoardTdDate">작성날짜 작성시간</div>
                        <div class="mentorBoardTd mentorBoardTdAuthor">작성자1</div>
                        <div class="mentorBoardTd mentorBoardTdViews">조회수 0</div>
                    </div>

                    <div class="mentorBoardRow" data-board-id="6">
                        <div class="mentorBoardTd mentorBoardTdNum">6</div>
                        <div class="mentorBoardTd mentorBoardTdTitle">게시글제목</div>
                        <div class="mentorBoardTd mentorBoardTdDate">작성날짜 작성시간</div>
                        <div class="mentorBoardTd mentorBoardTdAuthor">작성자1</div>
                        <div class="mentorBoardTd mentorBoardTdViews">조회수 0</div>
                    </div>

                    <div class="mentorBoardRow" data-board-id="7">
                        <div class="mentorBoardTd mentorBoardTdNum">7</div>
                        <div class="mentorBoardTd mentorBoardTdTitle">게시글제목</div>
                        <div class="mentorBoardTd mentorBoardTdDate">작성날짜 작성시간</div>
                        <div class="mentorBoardTd mentorBoardTdAuthor">작성자1</div>
                        <div class="mentorBoardTd mentorBoardTdViews">조회수 0</div>
                    </div>

                    <div class="mentorBoardRow" data-board-id="8">
                        <div class="mentorBoardTd mentorBoardTdNum">8</div>
                        <div class="mentorBoardTd mentorBoardTdTitle">게시글제목</div>
                        <div class="mentorBoardTd mentorBoardTdDate">작성날짜 작성시간</div>
                        <div class="mentorBoardTd mentorBoardTdAuthor">작성자1</div>
                        <div class="mentorBoardTd mentorBoardTdViews">조회수 0</div>
                    </div>

                    <div class="mentorBoardRow" data-board-id="9">
                        <div class="mentorBoardTd mentorBoardTdNum">9</div>
                        <div class="mentorBoardTd mentorBoardTdTitle">게시글제목</div>
                        <div class="mentorBoardTd mentorBoardTdDate">작성날짜 작성시간</div>
                        <div class="mentorBoardTd mentorBoardTdAuthor">작성자1</div>
                        <div class="mentorBoardTd mentorBoardTdViews">조회수 0</div>
                    </div>

                    <div class="mentorBoardRow" data-board-id="10">
                        <div class="mentorBoardTd mentorBoardTdNum">10</div>
                        <div class="mentorBoardTd mentorBoardTdTitle">게시글제목</div>
                        <div class="mentorBoardTd mentorBoardTdDate">작성날짜 작성시간</div>
                        <div class="mentorBoardTd mentorBoardTdAuthor">작성자3</div>
                        <div class="mentorBoardTd mentorBoardTdViews">조회수 0</div>
                    </div>

                </div>
            </div>

            <!-- 하단: 페이지네이션 + 글작성 -->
            <div class="mentorBoardFooter">
                <div class="mentorBoardPaginationWrap">
                    <button class="mentorBoardPageBtn active" data-page="1">1</button>
                    <button class="mentorBoardPageBtn" data-page="2">2</button>
                    <button class="mentorBoardPageBtn" data-page="3">3</button>
                    <button class="mentorBoardPageBtn" data-page="4">4</button>
                    <button class="mentorBoardPageBtn" data-page="5">5</button>
                    <button class="mentorBoardPageBtn mentorBoardPageNext">&#62;</button>
                </div>
                <button class="mentorBoardWriteBtn" id="mentorBoardWriteBtn">글작성</button>
            </div>

        </div>
    </div>

    <div id="footerContainer"></div>

    <script src="${pageContext.request.contextPath}/assets/js/mentorHeader.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/footer.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/user/mentor/mentorBoard/mentorBoardList.js"></script>

</body>

</html>