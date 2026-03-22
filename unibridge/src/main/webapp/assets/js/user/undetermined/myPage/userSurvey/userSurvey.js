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

// 1. 초기 상태 설정 함수 추가
function initSurveyForm() {
	// 현재 선택(checked)된 라디오 버튼에 따라 컨텐츠 표시/숨김
    const checkedRadio = document.querySelector('input[name="role"]:checked');
    
    if (checkedRadio) {
        if (checkedRadio.value === 'mentor') {
            document.getElementById('mentorContent').style.display = 'block';
            document.getElementById('menteeContent').style.display = 'none';
        } else if (checkedRadio.value === 'mentee') {
            document.getElementById('mentorContent').style.display = 'none';
            document.getElementById('menteeContent').style.display = 'block';
        }
    }
}

// 페이지 로드 시 및 모달 열 때 초기화 실행
window.addEventListener('DOMContentLoaded', initSurveyForm);

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

// 2. 제출 버튼 로직 수정
submitBtn.onclick = (event) => {
    event.preventDefault();

    const form = document.getElementById("surveyForm");
    const checkedInput = document.querySelector('input[name="role"]:checked');
    
    if(!checkedInput) {
        alert("역할을 선택해주세요.");
        return;
    }

    const selectedRole = checkedInput.value;
    
    // JSP에서 전역 변수로 contextPath를 선언해두어야 합니다. (아래 JSP 가이드 참고)
    const finalContextPath = typeof contextPath !== 'undefined' ? contextPath : "";

    if (selectedRole === "mentor") {
        form.action = finalContextPath + "/auth/undecided/survey.my";
    } else if (selectedRole === "mentee") {
        form.action = finalContextPath + "/auth/undecided/survey.my";
    }

    console.log("[JS LOG] 전송 경로: " + form.action);
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