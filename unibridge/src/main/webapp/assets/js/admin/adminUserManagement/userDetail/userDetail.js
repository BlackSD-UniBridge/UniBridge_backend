document.addEventListener("DOMContentLoaded", () => {
  /* ========================
     유저 유형 파악
     백엔드 연동 시: fetch("/api/user/{id}") 로 유형 받아와서 userType 교체
  ======================== */
  const userTypeBadge = document.getElementById("userType");
  const userType = userTypeBadge?.textContent.trim(); // "멘토" or "멘티"

  /* ========================
     설문조사 확인 버튼
     유형에 따라 멘토/멘티 모달 분기
  ======================== */
  const btnSurvey = document.getElementById("btnSurvey");
  if (btnSurvey) {
    btnSurvey.addEventListener("click", () => {
      if (userType === "멘토") {
        openModal("modalSurveyMentor");
      } else if (userType === "멘티") {
        openModal("modalSurveyMentee");
      }
    });
  }

  document.getElementById("closeSurveyMentor")?.addEventListener("click", () => closeModal("modalSurveyMentor"));
  document.getElementById("closeSurveyMentee")?.addEventListener("click", () => closeModal("modalSurveyMentee"));

  /* ========================
     회원퇴출 버튼
  ======================== */
  const btnWithdraw = document.getElementById("btnWithdraw");
  if (btnWithdraw) {
    btnWithdraw.addEventListener("click", async () => {
      if (confirm("해당 유저를 퇴출하시겠습니까?")) {
		const params = new URLSearchParams(window.location.search);
		const memberNumber = params.get("memberNumber");
		
        // 백엔드 연동 시: fetch("/api/user/{id}/withdraw", { method: "DELETE" })
		const response = await fetch(
			`${window.contextPath}/api/admin/userMM/updateUserPendingKill.admin`,
			{
				method: "post",
				body: JSON.stringify({ "memberNumber": memberNumber })
			}
		);
		
		const respJson = await response.json();
		if (respJson.state !== "ok") {
			alert("API에 실패했습니다.");
			return;	
		}
		
        alert("퇴출 처리되었습니다.");
		console.log(respJson.detail);
		
        location.href = `${window.contextPath}/app/admin/adminUserManagement/userMM.admin`;
      }
	});
  }

  /* ========================
     오버레이 클릭 시 닫기
  ======================== */
  document.querySelectorAll(".modal-overlay").forEach(overlay => {
    overlay.addEventListener("click", e => {
      if (e.target === overlay) closeModal(overlay.id);
    });
  });
});

function openModal(id) {
  document.getElementById(id)?.classList.add("is-open");
}

function closeModal(id) {
  document.getElementById(id)?.classList.remove("is-open");
}
