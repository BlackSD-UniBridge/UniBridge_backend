document.addEventListener("DOMContentLoaded", () => {

  const path = window.location.pathname;
  const isMentor = path.includes("mentorBoard");
  const boardType = isMentor ? "mentor" : "mentee";

  /* ========================
     탭 링크
  ======================== */
  const tabMentee = document.getElementById("tab-mentee");
  const tabMentor = document.getElementById("tab-mentor");

  if (tabMentee) {
    tabMentee.href = "../menteeBoard/menteeBoardList.html";
    if (!isMentor) tabMentee.classList.add("is-active");
  }
  if (tabMentor) {
    tabMentor.href = "../mentorBoard/mentorBoardList.html";
    if (isMentor) tabMentor.classList.add("is-active");
  }

  /* ========================
     목록 렌더
  ======================== */
  let currentPage = 1;
  let dateFrom = "";
  let dateTo = "";
  const boardTableBody = document.getElementById("boardTableBody");

  function renderTable(page) {
    if (!boardTableBody) return;
    const posts = BoardStore.getPage(boardType, page, dateFrom, dateTo);

    if (posts.length === 0) {
      boardTableBody.innerHTML = `<div style="text-align:center; padding:40px; color:#aaa; font-size:16px;">조회된 게시글이 없습니다.</div>`;
      return;
    }

    boardTableBody.innerHTML = posts.map(p => `
      <div class="board-table-row" data-id="${p.id}" style="cursor:pointer;">
        <div class="col col-no">${p.id}</div>
        <div class="col col-title">${p.title}</div>
        <div class="col col-author text-muted">${p.author}</div>
        <div class="col col-date text-muted">${p.date} ${p.time}</div>
        <div class="col col-views text-muted">조회수 ${p.views}</div>
      </div>
    `).join("");

    boardTableBody.querySelectorAll(".board-table-row").forEach(row => {
      row.addEventListener("click", () => {
        const id = parseInt(row.dataset.id);
        sessionStorage.setItem("currentPostId", id);
        sessionStorage.setItem("currentBoardType", boardType);
        location.href = `${boardType}BoardDetail.html`;
      });
    });
  }

  /* ========================
     페이지네이션
  ======================== */
  const pagination = document.getElementById("pagination");

  function renderPagination() {
    if (!pagination) return;
    const TOTAL_PAGES = BoardStore.totalPages(boardType, dateFrom, dateTo);
    if (TOTAL_PAGES === 0) { pagination.innerHTML = ""; return; }
    const GROUP_SIZE = 10;
    const groupStart = Math.floor((currentPage - 1) / GROUP_SIZE) * GROUP_SIZE + 1;
    const groupEnd = Math.min(groupStart + GROUP_SIZE - 1, TOTAL_PAGES);

    let html = "";
    // 처음 «
    html += `<button class="page-btn page-btn-nav" data-action="first" ${currentPage === 1 ? "disabled" : ""}>&laquo;</button>`;
    // 이전 그룹 ‹
    html += `<button class="page-btn page-btn-nav" data-action="prevGroup" ${groupStart === 1 ? "disabled" : ""}>&lsaquo;</button>`;
    // 페이지 번호
    for (let i = groupStart; i <= groupEnd; i++) {
      html += `<button class="page-btn ${i === currentPage ? "is-active" : ""}" data-page="${i}">${i}</button>`;
    }
    // 다음 그룹 ›
    html += `<button class="page-btn page-btn-nav" data-action="nextGroup" ${groupEnd >= TOTAL_PAGES ? "disabled" : ""}>&rsaquo;</button>`;
    // 마지막 »
    html += `<button class="page-btn page-btn-nav" data-action="last" ${currentPage === TOTAL_PAGES ? "disabled" : ""}>&raquo;</button>`;
    pagination.innerHTML = html;
  }

  if (pagination) {
    pagination.addEventListener("click", e => {
      const btn = e.target.closest(".page-btn");
      if (!btn || btn.disabled) return;
      const TOTAL_PAGES = BoardStore.totalPages(boardType, dateFrom, dateTo);
      const GROUP_SIZE = 10;
      const groupStart = Math.floor((currentPage - 1) / GROUP_SIZE) * GROUP_SIZE + 1;
      const groupEnd = Math.min(groupStart + GROUP_SIZE - 1, TOTAL_PAGES);

      if      (btn.dataset.action === "first")     currentPage = 1;
      else if (btn.dataset.action === "prevGroup") currentPage = groupStart - 1;
      else if (btn.dataset.action === "nextGroup") currentPage = groupEnd + 1;
      else if (btn.dataset.action === "last")      currentPage = TOTAL_PAGES;
      else if (btn.dataset.page)                   currentPage = parseInt(btn.dataset.page);

      renderTable(currentPage);
      renderPagination();
    });
  }

  renderTable(currentPage);
  renderPagination();

  /* ========================
     작성 버튼
  ======================== */
  const btnWrite = document.getElementById("btnWrite");
  if (btnWrite) {
    btnWrite.addEventListener("click", () => {
      location.href = `${boardType}BoardWrite.html`;
    });
  }

  /* ========================
     조회 버튼
  ======================== */
  const btnSearch = document.getElementById("btnSearch");
  if (btnSearch) {
    btnSearch.addEventListener("click", () => {
      const from = document.getElementById("dateFrom")?.value;
      const to = document.getElementById("dateTo")?.value;
      if (from && to && from > to) {
        alert("시작 날짜가 종료 날짜보다 클 수 없습니다.");
        return;
      }
      dateFrom = from || "";
      dateTo = to || "";
      currentPage = 1;
      renderTable(currentPage);
      renderPagination();
    });
  }

});
