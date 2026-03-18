document.addEventListener("DOMContentLoaded", () => {

  let currentPage = 1;
  let currentType = "전체";
  let dateFrom = "";
  let dateTo = "";

  /* ========================
     목록 렌더
  ======================== */
  const noticeTableBody = document.getElementById("noticeTableBody");

  function renderTable(page) {
    if (!noticeTableBody) return;
    const notices = NoticeStore.getPage(page, currentType, dateFrom, dateTo);

    if (notices.length === 0) {
      noticeTableBody.innerHTML = `<div style="text-align:center; padding:40px; color:#aaa; font-size:16px;">조회된 공지사항이 없습니다.</div>`;
      return;
    }

    noticeTableBody.innerHTML = notices.map(n => `
      <div class="notice-table-row" data-id="${n.id}" style="cursor:pointer;">
        <div class="col col-no">${n.id}</div>
        <div class="col col-title">[${n.type}] ${n.title}</div>
        <div class="col col-date text-muted">${n.date} ${n.time}</div>
        <div class="col col-views text-muted">조회수 ${n.views}</div>
      </div>
    `).join("");

    noticeTableBody.querySelectorAll(".notice-table-row").forEach(row => {
      row.addEventListener("click", () => {
        sessionStorage.setItem("currentNoticeId", row.dataset.id);
        location.href = "noticeDetail.html";
      });
    });
  }

  /* ========================
     페이지네이션
  ======================== */
  const pagination = document.getElementById("pagination");

  function renderPagination() {
    if (!pagination) return;
    const TOTAL_PAGES = NoticeStore.totalPages(currentType, dateFrom, dateTo);
    if (TOTAL_PAGES === 0) { pagination.innerHTML = ""; return; }
    const GROUP_SIZE = 10;
    const groupStart = Math.floor((currentPage - 1) / GROUP_SIZE) * GROUP_SIZE + 1;
    const groupEnd = Math.min(groupStart + GROUP_SIZE - 1, TOTAL_PAGES);

    let html = "";
    html += `<button class="page-btn page-btn-nav" data-action="first" ${currentPage === 1 ? "disabled" : ""}>&laquo;</button>`;
    html += `<button class="page-btn page-btn-nav" data-action="prevGroup" ${groupStart === 1 ? "disabled" : ""}>&lsaquo;</button>`;
    for (let i = groupStart; i <= groupEnd; i++) {
      html += `<button class="page-btn ${i === currentPage ? "is-active" : ""}" data-page="${i}">${i}</button>`;
    }
    html += `<button class="page-btn page-btn-nav" data-action="nextGroup" ${groupEnd >= TOTAL_PAGES ? "disabled" : ""}>&rsaquo;</button>`;
    html += `<button class="page-btn page-btn-nav" data-action="last" ${currentPage === TOTAL_PAGES ? "disabled" : ""}>&raquo;</button>`;
    pagination.innerHTML = html;
  }

  if (pagination) {
    pagination.addEventListener("click", e => {
      const btn = e.target.closest(".page-btn");
      if (!btn || btn.disabled) return;
      const TOTAL_PAGES = NoticeStore.totalPages(currentType, dateFrom, dateTo);
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

  /* ========================
     종류 드롭다운
  ======================== */
  const btnType = document.getElementById("btnType");
  const typeDropdown = document.getElementById("typeDropdown");
  if (btnType && typeDropdown) {
    btnType.addEventListener("click", () => {
      typeDropdown.style.display = typeDropdown.style.display === "block" ? "none" : "block";
    });
    document.addEventListener("click", e => {
      if (!btnType.contains(e.target)) typeDropdown.style.display = "none";
    });
  }

  window.selectType = function(type) {
    currentType = type;
    currentPage = 1;
    document.getElementById("btnType").textContent = type + " ▼";
    document.getElementById("typeDropdown").style.display = "none";
    renderTable(currentPage);
    renderPagination();
  };

  /* ========================
     등록 버튼
  ======================== */
  const btnWrite = document.getElementById("btnWrite");
  if (btnWrite) {
    btnWrite.addEventListener("click", () => {
      location.href = "noticeWrite.html";
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

  renderTable(currentPage);
  renderPagination();

});
