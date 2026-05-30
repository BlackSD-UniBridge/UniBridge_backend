import { createDetailPopup, createModifyPopup, createWritePopup } from "./popupUtils.js"

function createLearningReportGroup(data) {
  return Object.entries(data).map(([week, reports], index) => createLearningReportWeek(week, reports, index)).join("\n");
}

function createLearningReportWeek(stWeek, reports, index) {
  const curReport = reports[0];
  const reportsHtml = reports.map((value) => createLearningReport(value)).join("\n");
  const nowDate = new Date();
  const firstDay = new Date(nowDate.getFullYear(), nowDate.getMonth(), 1);
  const dayOfMonth = nowDate.getDate();
  const stMonth = new Date(curReport.lrReportDate).getMonth() + 1;
  
  const curMonth = (nowDate).getMonth() + 1;
  const curWeek  = Math.ceil((dayOfMonth + firstDay.getDay()) / 7);
  const isCurrentWeek = (curMonth === stMonth) && 
    (stWeek === curWeek);

  const formatDateToYMD = (dateValue) => {
    const date = new Date(dateValue);
  
    const yyyy = date.getFullYear();
    const mm = String(date.getMonth() + 1).padStart(2, "0");
    const dd = String(date.getDate()).padStart(2, "0");
  
    return `${yyyy}-${mm}-${dd}`;
  }

  const scopeStartDate = new Date(curReport.matchingStart);
	scopeStartDate.setDate(scopeStartDate.getDate() + (index * 7));
  const scopeEndDate = new Date(scopeStartDate);
	scopeEndDate.setDate(scopeEndDate.getDate() + 7);
	
  const html = `
    <li class="list-content">
      <div class="list-content__title">
        <div class="list-content__date">
          <span>${stMonth}월 ${stWeek}주차</span>
        </div>
        <div class="list-content__subtitle">
          <span class="date">${formatDateToYMD(scopeStartDate)} ~ ${formatDateToYMD(scopeEndDate)}</span>
          <span class="desc">${isCurrentWeek ? "이번" : "저번"} 주에 작성된 학습보고서</span>
        </div>
      </div>
      <div class="list-content__body">
        <div class="list-content__body-title">
          <span class="write-date">작성일</span>
          <span class="report-name">학습일지명</span>
          <span class="mentor-mentee-name">멘토/멘티</span>
          <span class="subject-name">과목명</span>
          <span class="week">주차</span>
          <span class="session">회차</span>
          <span class="actions">관리</span>
        </div>
        <ul class="list-content__body-content">${reportsHtml}</ul>
      </div>
    </li>
  `;

  return html;
}

function createLearningReport(report) {
	const stDate  = new Date(report.lrReportDate).toDateString();
	const curDate = new Date().toDateString();
	const canEdit = stDate === curDate;
	
  const html = `
    <li class="list-learning-content rid-${report.lrReportNumber}">
      <div class="date">${report.lrReportDate}</div>
      <div class="report-name">
        <div class="title">${report.lrSubjectTitle}</div>
        <div class="subtitle">${report.lrSubjectSummary}</div>
      </div>
      <div class="mentor-mentee-name">
        <div class="mentor">${report.mentorName}</div>
        <div class="mentee">${report.menteeName}</div>
      </div>
      <div class="subject-name">${report.lrSubjectName}</div>
      <div class="week">${report.lrReportWeek}주차</div>
      <div class="session">${report.lrReportSession}회차</div>
      <div class="actions">
        <div class="detail">상세보기</div>
        ${canEdit ? 
          '<div class="modify">수정</div>' : ""}
      </div>
    </li>
  `;

  return html;
}

async function initLrLists(userNumber, responseJson) {
	// 학습보고서 컨텐츠에 리스트 삽입
	const listLrReportContainer = document.querySelector(
	  ".list-container__inner  " +
	  ".list-content-container"
	);
	listLrReportContainer.innerHTML = createLearningReportGroup(responseJson);

	// 상세보기 버튼 클릭 시 팝업 생성
	const detailButton = document.querySelectorAll(
	    ".list-container__inner " + 
	    ".list-content-container " + 
	    ".list-content " + 
	    ".list-content__body " + 
	    ".list-content__body-content " + 
	    ".list-learning-content " + 
	    ".actions " + 
	    ".detail"
	);
	detailButton.forEach(function(item) {
	    item.addEventListener('click', (e) => createDetailPopup(e));
	});

	// 수정 버튼 클릭 시 팝업 생성
	const modifyButtons = document.querySelectorAll(
	    ".list-container__inner " + 
	    ".list-content-container " + 
	    ".list-content " + 
	    ".list-content__body " + 
	    ".list-content__body-content " + 
	    ".list-learning-content " + 
	    ".actions " + 
	    ".modify"
	);
	modifyButtons.forEach(function(item) {
	    item.addEventListener('click', (e) => createModifyPopup(e));
	});
}

