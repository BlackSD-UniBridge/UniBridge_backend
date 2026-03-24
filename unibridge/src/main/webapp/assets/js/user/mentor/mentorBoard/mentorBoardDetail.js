document.addEventListener('DOMContentLoaded', () => {
 
  // ── 목록 버튼 ──
  const listBtn = document.querySelector('.list-btn');
  if (listBtn) {
    listBtn.addEventListener('click', () => {
      window.location.href = `${contextPath}/mentor/mentorBoard/MentorBoardList.mob`;
    });
  }
 
  // ── 수정 버튼 ──
  const modifyBtn = document.querySelector('.modify-btn');
  if (modifyBtn) {
    modifyBtn.addEventListener('click', () => {
      const boardNumber = listBtn?.dataset.boardNumber;
      if (!boardNumber) return alert('게시글 번호를 찾을 수 없습니다.');
      window.location.href = `${contextPath}/mentor/mentorBoard/MentorBoardUpdate.mob?MentorBoardNumber=${boardNumber}`;
    });
  }
 
  // ── 삭제 버튼 ──
  const deleteBtn = document.querySelector('.delete-btn');
  if (deleteBtn) {
    deleteBtn.addEventListener('click', async () => {
      if (!confirm('게시글을 삭제하시겠습니까?')) return;
 
      const boardNumber = listBtn?.dataset.boardNumber;
      if (!boardNumber) return alert('게시글 번호를 찾을 수 없습니다.');
 
      try {
        const res = await fetch(
          `${contextPath}/mentor/mentorBoard/MentorBoardDelete.mob?MentorBoardNumber=${encodeURIComponent(boardNumber)}`,
          { method: 'POST', headers: { 'X-Requested-With': 'fetch' } }
        );
        if (!res.ok) throw new Error('삭제 요청 실패');
        alert('게시글이 삭제되었습니다.');
        window.location.href = `${contextPath}/mentor/mentorBoard/MentorBoardList.mob`;
      } catch (err) {
        console.error('게시글 삭제 실패:', err);
        alert('게시글 삭제에 실패했습니다.');
      }
    });
  }
 
  // ── 댓글 수정 버튼 ──
  document.querySelectorAll('.comment-modify-btn').forEach(btn => {
    btn.addEventListener('click', () => {
      const comId   = btn.dataset.comId;
      const boardId = btn.dataset.boardId;
      const content = btn.dataset.content;
 
      const newContent = prompt('댓글을 수정해주세요.', content);
      if (newContent === null) return;             // 취소
      if (!newContent.trim()) return alert('댓글 내용을 입력해주세요.');
 
      document.getElementById('updateComId').value    = comId;
      document.getElementById('updateBoardId').value  = boardId;
      document.getElementById('updateContent').value  = newContent.trim();
      document.getElementById('comment-update-form').submit();
    });
  });
 
  // ── 댓글 삭제 버튼 ──
  document.querySelectorAll('.comment-delete-btn').forEach(btn => {
    btn.addEventListener('click', () => {
      if (!confirm('댓글을 삭제하시겠습니까?')) return;
 
      const comId   = btn.dataset.comId;
      const boardId = btn.dataset.boardId;
 
      document.getElementById('deleteComId').value   = comId;
      document.getElementById('deleteBoardId').value = boardId;
      document.getElementById('comment-delete-form').submit();
    });
  });
 
});