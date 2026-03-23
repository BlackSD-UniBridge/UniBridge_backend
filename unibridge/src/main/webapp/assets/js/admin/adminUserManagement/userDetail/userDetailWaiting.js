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
     거부 버튼 → 사유서 모달
  ======================== */
  const btnReject = document.getElementById("btnReject");
  if (btnReject) {
    btnReject.addEventListener("click", () => {
      openModal("modalReject");
    });
  }

  /* ========================
     사유서 발송 버튼
     백엔드 연동 시: fetch("/api/user/{id}/reject", { method: "POST", body: { reason } })
  ======================== */
  const btnSendReject = document.getElementById("btnSendReject");
  if (btnSendReject) {
    btnSendReject.addEventListener("click", async () => {
      const reason = document.getElementById("rejectReason")?.value.trim();
      if (!reason) { alert("거부 사유를 입력해주세요."); return; }
      // 백엔드 연동 시 여기서 fetch 호출
	  
	  const params = new URLSearchParams(window.location.search);
	  const memberNumber = params.get("memberNumber");
	  
	  const response = await fetch(
		`${window.contextPath}/api/admin/userMM/rejectUserStateBySurvey.admin`,
		{
		  method: "post",
		  body: JSON.stringify({ 
		  	"memberNumber": memberNumber,
		  	"rejectReason": reason 
		  })
		}
	  );
	  const respJson = await response.json();
	  if (respJson.state != "ok") {	
	      alert("거부 처리에 실패했습니다.");
		  return;
	  }
	  
	  alert("거부 처리 되었습니다.");
      closeModal("modalReject");
      location.href = `${window.contextPath}/app/admin/adminUserManagement/userMM.admin`;
    });
  }

  /* ========================
     승인 버튼
     백엔드 연동 시: fetch("/api/user/{id}/approve", { method: "POST" })
  ======================== */
  const btnApprove = document.getElementById("btnApprove");
  if (btnApprove) {
    btnApprove.addEventListener("click", async () => {
      if (confirm("해당 유저를 승인하시겠습니까?")) {
        // 백엔드 연동 시 여기서 fetch 호출
		const params = new URLSearchParams(window.location.search);
		const memberNumber = params.get("memberNumber");
		
		const response = await fetch(
			`${window.contextPath}/api/admin/userMM/acceptUserStateBySurvey.admin`,
			{
				method: "post",
				body: JSON.stringify({ "memberNumber": memberNumber })
			}
		);
		const respJson = await response.json();
		if (respJson.state != "ok") {
			alert("승인처리가 실패했습니다.");
			return;
		}
		
        alert("승인 처리되었습니다.");
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
