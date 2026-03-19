/**
 * calendar.js - 기간 선택 달력 및 서버 필터링 전송
 */
const dateFilterTrigger = document.getElementById("dateFilterTrigger");
const dateFilterText    = document.getElementById("dateFilterText");
const datePickerPanel   = document.getElementById("datePickerPanel");
const datePickerClose   = document.getElementById("datePickerClose");
const dayGrid           = document.getElementById("dayGrid");
const monthTitle        = document.getElementById("monthTitle");
const selectedRange     = document.getElementById("selectedRange");
const prevMonth         = document.getElementById("prevMonth");
const nextMonth         = document.getElementById("nextMonth");
const resetDate         = document.getElementById("resetDate");
const applyDate         = document.getElementById("applyDate");
const quickButtons      = document.querySelectorAll(".quick-btn");

let currentDate = new Date();
let visibleYear = currentDate.getFullYear();
let visibleMonth = currentDate.getMonth();

let startDate = null;
let endDate = null;
let tempMode = "all";

// 날짜 포맷 함수 (YYYY.MM.DD) - 화면 표시용
function formatDate(date) {
  if (!date) return "";
  const y = date.getFullYear();
  const m = String(date.getMonth() + 1).padStart(2, "0");
  const d = String(date.getDate()).padStart(2, "0");
  return `${y}.${m}.${d}`;
}

// 서버 전송용 포맷 함수 (YYYY-MM-DD)
function formatDateForServer(date) {
  if (!date) return "";
  const y = date.getFullYear();
  const m = String(date.getMonth() + 1).padStart(2, "0");
  const d = String(date.getDate()).padStart(2, "0");
  return `${y}-${m}-${d}`;
}

function sameDate(a, b) {
  return (
    a && b &&
    a.getFullYear() === b.getFullYear() &&
    a.getMonth() === b.getMonth() &&
    a.getDate() === b.getDate()
  );
}

function isBetween(target, start, end) {
  if (!start || !end) return false;
  const t = new Date(target.getFullYear(), target.getMonth(), target.getDate()).getTime();
  const s = new Date(start.getFullYear(), start.getMonth(), start.getDate()).getTime();
  const e = new Date(end.getFullYear(), end.getMonth(), end.getDate()).getTime();
  return t > s && t < e;
}

function setQuickActive(type) {
  quickButtons.forEach((btn) => {
    btn.classList.toggle("active", btn.dataset.range === type);
  });
}

function updateSelectedRangeText() {
  if (tempMode === "all" || (!startDate && !endDate)) {
    selectedRange.textContent = "전체 기간";
    return;
  }
  if (startDate && endDate) {
    selectedRange.textContent = `${formatDate(startDate)} ~ ${formatDate(endDate)}`;
    return;
  }
  if (startDate) {
    selectedRange.textContent = `${formatDate(startDate)} ~ 종료일 선택`;
  }
}

function renderCalendar(year, month) {
  dayGrid.innerHTML = "";
  monthTitle.textContent = `${year}년 ${month + 1}월`;

  const firstDay = new Date(year, month, 1);
  const lastDate = new Date(year, month + 1, 0).getDate();
  const startWeekday = firstDay.getDay();

  for (let i = 0; i < startWeekday; i++) {
    const empty = document.createElement("button");
    empty.className = "day-cell muted";
    dayGrid.appendChild(empty);
  }

  for (let day = 1; day <= lastDate; day++) {
    const cell = document.createElement("button");
    const thisDate = new Date(year, month, day);

    cell.type = "button";
    cell.className = "day-cell";
    cell.textContent = day;

    if (sameDate(thisDate, new Date())) cell.classList.add("today");
    if (sameDate(thisDate, startDate)) cell.classList.add("selected-start");
    if (sameDate(thisDate, endDate)) cell.classList.add("selected-end");
    if (isBetween(thisDate, startDate, endDate)) cell.classList.add("in-range");

    cell.addEventListener("click", () => {
      tempMode = "custom";
      setQuickActive("");
      if (!startDate || (startDate && endDate)) {
        startDate = thisDate;
        endDate = null;
      } else if (thisDate < startDate) {
        endDate = startDate;
        startDate = thisDate;
      } else {
        endDate = thisDate;
      }
      updateSelectedRangeText();
      renderCalendar(visibleYear, visibleMonth);
    });
    dayGrid.appendChild(cell);
  }
}

function applyQuickRange(type) {
  const today = new Date();
  if (type === "all") {
    tempMode = "all";
    startDate = null;
    endDate = null;
  } else if (type === "today") {
    tempMode = "today";
    startDate = new Date(today.getFullYear(), today.getMonth(), today.getDate());
    endDate = new Date(today.getFullYear(), today.getMonth(), today.getDate());
  } else if (type === "week") {
    tempMode = "week";
    const day = today.getDay();
    const weekStart = new Date(today);
    weekStart.setDate(today.getDate() - day);
    const weekEnd = new Date(today);
    weekEnd.setDate(today.getDate() + (6 - day));
    startDate = new Date(weekStart.getFullYear(), weekStart.getMonth(), weekStart.getDate());
    endDate = new Date(weekEnd.getFullYear(), weekEnd.getMonth(), weekEnd.getDate());
  } else if (type === "month") {
    tempMode = "month";
    startDate = new Date(today.getFullYear(), today.getMonth(), 1);
    endDate = new Date(today.getFullYear(), today.getMonth() + 1, 0);
  }
  setQuickActive(type);
  updateSelectedRangeText();
  renderCalendar(visibleYear, visibleMonth);
}

// 이벤트 바인딩
dateFilterTrigger.addEventListener("click", () => {
  datePickerPanel.classList.toggle("open");
  dateFilterTrigger.classList.toggle("active");
});

datePickerClose.addEventListener("click", () => {
  datePickerPanel.classList.remove("open");
  dateFilterTrigger.classList.remove("active");
});

prevMonth.addEventListener("click", () => {
  visibleMonth--;
  if (visibleMonth < 0) { visibleMonth = 11; visibleYear--; }
  renderCalendar(visibleYear, visibleMonth);
});

nextMonth.addEventListener("click", () => {
  visibleMonth++;
  if (visibleMonth > 11) { visibleMonth = 0; visibleYear++; }
  renderCalendar(visibleYear, visibleMonth);
});

quickButtons.forEach((btn) => {
  btn.addEventListener("click", () => applyQuickRange(btn.dataset.range));
});

resetDate.addEventListener("click", () => applyQuickRange("all"));

// ★ [적용] 버튼 클릭 시 서버 전송 로직 ★
// calendar.js 내부의 applyDate 이벤트 리스너 수정
applyDate.addEventListener("click", () => {
  const params = new URLSearchParams(window.location.search);

  if (tempMode === "all" || (!startDate && !endDate)) {
    params.delete("startDate");
    params.delete("endDate");
  } else if (startDate && endDate) {
    params.set("startDate", formatDateForServer(startDate));
    params.set("endDate", formatDateForServer(endDate));
  }

  // web.xml 설정에 맞게 .rep 확장자로 요청을 보냅니다.
  // 현재 위치가 /mvc/auth/ 라면 아래와 같이 전송됩니다.
  window.location.href = `reportList.rep?${params.toString()}`;
});

// 초기화
applyQuickRange("all");
renderCalendar(visibleYear, visibleMonth);