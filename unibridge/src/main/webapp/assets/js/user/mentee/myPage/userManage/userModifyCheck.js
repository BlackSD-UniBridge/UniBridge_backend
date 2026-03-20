
document.addEventListener('DOMContentLoaded', () => {
    const inputs = document.querySelectorAll('.userInput');
    const sendBtn = document.querySelector('#sendSmsBtn');
    const verifyBtn = document.querySelector('#verifySmsBtn'); // ID 선택 권장
    const modifyForm = document.getElementById('goUserModify');
    
    let isAuthVerified = false; 

    // 1. 인증번호 전송 (010+8자리 숫자 검증 포함)
    sendBtn.addEventListener('click', (e) => {
        e.preventDefault();
        const phone = inputs[1].value.trim();

        // 하이픈 없이 숫자 11자리만 허용하는 정규표현식
        const phoneRegex = /^010\d{8}$/;

        if (!phone) {
            alert("전화번호를 입력해주세요.");
            return;
        }

        if (!phoneRegex.test(phone)) {
            alert("하이픈(-) 없이 숫자 11자리만 입력해주세요.\n(예: 01012345678)");
            inputs[1].focus();
            return;
        }

		fetch('/unibridge/mvc/auth/mentee/verifyAction.my?mode=send', {
		    method: 'POST',
		    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
		    body: `phoneNumber=${encodeURIComponent(phone)}`
		})
        .then(res => res.text())
        .then(data => {
            if (data === "success") alert("인증번호가 발송되었습니다.");
            else alert("발송 실패. 번호를 확인해주세요.");
        });
    });

    // 2. 인증번호 확인 (JSON POST 방식)
    verifyBtn.addEventListener('click', (e) => {
        e.preventDefault();
        const code = inputs[2].value.trim();

        if (!code) {
            alert("인증번호를 입력해주세요.");
            return;
        }

        // 경로를 verifyAction.my로 수정
		fetch('/unibridge/mvc/auth/mentee/verifyAction.my?mode=check', {
		    method: 'POST',
		    headers: { 'Content-Type': 'application/json' },
		    body: JSON.stringify({ authCode: code })
		})
        .then(res => res.text())
		.then(data => {
		    const verifyMsg = document.getElementById('verify-msg'); // 메시지 요소
		    if(data.trim() === "verified") {
		        alert("인증 성공!");
		        isAuthVerified = true;
		        verifyBtn.disabled = true;
		        verifyMsg.style.display = 'none'; // 성공 시 메시지 숨김
				location.href = '/unibridge/mvc/auth/mentee/updateOk.my';
		    } else {
		        // 이미지와 동일한 실패 문구 노출
		        verifyMsg.innerText = "인증에 실패하였습니다.";
		        verifyMsg.style.display = 'block';
		    }
		});
    });

    // 3. 최종 제출 (Submit)
    modifyForm.addEventListener('submit', (e) => {
        if (!isAuthVerified) {
            e.preventDefault();
            alert('휴대폰 인증을 완료해주세요.');
        }
        // JSP의 form action이 verifySubmit.my인지 꼭 확인하세요!
    });
});