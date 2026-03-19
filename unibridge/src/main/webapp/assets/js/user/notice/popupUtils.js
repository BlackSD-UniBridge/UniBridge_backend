/**
 * popupUtils.js - 팝업 생성 및 데이터 바인딩 도구
 */

// 상세 보기 팝업 데이터 주입 함수
export function insertLrViewerPopup({ target, data }) {
    // data는 서버에서 받아온 상세 정보 객체 (DTO)
    const html = `
      <div class="popup-viewer-container">
        <div class="popup-header">
          <div class="popup-title">학습일지 상세보기</div>
          <button class="popup-close-button">&times;</button>
        </div>
        <div class="popup-body">
          <div class="popup-body-top">
            <div class="config-container">
              <div class="config-week-session-container">
                <span class="config-subject-title__inner">학습 회차</span>
                <div class="viewer">${data.reportWeek}주차 ${data.reportSession}회차</div>
              </div>
              <div class="config-subject-container">
                <span class="config-subject-title__inner">수강 과목</span>
                <div class="viewer">${data.subjectTitle}</div>
              </div>
              <div class="config-subject-topic-container">
                <span class="config-subject-title__inner">학습일지 주제</span>
                <div class="viewer">${data.subjectSummary}</div>
              </div>
            </div>
          </div>
          <div class="popup-body-content">
            <div class="content-title">학습 내용</div>
            <div class="viewer content-text-box">${data.subjectContent}</div>
          </div>
        </div>
      </div>
    `;

    // 기존 dim(어두운 배경)은 유지하고 팝업 내용만 교체
    const dim = target.querySelector(".dim") || document.createElement("div");
    if(!target.querySelector(".dim")) {
        dim.className = "dim";
        target.appendChild(dim);
    }
    
    // 이전 팝업 내용 삭제 후 새 내용 삽입
    const oldPopup = target.querySelector(".popup-viewer-container");
    if(oldPopup) oldPopup.remove();
    
    const temp = document.createElement("div");
    temp.innerHTML = html.trim();
    target.appendChild(temp.firstChild);

    // 닫기 이벤트
    target.querySelector(".popup-close-button").onclick = () => {
        target.classList.remove('active');
    };
    dim.onclick = () => {
        target.classList.remove('active');
    };
}

// 작성/수정 팝업은 기존 로직을 유지하되 더미 데이터만 제거하여 사용하세요.