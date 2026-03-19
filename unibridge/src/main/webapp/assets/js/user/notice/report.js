import { insertLrViewerPopup } from "./popupUtils.js";

document.addEventListener("DOMContentLoaded", () => {
	const listContainer = document.querySelector(".list-content-container");
	const writeBtn = document.querySelector(".filter-create-container");

	if (listContainer) {
		listContainer.addEventListener("click", (e) => {
			// 1. 상세보기 클릭 (클래스명 .detail 또는 .view-detail 모두 대응)
			const detailBtn = e.target.closest(".detail") || e.target.closest(".view-detail");
			if (detailBtn) {
				const item = detailBtn.closest(".list-learning-content") || detailBtn.closest(".report-item");
				const reportId = item.dataset.id;
				openDetailPopup(reportId);
			}

			// 2. 수정하기 클릭
			const modifyBtn = e.target.closest(".modify");
			if (modifyBtn) {
				const reportId = modifyBtn.closest(".list-learning-content").dataset.id;
				// openModifyPopup(reportId); 
			}
		});
	}

	// 작성 버튼 클릭 시 (팝업 오픈)
	if (writeBtn) {
		writeBtn.onclick = () => {
			document.querySelector(".popup-container").classList.add("active");
			// appendStudyLogPopup({ container: ..., type: 'write' });
		};
	}
});

async function openDetailPopup(reportId) {
	const popupContainer = document.querySelector(".popup-container");

	try {
		// .rep 확장자를 사용하여 서블릿을 호출합니다.
		const response = await fetch(`reportDetail.rep?reportId=${reportId}`);
		const result = await response.json();

		popupContainer.classList.add('active');
		insertLrViewerPopup({ target: popupContainer, data: result.data });

	} catch (error) {
		console.error("데이터 로드 실패:", error);
	}
}