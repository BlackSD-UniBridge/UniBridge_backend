// =====================================================
//  boardData.js  –  게시판 더미 데이터 공유 스토어
//  멘티/멘토 boardList · boardDetail · boardEdit · boardWrite
//  에서 모두 이 파일을 먼저 로드한 뒤 window.BoardStore 사용
// =====================================================

(function () {

  const ITEMS_PER_PAGE = 10;

  /* ---------- 더미 데이터 생성 ---------- */
  function makePosts(boardType) {
    const key = `boardPosts_${boardType}`;
    if (sessionStorage.getItem(key)) {
      return JSON.parse(sessionStorage.getItem(key));
    }

    const comments = [
      ["댓글내용1", "댓글내용2", "댓글내용3"],
      ["좋은 글이네요!", "감사합니다.", "도움이 됐어요."],
      ["잘 읽었습니다.", "공감해요.", "응원합니다!"],
    ];

    const posts = Array.from({ length: 50 }, (_, i) => {
      const no = 50 - i;
      const d = new Date(2025, 0, 1);
      d.setDate(d.getDate() + i * 3);
      const dateStr = `${d.getFullYear()}-${String(d.getMonth()+1).padStart(2,'0')}-${String(d.getDate()).padStart(2,'0')}`;
      const timeStr = `${String(9 + (i % 10)).padStart(2,'0')}:${String(i % 60).padStart(2,'0')}`;

      const cmtSet = comments[i % 3];
      const isAdmin = no % 7 === 0;
      const author = isAdmin ? "관리자" : `작성자${no}`;
      return {
        id: no,
        title: `제목${no}`,
        author,
        content: `게시글 ${no}번의 내용입니다.\n이 글은 더미 데이터로 생성된 게시글입니다.\n작성자: ${author}`,
        date: dateStr,
        time: timeStr,
        views: Math.floor(Math.random() * 200),
        isAdmin,
        comments: cmtSet.map((txt, ci) => ({
          id: ci + 1,
          nickname: `닉네임${ci + 1}`,
          content: txt,
          date: `${dateStr} ${timeStr}`,
        })),
      };
    });

    sessionStorage.setItem(key, JSON.stringify(posts));
    return posts;
  }

  function save(boardType, posts) {
    sessionStorage.setItem(`boardPosts_${boardType}`, JSON.stringify(posts));
  }

  /* ---------- 공개 API ---------- */
  window.BoardStore = {
    ITEMS_PER_PAGE,

    getAll(boardType, dateFrom, dateTo) {
      let all = makePosts(boardType);
      if (dateFrom) {
        all = all.filter(p => p.date >= dateFrom);
      }
      if (dateTo) {
        all = all.filter(p => p.date <= dateTo);
      }
      return all;
    },

    getPage(boardType, page, dateFrom, dateTo) {
      const all = this.getAll(boardType, dateFrom, dateTo);
      const start = (page - 1) * ITEMS_PER_PAGE;
      return all.slice(start, start + ITEMS_PER_PAGE);
    },

    totalPages(boardType, dateFrom, dateTo) {
      return Math.ceil(this.getAll(boardType, dateFrom, dateTo).length / ITEMS_PER_PAGE);
    },

    getById(boardType, id) {
      return makePosts(boardType).find(p => p.id === id) || null;
    },

    incrementViews(boardType, id) {
      const posts = makePosts(boardType);
      const p = posts.find(p => p.id === id);
      if (p) { p.views++; save(boardType, posts); }
    },

    update(boardType, id, { title, content }) {
      const posts = makePosts(boardType);
      const p = posts.find(p => p.id === id);
      if (p) { p.title = title; p.content = content; save(boardType, posts); }
    },

    remove(boardType, id) {
      const posts = makePosts(boardType).filter(p => p.id !== id);
      save(boardType, posts);
    },

    addPost(boardType, { title, content }) {
      const posts = makePosts(boardType);
      const newId = posts.length ? posts[0].id + 1 : 1;
      const now = new Date();
      const dateStr = `${now.getFullYear()}-${String(now.getMonth()+1).padStart(2,'0')}-${String(now.getDate()).padStart(2,'0')}`;
      const timeStr = `${String(now.getHours()).padStart(2,'0')}:${String(now.getMinutes()).padStart(2,'0')}`;
      posts.unshift({ id: newId, title, author: '관리자', content, date: dateStr, time: timeStr, views: 0, isAdmin: true, comments: [] });
      save(boardType, posts);
      return newId;
    },

    deleteComment(boardType, postId, commentId) {
      const posts = makePosts(boardType);
      const p = posts.find(p => p.id === postId);
      if (p) { p.comments = p.comments.filter(c => c.id !== commentId); save(boardType, posts); }
    },
  };

})();