document.addEventListener('DOMContentLoaded', () => {
  // 글목록 버튼
  const backBtn = document.getElementById('mentorBoardDetailBackBtn');
  if (backBtn) {
    backBtn.addEventListener('click', () => {
      window.location.href = './mentorBoardList.html';
    });
  }

  // 수정 버튼
  const editBtn = document.getElementById('mentorBoardDetailEditBtn');
  if (editBtn) {
    editBtn.addEventListener('click', () => {
      const boardId = new URLSearchParams(window.location.search).get('boardId');
      // 현재 제목/내용을 sessionStorage에 저장
      const subject = document.querySelector('.mentorBoardDetailTitle')?.textContent || '';
      const content = document.querySelector('.mentorBoardDetailBody')?.textContent.trim() || '';
      sessionStorage.setItem('mentorBoardModifyData', JSON.stringify({ subject, content }));
      window.location.href = `./mentorBoardModify.html?boardId=${boardId}`;
    });
  }

  // 삭제 버튼
  const deleteBtn = document.getElementById('mentorBoardDetailDeleteBtn');
  if (deleteBtn) {
    deleteBtn.addEventListener('click', () => {
      if (confirm('게시글을 삭제하시겠습니까?')) {
        window.location.href = './mentorBoardList.html';
      }
    });
  }

  // 댓글 등록 버튼
  const commentSubmitBtn = document.getElementById('mentorBoardDetailCommentSubmitBtn');
  if (commentSubmitBtn) {
    commentSubmitBtn.addEventListener('click', () => {
      const input = document.getElementById('mentorBoardDetailCommentInput');
      const content = input.value.trim();
      if (!content) {
        alert('댓글 내용을 입력해주세요.');
        return;
      }
      input.value = '';
    });
  }

  // 댓글 수정 버튼
  document.querySelectorAll('.mentorBoardDetailCommentEditBtn').forEach((btn) => {
    btn.addEventListener('click', (e) => {
      const commentId = e.target.closest('.mentorBoardDetailCommentItem').dataset.commentId;
      // 댓글 수정 로직
    });
  });

  // 댓글 삭제 버튼
  document.querySelectorAll('.mentorBoardDetailCommentDeleteBtn').forEach((btn) => {
    btn.addEventListener('click', (e) => {
      if (confirm('댓글을 삭제하시겠습니까?')) {
        const item = e.target.closest('.mentorBoardDetailCommentItem');
        item.remove();
      }
    });
  });
});