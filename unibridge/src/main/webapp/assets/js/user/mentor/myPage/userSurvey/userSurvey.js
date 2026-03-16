/**
 * 멘토 설문조사
 */
const modal = document.getElementById("surveyModal");
const openBtn = document.getElementById("userWriteBtn");
const xBtn = document.querySelector(".closeBtn");
const closeBtn = document.getElementById("closeModalBtn");
const submitBtn = document.getElementById("submitBtn"); 

// 모든 라디오 버튼과 컨텐츠 영역을 가져옵니다.
const roleRadios = document.querySelectorAll('.radioUserType');
const mentorContent = document.getElementById('mentorContent');
const menteeContent = document.getElementById('menteeContent');

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

function updateFileName() {
    const fileInput = document.getElementById('surveyFile');
    const fileSelector = document.getElementById('fileSelector');
    const fileInfoDisplay = document.getElementById('fileInfoDisplay');
    const fileNameDisplay = document.getElementById('fileNameDisplay');
    
    if (fileInput.files.length > 0) {
        // 1. 파일이 선택되면 기존 '파일 선택' 버튼 숨기기
        fileSelector.classList.add('hidden');
        
        // 2. 파일 정보 영역 표시
        fileInfoDisplay.style.display = 'flex';
        
        // 3. 파일 이름 출력
        fileNameDisplay.textContent = fileInput.files[0].name;
    } else {
        // 파일 선택을 취소하거나 비었을 때 다시 초기 상태로
        fileSelector.classList.remove('hidden');
        fileInfoDisplay.style.display = 'none';
    }
}

submitBtn.onclick = (event) => {
    // 1. 폼 전송으로 인한 새로고침 방지
    event.preventDefault();

    // 2. 원하는 경로를 직접 지정하여 이동합니다.
    const targetPath = "/frontend/html/user/mentor/myPage/myPage.html"; 
    window.location.href = targetPath;
};

roleRadios.forEach(radio => {
    radio.addEventListener('change', (e) => {
        // 선택된 값에 따라 디스플레이 설정
        if (e.target.value === 'mentor') {
            mentorContent.style.display = 'block';
            menteeContent.style.display = 'none';
        } else if (e.target.value === 'mentee') {
            mentorContent.style.display = 'none';
            menteeContent.style.display = 'block';
        }
    });
});