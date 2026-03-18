const loginForm = document.getElementById("loginForm");
const adminId = document.getElementById("adminId");
const adminPw = document.getElementById("adminPw");
const errorText = document.getElementById("errorText");

// 테스트용 관리자 계정
const ADMIN_ACCOUNT = {
  id: "admin",
  pw: "1234",
};

loginForm.addEventListener("submit", function (event) {
  event.preventDefault();

  const idValue = adminId.value.trim();
  const pwValue = adminPw.value.trim();

  if (!idValue || !pwValue) {
    errorText.textContent = "아이디와 비밀번호를 입력해주세요.";
    return;
  }

  if (idValue === ADMIN_ACCOUNT.id && pwValue === ADMIN_ACCOUNT.pw) {
    errorText.textContent = "";

    window.location.href = "./../../../html/admin/adminMain/main.html";
    return;
  }

  errorText.textContent = "아이디와 비밀번호가 맞지 않습니다. 다시 확인해주세요.";
});