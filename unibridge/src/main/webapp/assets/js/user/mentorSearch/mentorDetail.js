/**
 * 
 */

const payBtn = document.getElementById("pay");

if(payBtn) { 
  payBtn.addEventListener("click", () => {
    if(confirm("이 멘토님과 매칭을 진행하시겠습니까?")) {
    }
  });
}