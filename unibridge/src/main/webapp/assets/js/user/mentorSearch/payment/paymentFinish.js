/**
 * paymentFinish.js
 */

const button = document.querySelector("button");

button.addEventListener("click", () => {
  // 전역 변수가 있으면 사용하고, 없으면 빈 값을 기본값으로 설정
  const cp = (typeof globalContextPath !== 'undefined') ? globalContextPath : '';
  
  // 컨텍스트 패스와 메인 경로(.main)를 결합하여 이동
  location.href = cp + "/index.main";
});