/**
 * 
 */
const DUPLICATE_NICKNAMES = ["test"];
const VALID_AUTH_CODE = "12345"; // 테스트용 인증번호

document.addEventListener("DOMContentLoaded", () => {
    // ── 요소 참조 ──
    const completeBtn = document.querySelector(".userModifyBtn");
    const profileImg = document.querySelector(".userImg > img");
    const photoBtn = document.querySelector("#imgBtn");
    const photoError = document.querySelector(".userImg .errorMsg");

    // 상태 변수
    let isNickChecked = false; // 중복 확인 완료 여부
    let isPhoneAuthSent = false; // 인증번호 발송 여부
    let isPhoneVerified = false; // 인증번호 확인 완료 여부
    let isPhotoAttached = !!profileImg.getAttribute("src") && !profileImg.src.includes("ex1.png"); 
    // 기본 이미지(ex1.png)가 아닐 경우 이미 등록된 것으로 간주

    // ──────────────────────────────────────
    // 1. 프로필 사진 관리
    // ──────────────────────────────────────
    const fileInput = document.createElement("input");
    fileInput.type = "file";
    fileInput.accept = "image/*";

    photoBtn.addEventListener("click", () => fileInput.click());

    fileInput.addEventListener("change", (e) => {
        const file = e.target.files[0];
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

    // ──────────────────────────────────────
    // 2. 닉네임 변경
    // ──────────────────────────────────────
    const nickGroup = document.querySelectorAll(".inputGroup")[2];
    const nickInput = nickGroup.querySelector("input");
    const nickDupBtn = nickGroup.querySelectorAll("button")[0];
    const nickChangeBtn = nickGroup.querySelectorAll("button")[1];
    const nickError = nickGroup.querySelector(".errorMsg");

    nickInput.addEventListener("input", () => {
        isNickChecked = false;
        nickError.style.color = "red";
        nickError.textContent = "중복 닉네임 확인이 필요합니다.";
    });

    nickDupBtn.addEventListener("click", () => {
        const val = nickInput.value.trim();
        if (!val) {
            nickError.textContent = "닉네임을 입력해주세요.";
            return;
        }
        if (DUPLICATE_NICKNAMES.includes(val)) {
            nickError.style.color = "red";
            nickError.textContent = "중복 닉네임이 있습니다.";
            isNickChecked = false;
        } else {
            nickError.textContent = "사용 가능한 닉네임입니다.";
            nickError.style.color = "blue";
            isNickChecked = true;
        }
    });

    nickChangeBtn.addEventListener("click", () => {
        if (!isNickChecked) {
            nickError.style.color = "red";
            nickError.textContent = "중복 닉네임 확인이 필요합니다.";
        } else {
            alert("닉네임이 성공적으로 변경되었습니다.");
            nickError.textContent = "";
        }
    });

    // ──────────────────────────────────────
    // 3. 비밀번호 변경
    // ──────────────────────────────────────
    const pwInput = document.querySelectorAll(".inputGroup")[3].querySelector("input");
    const pwConfirmGroup = document.querySelectorAll(".inputGroup")[4];
    const pwConfirmInput = pwConfirmGroup.querySelector("input");
    const pwCheckBtn = pwConfirmGroup.querySelectorAll("button")[0];
    const pwChangeBtn = pwConfirmGroup.querySelectorAll("button")[1];
    const pwError = pwConfirmGroup.querySelector(".errorMsg");

    let isPwMatched = false;

    pwCheckBtn.addEventListener("click", () => {
        if (!pwInput.value || !pwConfirmInput.value) {
            pwError.style.color = "red";
            pwError.textContent = "비밀번호를 모두 입력해주세요.";
            return;
        }
        if (pwInput.value !== pwConfirmInput.value) {
            pwError.style.color = "red";
            pwError.textContent = "비밀번호가 일치하지 않습니다.";
            isPwMatched = false;
        } else {
            pwError.textContent = "비밀번호가 일치합니다.";
            pwError.style.color = "blue";
            isPwMatched = true;
        }
    });

    pwChangeBtn.addEventListener("click", () => {
        if (!isPwMatched) {
            pwError.style.color = "red";
            pwError.textContent = "비밀번호 확인이 필요합니다.";
        } else {
            alert("비밀번호가 성공적으로 변경되었습니다.");
            pwError.textContent = "";
        }
    });

    // ──────────────────────────────────────
    // 4. 전화번호 및 인증
    // ──────────────────────────────────────
    const phoneInput = document.querySelectorAll(".inputGroup")[5].querySelector("input");
    const authSendBtn = document.querySelector(".authBtn");
    const phoneError = document.querySelectorAll(".inputGroup")[5].querySelector(".errorMsg");

    const authGroup = document.querySelectorAll(".inputGroup")[6];
    const authInput = authGroup.querySelector("input");
    const authConfirmBtn = authGroup.querySelectorAll("button")[0];
    const phoneChangeBtn = authGroup.querySelectorAll("button")[1];
    const authError = authGroup.querySelector(".errorMsg");

    authSendBtn.addEventListener("click", () => {
        if (!phoneInput.value) {
            phoneError.style.color = "red";
            phoneError.textContent = "전화번호를 입력해주세요.";
            return;
        }
        alert("인증번호가 발송되었습니다.");
        isPhoneAuthSent = true;
        phoneError.textContent = "";
    });

    authConfirmBtn.addEventListener("click", () => {
        if (!isPhoneAuthSent) {
            authError.style.color = "red";
            authError.textContent = "인증번호를 먼저 발송해주세요.";
            return;
        }
        if (authInput.value === VALID_AUTH_CODE) {
            authError.textContent = "인증에 성공하였습니다.";
            authError.style.color = "blue";
            isPhoneVerified = true;
        } else {
            authError.style.color = "red";
            authError.textContent = "인증번호가 일치하지 않습니다.";
            isPhoneVerified = false;
        }
    });

    phoneChangeBtn.addEventListener("click", () => {
        if (!isPhoneVerified) {
            authError.style.color = "red";
            authError.textContent = "인증번호 확인 절차가 필요합니다.";
        } else {
            alert("전화번호가 성공적으로 변경되었습니다.");
            authError.textContent = "";
        }
    });

    // ──────────────────────────────────────
    // 5. 성별 변경 (추가된 부분)
    // ──────────────────────────────────────
    const genderGroup = document.querySelectorAll(".inputGroup")[7];
    const genderChangeBtn = genderGroup.querySelector(".changeBtn");

    genderChangeBtn.addEventListener("click", () => {
        const selectedGender = genderGroup.querySelector('input[name="role"]:checked');
        if (selectedGender) {
            alert("성별이 성공적으로 변경되었습니다.");
        }
    });

    // ──────────────────────────────────────
    // 6. 완료 버튼 (최종 페이지 이동)
    // ──────────────────────────────────────
    completeBtn.addEventListener("click", (e) => {
        if (!isPhotoAttached) {
            photoError.style.color = "red";
            photoError.textContent = "사진 첨부는 필수입니다.";
            window.scrollTo({ top: 0, behavior: 'smooth' });
            return;
        }

        // 모든 로직 통과 시 이동
        window.location.href = '/frontend/html/user/mentee/myPage/myPage.html';
    });
});