// mentorBoardCreate.js

document.addEventListener('DOMContentLoaded', () => {
  // 글목록 버튼
  const backBtn = document.getElementById('mentorBoardCreateBackBtn');
  if (backBtn) {
    backBtn.addEventListener('click', () => {
      window.location.href = './mentorBoardList.jsp'; 
    });
  }

  // 게시글 등록 버튼
  const submitBtn = document.getElementById('mentorBoardCreateSubmitBtn');
  if (submitBtn) {
    submitBtn.addEventListener('click', () => {
      const subject = document.getElementById('mentorBoardCreateSubject').value.trim();
      const content = document.getElementById('mentorBoardCreateContent').value.trim();

      if (!subject) {
        alert('제목을 입력해주세요.');
        return;
      }
      if (!content) {
        alert('내용을 입력해주세요.');
        return;
      }

      // 등록 성공 가정 후 목록으로 이동
      alert('게시글이 등록되었습니다.');
      window.location.href = 'mentorBoardList.jsp';
    });
  }

  // 제목 최대 50자 제한
  const subjectInput = document.getElementById('mentorBoardCreateSubject');
  if (subjectInput) {
    subjectInput.addEventListener('input', () => {
      if (subjectInput.value.length > 50) {
        subjectInput.value = subjectInput.value.slice(0, 50);
      }
    });
  }
});