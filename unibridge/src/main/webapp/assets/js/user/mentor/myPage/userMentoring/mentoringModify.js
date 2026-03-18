/**
 * mentoringModify.js
 */

document.addEventListener("DOMContentLoaded", function() {
    const form = document.querySelector("form");

    // 1. 수정 완료 제출 시 최종 확인 및 유효성 검사
    if (form) {
        form.addEventListener("submit", function(e) {
            const title = document.querySelector('input[name="mentoringTitle"]');
            
            // 간단한 유효성 검사 추가
            if (title && title.value.trim() === "") {
                alert("제목을 입력해주세요.");
                title.focus();
                e.preventDefault();
                return;
            }

            if (!confirm("수정된 내용을 저장하시겠습니까?")) {
                e.preventDefault();
            }
        });
    }

    // 2. 취소 버튼 로직 (id="cancel"인 경우)
    const cancelBtn = document.getElementById("cancel");
    if (cancelBtn) {
        cancelBtn.addEventListener("click", function() {
            if (confirm("수정을 취소하고 돌아가시겠습니까? 입력한 내용은 저장되지 않습니다.")) {
                history.back();
            }
        });
    }
});
