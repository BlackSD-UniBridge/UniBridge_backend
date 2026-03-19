// header.js
(function () {
  // 1. 컨텍스트 루트 추출 (페이지 이동 시 필요)
  const getBase = () => {
    const { origin, pathname } = window.location;
    const contextName = 'unibridge'; // 프로젝트명
    if (pathname.includes(`/${contextName}`)) {
      return origin + `/${contextName}`;
    }
    return origin;
  };

  const base = getBase();
  console.log("UniBridge Header JS Loaded. Base Path:", base);

<<<<<<< HEAD
  const roleMap = {
    mentor: { label: '멘토', cls: 'mentoRoleBadge' },
    mentee: { label: '멘티', cls: 'mentiRoleBadge' },
    nodecided: { label: '미정', cls: 'nodecided' },
  };

  let authState = {
    loggedIn: true,
    role: 'mentee',
    userName: '홍길동'
  };

  const getHeaderTemplate = () => {
    const commonNav = `
      <a href="${base}/main.jsp" class="headerLogo">
        <img src="${base}/assets/img/UniBridge.png" alt="UniBridge" />
      </a>
      <nav class="headerNav">
       	<a href="${base}/mentor/mentorSearchOk.sch" id="test-mentor-link">멘토 검색</a>
        <a href="${base}/app/user/mentee/menteeBoard/menteeBoardList.jsp">게시판</a>
        <a href="${base}/app/user/notice/report.jsp">학습보고서</a>
        <a href="${base}/announceBoard.jsp">공지사항</a>
      </nav>
    `;

	
    const authSection = !authState.loggedIn ? `
      <div class="headerAuthGroup">
        <a href="${base}/app/user/siginup/terms.jsp" class="headerBtnText">회원가입</a>
        <div class="headerDivider"></div>
        <a href="${base}/app/user/siginin/signin.jsp" class="headerBtnSignIn">로그인</a>
      </div>` : `
      <div class="headerAuthGroup">
        <div class="userInfoWrap">
          <span class="userName">${authState.userName}</span>
          <span class="userRoleDivider">/</span>
          <span class="userRoleBadge ${roleMap[authState.role].cls}">${roleMap[authState.role].label}</span>
        </div>
        <div class="headerDivider"></div>
        <a href="${base}/app/user/mentor/myPage/myPage.jsp" class="headerBtnText">마이페이지</a>
        <div class="headerDivider"></div>
        <a href="#" class="headerBtnText logout" id="headerBtnLogout">로그아웃</a>
      </div>`;

    return `
      <header class="headerWrap">
        <div class="headerInner">
          ${commonNav}
          ${authSection}
        </div>
      </header>
    `;
  };
  
  console.log("현재 설정된 멘토검색 주소: ", document.getElementById('test-mentor-link')?.href);

  // 3. 렌더링 함수
  function renderHeader() {
    const mount = document.getElementById('headerContainer');
    if (mount) {
      mount.innerHTML = getHeaderTemplate();

      // 로그아웃 버튼 이벤트 리스너
      const logoutBtn = document.getElementById('headerBtnLogout');
      /*if (logoutBtn) {
        logoutBtn.addEventListener('click', (e) => {
          e.preventDefault();
          
          // --- 수정 부분 시작 ---
          authState.loggedIn = false; // 상태 변경
          alert('로그아웃 되었습니다.');
          
          renderHeader(); // 변경된 상태로 화면 다시 그리기
          // --- 수정 부분 끝 ---
        });
      }*/
	  if (logoutBtn) {
	      logoutBtn.addEventListener('click', (e) => {
	          // e.preventDefault(); 제거하여 href 경로로 이동 허용
	          alert('로그아웃 되었습니다.');
	      });
	  }
	  
=======
  // 2. 초기화 함수 (이벤트 바인딩만 담당)
  function initHeaderEvents() {
    // JSP(서버)에서 이미 그려진 로그아웃 버튼을 찾습니다.
    // HTML에 class="logout"이 있는지 확인하세요.
    const logoutBtn = document.querySelector('.headerBtnText.logout');

    if (logoutBtn) {
      logoutBtn.addEventListener('click', (e) => {
        e.preventDefault();
        
        if (confirm('로그아웃 하시겠습니까?')) {
          // 서버의 로그아웃 서블릿으로 이동 (매핑에 맞게 .main 또는 .mem 수정)
          window.location.href = `${base}/logout.mem`; 
        }
      });
      console.log("Logout event bound successfully.");
>>>>>>> 60f7e90a68ab30342cb6041fc9d40e247b9476e5
    }
  }

  // DOM 로드 완료 시 실행
  if (document.readyState === 'loading') {
    document.addEventListener('DOMContentLoaded', initHeaderEvents);
  } else {
    initHeaderEvents();
  }
})();