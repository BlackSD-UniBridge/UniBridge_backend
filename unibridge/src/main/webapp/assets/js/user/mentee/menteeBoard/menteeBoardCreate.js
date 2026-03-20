// DOM 요소 가져오기
const cancelButton = document.querySelector('.cancle-btn');
const writeForm = document.getElementById('write-form');

// 취소 버튼 클릭 핸들러
cancelButton?.addEventListener('click', () => {
    window.location.href = contextPath + '/mentee/menteeBoard/MenteeBoardList.meb';
});

// 제목 최대 50자 제한
const subjectInput = document.getElementById('menteeBoardCreateSubject');
if (subjectInput) {
    subjectInput.addEventListener('input', () => {
        if (subjectInput.value.length > 50) {
            subjectInput.value = subjectInput.value.slice(0, 50);
        }
    });
}

