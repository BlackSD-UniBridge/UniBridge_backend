document.addEventListener("DOMContentLoaded", function () {
  // 1. 페이지네이션
  const pageBtns = document.querySelectorAll('.menteeBoardPageBtn');

  pageBtns.forEach((btn) => {
    btn.addEventListener('click', (e) => {
      // 다음 페이지 버튼(>)은 별도 로직이 필요하므로 현재는 리턴
      if (btn.classList.contains('menteeBoardPageNext')) return;

      // 활성 상태 변경

      btn.addEventListener('click', () => {
        if (btn.classList.contains('menteeBoardPageNext')) return;


        pageBtns.forEach((b) => b.classList.remove('active'));
        btn.classList.add('active');

        const pageNum = btn.dataset.page;
        console.log(`${pageNum} 페이지로 이동 또는 데이터 로드`);

      });
    });

    // 2. 행 클릭 → 상세 페이지 이동
    const rows = document.querySelectorAll('.menteeBoardRow');
    rows.forEach((row) => {
      row.addEventListener('click', () => {
        const boardId = row.dataset.boardId;
        if (boardId) {
          window.location.href = `/unibridge/app/user//mentee/menteeBoard/menteeBoardDetail.jsp?boardId=${boardId}`;
        }
      });
    });
	
    const writeBtn = document.getElementById('menteeBoardWriteBtn');
    if (writeBtn) {
      writeBtn.addEventListener('click', () => {
        // menteeBoardCreate.html로 이동
        window.location.href = '/unibridge/app/user//mentee/menteeBoard/menteeBoardCreate.jsp';
      });
    }
  });

  // 3. 글작성 버튼
  const writeBtn = document.getElementById('menteeBoardWriteBtn');
  if (writeBtn) {
    writeBtn.addEventListener('click', () => {
      window.location.href = '/unibridge/app/user//mentee/menteeBoard/menteeBoardCreate.jsp';
    });
  }
});

