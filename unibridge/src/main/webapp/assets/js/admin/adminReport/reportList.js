const weekContainer = document.querySelector(".date-container");
const weekContentEl =  weekContainer.querySelector(".date-content");
const selectDropdown = document.querySelector(".date-container .week-select-dropdown");

weekContainer.addEventListener('click', () => {
  selectDropdown.classList.remove("disabled");
});

const selectorDropdownItems = selectDropdown.querySelectorAll(".week-selector li");
selectorDropdownItems.forEach((value) => {
  value.addEventListener('click', (event) => {
	event.preventDefault();
	event.stopPropagation();
	
	weekContentEl.innerText = value.innerText;
    selectDropdown.classList.add("disabled");
  });
});

const selectBtnEl = document.querySelector(".select-button");
selectBtnEl.addEventListener('click', () => {
	const learningReports = JSON.parse(sessionStorage.getItem("learningReports"));
	const selectedWeek = weekContentEl.innerText.replace("주차", "");
	const week =  selectedWeek === "전체"
		? -1 : Number(selectedWeek);
	
	const filteredlearningReports = learningReports.filter(value => value.lrReportWeek === week);
	const listContainer = document.querySelector(".list-container");
	listContainer.innerHTML = createLearningReportGroup(
		filteredlearningReports.length 
			? filteredlearningReports : learningReports
	); 
});

function createLearningReportGroup(reports) {
	return reports
		.map(report => createLearningReport(report))
		.join("\n");
}

function createLearningReport(report) {
	const html = `
		<li class="lr-report">
		  <div class="lr-report-desc">
		    <div class="lr-report-idx">${report.lrReportWeek}주차 ${report.lrReportWeek}일차 - ${report.lrSubjectTitle}</div>
		    <div class="lr-report-date">${report.lrReportDate}</div>
		  </div>
		  <div 
		  	class="lr-report-submit-button"
		  	onclick="window.location.href='${window.contextPath}/reportDetail.admin?reportNumber=${report.lrReportNumber}'"
		  >확인</div>
		</li>
	`;
	
	return html;
}