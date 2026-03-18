document.addEventListener("DOMContentLoaded", () => {

  const path = window.location.pathname;
  const isMentor = path.includes("mentorBoard");
  const boardType = sessionStorage.getItem("currentBoardType") || (isMentor ? "mentor" : "mentee");

  /* ========================
     등록 폼 submit
  ======================== */
  const writeForm = document.getElementById("writeForm");
  if (writeForm) {
    writeForm.addEventListener("submit", e => {
      e.preventDefault();
      const title = document.getElementById("inputTitle")?.value.trim();
      const content = document.getElementById("inputContent")?.value.trim();
      if (!title) { alert("제목을 입력해주세요."); return; }
      if (!content) { alert("내용을 입력해주세요."); return; }
      const newId = BoardStore.addPost(boardType, { title, content });
      sessionStorage.setItem("currentPostId", newId);
      alert("등록되었습니다.");
      location.href = `${boardType}BoardList.html`;
    });
  }

});
