/**
 * mentoringView.js 
 */

// 삭제 버튼 클릭 시
function deleteMentoring(internalId) {
    if(confirm("정말로 이 멘토링을 삭제하시겠습니까?")) {
        // 경로에 id 파라미터명 확인
        location.href = "mentoringDelete.my?id=" + internalId;
    }
}

function goToModify(internalId) {
    location.href = contextPath + "mentoringModify.my?id=" + internalId;
}

// 입력 폼 유효성 검사 (Create/Modify 공통)
const validateForm = () => {
    const title = document.querySelector('input[name="mentoringTitle"]');
    if (title.value.trim() === "") {
        alert("제목을 입력해주세요.");
        title.focus();
        return false;
    }
    return true;
};

document.addEventListener("DOMContentLoaded", function() {
    const delBtn = document.getElementById("delBtn");
    const modifyBtn = document.querySelector(".btn-modify"); // 수정 버튼 클래스명 확인 필요

    // 삭제 버튼 클릭 시
    if(delBtn) {
        delBtn.addEventListener("click", function() {
            // JSP에서 넘겨준 ID값을 가져오거나 함수 호출 방식 사용
            // 예시: deleteMentoring('${mentoring.id}');
        });
    }
});

// 수정 페이지 이동 시에도 internalId 사용
function goToModify(internalId) {
    location.href = contextPath + "mentoringModify.my?id=" + internalId;
}

