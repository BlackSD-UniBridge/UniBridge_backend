document.addEventListener("DOMContentLoaded", () => {

  const noticeId = parseInt(sessionStorage.getItem("currentNoticeId"));
  const notice = noticeId ? NoticeStore.getById(noticeId) : null;

  /* ========================
     기존 데이터 세팅
  ======================== */
  if (notice) {
    const inputTitle = document.getElementById("inputTitle");
    const inputContent = document.getElementById("inputContent");
    if (inputTitle) inputTitle.value = notice.title;
    if (inputContent) inputContent.value = notice.content;
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
      NoticeStore.update(noticeId, { title, content });
      alert("저장되었습니다.");
      location.href = "noticeDetail.html";
    });
  }

  /* ========================
     삭제 버튼
  ======================== */
  const btnEditDelete = document.getElementById("btnEditDelete");
  if (btnEditDelete) {
    btnEditDelete.addEventListener("click", () => {
      if (confirm("공지사항을 삭제하시겠습니까?")) {
        NoticeStore.remove(noticeId);
        location.href = "noticeList.html";
      }
    });
  }

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

});
