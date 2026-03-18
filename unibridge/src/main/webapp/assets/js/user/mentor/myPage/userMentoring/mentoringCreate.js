/**
 * mentoringCreate.js *
 */


document.addEventListener("DOMContentLoaded", function() {
    const form = document.getElementById("createForm");
    
    form.addEventListener("submit", function(e) {
        const subject = document.getElementById("mentoringSubject").value;
        const title = document.getElementById("mentoringTitle").value;
        const purpose = document.getElementById("mentoringPurpose").value;

        if (!subject || subject === "none") {
            alert("학습 과목을 선택해주세요.");
            e.preventDefault();
            return;
        }

        if (title.trim().length < 2) {
            alert("제목을 2자 이상 입력해주세요.");
            e.preventDefault();
            return;
        }

        if (purpose.trim().length < 10) {
            alert("멘토링 목적을 상세히(10자 이상) 입력해주세요.");
            e.preventDefault();
            return;
        }

        if (!confirm("멘토링을 등록하시겠습니까?")) {
            e.preventDefault();
        }
    });
});