/**
 * 멘토링 등록 파일 업로드 핸들링
 */
document.addEventListener('DOMContentLoaded', () => {
    const fileInput = document.querySelector('#curriculumFile');
    const customBtn = document.querySelector('#customFileBtn');
    const nameDisplay = document.querySelector('#fileNameDisplay');
    const form = document.querySelector('form');

    // 1. 디자인된 버튼 클릭 시 실제 input 실행
    if (customBtn) {
        customBtn.addEventListener('click', () => fileInput.click());
    }

    // 2. 파일 선택 시 이벤트
    if (fileInput) {
        fileInput.addEventListener('change', function() {
            if (this.files && this.files.length > 0) {
                const file = this.files[0];
                const fileName = file.name;
                const fileSize = file.size;
                const maxSize = 10 * 1024 * 1024; // 10MB 제한

                // 확장자 체크
                const ext = fileName.split('.').pop().toLowerCase();
                const allowedExts = ['pdf', 'doc', 'docx', 'hwp'];

                if (!allowedExts.includes(ext)) {
                    alert('PDF 또는 문서 파일만 업로드 가능합니다.');
                    this.value = '';
                    nameDisplay.textContent = '선택된 파일이 없습니다.';
                    return;
                }

                // 용량 체크
                if (fileSize > maxSize) {
                    alert('파일 용량은 10MB를 초과할 수 없습니다.');
                    this.value = '';
                    nameDisplay.textContent = '선택된 파일이 없습니다.';
                    return;
                }

                // 파일명 표시
                nameDisplay.textContent = fileName;
            } else {
                nameDisplay.textContent = '선택된 파일이 없습니다.';
            }
        });
    }

    // 3. 폼 제출 전 최종 유효성 검사
    form.addEventListener('submit', (e) => {
        const subject = document.querySelector('#mentoringSubject').value;
        const title = document.querySelector('#mentoringTitle').value.trim();

        if (subject === 'none') {
            alert('학습 과목을 선택해주세요.');
            e.preventDefault();
            return;
        }

        if (!title) {
            alert('멘토링 주제를 입력해주세요.');
            e.preventDefault();
            return;
        }
    });
});