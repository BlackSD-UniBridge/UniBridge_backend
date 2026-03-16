/**
 * 
 */
// 팝업 생성 함수
export function createModifyPopup() {
	const popupContainer = document.querySelector(".popup-container");
	popupContainer.classList.add('active');

	const popup = appendStudyLogPopup({
		container: popupContainer,
		type: "modify",
		subjects: ["과목 1", "과목 2", "과목 3"]
	});
}

export function createDetailPopup(e) {
	const popupContainer = document.querySelector(".popup-container");
	popupContainer.classList.add('active');

	const _ = insertLrViewerPopup({
		target: popupContainer,
		event: e
	});
}

export function createWritePopup() {
	const popupContainer = document.querySelector(".popup-container");
	popupContainer.classList.add('active');

	const popup = appendStudyLogPopup({
		container: popupContainer,
		type: "write",
	});
}

// 팝업 생성 함수 (내부적으로 사용)
export function appendStudyLogPopup({
	container,
	type,
	e = null,
	subjects = ["과목 1", "과목 2", "과목 3"]
} = {}) {
	const subjectItems = subjects
		.map(subject => `<li class="select-subject-item">${subject}</li>`)
		.join("");

	const createButtonContainer = (type) => {
		switch (type) {
			case "write":
				return `
          <div class="button-container">
            <div class="button-cancel">취소</div>
            <div class="button-write">작성</div>
          </div>
        `;

			case "modify":
				return `
          <div class="button-container">
            <div class="button-cancel">취소</div>
            <div class="button-modify">수정</div>
          </div>
        `;

			default:
				return ``;
		}
	};

	function createTitle(type) {
		switch (type) {
			case "write":
				return "학습일지 작성";
			case "modify":
				return "학습일지 수정";
			case "detail":
				return "학습일지 상세보기";
		}
	};

	function createTextareaOrDiv(type, placeHolder, extraClsName) {
		switch (type) {
			case "write":
			case "modify":
				return `
          <div 
            class="editor ${extraClsName}" 
            role="textbox"
            contenteditable="true" 
            aria-label="내용 입력" 
            aria-placeholder="${placeHolder}"
            data-placeholder="제목을 입력해주세요."
          ></div>
        `;
			case "detail":
				return `
          <div class="viewer ${extraClsName}"></div>
        `;
		}
	}

	let lrInfoIfDetail = null;
	if (type === "detail") {
		const parentElement = e.target.closest(".list-learning-content");
		const reportID = [...parentElement.classList].find((value) => value.startsWith("rid-")).replace("rid-", "");

		const session = sessionStorage.getItem("learningReportDummyResponse");
		if (session) {
			const reportData = JSON.parse(session);
			for (const group of reportData.data) {
				const foundReport = group.reports.find((report) => String(report.reportId) === String(reportID));

				if (foundReport) {
					lrInfoIfDetail = foundReport;
					console.log(lrInfoIfDetail);
				}
			}
		}
	}

	const html = `
    <div class="popup">
      <div class="popup-head">
        <div class="popup-title">${createTitle(type)}</div>
        <div class="popup-close-button"></div>
      </div>
      <div class="popup-body">
        <div class="popup-body-config">
          <div class="config-title">학습 주제 및 목표</div>
          <div class="config-subject-container">
            <div class="config-subject-name-container">
              <span class="config-subject-title__inner">학습과목</span>
              <div class="select-subject-container" ${type === "detail" ? 'style="cursor: auto;"' : ''}>
                <div class="select-subject-placeholder select-placeholder">과목명을 선택해주세요.</div>
                <div class="select-subject-selector">${type !== "detail" ? "▼" : ""}</div>
                <div class="select-subject-dropdown disabled">
                  <ul class="select-subject-dropdown__inner">
                    ${subjectItems}
                  </ul>
                </div>
              </div>
            </div>
            <div class="config-subject-topic-container">
              <span class="config-subject-title__inner">학습일지 주제</span>
              ${createTextareaOrDiv(type, "제목을 입력해주세요.")}
            </div>
            <div class="config-subject-summary-container">
              <span class="config-subject-title__inner">학습일지 요약</span>
              ${createTextareaOrDiv(type, "내용을 입력해주세요.", "config-subject-summary-textbox")}
            </div>
          </div>
        </div>
        <div class="popup-body-content">
          <div class="content-title">학습 내용</div>
          ${createTextareaOrDiv(type, "내용을 입력해주세요.", "content-text-box")}
        </div>
        ${createButtonContainer(type)}
      </div>
    </div>
  `;

	const temp = document.createElement("div");
	temp.innerHTML = html.trim();

	const popup = temp.firstElementChild;
	container.appendChild(popup);

	// 닫기 버튼
	const closeButton = popup.querySelector(".popup-close-button");
	closeButton.addEventListener("click", () => {
		container.classList.remove('active');
		popup.remove();
	});

	switch (type) {
		case "write":
		case "modify": {
			// 드롭다운 열기/닫기
			const subjectContainer = popup.querySelector(".select-subject-container");
			const dropdown = popup.querySelector(".select-subject-dropdown");
			const placeholder = popup.querySelector(".select-subject-placeholder");

			subjectContainer.addEventListener("click", (e) => {
				if (
					e.target.classList.contains("select-subject-placeholder") ||
					e.target.classList.contains("select-subject-selector")
				) {
					dropdown.classList.toggle("disabled");
				}
			});

			// 과목 선택
			popup.querySelectorAll(".select-subject-item").forEach(item => {
				item.addEventListener("click", () => {
					placeholder.textContent = item.textContent;
					dropdown.classList.add("disabled");
				});
			});
		}
			break;
		case "detail":
			break;
	}
	// 취소 버튼 (존재 시 설정)
	const cancelBtn = popup.querySelector(".button-cancel");
	if (cancelBtn) {
		cancelBtn.addEventListener("click", () => {
			container.classList.remove('active');
			popup.remove();
		});
	}

	return popup;
}

