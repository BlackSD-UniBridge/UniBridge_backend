const loginForm = document.getElementById("loginForm");
const adminId = document.getElementById("adminId");
const adminPw = document.getElementById("adminPw");
const errorText = document.getElementById("errorText");

loginForm.addEventListener("submit", function (event) {
  event.preventDefault();

  const idValue = adminId.value.trim();
  const pwValue = adminPw.value.trim();

  errorText.textContent = "";
  
  if (!idValue || !pwValue) {
    errorText.textContent = "아이디와 비밀번호를 모두 입력해주세요.";
 
    return;
  }

  loginForm.submit();		//전송
});