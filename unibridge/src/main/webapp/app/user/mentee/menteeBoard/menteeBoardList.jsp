<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">

<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>UniBridge - 멘티 게시판</title>
  <link
    href="https://fonts.googleapis.com/css2?family=Playfair+Display:wght@600&family=Noto+Sans+KR:wght@300;400;500;700&display=swap"
    rel="stylesheet" />
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/header.css" />
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/footer.css" />
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/user/mentee/menteeBoard/menteeBoardList.css" />

</head>

<body>

  <div id="headerContainer"></div>

  <div class="pageContent">
    <div class="menteeBoardWrap">

      <h1 class="menteeBoardTitle">멘티전용 게시판</h1>

      <div class="menteeBoardList">

        <!-- 헤더 행 -->
        <div class="menteeBoardHeader">
          <div class="menteeBoardHeaderRow">
            <div class="menteeBoardTh">번호</div>
            <div class="menteeBoardTh menteeBoardThTitle">제목</div>
            <div class="menteeBoardTh menteeBoardThDate">작성날짜 작성시간</div>
            <div class="menteeBoardTh menteeBoardThAuthor">작성자</div>
            <div class="menteeBoardTh menteeBoardThViews">조회수</div>
          </div>
        </div>

        <!-- 본문 행 -->
        <div class="menteeBoardBody">

          <div class="menteeBoardRow" data-board-id="1">
            <div class="menteeBoardTd menteeBoardTdNum">1</div>
            <div class="menteeBoardTd menteeBoardTdTitle">게시글제목</div>
            <div class="menteeBoardTd menteeBoardTdDate">작성날짜 작성시간</div>
            <div class="menteeBoardTd menteeBoardTdAuthor">작성자1</div>
            <div class="menteeBoardTd menteeBoardTdViews">조회수 0</div>
          </div>

          <div class="menteeBoardRow" data-board-id="2">
            <div class="menteeBoardTd menteeBoardTdNum">2</div>
            <div class="menteeBoardTd menteeBoardTdTitle">게시글제목</div>
            <div class="menteeBoardTd menteeBoardTdDate">작성날짜 작성시간</div>
            <div class="menteeBoardTd menteeBoardTdAuthor">작성자1</div>
            <div class="menteeBoardTd menteeBoardTdViews">조회수 0</div>
          </div>

          <div class="menteeBoardRow" data-board-id="3">
            <div class="menteeBoardTd menteeBoardTdNum">3</div>
            <div class="menteeBoardTd menteeBoardTdTitle">게시글제목</div>
            <div class="menteeBoardTd menteeBoardTdDate">작성날짜 작성시간</div>
            <div class="menteeBoardTd menteeBoardTdAuthor">작성자1</div>
            <div class="menteeBoardTd menteeBoardTdViews">조회수 0</div>
          </div>

          <div class="menteeBoardRow" data-board-id="4">
            <div class="menteeBoardTd menteeBoardTdNum">4</div>
            <div class="menteeBoardTd menteeBoardTdTitle">게시글제목</div>
            <div class="menteeBoardTd menteeBoardTdDate">작성날짜 작성시간</div>
            <div class="menteeBoardTd menteeBoardTdAuthor">작성자1</div>
            <div class="menteeBoardTd menteeBoardTdViews">조회수 0</div>
          </div>

          <div class="menteeBoardRow" data-board-id="5">
            <div class="menteeBoardTd menteeBoardTdNum">5</div>
            <div class="menteeBoardTd menteeBoardTdTitle">게시글제목</div>
            <div class="menteeBoardTd menteeBoardTdDate">작성날짜 작성시간</div>
            <div class="menteeBoardTd menteeBoardTdAuthor">작성자1</div>
            <div class="menteeBoardTd menteeBoardTdViews">조회수 0</div>
          </div>

          <div class="menteeBoardRow" data-board-id="6">
            <div class="menteeBoardTd menteeBoardTdNum">6</div>
            <div class="menteeBoardTd menteeBoardTdTitle">게시글제목</div>
            <div class="menteeBoardTd menteeBoardTdDate">작성날짜 작성시간</div>
            <div class="menteeBoardTd menteeBoardTdAuthor">작성자1</div>
            <div class="menteeBoardTd menteeBoardTdViews">조회수 0</div>
          </div>

          <div class="menteeBoardRow" data-board-id="7">
            <div class="menteeBoardTd menteeBoardTdNum">7</div>
            <div class="menteeBoardTd menteeBoardTdTitle">게시글제목</div>
            <div class="menteeBoardTd menteeBoardTdDate">작성날짜 작성시간</div>
            <div class="menteeBoardTd menteeBoardTdAuthor">작성자1</div>
            <div class="menteeBoardTd menteeBoardTdViews">조회수 0</div>
          </div>

          <div class="menteeBoardRow" data-board-id="8">
            <div class="menteeBoardTd menteeBoardTdNum">8</div>
            <div class="menteeBoardTd menteeBoardTdTitle">게시글제목</div>
            <div class="menteeBoardTd menteeBoardTdDate">작성날짜 작성시간</div>
            <div class="menteeBoardTd menteeBoardTdAuthor">작성자1</div>
            <div class="menteeBoardTd menteeBoardTdViews">조회수 0</div>
          </div>

          <div class="menteeBoardRow" data-board-id="9">
            <div class="menteeBoardTd menteeBoardTdNum">9</div>
            <div class="menteeBoardTd menteeBoardTdTitle">게시글제목</div>
            <div class="menteeBoardTd menteeBoardTdDate">작성날짜 작성시간</div>
            <div class="menteeBoardTd menteeBoardTdAuthor">작성자1</div>
            <div class="menteeBoardTd menteeBoardTdViews">조회수 0</div>
          </div>

          <div class="menteeBoardRow" data-board-id="10">
            <div class="menteeBoardTd menteeBoardTdNum">10</div>
            <div class="menteeBoardTd menteeBoardTdTitle">게시글제목</div>
            <div class="menteeBoardTd menteeBoardTdDate">작성날짜 작성시간</div>
            <div class="menteeBoardTd menteeBoardTdAuthor">작성자3</div>
            <div class="menteeBoardTd menteeBoardTdViews">조회수 0</div>
          </div>

        </div>
      </div>


      <!-- 하단: 페이지네이션 + 글작성 -->
      <div class="menteeBoardFooter">
        <div class="menteeBoardPaginationWrap">
          <button class="menteeBoardPageBtn active" data-page="1">1</button>
          <button class="menteeBoardPageBtn" data-page="2">2</button>
          <button class="menteeBoardPageBtn" data-page="3">3</button>
          <button class="menteeBoardPageBtn" data-page="4">4</button>
          <button class="menteeBoardPageBtn" data-page="5">5</button>
          <button class="menteeBoardPageBtn menteeBoardPageNext">&#62;</button>
        </div>
        <button class="menteeBoardWriteBtn" id="menteeBoardWriteBtn">글작성</button>
      </div>

    </div>
  </div>

  <div id="footerContainer"></div>

  <script src="${pageContext.request.contextPath}/assets/js/header.js"></script>
  <script src="${pageContext.request.contextPath}/assets/js/footer.js"></script>
  <script src="${pageContext.request.contextPath}/assets/js/user/mentee/menteeBoard/menteeBoardList.js"></script>

</body>

</html>