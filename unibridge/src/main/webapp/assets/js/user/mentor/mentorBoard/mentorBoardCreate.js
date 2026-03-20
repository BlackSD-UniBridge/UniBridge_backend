const cancelButton = document.querySelector('.cancle-btn');
const writeForm = document.getElementById('write-form');

// 취소 버튼
cancelButton?.addEventListener('click', () => {
  window.location.href = `${contextPath}/mentor/mentorBoard/MentorBoardList.mob`;
});

// 제목 최대 50자 제한
const subjectInput = document.getElementById('mentorBoardCreateSubject');
if (subjectInput) {
  subjectInput.addEventListener('input', () => {
    if (subjectInput.value.length > 50) {
      subjectInput.value = subjectInput.value.slice(0, 50);
    }
  });
}
