document.addEventListener("DOMContentLoaded", () => {

  const noticeId = parseInt(sessionStorage.getItem("currentNoticeId"));
  const notice = noticeId ? NoticeStore.getById(noticeId) : null;

  /* ========================
     게시글 데이터 렌더
  ======================== */
  if (notice) {
    NoticeStore.incrementViews(noticeId);

    const titleText = document.querySelector(".detail-title-text");
    if (titleText) titleText.textContent = `[${notice.type}] ${notice.title}`;

    const metaEl = document.querySelector(".detail-meta");
    if (metaEl) metaEl.textContent = `조회수 ${notice.views + 1}`;

    const contentBox = document.querySelector(".detail-content-box");
    if (contentBox) {
      contentBox.style.color = "#111";
      contentBox.style.whiteSpace = "pre-line";
      contentBox.textContent = notice.content;
    }

    // 첨부파일
    const attachBox = document.querySelector(".attach-box");
    if (attachBox) {
      if (notice.files && notice.files.length > 0) {
        attachBox.innerHTML = notice.files.map(f => `<div>📎 ${f}</div>`).join("");
      } else {
        attachBox.innerHTML = `<div style="color:#aaa;">첨부파일 없음</div>`;
      }
    }
  }

  /* ========================
     수정 버튼
  ======================== */
  const btnEdit = document.getElementById("btnEdit");
  if (btnEdit) {
    btnEdit.addEventListener("click", () => {
      location.href = "noticeEdit.html";
    });
  }

  /* ========================
     삭제 버튼
  ======================== */
  const btnDelete = document.getElementById("btnDelete");
  if (btnDelete) {
    btnDelete.addEventListener("click", () => {
      if (confirm("공지사항을 삭제하시겠습니까?")) {
        NoticeStore.remove(noticeId);
        location.href = "noticeList.html";
      }
    });
  }

});
