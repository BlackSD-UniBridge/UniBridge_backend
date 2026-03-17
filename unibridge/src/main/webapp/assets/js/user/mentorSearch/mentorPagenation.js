/**
 * 멘토 페이지네이션 클래스
 */
class MentorPagination {
	constructor({ totalMentors, mentorsPerPage = 10, pagesPerGroup = 10 }) {
		this.totalMentors = totalMentors;
		this.mentorsPerPage = mentorsPerPage;
		this.pagesPerGroup = pagesPerGroup;

		this.totalPages = Math.ceil(totalMentors / mentorsPerPage) || 1;
		this.currentPage = 1;
		this.currentGroup = 1;

		this.pageNumberContainer = document.querySelector('#pageNumber ul');
		this.leftBtn = null;
		this.rightBtn = null;

		this._init();
	}

	_init() {
		if (this.pageNumberContainer) this._render();
	}

	_groupRange() {
		const start = (this.currentGroup - 1) * this.pagesPerGroup + 1;
		const end = Math.min(start + this.pagesPerGroup - 1, this.totalPages);
		return { start, end };
	}

	get totalGroups() {
		return Math.ceil(this.totalPages / this.pagesPerGroup);
	}

	_render() {
		const { start, end } = this._groupRange();
		this.pageNumberContainer.innerHTML = '';

		const leftEl = this._buildArrow('left', '&lt;');
		leftEl.style.visibility = this.currentGroup === 1 ? 'hidden' : 'visible';
		this.leftBtn = leftEl;
		this.pageNumberContainer.appendChild(leftEl);

		for (let i = start; i <= end; i++) {
			const li = document.createElement('li');
			li.textContent = i;
			if (i === this.currentPage) li.id = 'nowPage';
			li.addEventListener('click', () => this._goTo(i));
			this.pageNumberContainer.appendChild(li);
		}

		const rightEl = this._buildArrow('right', '&gt;');
		rightEl.style.visibility = this.currentGroup >= this.totalGroups ? 'hidden' : 'visible';
		this.rightBtn = rightEl;
		this.pageNumberContainer.appendChild(rightEl);

		this._bindArrows();

		if (typeof this.onPageChange === 'function') {
			this.onPageChange(this.currentPage);
		}
	}

	_buildArrow(id, html) {
		const li = document.createElement('li');
		li.id = id;
		li.innerHTML = html;
		return li;
	}

	_bindArrows() {
		if (this.leftBtn) this.leftBtn.onclick = () => this._prevGroup();
		if (this.rightBtn) this.rightBtn.onclick = () => this._nextGroup();
	}

	_goTo(page) {
		if (page < 1 || page > this.totalPages) return;
		this.currentPage = page;
		this.currentGroup = Math.ceil(page / this.pagesPerGroup);
		this._render();
	}

	_prevGroup() {
		if (this.currentGroup <= 1) return;
		this.currentGroup--;
		this.currentPage = (this.currentGroup - 1) * this.pagesPerGroup + this.pagesPerGroup;
		this._render();
	}

	_nextGroup() {
		if (this.currentGroup >= this.totalGroups) return;
		this.currentGroup++;
		this.currentPage = (this.currentGroup - 1) * this.pagesPerGroup + 1;
		this._render();
	}
}

/* ── 실제 데이터 렌더링 로직 ── */

const MENTOR_DATA = (typeof REAL_MENTORS !== 'undefined') ? REAL_MENTORS : [];

function createMentoCard(mentor) {
	const cp = (typeof globalContextPath !== 'undefined') ? globalContextPath : '';

	// 백틱 대신 따옴표와 +를 사용하여 직접 연결합니다.
	var html = '<div class="mentoInfo">';
	html += '  <div class="mentoName">' + mentor.name + ' 멘토님</div>';
	html += '  <div class="mentoCard">';
	html += '    <div class="mentoCardHead">';
	html += '      <div class="mentoSubject">' + (mentor.subject || '전공미정') + '</div>';
	html += '      <div class="mentoCardMain">';
	html += '        <div class="mentoFront">';
	html += '          <img src="' + cp + '/upload/' + mentor.img + '" alt="사진">';
	html += '          <button type="button" class="matching" data-id="' + mentor.id + '">매칭하기</button>';
	html += '        </div>';
	html += '        <div class="mentoBack">';
	html += '          <div class="mentoringPurpose">' + (mentor.purpose || '소개 없음') + '</div>';
	html += '          <div class="mentoUniSchool">학교: ' + mentor.university + '</div>';
	html += '          <div class="mentoMajor">학과: ' + mentor.major + '</div>';
	html += '          <div class="mentoringDay">' + mentor.date + '</div>';
	html += '        </div>';
	html += '      </div>';
	html += '    </div>';
	html += '  </div>';
	html += '</div>';

	return html;
}

function renderMentors(page, mentorsPerPage = 10) {
	const contentsEl = document.querySelector('.contents');
	if (!contentsEl) return;

	contentsEl.innerHTML = '';

	if (MENTOR_DATA.length === 0) {
		contentsEl.innerHTML = '<div style="text-align:center; width:100%; padding:100px 0;">등록된 멘토 정보가 없습니다.</div>';
		return;
	}

	const start = (page - 1) * mentorsPerPage;
	const end = Math.min(start + mentorsPerPage, MENTOR_DATA.length);
	const pageData = MENTOR_DATA.slice(start, end);

	for (let i = 0; i < pageData.length; i += 2) {
		const row = document.createElement('div');
		row.className = 'mentoList';
		let html = createMentoCard(pageData[i]);
		if (pageData[i + 1]) html += createMentoCard(pageData[i + 1]);
		row.innerHTML = html;
		contentsEl.appendChild(row);
	}
}

/* ── 초기화 및 이벤트 ── */

document.addEventListener('click', (e) => {
	if (e.target && e.target.classList.contains('matching')) {
		// 1. 버튼에 심어둔 mentorId(숫자)를 가져옵니다.
		const mentorId = e.target.getAttribute('data-id');

		// 2. JSP에서 선언한 globalContextPath 변수를 활용합니다.
		const cp = (typeof globalContextPath !== 'undefined') ? globalContextPath : '';

		// 3. 백틱(`)을 사용하되, 안에는 반드시 위에서 정의한 'mentorId' 변수를 넣어야 합니다.
		// .ms 대신 .sch를 사용하기로 했으니 경로를 맞춰줍니다.
		location.href = `${cp}/mentor/mentorDetailOk.sch?memberNumber=${mentorId}`;

		// 확인용 로그 (콘솔에서 주소가 어떻게 찍히는지 보세요)
		console.log("이동 경로: ", `${cp}/mentor/mentorDetailOk.sch?memberNumber=${mentorId}`);
	}
});

document.addEventListener('DOMContentLoaded', () => {
	const pagination = new MentorPagination({
		totalMentors: MENTOR_DATA.length,
		mentorsPerPage: 10,
		pagesPerGroup: 5,
	});

	pagination.onPageChange = (page) => {
		renderMentors(page, 10);
	};

	renderMentors(1, 10);
});

console.log(REAL_MENTORS); // JSP에서 이미 만들어뒀으므로 바로 사용 가능

function render() {
	REAL_MENTORS.forEach(mentor => {
		console.log(mentor.name + " 님을 화면에 그립니다.");
	});
}