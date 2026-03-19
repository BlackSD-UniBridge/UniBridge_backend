// mentorBoardModify.js

document.addEventListener('DOMContentLoaded', () => {
  // URL에서 boardId 추출
  const params = new URLSearchParams(window.location.search);
  const boardId = params.get('mentorboard_number');

  // detail 페이지에서 넘어온 데이터를 sessionStorage에서 불러오기
  const savedData = sessionStorage.getItem('mentorBoardModifyData');
  if (savedData) {
    const data = JSON.parse(savedData);
    const subjectInput = document.getElementById('mentorBoardModifySubject');
    const contentTextarea = document.getElementById('mentorBoardModifyContent');
    if (subjectInput) subjectInput.value = data.subject || '';
    if (contentTextarea) contentTextarea.value = data.content || '';
  }

  // 글목록 버튼
  const backBtn = document.getElementById('mentorBoardModifyBackBtn');
  if (backBtn) {
    backBtn.addEventListener('click', () => {
      window.location.href = `./mentorBoardDetail.jsp?boardId=${boardId}`;
    });
  }

  // 수정 버튼
  const submitBtn = document.getElementById('mentorBoardModifySubmitBtn');
  if (submitBtn) {
    submitBtn.addEventListener('click', () => {
      const subject = document.getElementById('mentorBoardModifySubject').value.trim();
      const content = document.getElementById('mentorBoardModifyContent').value.trim();

      if (!subject) { alert('제목을 입력해주세요.'); return; }
      if (!content) { alert('내용을 입력해주세요.'); return; }

      sessionStorage.removeItem('mentorBoardModifyData');
      window.location.href = `./mentorBoardDetail.jsp?boardId=${boardId}`;
    });
  }

  // 삭제 버튼
  const deleteBtn = document.getElementById('mentorBoardModifyDeleteBtn');
  if (deleteBtn) {
    deleteBtn.addEventListener('click', () => {
      if (confirm('게시글을 삭제하시겠습니까?')) {
        sessionStorage.removeItem('mentorBoardModifyData');
        window.location.href = './mentorBoardList.jsp';
      }
    });
  }
});