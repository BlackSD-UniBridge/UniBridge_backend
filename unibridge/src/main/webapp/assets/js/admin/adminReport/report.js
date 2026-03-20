async function loadReports () {
	const response = await fetch(`${window.contextPath}/api/admin/report/searchUsers.admin`);	
	if (!response.ok) {
		return;
	}
	
	const matchingJson = await response.json();
	window.filteredMatchingList = matchingJson;
	return window.filteredMatchingList;
}

function createMatchingGroup(response) {
	return Object.values(response)
		.map(([key, value]) => createMatchingPerWeek(key, value))
		.join("\n");
}

function createMatchingPerWeek(date, matchings) {
	const matchingHtml = matchings.map(value => createMatching(value)).join("\n");
	const [yearMonthDay, dayOfWeek] = date.split(" ");
	const [_, month, day] = yearMonthDay.split("-");
	const dayOfWeekFilter = {
		"MONDAY"	: "월",
		"TUESDAY"	: "화",
		"WEDNESDAY"	: "수",
		"THURSDAY"	: "목",
		"FRIDAY"	: "금",
		"SATURDAY"	: "토",
		"SUNDAY"	: "일"	
	}
	
	const html = `
		<li class="report-content">
		  <div class="title">${month}월 ${day}일 (${dayOfWeekFilter[dayOfWeek]})</div>
		  <div class="vertical-line"></div>
		  <ul class="reports">${matchingHtml}</ul>
		</li>
	`;
	
	return html;
}


function createMatching({ matchingNumber, mentorName, menteeName }) {
	const html = `
	  <li class="report-item" id="matching-id-${matchingNumber}"}>
	    <div class="mentor-mentee">
	      <div class="user-info mentor">
	        <div class="user-icon"><img src ="${window.contextPath}/assets/img/admin/adminNoticeImg/userIcon.png"></div>
	        <div class="user-desc">
	          <span>멘토</span>
	          <span class="mentor-name">${mentorName}</span>
	        </div>
	      </div>
	      <div class="user-info mentee">
	        <div class="user-icon"><img src ="${window.contextPath}/assets/img/admin/adminNoticeImg/userIcon.png"></div>
	        <div class="user-desc">
	          <span>멘티</span>
	          <span class="mentee-name">${menteeName}</span>
	        </div>
	      </div>
	    </div>
	    <div class="detail-button">상세</div>
	  </li>
	`;
	
	return html;
}

async function initMatchingGroup(
	httpReq=false,
	filterByYear=null,
	filterByMonth=null
) {
	if (httpReq) {
		await loadReports();
	}
	
	let filteredMatchingList = Object.entries(window.filteredMatchingList);
	if (filterByYear) {
		filteredMatchingList = filteredMatchingList
			.filter(([key, _]) => Number(key.split("-")[0]) === filterByYear);
	}
	
	if (filterByMonth) {
		filteredMatchingList = filteredMatchingList
			.filter(([key, _]) => Number(key.split("-")[1]) === filterByMonth);
	}
	
	const reportContainerEl = document.querySelector("#root > div > main > div.content-container > div > div.report-container > div > ul");
	reportContainerEl.innerHTML = createMatchingGroup(filteredMatchingList);
	const detailBtnEls = document.querySelectorAll("#root > div > main > div.content-container > div > div.report-container > div > ul > li > ul > li > div.detail-button");
	detailBtnEls.forEach((element) => {
		element.addEventListener('click', (event) => {
			const targetNumber = Number(event.target.parentElement.id.split("-").at(-1));
			window.location.href = `${window.contextPath}/reportDetail.admin?number=${targetNumber}`;
		});
	});
}

async function init() {
	await initMatchingGroup(true);
	
	const selectedYearEl = document.querySelector(".title-container .date-selector-container > .selected-year");
	
	const leftButtonEl = document.querySelector(".title-container .date-selector-container > div.left-button.button");
	leftButtonEl.addEventListener('click', () => {
		const prevYear = Number(selectedYearEl.innerText) - 1;
		selectedYearEl.innerText = prevYear;
		initMatchingGroup(false, prevYear);
	});
	
	const rightButtonEl = document.querySelector(".title-container .date-selector-container > div.right-button.button");
	rightButtonEl.addEventListener('click', () => {
		const nextYear = Number(selectedYearEl.innerText) + 1;
		selectedYearEl.innerText = nextYear;
		initMatchingGroup(false, nextYear);
	});
	
	
	const filteredMonthSelector = document.querySelectorAll(".content-container .content-container__inner > .month-selector li");
	const unselectedAll = () => {
		filteredMonthSelector.forEach(el => {
			el.classList.remove("selected");
		});
	}

	filteredMonthSelector.forEach(el => {
		el.addEventListener('click', () => {
			unselectedAll();
			el.classList.add("selected");
			
			const year = Number(selectedYearEl.innerText);
			const month = Number(el.innerText.replace("월", ""));
			initMatchingGroup(false, year, month);
		})
	});
}

init();