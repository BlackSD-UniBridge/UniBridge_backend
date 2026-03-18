document.addEventListener("DOMContentLoaded", () => {

  /* ========================
     유저 목록 데이터
  ======================== */
  const users = [
    { id: 1, name: "유저 이름 1", type: "미정",  status: "대기",   page: "./userDetail/userDetailWaiting.html" },
    { id: 2, name: "유저 이름 2", type: "멘토",  status: "매칭됨", page: "./userDetail/userDetail.html" },
    { id: 3, name: "유저 이름 3", type: "멘토",  status: "매칭중", page: "./userDetail/userDetail.html" },
    { id: 4, name: "유저 이름 4", type: "멘티",  status: "매칭중", page: "./userDetail/userDetail.html" },
    { id: 5, name: "유저 이름 5", type: "멘티",  status: "매칭됨", page: "./userDetail/userDetail.html" },
    { id: 6, name: "유저 이름 6", type: "멘티",  status: "매칭됨", page: "./userDetail/userDetail.html" },
    { id: 7, name: "유저 이름 7", type: "멘토",  status: "매칭중", page: "./userDetail/userDetail.html" },
    { id: 8, name: "유저 이름 8", type: "멘티",  status: "매칭됨", page: "./userDetail/userDetail.html" },
  ];

  let currentTypeFilter   = "전체";
  let currentStatusFilter = "전체";

  /* ========================
     유저 카드 SVG 아이콘
  ======================== */
  const avatarSVG = `
    <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="1.6">
      <circle cx="12" cy="8" r="4"/>
      <path d="M5 19c1.5-3 4.1-4.6 7-4.6s5.5 1.6 7 4.6" stroke-linecap="round"/>
    </svg>`;

  const chatSVG = `
    <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="1.6">
      <path d="M21 15a2 2 0 01-2 2H7l-4 4V5a2 2 0 012-2h14a2 2 0 012 2z" stroke-linecap="round" stroke-linejoin="round"/>
      <line x1="9" y1="10" x2="15" y2="10" stroke-linecap="round"/>
      <line x1="9" y1="14" x2="13" y2="14" stroke-linecap="round"/>
    </svg>`;

  /* ========================
     카드 렌더링
  ======================== */
  function renderUserList() {
    const container = document.getElementById("userList");
    if (!container) return;

    const filtered = users.filter(u => {
      const typeOk   = currentTypeFilter   === "전체" || u.type   === currentTypeFilter;
      const statusOk = currentStatusFilter === "전체" || u.status === currentStatusFilter;
      return typeOk && statusOk;
    });

    if (filtered.length === 0) {
      container.innerHTML = `<div style="text-align:center;padding:40px;color:#aaa;font-size:18px;">해당하는 유저가 없습니다.</div>`;
      return;
    }

    container.innerHTML = filtered.map(u => `
      <div class="user-card" onclick="location.href='${u.page}'">
        <div class="user-card-left">
          <div class="user-avatar">${avatarSVG}</div>
          <span class="user-name">${u.name}</span>
          <span class="user-type-badge">${u.type}</span>
        </div>
        <div class="user-card-right">
          <span class="user-status">${u.status}</span>
          <div class="user-chat-icon" onclick="event.stopPropagation(); alert('채팅 기능은 준비 중입니다.')">${chatSVG}</div>
        </div>
      </div>
    `).join("");
  }

  /* ========================
     필터 버튼 이벤트
  ======================== */
  const btnType   = document.getElementById("btnFilterType");
  const btnStatus = document.getElementById("btnFilterStatus");

  if (btnType) {
    btnType.addEventListener("click", () => {
      const types = ["전체", "멘토", "멘티", "미정"];
      const idx = types.indexOf(currentTypeFilter);
      currentTypeFilter = types[(idx + 1) % types.length];
      btnType.textContent = currentTypeFilter === "전체" ? "유형" : currentTypeFilter;
      btnType.classList.toggle("is-active", currentTypeFilter !== "전체");
      renderUserList();
    });
  }

  if (btnStatus) {
    btnStatus.addEventListener("click", () => {
      const statuses = ["전체", "대기", "매칭중", "매칭됨"];
      const idx = statuses.indexOf(currentStatusFilter);
      currentStatusFilter = statuses[(idx + 1) % statuses.length];
      btnStatus.textContent = currentStatusFilter === "전체" ? "상태" : currentStatusFilter;
      btnStatus.classList.toggle("is-active", currentStatusFilter !== "전체");
      renderUserList();
    });
  }

  renderUserList();


  /* ========================
     설문조사 확인
  ======================== */
  document.querySelectorAll(".btn-survey").forEach(btn => {
    btn.addEventListener("click", () => {
      alert("설문조사 기능은 백엔드 연동 후 동작합니다.");
    });
  });

});
