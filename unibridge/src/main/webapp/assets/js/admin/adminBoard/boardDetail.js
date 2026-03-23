document.addEventListener("DOMContentLoaded", () => {

  const path = window.location.pathname;
  const isMentor = path.includes("mentorBoard");
  const boardType = sessionStorage.getItem("currentBoardType") || (isMentor ? "mentor" : "mentee");
  const postId = parseInt(sessionStorage.getItem("currentPostId"));

  const post = postId ? BoardStore.getById(boardType, postId) : null;

  /* ========================
     수정/삭제 버튼 렌더
  ======================== */
  
  console.log(writeNumber);
  console.log(sessionNumber);
  
  const postActions = document.getElementById("postActions");
     if (Number(writeNumber) === Number(sessionNumber)){
      postActions.innerHTML = `
        <button class="btn btn-blue" id="btnEdit">수정</button>
        <button class="btn btn-red" id="btnDelete">삭제</button>
      `;
    } else {
      postActions.innerHTML = 
	  `<button class="btn btn-red" id="btnDelete">삭제</button>`;
    }
	

  postActions.addEventListener("click", e => {
    if (e.target.id === "btnEdit") {
      window.location.href = `${boardType}BoardEdit.admin?boardNumber=${boardNumber}`;
    }
    if (e.target.id === "btnDelete") {
      if (confirm("게시글을 삭제하시겠습니까?")) {
        window.location.href = `${boardType}BoardDeleteOk.admin?boardNumber=${boardNumber}`;
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
        location.href = `adminMenteeCommentDeleteOk.admin?commentNumber=${commentNumber}&boardNumber=${boardNumber}`;
      }
    };
  }

});
