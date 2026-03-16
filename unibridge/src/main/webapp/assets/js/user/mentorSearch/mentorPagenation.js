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
  
  return `
    <div class="mentoInfo">
      <div class="mentoName">\${mentor.name} 멘토님</div>
      <div class="mentoCard">
        <div class="mentoCardHead">
          <div class="mentoSubject">\${mentor.subject}</div>
          <div class="mentoCardMain">
            <div class="mentoFront">
              <img src="\${cp}\${mentor.img}" alt="멘토 사진">
              <button type="button" class="matching" data-id="\${mentor.id}">매칭하기</button>
            </div>
            <div class="mentoBack">
              <div class="mentoringPurpose">\${mentor.purpose}</div>
              <div>
                <div class="mentoUniSchool">학교: \${mentor.university}</div>
                <div class="mentoMajor">학과: \${mentor.major}</div>
              </div>
              <div class="mentoringDay">\${mentor.date}</div>
            </div>
          </div>
        </div>
      </div>
    </div>`;
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
    const mentorId = e.target.getAttribute('data-id');
    location.href = `\${globalContextPath}/mentor/mentorDetailOk.ms?memberNumber=\${mentorId}`;
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