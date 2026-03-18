document.addEventListener("DOMContentLoaded", () => {

  /* =========================
     관리자 통계 데이터
  ========================= */
  const summaryData = {
    totalUsers: 136,
    mentorCount: 60,
    menteeCount: 76,
    todayVisitors: 58,
    matchingCount: 48,
  };

  const recentBoards = [
    { no: 1, title: "아무개 제목 5", date: "03.03" },
    { no: 2, title: "아무개 제목 4", date: "03.03" },
    { no: 3, title: "아무개 제목 3", date: "03.02" },
    { no: 4, title: "아무개 제목 2", date: "03.02" },
    { no: 5, title: "아무개 제목 1", date: "03.01" },
  ];

  const recentUsers = [
    { name: "유저1", type: "멘토", date: "03.03" },
    { name: "유저2", type: "멘티", date: "03.03" },
    { name: "유저3", type: "멘티", date: "03.02" },
    { name: "유저4", type: "멘토", date: "03.02" },
    { name: "유저5", type: "멘티", date: "03.01" },
  ];

  /* =========================
     summary 데이터 출력
  ========================= */
  const totalJoinCount = document.getElementById("totalJoinCount");
  const joinSubText    = document.getElementById("joinSubText");
  const todayVisitCount = document.getElementById("todayVisitCount");
  const matchingCount  = document.getElementById("matchingCount");

  if (totalJoinCount) totalJoinCount.textContent = `${summaryData.totalUsers} 명`;
  if (joinSubText)    joinSubText.textContent    = `멘토 ${summaryData.mentorCount}  멘티 ${summaryData.menteeCount}`;
  if (todayVisitCount) todayVisitCount.textContent = `${summaryData.todayVisitors} 명`;
  if (matchingCount)  matchingCount.textContent  = `${summaryData.matchingCount} 명`;

  /* =========================
     최근 게시글 출력
  ========================= */
  const recentBoardList = document.getElementById("recentBoardList");
  if (recentBoardList) {
    recentBoardList.innerHTML = recentBoards
      .map(item => `
        <div class="table-row">
          <span>${item.no}</span>
          <span>${item.title}</span>
          <span>${item.date}</span>
        </div>
      `)
      .join("");
  }

  /* =========================
     최근 가입자 출력
  ========================= */
  const recentUserList = document.getElementById("recentUserList");
  if (recentUserList) {
    recentUserList.innerHTML = recentUsers
      .map(item => `
        <div class="table-row">
          <span>${item.name}</span>
          <span>${item.type}</span>
          <span>${item.date}</span>
        </div>
      `)
      .join("");
  }

});