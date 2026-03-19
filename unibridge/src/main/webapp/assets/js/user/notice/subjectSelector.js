/**
 * subjectSelector.js - 과목 선택 드롭다운 제어
 */
const filterSubjectSelector = document.querySelector('.filter-subject-container');

if (filterSubjectSelector) {
    filterSubjectSelector.addEventListener('click', function() {
        const filterSubjectDropdown = document.querySelector('.filter-subject-dropdown');
        filterSubjectDropdown.classList.toggle('disabled');
    });
}

// 과목 아이템 클릭 시 선택 처리 (이벤트 위임)
document.addEventListener('click', (e) => {
    const item = e.target.closest('.filter-subject-item');
    if (item) {
        const placeholder = document.querySelector('.filter-subject-placeholder');
        placeholder.textContent = item.textContent;
        placeholder.classList.add('selected');
        document.querySelector('.filter-subject-dropdown').classList.add('disabled');
        
        // 여기서 선택된 과목으로 리스트를 필터링하는 로직(조회 API 호출 등)을 넣을 수 있습니다.
    }
});

// 더미 데이터 관련 변수 및 초기화 코드 삭제됨