/**
 * 
 */

document.addEventListener('DOMContentLoaded', () => {
    const payBtn = document.getElementById('kakaoPayBtn');

    if (payBtn) {
        payBtn.addEventListener('click', () => {
            const isChecked = document.getElementById('kakao').checked;
            
            if (!isChecked) {
                alert('카카오페이머니 결제를 선택해주세요.');
                return;
            }

            // 서블릿으로 결제 정보 전달 (주소는 프로젝트 경로에 맞춰 자동 생성)
            const itemName = encodeURIComponent('수능 국어 맞춤 멘토링');
            const totalAmount = 10000;
            
            // 현재 위치에서 paymentOk.pay를 호출
            location.href = `paymentOk.pay?item_name=${itemName}&total_amount=${totalAmount}`;
        });
    }
});