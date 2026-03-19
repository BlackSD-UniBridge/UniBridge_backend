// menteeBoardModify.js

document.addEventListener('DOMContentLoaded', () => {
  // URL에서 boardId 추출
  const params = new URLSearchParams(window.location.search);
  const boardId = params.get('boardId');

  // detail 페이지에서 넘어온 데이터를 sessionStorage에서 불러오기
  const savedData = sessionStorage.getItem('menteeBoardModifyData');
  if (savedData) {
    const data = JSON.parse(savedData);
    const subjectInput = document.getElementById('menteeBoardModifySubject');
    const contentTextarea = document.getElementById('menteeBoardModifyContent');
    if (subjectInput) subjectInput.value = data.subject || '';
    if (contentTextarea) contentTextarea.value = data.content || '';
  }

  // 글목록 버튼
  const backBtn = document.getElementById('menteeBoardModifyBackBtn');
  if (backBtn) {
    backBtn.addEventListener('click', () => {
      window.location.href = `./menteeBoardDetail.jsp?boardId=${boardId}`;
    });
  }

  // 수정 버튼
  const submitBtn = document.getElementById('menteeBoardModifySubmitBtn');
  if (submitBtn) {
    submitBtn.addEventListener('click', () => {
      const subject = document.getElementById('menteeBoardModifySubject').value.trim();
      const content = document.getElementById('menteeBoardModifyContent').value.trim();

      if (!subject) { alert('제목을 입력해주세요.'); return; }
      if (!content) { alert('내용을 입력해주세요.'); return; }

      sessionStorage.removeItem('menteeBoardModifyData');
      window.location.href = `./menteeBoardDetail.jsp?boardId=${boardId}`;
    });
  }

  // 삭제 버튼
  const deleteBtn = document.getElementById('menteeBoardModifyDeleteBtn');
  if (deleteBtn) {
    deleteBtn.addEventListener('click', () => {
      if (confirm('게시글을 삭제하시겠습니까?')) {
        sessionStorage.removeItem('menteeBoardModifyData');
        window.location.href = './menteeBoardList.jsp';
      }
    });
  }
});