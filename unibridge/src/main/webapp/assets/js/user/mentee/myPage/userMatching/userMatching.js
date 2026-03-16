/**
 * 
 */
const modal = document.getElementById("matchingModal");
const openBtn = document.getElementById("openModalBtn");
const xBtn = document.querySelector(".closeBtn");    // 상단 X 버튼
const closeBtn = document.getElementById("closeModalBtn");
const submitBtn = document.getElementById("sumbitBtn"); //작성완료
const reportBtn = document.getElementById("reportBtn"); //작성완료

// 모달 열기
openBtn.onclick = () => {
    modal.style.display = "block";
}

// 모달 닫기 (취소 버튼 또는 X 버튼)
[closeBtn, xBtn].forEach(btn => {
    btn.onclick = () => {
        modal.style.display = "none";
    }
});

// 모달 바깥쪽 어두운 배경 클릭 시 닫기
window.onclick = (event) => {
    if (event.target == modal) {
        modal.style.display = "none";
    }
}

submitBtn.onclick = (event) => {
    // 1. 폼 전송으로 인한 새로고침 방지
    event.preventDefault();

    // 2. 사용자 확인 창 띄우기
    const isConfirmed = confirm("정말로 매칭을 취소 신청하시겠습니까?");

    if (isConfirmed) {
        // [확인] 클릭 시: 특정 경로로 이동
        const targetPath = "/frontend/html/user/mentee/myPage/myPage.html"; 
        window.location.href = targetPath;
    } else {
        // [취소] 클릭 시: 아무 동작도 하지 않음 (모달 상태 유지)
        console.log("매칭 취소 신청이 중단되었습니다.");
    }
};
reportBtn.onclick = (event) => {
    // 1. 폼 전송으로 인한 새로고침 방지
    event.preventDefault();

    // 2. 원하는 경로를 직접 지정하여 이동합니다.
    // 예: "userModify.html" 또는 "../../main.html" 등
    const targetPath = "/frontend/html/user/notice/report.html"; 
    window.location.href = targetPath;
}
