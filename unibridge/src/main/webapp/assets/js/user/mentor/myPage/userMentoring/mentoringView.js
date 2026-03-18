/**
 * mentoringView.js 
 */


// 입력 폼 유효성 검사 (나중에 수정 폼 등에서 재사용 가능)
const validateForm = () => {
    const title = document.querySelector('input[name="mentoringTitle"]');
    if (!title || title.value.trim() === "") {
        alert("제목을 입력해주세요.");
        if(title) title.focus();
        return false;
    }
    return true;
};

// 페이지 로드 시 필요한 설정 (현재는 특별한 설정이 없으므로 비워두거나 확장 가능)
document.addEventListener("DOMContentLoaded", function() {
    console.log("[Log] mentoringView.js 로드 완료");
    
    // 만약 버튼에 별도의 애니메이션이나 추가 로그가 필요하다면 여기에 작성합니다.
});

