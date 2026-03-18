// =====================================================
//  noticeData.js  –  공지사항 더미 데이터 공유 스토어
//  noticeList · noticeDetail · noticeEdit · noticeWrite
//  에서 모두 이 파일을 먼저 로드한 뒤 window.NoticeStore 사용
// =====================================================

(function () {

  const ITEMS_PER_PAGE = 10;
  const STORAGE_KEY = "noticePosts";

  const TYPES = ["공지", "공지", "이벤트", "공지", "이벤트"]; // 비율 조절

  /* ---------- 더미 데이터 생성 ---------- */
  function makePosts() {
    if (sessionStorage.getItem(STORAGE_KEY)) {
      return JSON.parse(sessionStorage.getItem(STORAGE_KEY));
    }

    const posts = Array.from({ length: 50 }, (_, i) => {
      const no = 50 - i;
      const d = new Date(2025, 0, 1);
      d.setDate(d.getDate() + i * 3);
      const dateStr = `${d.getFullYear()}-${String(d.getMonth()+1).padStart(2,'0')}-${String(d.getDate()).padStart(2,'0')}`;
      const timeStr = `${String(9 + (i % 10)).padStart(2,'0')}:${String(i % 60).padStart(2,'0')}`;
      const type = TYPES[i % TYPES.length];

      return {
        id: no,
        type,
        title: `제목${no}`,
        content: `공지사항 ${no}번 내용입니다.\n이 글은 더미 데이터로 생성된 공지사항입니다.\n종류: ${type}`,
        date: dateStr,
        time: timeStr,
        views: Math.floor(Math.random() * 300),
        files: no % 5 === 0 ? [`첨부파일_${no}.pdf`] : [],
      };
    });

    sessionStorage.setItem(STORAGE_KEY, JSON.stringify(posts));
    return posts;
  }

  function save(posts) {
    sessionStorage.setItem(STORAGE_KEY, JSON.stringify(posts));
  }

  /* ---------- 공개 API ---------- */
  window.NoticeStore = {
    ITEMS_PER_PAGE,

    getAll(typeFilter, dateFrom, dateTo) {
      let all = makePosts();
      if (typeFilter && typeFilter !== "전체") {
        all = all.filter(p => p.type === typeFilter);
      }
      if (dateFrom) {
        all = all.filter(p => p.date >= dateFrom);
      }
      if (dateTo) {
        all = all.filter(p => p.date <= dateTo);
      }
      return all;
    },

    getPage(page, typeFilter, dateFrom, dateTo) {
      const all = this.getAll(typeFilter, dateFrom, dateTo);
      const start = (page - 1) * ITEMS_PER_PAGE;
      return all.slice(start, start + ITEMS_PER_PAGE);
    },

    totalPages(typeFilter, dateFrom, dateTo) {
      return Math.ceil(this.getAll(typeFilter, dateFrom, dateTo).length / ITEMS_PER_PAGE);
    },

    getById(id) {
      return makePosts().find(p => p.id === id) || null;
    },

    incrementViews(id) {
      const posts = makePosts();
      const p = posts.find(p => p.id === id);
      if (p) { p.views++; save(posts); }
    },

    update(id, { title, content, type }) {
      const posts = makePosts();
      const p = posts.find(p => p.id === id);
      if (p) {
        if (title !== undefined) p.title = title;
        if (content !== undefined) p.content = content;
        if (type !== undefined) p.type = type;
        save(posts);
      }
    },

    remove(id) {
      save(makePosts().filter(p => p.id !== id));
    },

    addPost({ title, content, type }) {
      const posts = makePosts();
      const newId = posts.length ? posts[0].id + 1 : 1;
      const now = new Date();
      const dateStr = `${now.getFullYear()}-${String(now.getMonth()+1).padStart(2,'0')}-${String(now.getDate()).padStart(2,'0')}`;
      const timeStr = `${String(now.getHours()).padStart(2,'0')}:${String(now.getMinutes()).padStart(2,'0')}`;
      posts.unshift({ id: newId, type: type || "공지", title, content, date: dateStr, time: timeStr, views: 0, files: [] });
      save(posts);
      return newId;
    },
  };

})();
