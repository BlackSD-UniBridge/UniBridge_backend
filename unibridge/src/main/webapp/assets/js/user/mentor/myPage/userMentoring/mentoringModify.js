/**
 * mentoringModify.js
 */


document.addEventListener("DOMContentLoaded", function() {
    const form = document.querySelector("form");

    // 수정 완료 시 확인
    form.addEventListener("submit", function(e) {
        if (!confirm("수정된 내용을 저장하시겠습니까?")) {
            e.preventDefault();
        }
    });

    // 취소 버튼 (history.back() 외에 경로 지정 시)
    const cancelBtn = document.getElementById("cancel");
    if(cancelBtn) {
        cancelBtn.addEventListener("click", function() {
            if(confirm("수정을 취소하고 돌아가시겠습니까? 입력한 내용은 저장되지 않습니다.")) {
                history.back();
            }
        });
    }
});

// 삭제 함수 (JSP에서 호출)
function deleteMentoring(internalId) {
    if(confirm("정말로 이 멘토링을 삭제하시겠습니까?\n삭제된 데이터는 복구할 수 없습니다.")) {
        // .mo는 프로젝트 컨트롤러 확장자에 맞춰 수정하세요
        location.href = "/mentoringDeleteOk.mo?mentoringinternalId=" + internalId;
    }
}