document.addEventListener("DOMContentLoaded", () => {

  const path = window.location.pathname;
  const isMentor = path.includes("mentorBoard");
  const boardType = sessionStorage.getItem("currentBoardType") || (isMentor ? "mentor" : "mentee");
  const postId = parseInt(sessionStorage.getItem("currentPostId"));

  const post = postId ? BoardStore.getById(boardType, postId) : null;

  /* ========================
     기존 데이터 세팅
  ======================== */
  if (post) {
    const inputTitle = document.getElementById("inputTitle");
    const inputContent = document.getElementById("inputContent");
    if (inputTitle) inputTitle.value = post.title;
    if (inputContent) inputContent.value = post.content;
  }

  /* ========================
     저장 버튼
  ======================== */
  const btnSave = document.getElementById("btnSave");
  if (btnSave) {
    btnSave.addEventListener("click", () => {
      const title = document.getElementById("inputTitle")?.value.trim();
      const content = document.getElementById("inputContent")?.value.trim();
      if (!title) { alert("제목을 입력해주세요."); return; }
      if (!content) { alert("내용을 입력해주세요."); return; }
      BoardStore.update(boardType, postId, { title, content });
      alert("저장되었습니다.");
      location.href = `${boardType}BoardDetail.html`;
    });
  }

  /* ========================
     삭제 버튼
  ======================== */
  const btnEditDelete = document.getElementById("btnEditDelete");
  if (btnEditDelete) {
    btnEditDelete.addEventListener("click", () => {
      if (confirm("게시글을 삭제하시겠습니까?")) {
        BoardStore.remove(boardType, postId);
        location.href = `${boardType}BoardList.html`;
      }
    });
  }

});
