/**
 * 
 */
document.addEventListener("DOMContentLoaded", () => {
    const deleteForm = document.getElementById("delete");
    const userId = document.getElementById("userId");
    const userPw = document.getElementById("userPw");
    const userPhone = document.getElementById("userPhone");
    const authCode = document.getElementById("authCode");
    const submitBtn = document.getElementById("submitBtn");
    
    const loginError = document.getElementById("login-error");
    const authError = document.getElementById("auth-error");

    // [추가] 버튼 요소들
    const sendSmsBtn = document.getElementById("sendSms"); // 전송 버튼
    const verifyBtn = document.getElementById("verifySms"); // 확인 버튼

    const TEST_ID = "test";
    const TEST_PW = "1234";
    const TEST_AUTH = "12345";

    let isAuthVerified = false;

    const validateForm = () => {
        const allFilled = userId.value.trim() !== "" && 
                          userPw.value.trim() !== "" && 
                          userPhone.value.trim() !== "" && 
                          authCode.value.trim() !== "";
        
        submitBtn.disabled = !(allFilled && isAuthVerified);
    };

    [userId, userPw, userPhone, authCode].forEach(input => {
        input.addEventListener("input", validateForm);
    });

    // 1. 인증번호 전송 로직 추가
    if (sendSmsBtn) {
        sendSmsBtn.addEventListener("click", () => {
            if (userPhone.value.trim() === "") {
                alert("전화번호를 입력해주세요.");
                userPhone.focus();
                return;
            }
            // 발송 알림 및 비활성화
            alert("인증번호가 발송되었습니다.");
            sendSmsBtn.disabled = true;
            sendSmsBtn.textContent = "발송 완료";
        });
    }

    // 2. 인증 확인 로직 수정 (비활성화 추가)
    verifyBtn.addEventListener("click", () => {
        if (authCode.value === TEST_AUTH) {
            authError.textContent = ""; 
            isAuthVerified = true;
            alert("인증에 성공하였습니다.");
            
            // 인증 성공 시 비활성화 처리
            authCode.readOnly = true; 
            verifyBtn.disabled = true;
            verifyBtn.textContent = "확인 완료";
        } else {
            authError.textContent = "인증번호가 일치하지 않습니다.";
            isAuthVerified = false;
        }
        validateForm();
    });

    deleteForm.addEventListener("submit", (e) => {
        e.preventDefault();
        
        if (userId.value === TEST_ID && userPw.value === TEST_PW) {
            loginError.textContent = "";
            if(confirm("정말로 탈퇴하시겠습니까?")) {
                alert("탈퇴 신청이 완료되었습니다.");
                window.location.href = '/frontend/main.html';
            }
        } else {
            loginError.textContent = "정보가 맞지 않습니다. 다시 확인해주세요.";
        }
    });
});