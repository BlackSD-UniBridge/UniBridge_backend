/**
 * 
 */
document.addEventListener('DOMContentLoaded', () => {
    const modifyForm = document.getElementById('goUserModify');
    const inputs = document.querySelectorAll('.userInput');
    
    // 버튼 요소 가져오기
    const sendAuthBtn = document.querySelectorAll('.duplication')[0]; // 첫 번째 '인증 번호 전송' 버튼
    const confirmAuthBtn = document.querySelectorAll('.duplication')[1]; // 두 번째 '인증 확인' 버튼
    
    // 상태 변수 및 임의 값 설정
    let isAuthVerified = false; 
    const CORRECT_PW = "1234";    
    const CORRECT_AUTH = "12345"; 

    // [1] 인증 번호 전송 버튼 클릭 이벤트
    sendAuthBtn.addEventListener('click', (e) => {
        e.preventDefault(); // 폼 제출 방지

        const phoneInput = inputs[1].value.trim();
        if (!phoneInput) {
            alert('전화번호를 먼저 입력해주세요.');
            inputs[1].focus();
            return;
        }

        // 전송 시뮬레이션
        alert('인증번호가 발송되었습니다.');
        
        // 버튼 비활성화 (선택 사항: 다시 전송 못 하게 하거나 디자인 변경)
        sendAuthBtn.disabled = true;
        sendAuthBtn.innerText = "발송 완료";
        sendAuthBtn.style.backgroundColor = "#ccc";
        sendAuthBtn.style.cursor = "default";
    });

    // [2] 인증 확인 버튼 클릭 이벤트
    confirmAuthBtn.addEventListener('click', (e) => {
        e.preventDefault(); 
        
        const enteredAuth = inputs[2].value.trim();

        if (enteredAuth === CORRECT_AUTH) {
            alert('인증에 성공하였습니다.');
            isAuthVerified = true;
            
            // 인증번호 입력창 및 버튼 비활성화
            inputs[2].readOnly = true; 
            confirmAuthBtn.disabled = true;
            confirmAuthBtn.innerText = "확인 완료";
            confirmAuthBtn.style.backgroundColor = "#ccc";
            confirmAuthBtn.style.cursor = "default";
        } else {
            alert('인증번호가 일치하지 않습니다. (임의 번호: 12345)');
            isAuthVerified = false;
        }
    });

    // [3] 최종 정보 수정 버튼 클릭 이벤트
    modifyForm.addEventListener('submit', (e) => {
        e.preventDefault();

        const enteredPw = inputs[0].value.trim();
        const enteredPhone = inputs[1].value.trim();

        if (!enteredPw) {
            alert('현재 비밀번호를 입력해주세요.');
            inputs[0].focus();
            return;
        }

        // 비밀번호 체크
        if (enteredPw !== CORRECT_PW) {
            alert('현재 비밀번호가 일치하지 않습니다. (임의 번호: 1234)');
            inputs[0].focus();
            return;
        }

        // 인증 완료 여부 체크
        if (!isAuthVerified) {
            alert('휴대폰 인증 확인이 완료되지 않았습니다.');
            return;
        }

        // 모든 조건 충족 시
        if (confirm('회원 정보를 수정하시겠습니까?')) {
            window.location.href = '/frontend/html/user/mentor/myPage/userManage/userModify.html'; 
        }
    });
});