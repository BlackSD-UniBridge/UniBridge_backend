document.addEventListener("DOMContentLoaded", () => {
    const deleteForm = document.getElementById("deleteForm");
    const userId = document.getElementById("userId");
    const userPw = document.getElementById("userPw");
    const userPhone = document.getElementById("userPhone");
    const authCode = document.getElementById("authCode");
    const submitBtn = document.getElementById("submitBtn");
    
    const loginError = document.getElementById("loginError");
    const authError = document.getElementById("authError");

    const sendSmsBtn = document.getElementById("sendSms");
    const verifyBtn = document.getElementById("verifySms");

    let isAuthVerified = false;

    // 필드 체크 및 버튼 활성화 함수
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

    // 1. 인증번호 전송 (MenteeDeleteController 호출)
    if (sendSmsBtn) {
        sendSmsBtn.addEventListener("click", () => {
            const phone = userPhone.value.trim();
            if (!/^010\d{8}$/.test(phone)) {
                alert("올바른 전화번호를 입력하세요.");
                return;
            }

            fetch(`${contextPath}/mvc/auth/undecided/delete.my?mode=send&phoneNumber=${phone}`, {
                method: "POST"
            })
            .then(res => res.text())
            .then(data => {
                if(data === "success") {
                    alert("인증번호가 발송되었습니다.");
                    sendSmsBtn.disabled = true;
                    sendSmsBtn.textContent = "발송 완료";
                }
            });
        });
    }

    // 2. 인증번호 확인 (MenteeDeleteController 호출)
    verifyBtn.addEventListener("click", () => {
        const code = authCode.value.trim();
        fetch(`${contextPath}/mvc/auth/undecided/delete.my?mode=check`, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ authCode: code })
        })
        .then(res => res.text())
        .then(data => {
            if (data === "verified") {
                authError.textContent = "인증 성공";
                authError.style.color = "blue";
                isAuthVerified = true;
                authCode.readOnly = true;
                verifyBtn.disabled = true;
            } else {
                authError.textContent = "인증번호가 틀렸습니다.";
                authError.style.color = "red";
                isAuthVerified = false;
            }
            validateForm();
        });
    });

    // 3. 최종 탈퇴 폼 제출
    deleteForm.addEventListener("submit", (e) => {
        if(!confirm("정말로 탈퇴하시겠습니까?")) {
            e.preventDefault();
        }
    });
});