document.addEventListener("DOMContentLoaded", () => {

  /* ========================
     메뉴 경로 매핑
  ======================== */
  const menuMap = {
    main:     `${toAdmin}/adminMain/main.jsp`,
    notice:   `${toAdmin}/adminNotice/noticeList.jsp`,
    board:    `${toAdmin}/adminBoard/menteeBoard/menteeBoardList.jsp`,
    report:   `${toAdmin}/adminReport/reportList.jsp`,
    user:     `${toAdmin}/adminUserManagement/userList.jsp`,
    matching: `${toAdmin}/adminMatching/matchingList.jsp`,
    logout:   `${toAdmin}/adminLogin/login.jsp`,
  };

  /* ========================
     현재 페이지 → 활성 메뉴 판별
  ======================== */

  const pageKeyMap = {
    notice:   ["noticeList", "noticeDetail.jsp", "noticeWrite.jsp", "noticeEdit.jsp"],
    board:    ["mentorBoardList.jsp", "menteeBoardList.jsp", "mentorBoardDetail.jsp", "menteeBoardDetail.jsp", "mentorBoardWrite.jsp", "menteeBoardWrite.jsp", "mentorBoardEdit.jsp", "menteeBoardEdit.jsp"],
    report:   ["reportList.jsp", "reportDetail.jsp"],
    user:     ["userList.jsp", "userDetail.jsp", "userDetailWaiting.jsp"],
    matching: ["matchingList.jsp", "matchingDetail.jsp"],
  };

  const currentPath = window.location.pathname;
  const getCurrentMenuKey = () => {
    for (const [key, pages] of Object.entries(pageKeyMap)) {
      if (pages.some(name => currentPath.includes(name))) return key;
    }
    return "";
  };

  const currentMenuKey = getCurrentMenuKey();

  document.querySelectorAll("[data-link]").forEach(btn => {
    const target = btn.dataset.link;

    if (target === currentMenuKey) btn.classList.add("is-active");

    btn.addEventListener("click", e => {
      e.preventDefault();
      if (target === "logout") {
        if (!window.confirm("로그아웃 하시겠습니까?")) return;
      }
      const path = menuMap[target];
      if (path) window.location.href = path;
    });
  });

});
