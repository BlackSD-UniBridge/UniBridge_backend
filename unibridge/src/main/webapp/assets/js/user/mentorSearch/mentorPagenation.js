/**
 * 
 */

class MentorPagination {
  constructor({ totalMentors, mentorsPerPage = 10, pagesPerGroup = 10 }) {
    this.totalMentors = totalMentors;
    this.mentorsPerPage = mentorsPerPage;
    this.pagesPerGroup = pagesPerGroup;

    this.totalPages = Math.ceil(totalMentors / mentorsPerPage);
    this.currentPage = 1;
    this.currentGroup = 1;

    this.pageNumberContainer = document.querySelector('#pageNumber ul');
    // 초기 렌더링 시점에 id가 없을 수 있으므로 렌더링 메서드에서 동적으로 잡습니다.
    this.leftBtn = null;
    this.rightBtn = null;

    this._init();
  }

  _init() {
    this._render();
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

    // 왼쪽 화살표
    const leftEl = this._buildArrow('left', '&lt;');
    leftEl.style.visibility = this.currentGroup === 1 ? 'hidden' : 'visible';
    this.leftBtn = leftEl;
    this.pageNumberContainer.appendChild(leftEl);

    // 페이지 번호
    for (let i = start; i <= end; i++) {
      const li = document.createElement('li');
      li.textContent = i;
      if (i === this.currentPage) li.id = 'nowPage';
      li.addEventListener('click', () => this._goTo(i));
      this.pageNumberContainer.appendChild(li);
    }

    // 오른쪽 화살표
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
    if (this.leftBtn) {
      this.leftBtn.onclick = () => this._prevGroup();
    }
    if (this.rightBtn) {
      this.rightBtn.onclick = () => this._nextGroup();
    }
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

/* ── 렌더링 및 이벤트 관리 ── */

// 이미지 경로에서 /frontend 제거 (JSP 구조에 맞춤)
const DUMMY_MENTORS = Array.from({ length: 47 }, (_, i) => ({
  id: i + 1,
  name: `멘토${i + 1}`,
  subject: ['국어', '영어', '수학', 'C언어', 'Java', 'Python'][i % 6],
  purpose: '멘토링 목적 설명입니다. 함께 성장해요!',
  university: '코딩대학교',
  major: '컴퓨터공학과',
  date: '2026.03.10(화)',
  img: `/assets/img/user/userProfile/ex1.png`, // 경로 수정
}));

function createMentoCard(mentor) {
  // ContextPath 변수 처리 (필요시 전역변수로 설정)
  const cp = window.location.pathname.substring(0, window.location.pathname.indexOf("/", 2));
  
  return `
    <div class="mentoInfo">
      <div class="mentoName">${mentor.name}</div>
      <div class="mentoCard">
        <div class="mentoCardHead">
          <div class="mentoSubject">${mentor.subject}</div>
          <div class="mentoCardMain">
            <div class="mentoFront">
              <img src="${cp}${mentor.img}" alt="멘토 사진">
              <button type="button" class="matching" data-id="${mentor.id}">매칭</button>
            </div>
            <div class="mentoBack">
              <div class="mentoringPurpose">${mentor.purpose}</div>
              <div>
                <div class="mentoUniSchool">대학교 : ${mentor.university}</div>
                <div class="mentoMajor">학과 : ${mentor.major}</div>
              </div>
              <div class="mentoringDay">${mentor.date}</div>
            </div>
          </div>
        </div>
      </div>
    </div>`;
}

function renderMentors(page, mentorsPerPage = 10) {
  const contentsEl = document.querySelector('.contents');
  if(!contentsEl) return;
  
  contentsEl.innerHTML = '';

  const start = (page - 1) * mentorsPerPage;
  const end = Math.min(start + mentorsPerPage, DUMMY_MENTORS.length);
  const pageData = DUMMY_MENTORS.slice(start, end);

  for (let i = 0; i < pageData.length; i += 2) {
    const row = document.createElement('div');
    row.className = 'mentoList';
    let html = createMentoCard(pageData[i]);
    if (pageData[i + 1]) html += createMentoCard(pageData[i + 1]);
    row.innerHTML = html;
    contentsEl.appendChild(row);
  }
}

/* ── 이벤트 위임 (Event Delegation) 적용 ── */
// 멘토 카드가 새로 그려질 때마다 이벤트를 걸지 않고, 부모 요소에 한 번만 겁니다.
document.addEventListener('click', (e) => {
  if (e.target && e.target.classList.contains('matching')) {
    const mentorId = e.target.getAttribute('data-id');
    // .html이 아닌 컨트롤러 주소(.me)로 이동
    location.href = `mentorDetail.me?id=${mentorId}`;
  }
});

document.addEventListener('DOMContentLoaded', () => {
  const pagination = new MentorPagination({
    totalMentors: DUMMY_MENTORS.length,
    mentorsPerPage: 10,
    pagesPerGroup: 10,
  });

  pagination.onPageChange = (page) => {
    renderMentors(page, 10);
  };

  renderMentors(1, 10);
});