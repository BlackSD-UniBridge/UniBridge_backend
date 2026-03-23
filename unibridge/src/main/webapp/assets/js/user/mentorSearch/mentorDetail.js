/**
 * 
 */

const payBtn = document.getElementById("pay");

if(payBtn) { 
  payBtn.addEventListener("click", () => {
    if(confirm("이 멘토님과 매칭을 진행하시겠습니까?")) {
        // JSP에서 넣어준 멘토 번호를 가져와서 이동
        // 버튼에 data-num="${mentor.memberNumber}" 같은 속성을 추가하면 편합니다.
        const memberNumber = payBtn.getAttribute("data-num"); 
        location.href = `${globalContextPath}/pay/payment.pay?memberNumber=${memberNumber}`;
    }
  });
}