document.addEventListener("DOMContentLoaded", () => {
    // ── 공통 설정 ──
    const contextPath = window.location.pathname.substring(0, window.location.pathname.indexOf("/", 2));

    // ── 1. 프로필 사진 관리 (미리보기) ──
    const profileImg = document.querySelector(".userImg > img");
    const photoBtn = document.querySelector("#imgBtn");
    const photoError = document.querySelector(".userImg .errorMsg");
    let isPhotoAttached = !profileImg.src.includes("ex1.png");

    const fileInput = document.createElement("input");
    fileInput.type = "file";
    fileInput.accept = "image/*";
    photoBtn.addEventListener("click", () => fileInput.click());

    fileInput.addEventListener("change", (e) => {
        const file = e.target.files; // 인덱스 추가 확인
        if (file) {
            const reader = new FileReader();
            reader.onload = (event) => {
                profileImg.src = event.target.result;
                isPhotoAttached = true;
                photoError.textContent = ""; 
            };
            reader.readAsDataURL(file);
        }
    });

    // ── 2. 전화번호 인증 (실제 서버 통신 로직으로 교체) ──
    window.sendSms = function() {
        const phone = document.getElementById("memberPhone").value;
        const phoneError = document.getElementById("phoneSendError");

        if (!phone) {
            alert("전화번호를 입력해주세요.");
            return;
        }

        // MenteeVerifyActionController 호출
        fetch(`${contextPath}/mvc/auth/mentee/verify.my?mode=send&phoneNumber=${phone}`)
            .then(res => res.text())
            .then(data => {
                if (data === "success") {
                    alert("인증번호가 발송되었습니다.");
                } else {
                    alert("발송 실패: 서버 로그를 확인하세요.");
                }
            });
    };

    window.verifyCode = function() {
        const code = document.getElementById("authCodeInput").value;
        
        // JSON 형태로 서버에 전달
        fetch(`${contextPath}/mvc/auth/mentee/verify.my?mode=check`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ authCode: code })
        })
        .then(res => res.text())
        .then(data => {
            if (data === "verified") {
                alert("인증에 성공하였습니다.");
            } else {
                alert("인증번호가 일치하지 않습니다.");
            }
        });
    };

    // ── 3. 완료 버튼 ──
    document.querySelector(".userModifyBtn").addEventListener("click", () => {
        if (!isPhotoAttached) {
            photoError.style.color = "red";
            photoError.textContent = "사진 첨부는 필수입니다.";
            window.scrollTo({ top: 0, behavior: 'smooth' });
            return;
        }
        // 메인 마이페이지로 이동
        location.href = `${contextPath}/mvc/auth/mentee/myPage.my`;
    });
});