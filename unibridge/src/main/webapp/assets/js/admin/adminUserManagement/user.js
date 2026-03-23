document.addEventListener("DOMContentLoaded", () => {
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
  
  async function updateUserList() {
	const response = await fetch(window.contextPath + "/api/admin/adminMM/searchUsers.admin");
	if (!response.ok) {
	  container.innerHTML = `<div style="text-align:center;padding:40px;color:#aaa;font-size:18px;">해당하는 유저가 없습니다.</div>`;
	  return;
	}

	const userJson = await response.json();
	const filteredUserJson = userJson.map(u => {
	  /* 우선 고정 경로로 설정 후 나중에 서블릿 경로로 변경 */
	  const redirectPaths = {
		/* 공통 분모가 되는 경우 */
		"공통"		: `${window.contextPath}/userMMDetail.admin?memberNumber=${u.memberNumber}`,
		
		/* 멘토/멘티의 경우*/
		"매칭 대기" 	: `${window.contextPath}/userMMDetailWait.admin?memberNumber=${u.memberNumber}`,
		"매칭 취소"	: `${window.contextPath}/userMMDetail.admin?memberNumber=${u.memberNumber}`,
		"매칭중"		: `${window.contextPath}/userMMDetail.admin?memberNumber=${u.memberNumber}`,
		"매칭됨"	  	: `${window.contextPath}/userMMDetail.admin?memberNumber=${u.memberNumber}`,
		
		/* 미정의 경우 */
		"대기"		: `${window.contextPath}/userMMDetailWait.admin?memberNumber=${u.memberNumber}`,
		"거부"		: `${window.contextPath}/userMMDetail.admin?memberNumber=${u.memberNumber}`,
		"승인"		: `${window.contextPath}/userMMDetail.admin?memberNumber=${u.memberNumber}`,
	  };
	  
	  u.redirectPath = redirectPaths[u.memberState] 
	  	?? redirectPaths['공통'];
		
	  return u;
	});

	if (filteredUserJson.length === 0) {
	  container.innerHTML = `<div style="text-align:center;padding:40px;color:#aaa;font-size:18px;">해당하는 유저가 없습니다.</div>`;
	  return;
	}
	
	window.filteredUserList = filteredUserJson;
	return window.filteredUserList
  }
   
  /* ========================
     카드 렌더링
  ======================== */
  async function renderUserList(userType, userState) {
    const container = document.getElementById("userList");
    if (!container) return;
	
	let userList = window.filteredUserList;
	if (!userList) {
	  userList = await updateUserList();
	}
		
	const memberTypeToEngFilter = {
	  "맨토": "mentor",
	  "멘티": "mentee",
	  "미정": "non-decided",
	  "알 수 없음": "unknown"
	};
	
	const memberStateToEngFilter = {
		"대기": "pending",
		"매칭중": "pending-matching",
		"매칭됨": "matching"
	};
	
    container.innerHTML = userList
	  .filter(u => 
	  	u.memberState.includes(userState ?? '') &&
	  	u.memberType .includes(userType  ?? '')
	  )
	  .map(u => `
        <div class="user-card" onclick="location.href='${u.redirectPath}'">
          <div class="user-card-left ${memberTypeToEngFilter[u.memberType]} ${memberStateToEngFilter[u.memberState]}">
            <div class="user-avatar">${avatarSVG}</div>
            <span class="user-name">${u.memberName}</span>
            <span class="user-type-badge">${u.memberType}</span>
          </div>
          <div class="user-card-right">
            <span class="user-status">${u.memberState}</span>
            <div class="user-chat-icon" onclick="event.stopPropagation(); alert('채팅 기능은 준비 중입니다.')">${chatSVG}</div>
          </div>
        </div>
      `)
	  .join("");
  }

  let currentTypeFilter   = "전체";
  let currentStatusFilter = "전체";
  
  const types 	 = ["전체", "멘토", "멘티", "미정"];
  const statuses = ["전체", "대기", "매칭중", "매칭됨"];
  
  /* ========================
     필터 버튼 이벤트
  ======================== */
  const btnType   = document.getElementById("btnFilterType");
  const btnStatus = document.getElementById("btnFilterStatus");

  if (btnType) {
    btnType.addEventListener("click", () => {
      const idx = types.indexOf(currentTypeFilter);
      currentTypeFilter = types[(idx + 1) % types.length];
      btnType.textContent = currentTypeFilter === "전체" ? "유형" : currentTypeFilter;
      btnType.classList.toggle("is-active", currentTypeFilter !== "전체");
	  renderUserList(
	    currentTypeFilter 	=== "전체" ? undefined : currentTypeFilter,
	    currentStatusFilter === "전체" ? undefined : currentStatusFilter,
	  );
    });
  }

  if (btnStatus) {
    btnStatus.addEventListener("click", () => {
      
      const idx = statuses.indexOf(currentStatusFilter);
      currentStatusFilter = statuses[(idx + 1) % statuses.length];
      btnStatus.textContent = currentStatusFilter === "전체" ? "상태" : currentStatusFilter;
      btnStatus.classList.toggle("is-active", currentStatusFilter !== "전체");
	  renderUserList(
	    currentTypeFilter 	=== "전체" ? undefined : currentTypeFilter,
	    currentStatusFilter === "전체" ? undefined : currentStatusFilter,
	  );
    });
  }

  renderUserList(
    currentTypeFilter 	=== "전체" ? undefined : currentTypeFilter,
    currentStatusFilter === "전체" ? undefined : currentStatusFilter,
  );


  /* ========================
     설문조사 확인
  ======================== */
  document.querySelectorAll(".btn-survey").forEach(btn => {
    btn.addEventListener("click", () => {
      alert("설문조사 기능은 백엔드 연동 후 동작합니다.");
    });
  });

});
