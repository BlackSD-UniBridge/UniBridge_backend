document.addEventListener('DOMContentLoaded', () => {
  const listBtn = document.querySelector(".list-btn");
  const modifyBtn = document.querySelector(".modify-btn");
  const deleteBtn = document.querySelector(".delete-btn");

  const boardNumber = listBtn?.dataset.boardNumber ?? window.boardNumber;
  const memberNumber = listBtn?.dataset.memberNumber ?? window.memberNumber;

  console.log("확인 boardNumber : ", boardNumber);
  console.log("확인 memberNumber : ", memberNumber);

  // 목록 버튼
  listBtn?.addEventListener("click", () => {
    window.location.href = `${contextPath}/mentor/mentorBoard/MentorBoardList.mob`;
  });

  // 수정 버튼
  modifyBtn?.addEventListener("click", () => {
    if (!boardNumber) return alert("boardNumber가 없습니다");
    window.location.href = `${contextPath}/mentor/mentorBoard/MentorBoardUpdate.mob?MentorBoardNumber=${encodeURIComponent(boardNumber)}`;
  });

  // 삭제 버튼
  deleteBtn?.addEventListener("click", async () => {
    if (!boardNumber) return alert("boardNumber가 없습니다.");
    if (!confirm("정말 삭제하시겠습니까?")) return;

    try {
      const res = await fetch(`${contextPath}/mentor/mentorBoard/MentorBoardDelete.mob?MentorBoardNumber=${encodeURIComponent(boardNumber)}`, {
        method: "POST",
        headers: { "X-Requested-With": "fetch" },
      });
      if (!res.ok) throw new Error("삭제 요청 실패");

      alert("게시글이 삭제되었습니다");
      window.location.href = `${contextPath}/mentor/mentorBoard/MentorBoardList.mob`;
    } catch (err) {
      console.error("게시글 삭제 실패 : ", err);
      alert("게시글 삭제에 실패했습니다");
    }
  });
  
  //댓글 수정 버튼
    document.querySelectorAll(".comment-modify-btn").forEach((btn) => {
      btn.addEventListener("click", () => {
        const comId   = btn.dataset.comId;
        const boardId = btn.dataset.boardId;
        const content = btn.dataset.content;

        const newContent = prompt("댓글을 수정하세요.", content);
        if (newContent === null) return;         // 취소
        if (newContent.trim() === "") {
          alert("댓글 내용을 입력하세요.");
          return;
        }

        document.getElementById("updateComId").value   = comId;
        document.getElementById("updateBoardId").value = boardId;
        document.getElementById("updateContent").value = newContent.trim();
        document.getElementById("comment-update-form").submit();
      });
    });

    //댓글 삭제 버튼
    document.querySelectorAll(".comment-delete-btn").forEach((btn) => {
      btn.addEventListener("click", () => {
        if (!confirm("댓글을 삭제하시겠습니까?")) return;

        document.getElementById("deleteComId").value   = btn.dataset.comId;
        document.getElementById("deleteBoardId").value = btn.dataset.boardId;
        document.getElementById("comment-delete-form").submit();
      });
    });
	
});