export function insertLrViewerPopup({ target, event }) {
	let lrInfoIfDetail = null;
	const parentElement = event.target.closest(".list-learning-content");
	const reportID = [...parentElement.classList].find((value) => value.startsWith("rid-")).replace("rid-", "");

	const session = sessionStorage.getItem("learningReportDummyResponse");
	if (session) {
		const reportData = JSON.parse(session);
		for (const group of reportData.data) {
			const foundReport = group.reports.find((report) => String(report.reportId) === String(reportID));

			if (foundReport) {
				lrInfoIfDetail = foundReport;
				console.log(lrInfoIfDetail);
			}
		}
	}

	const html = `
      <div class="popup">
        <div class="popup-head">
          <div class="popup-title">학습일지 상세보기</div>
          <div class="popup-close-button"></div>
        </div>
        <div class="popup-body">
          <div class="popup-body-config">
            <div class="config-title">학습 주제 및 목표</div>
            <div class="config-subject-container">
              <div class="config-subject-name-container">
                <span class="config-subject-title__inner">학습과목</span>
                <div class="select-subject-container" style="cursor: auto;">
                  <div class="select-subject-placeholder select-placeholder">\${lrInfoIfDetail.subjectName}</div>
                  <div class="select-subject-selector"></div>
                  <div class="select-subject-dropdown disabled">
                    <ul class="select-subject-dropdown__inner"></ul>
                  </div>
                </div>
              </div>
              <div class="config-subject-topic-container">
                <span class="config-subject-title__inner">학습일지 주제</span>
                <div class="viewer">\${lrInfoIfDetail.reportTitle}</div>
              </div>
              <div class="config-subject-summary-container">
                <span class="config-subject-title__inner">학습일지 요약</span>
                <div class="viewer config-subject-summary-textbox">\${lrInfoIfDetail.reportSubtitle}</div>
              </div>
            </div>
          </div>
          <div class="popup-body-content">
            <div class="content-title">학습 내용</div>
            <div class="viewer content-text-box">\${lrInfoIfDetail.reportContent}</div>
          </div>
        </div>
      </div>
    `;

	const temp = document.createElement("div");
	temp.innerHTML = html.trim();

	const popup = temp.firstElementChild;
	target.appendChild(popup);

	const closeButton = popup.querySelector(".popup-close-button");
	closeButton.addEventListener("click", () => {
		target.classList.remove('active');
		popup.remove();
	});

	return popup;
}