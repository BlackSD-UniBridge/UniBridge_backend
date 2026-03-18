/**
 * 미정 설문조사
 */
const modal = document.getElementById("surveyModal");
const openBtn = document.getElementById("userWriteBtn");
const xBtn = document.querySelector(".closeBtn");    // 상단 X 버튼
const closeBtn = document.getElementById("closeModalBtn");
const submitBtn = document.getElementById("submitBtn"); //작성완료

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
        // 파일 선택을 취소하거나 비었을 때 다시 초기 상태로 (필요 시)
        fileSelector.classList.remove('hidden');
        fileInfoDisplay.style.display = 'none';
    }
}

submitBtn.onclick = (event) => {
    // 1. 기본 동작(페이지 이동) 방지
    event.preventDefault();

    const form = document.getElementById("surveyForm");
    
    // 2. 현재 선택된 라디오 버튼(멘토 또는 멘티) 가져오기
    const selectedRole = document.querySelector('input[name="role"]:checked').value;
    
    // 3. 역할에 따라 전송할 컨트롤러 경로 설정
    // contextPath가 필요한 경우 앞부분에 추가해 주세요 (예: /unibridge/mypage/...)
    if (selectedRole === "mentor") {
        form.action = "/mypage/surveyMentorOk.my";
    } else if (selectedRole === "mentee") {
        form.action = "/mypage/surveyMenteeOk.my";
    }

    // 4. 데이터가 잘 수집되는지 확인하기 위한 로그 (선택 사항)
    console.log("[JS LOG] 제출 역할: " + selectedRole);
    console.log("[JS LOG] 전송 경로: " + form.action);

    // 5. 서버로 폼 데이터 전송
    form.submit();
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
});/**
 * 
 */