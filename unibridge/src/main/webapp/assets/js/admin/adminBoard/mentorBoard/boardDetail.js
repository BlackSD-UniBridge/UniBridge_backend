document.addEventListener("DOMContentLoaded", () => {

  const path = window.location.pathname;
  const isMentor = path.includes("mentorBoard");
  const boardType = sessionStorage.getItem("currentBoardType") || (isMentor ? "mentor" : "mentee");
  const postId = parseInt(sessionStorage.getItem("currentPostId"));

  const post = postId ? BoardStore.getById(boardType, postId) : null;

  /* ========================
     게시글 데이터 렌더
  ======================== */
  if (post) {
    // 조회수 증가
    BoardStore.incrementViews(boardType, postId);

    const titleText = document.querySelector(".detail-title-text");
    if (titleText) titleText.textContent = post.title;

    const metaSpans = document.querySelectorAll(".detail-meta span");
    if (metaSpans[0]) metaSpans[0].textContent = `${post.date} ${post.time}`;
    if (metaSpans[1]) metaSpans[1].textContent = `조회수 ${post.views + 1}`;
    if (metaSpans[2]) metaSpans[2].textContent = `댓글 ${post.comments.length}`;

    const contentBox = document.querySelector(".detail-content-box");
    if (contentBox) {
      contentBox.style.color = "#111";
      contentBox.style.whiteSpace = "pre-line";
      contentBox.textContent = post.content;
    }

    const authorEl = document.querySelector(".detail-author");
    if (authorEl) authorEl.textContent = post.author;
  }

  /* ========================
     수정/삭제 버튼 렌더
  ======================== */
  const postActions = document.getElementById("postActions");
  if (postActions) {
    const isAdmin = post ? post.isAdmin : false;
    if (isAdmin) {
      postActions.innerHTML = `
        <button class="btn btn-blue" id="btnEdit">수정</button>
        <button class="btn btn-red" id="btnDelete">삭제</button>
      `;
    } else {
      postActions.innerHTML = `
        <button class="btn btn-red" id="btnDelete">삭제</button>
      `;
    }
  }

  postActions && postActions.addEventListener("click", e => {
    if (e.target.id === "btnEdit") {
      location.href = `${boardType}BoardEdit.html`;
    }
    if (e.target.id === "btnDelete") {
      if (confirm("게시글을 삭제하시겠습니까?")) {
        BoardStore.remove(boardType, postId);
        location.href = `${boardType}BoardList.html`;
      }
    }
  });

  /* ========================
     댓글 렌더
  ======================== */
  const commentList = document.getElementById("commentList");
  if (commentList && post) {
    function renderComments() {
      const fresh = BoardStore.getById(boardType, postId);
      if (!fresh) return;
      commentList.innerHTML = fresh.comments.map(c => `
        <div class="comment-item" data-cid="${c.id}">
          <div class="comment-body">
            <div class="comment-nickname">${c.nickname}</div>
            <div class="comment-content">${c.content}</div>
            <div class="comment-date">${c.date}</div>
          </div>
          <button class="btn btn-red" onclick="deleteComment(${c.id})">삭제</button>
        </div>
      `).join("");
    }
    renderComments();

    window.deleteComment = function(commentId) {
      if (confirm("댓글을 삭제하시겠습니까?")) {
        BoardStore.deleteComment(boardType, postId, commentId);
        renderComments();
      }
    };
  }

});
