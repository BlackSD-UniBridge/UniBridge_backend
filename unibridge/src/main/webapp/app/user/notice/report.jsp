<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/user/notice/report.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/user/notice/popup.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/user/notice/calendar.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/header.css">

    <script type="module" src="${pageContext.request.contextPath}/assets/js/user/notice/report.js" defer></script>
    <script type="module" src="${pageContext.request.contextPath}/assets/js/user/notice/popup.js" defer></script>
    <script type="module" src="${pageContext.request.contextPath}/assets/js/user/notice/subjectSelector.js" defer></script>
    <script type="module" src="${pageContext.request.contextPath}/assets/js/user/notice/calendar.js" defer></script>
    <script type="module" src="${pageContext.request.contextPath}/assets/js/header.js" defer></script>
    <title>Document</title>
</head>
<body>
    <div id="root">
        <div class="header-container" id="headerContainer"></div>
        <div class="main-container">
            <div class="filter-container">
                <div class="filter-container__inner">
                    <div class="filter-container__inner__left">
                        <div class="filter-date-container">
                            <div class="filter-date-container__inner">
                                <div class="filter-date-placeholder filter-placeholder" id="dateFilterText">기간 선택 · 전체</div>
                                <div class="filter-date-selector" id="dateFilterTrigger"></div>
                            </div>

                            <div class="date-picker-panel" id="datePickerPanel">
                                <div class="date-picker-top">
                                <div class="date-picker-title">기간 선택</div>
                                <button type="button" class="date-picker-close" id="datePickerClose">x</button>
                                </div>

                                <div class="date-picker-quick">
                                <button type="button" class="quick-btn active" data-range="all">전체</button>
                                <button type="button" class="quick-btn" data-range="today">오늘</button>
                                <button type="button" class="quick-btn" data-range="week">이번 주</button>
                                <button type="button" class="quick-btn" data-range="month">이번 달</button>
                                </div>

                                <div class="date-picker-calendar">
                                <div class="calendar-header">
                                    <button type="button" class="month-nav" id="prevMonth">&lt;</button>
                                    <div class="month-title" id="monthTitle">2026년 3월</div>
                                    <button type="button" class="month-nav" id="nextMonth">&gt;</button>
                                </div>

                                <div class="week-row">
                                    <div>일</div>
                                    <div>월</div>
                                    <div>화</div>
                                    <div>수</div>
                                    <div>목</div>
                                    <div>금</div>
                                    <div>토</div>
                                </div>

                                <div class="day-grid" id="dayGrid"></div>
                                </div>

                                <div class="date-picker-bottom">
                                <div class="selected-range" id="selectedRange">
                                    전체 기간
                                </div>

                                <div class="date-picker-actions">
                                    <button type="button" class="action-btn reset-btn" id="resetDate">초기화</button>
                                    <button type="button" class="action-btn apply-btn" id="applyDate">적용</button>
                                </div>
                                </div>
                            </div>
                        </div>
                        <div class="filter-subject-container">
                            <div class="filter-subject-placeholder filter-placeholder">필터링할 과목명을 선택해주세요.</div>
                            <div class="filter-subject-selector">▼</div>
                            <div class="filter-subject-dropdown disabled">
                            <!-- <div class="filter-subject-dropdown"> -->
                                <ul class="filter-subject-dropdown__inner">

                                </ul>
                            </div>
                        </div>
                    </div>
                    <div class="filter-container__inner__right">
                        <div class="filter-apply-button">필터 적용</div>
                        <div class="filter-create-container">새 학습 보고서 작성</div>
                    </div>
                </div>
            </div>

            <div class="list-container">
                <aside class="nav-container">
                    <div class="cur-status-container">
                        <div class="cur-status-title-container">
                            <div class="cur-status-title-container__inner">
                                <span>전체 현황</span>
                            </div>
                        </div>
                        <div class="cur-status-content-container">
                            <div class="cur-status-content-container__inner">
                                <div class="content-container">
                                    <div class="status-title">
                                        <div class="title">전체 학습일지</div>
                                        <div class="value">128</div>
                                    </div>
                                    <div class="status-content">
                                        <span>현재 등록된 멘토·멘티 학습일지 총 개수</span>
                                    </div>
                                </div>
                                <div class="content-container">
                                    <div class="status-title">
                                        <div class="title">이번 주 작성 수</div>
                                        <div class="value">16</div>
                                    </div>
                                    <div class="status-content">
                                        <span>이번 주에 작성된 학습일지 개수</span>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </aside>
                
                <main class="list-container__inner">
                    <div class="list-title-container">
                        <div class="list-main-title">학습일지 목록</div>
                        <div class="list-sub-title">멘토·멘티 학습일지를 날짜, 상태, 성취도 기준으로 확인할 수 있습니다.</div>
                    </div>
                    <ul class="list-content-container">

                    </ul>
                </main>
            </div>
        </div>

        <div class="popup-container">
            <div class="dim"></div>
        </div>
    </div>
</body>
</html>