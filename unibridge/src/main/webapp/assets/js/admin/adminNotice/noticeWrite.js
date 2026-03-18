document.addEventListener("DOMContentLoaded", () => {

  /* ========================
     파일 선택 시 파일명 표시
  ======================== */
  const fileInput = document.getElementById("fileInput");
  if (fileInput) {
    fileInput.addEventListener("change", () => {
      const fileName = fileInput.files[0]?.name || "선택된 파일 없음";
      const display = document.getElementById("fileDisplay");
      if (display) display.textContent = fileName;
    });
  }

  /* ========================
     등록 폼 submit
  ======================== */
  const writeForm = document.getElementById("rewriteForm") || document.getElementById("writeForm");
  if (writeForm) {
    writeForm.addEventListener("submit", e => {
      e.preventDefault();
      const title = document.getElementById("inputTitle")?.value.trim();
      const content = document.getElementById("inputContent")?.value.trim();
      if (!title) { alert("제목을 입력해주세요."); return; }
      if (!content) { alert("내용을 입력해주세요."); return; }
      const newId = NoticeStore.addPost({ title, content, type: "공지" });
      sessionStorage.setItem("currentNoticeId", newId);
      alert("등록되었습니다.");
      location.href = "noticeList.html";
    });
  }

});