async function init() {
	const userNumber = sessionStorage.getItem("userNumber");
	const userType 	 = sessionStorage.getItem("userType");
	
	const response = await fetch(`${window.contextPath}/api/user/lr/searchAllReports.rep?userNumber=${userNumber}&userType=${userType}`);
	const responseJson = await response.json();
	
	const responseSubjects = await fetch(`${window.contextPath}/api/user/lr/selectAllSubjects.rep`);
	const responseSubjectsJson = await responseSubjects.json();
	
	const respReportNum = await fetch(`${window.contextPath}/api/user/lr/searchAllReportsNum.rep?userNumber=${userNumber}&userType=${userType}`);
	const respReportNumJson = await respReportNum.json();
	
	// 학습보고서 컨텐츠 생성
	await initLrLists(userNumber, responseJson);
	
	// 새 학습 보고서 작성 클릭 시 팝업 생성
	const writeButton = document.querySelector(
	    ".main-container " +
	    ".filter-container " +
	    ".filter-container__inner " +
	    ".filter-container__inner__right " +
	    ".filter-create-container"
	);
	writeButton.addEventListener('click', createWritePopup);
	
	// 과목 선택 드롭다운 제어
	const filterSubjectSelector = document.querySelector('.filter-subject-container');
	filterSubjectSelector.addEventListener('click', function() {
	  const filterSubjectDropdown = document.querySelector('.filter-subject-dropdown');
	  const isDisabled = filterSubjectDropdown.classList.contains('disabled');

	  if (isDisabled) {
	    filterSubjectDropdown.classList.remove('disabled');
	  } else {
	    filterSubjectDropdown.classList.add('disabled');
	  }
	});

	function createSubjectDropdown(data) {
	  return data.map((value) => `<li class="filter-subject-item">${value}</li>`).join("\n");
	}

	function closeDropdownAndSetPlaceHolder(event, thisEl) {
	  event.preventDefault();
	  event.stopPropagation();

	  const filterSubjectDropdown = document.querySelector('.filter-subject-dropdown');
	  filterSubjectDropdown.classList.add('disabled');

	  const filterSubjectPlaceholder = document.querySelector('.filter-subject-placeholder');
	  filterSubjectPlaceholder.textContent = thisEl.textContent;
	  filterSubjectPlaceholder.classList.add('selected');
	}

	const subjects = responseSubjectsJson.map(({ subjectName }) => subjectName );
	const filterSubjectSelectorInner = filterSubjectSelector.querySelector(".filter-subject-dropdown__inner");
	filterSubjectSelectorInner.innerHTML = createSubjectDropdown(subjects);

	const filterSubjectItems = document.querySelectorAll('.filter-subject-item');
	filterSubjectItems.forEach(function(item) {
	  item.addEventListener('click', function(event) {
	    closeDropdownAndSetPlaceHolder(event, item);
	  });
	});
	
	function isDateInRange(targetDate, startDate = null, endDate = null) {
	  const target = targetDate ? new Date(targetDate).getTime() : new Date().getTime();
	  const start  = startDate  ? new Date(startDate).getTime()  : -Infinity;
	  const end = endDate ? new Date(endDate).getTime() : Infinity;

	  return start <= target && target <= end;
	}
	
	const filterApplyButton = document.querySelector(".filter-apply-button");
	filterApplyButton?.addEventListener('click', async () => {
		const learningReportJson = JSON.parse(
		  sessionStorage.getItem("learningReportDummyResponse")
		) || {};

		let filterSubject = document.querySelector(".filter-subject-placeholder")?.textContent?.trim() || null;
		if (filterSubject === "필터링할 과목명을 선택해주세요.") {
		  filterSubject = null;
		}

		let filterDate = document.querySelector(".filter-date-placeholder")?.textContent?.trim() || null;
		let startDate = null;
		let endDate = null;

		if (filterDate && filterDate !== "기간 선택 · 전체") {
		  [startDate, endDate] = filterDate.split("~").map(v => v.trim());
		} else {
		  filterDate = null;
		}

		const retLrJson = Object.fromEntries(
		  Object.entries(learningReportJson).map(([key, value]) => {
		    const filtered = value.filter((inner) => {
		      const subjectPass =
		        filterSubject === null || inner.lrSubjectName === filterSubject;

		      const datePass =
		        filterDate === null || isDateInRange(inner.lrReportDate, startDate, endDate);

		      return subjectPass && datePass;
		    });

		    return [key, filtered];
		  }).filter(([_, value]) => value.length > 0)
		);
		
		await initLrLists(userNumber, retLrJson);
	});
	
	// 학습일지 개수 설정
	const navStatusContainerEl = document.querySelector(".nav-container .cur-status-container");
	const reportNumEl 		 = navStatusContainerEl.querySelector(".report-num .status-title .value");
	const reportNumPerWeekEl = navStatusContainerEl.querySelector(".report-num-per-week .status-title .value");
	reportNumEl		  .textContent = respReportNumJson["reportNum"];
	reportNumPerWeekEl.textContent = respReportNumJson["reportNumPerWeek"]["1"];
	
	sessionStorage.setItem(
	  "learningReportDummyResponse",
	  JSON.stringify(responseJson)
	);
	
	sessionStorage.setItem(
	  "learningReportSubjects",
	  JSON.stringify(responseSubjectsJson)
	);
}

init();