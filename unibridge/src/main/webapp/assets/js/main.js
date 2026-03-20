// main.js

/**
 * 1. 슬라이더 공통 초기화 함수
 */
function initSlider(sliderId, prevBtnId, nextBtnId, visibleCount) {
    const track = document.getElementById(sliderId);
    const prevBtn = document.getElementById(prevBtnId);
    const nextBtn = document.getElementById(nextBtnId);

    if (!track || !prevBtn || !nextBtn) return;

    const cards = track.children;
    let currentIdx = 0;
    const getCardWidth = () => (cards[0].getBoundingClientRect().width + 16);

    const updateSlider = () => {
        track.style.transform = `translateX(-${currentIdx * getCardWidth()}px)`;
        prevBtn.disabled = currentIdx === 0;
        nextBtn.disabled = currentIdx >= cards.length - visibleCount;
        prevBtn.style.opacity = prevBtn.disabled ? '0.3' : '1';
    };

    prevBtn.addEventListener('click', () => { if (currentIdx > 0) { currentIdx--; updateSlider(); } });
    nextBtn.addEventListener('click', () => { if (currentIdx < cards.length - visibleCount) { currentIdx++; updateSlider(); } });
    updateSlider();
}

/**
 * 2. 전체 페이지 공통 및 전용 로직
 */
document.addEventListener("DOMContentLoaded", function () {
    
    // [메인 페이지] 슬라이더 및 멘토 카드
    initSlider('contestSliderTrack', 'contestPrevBtn', 'contestNextBtn', 5);
    initSlider('jobBannerTrack', 'jobBannerPrev', 'jobBannerNext', 5);

    const mentoCards = document.querySelectorAll('.mentoRecommendCard');
    mentoCards.forEach((card) => {
        card.addEventListener('mouseenter', () => {
            const avatar = card.querySelector('.mentoRecommendAvatar');
            if (avatar) avatar.style.borderColor = '#2c5f8a';
        });
        card.addEventListener('mouseleave', () => {
            const avatar = card.querySelector('.mentoRecommendAvatar');
            if (avatar) avatar.style.borderColor = '#e8eef5';
        });
    });

    // [게시판 리스트 페이지] 전용 로직 (요소가 있을 때만 실행됨)
    const pageBtns = document.querySelectorAll('.menteeBoardPageBtn');
    if (pageBtns.length > 0) {
        pageBtns.forEach((btn) => {
            btn.addEventListener('click', () => {
                if (btn.classList.contains('menteeBoardPageNext')) return;
                pageBtns.forEach((b) => b.classList.remove('active'));
                btn.classList.add('active');
            });
        });
    }

    const rows = document.querySelectorAll('.menteeBoardRow');
    rows.forEach((row) => {
        row.addEventListener('click', () => {
            const boardId = row.dataset.boardId;
            if (boardId) {
                // 경로 주의: 상대 경로 ./ 가 현재 페이지 위치에 따라 달라질 수 있음
                window.location.href = `./menteeBoardDetail.jsp?boardId=${boardId}`;
            }
        });
    });

    const writeBtn = document.getElementById('menteeBoardWriteBtn');
    if (writeBtn) {
        writeBtn.addEventListener('click', () => {
            window.location.href = './menteeBoardCreate.jsp';
        });
    }
});

document.addEventListener("DOMContentLoaded", function () {
    // 대회 카드 클릭 시 이동
    const contestCards = document.querySelectorAll('.contestCard');
    contestCards.forEach((card) => {
        card.addEventListener('click', function() {
            // JSP에서 data-contest-id="${contest.contestNumber}" 로 넘겼을 경우
            const contestId = this.dataset.contestId;
            if(contestId) {
                location.href = window.location.origin + "/unibridge/common/noticeBoardReadOk.ntb?contestNumber=" + contestId;
            }
        });
    });
});