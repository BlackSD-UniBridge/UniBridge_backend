document.addEventListener('DOMContentLoaded', () => {
    // 1. 서버에서 처리 완료 후 알림창 띄우기
    if (window.SERVER_UPDATE_STATUS) {
        switch(window.SERVER_UPDATE_STATUS) {
            case "nickname_ok": alert("닉네임이 변경되었습니다."); break;
            case "password_ok": alert("비밀번호가 성공적으로 변경되었습니다."); break;
            case "phone_ok": alert("전화번호가 변경되었습니다."); break;
            case "gender_ok": alert("성별 정보가 업데이트되었습니다."); break;
            case "profileImg_ok": alert("프로필 사진이 성공적으로 변경되었습니다."); break;
        }
    }

	// 2. 프로필 사진 즉시 변경 로직
    const profileFileInput = document.getElementById('profileFileInput');
    if (profileFileInput) {
        profileFileInput.addEventListener('change', function() {
            if (this.files && this.files) {
                if (confirm("선택하신 사진으로 프로필을 변경하시겠습니까?")) {
                    document.getElementById('imageForm').submit();
                } else {
                    this.value = ""; // 취소 시 초기화
                }
            }
        });
    }
    // --- 프로필 이미지 변경 로직 끝 ---

    // 2. 닉네임 중복 확인 (AJAX)
    window.checkNick = function() {
        const nick = document.getElementById('memberNickname').value.trim();
        const errorMsg = document.getElementById('nickErrorMsg');
        
        if(!nick) { alert("닉네임을 입력해주세요."); return; }

        fetch(`${window.contextPath}/mvc/auth/mentor/updateOk.my?mode=checkNick&memberNickname=${nick}`, {
            method: 'POST'
        })
        .then(res => res.text())
        .then(data => {
            if(data.trim() === "available") {
                alert("사용 가능한 닉네임입니다.");
                errorMsg.innerText = "";
                window.isNickChecked = true; 
            } else {
                errorMsg.innerText = "이미 사용 중인 닉네임입니다.";
                window.isNickChecked = false;
            }
        });
    };

    // 3. 비밀번호 일치 확인
    window.checkPwMatch = function() {
        const pw = document.getElementById('newPw').value;
        const confirm = document.getElementById('newPwConfirm').value;
        const msg = document.getElementById('pwErrorMsg');

        if(pw === confirm && pw !== "") {
            alert("비밀번호가 일치합니다.");
            msg.innerText = "";
            window.isPwMatched = true;
        } else {
            msg.innerText = "비밀번호가 일치하지 않습니다.";
            window.isPwMatched = false;
        }
    };

    // 4. 완료 버튼 클릭 시 최종 체크
    const modifyBtn = document.querySelector('.userModifyBtn');
    modifyBtn.addEventListener('click', () => {
        const userImg = document.getElementById('profileDisplay');
        const imgError = document.getElementById('imageError'); // HTML에 추가된 id
        
        // 이미지 필수 체크 (프로젝트 정책에 따라 선택)
        if (!userImg.src || userImg.src.includes("default")) { 
            if(imgError) imgError.innerText = "프로필 사진은 필수입니다.";
            alert("프로필 사진을 등록해주세요.");
            return;
        }
        
        // 최종 이동
        location.href = `${window.contextPath}/mvc/auth/mentor/myPage.my`;
    });
});

// 전화번호 인증 관련 함수
function sendSms() {
    const phone = document.getElementById('memberPhone').value;
    fetch(`${window.contextPath}/mvc/auth/mentor/verifyAction.my?mode=send&phoneNumber=${phone}`, { method: 'POST' })
    .then(res => res.text())
    .then(data => alert(data === "success" ? "인증번호가 발송되었습니다." : "발송 실패"));
}

function verifyCode() {
    const code = document.getElementById('authCodeInput').value;
    fetch(`${window.contextPath}/mvc/auth/mentor/verifyAction.my?mode=check`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ authCode: code })
    })
    .then(res => res.text())
    .then(data => {
        if(data.trim() === "verified") {
            alert("인증 성공!");
            window.isPhoneVerified = true;
        } else {
            const codeError = document.getElementById('authCodeError');
            if(codeError) codeError.innerText = "인증번호가 틀렸습니다.";
        }
    });
}