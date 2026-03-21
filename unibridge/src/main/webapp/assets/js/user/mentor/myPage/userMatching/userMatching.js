/**
 * 매칭 정보 관리 JavaScript
 * JSP의 동적 ID(matchingModal_번호)와 연동됩니다.
 */

// 1. 매칭 취소 모달 열기
function openCancelModal(matchNum) {
    console.log("모달 열기 시도 - 매칭번호: " + matchNum);
    const modal = document.getElementById('matchingModal_' + matchNum);
    
    if (modal) {
        // 배경을 포함한 모달 전체를 화면에 표시
        modal.style.display = "flex"; 
    } else {
        console.error("해당 모달을 찾을 수 없습니다: matchingModal_" + matchNum);
    }
}

// 2. 매칭 취소 모달 닫기
function closeCancelModal(matchNum) {
    const modal = document.getElementById('matchingModal_' + matchNum);
    if (modal) {
        modal.style.display = "none";
    }
}

// 3. 매칭 취소 신청 제출 처리
function submitCancel(matchNum) {
    const modal = document.getElementById('matchingModal_' + matchNum);
    const reasonArea = modal.querySelector('textarea[name="matchingCanReason"]');
    // 공백 제거 후 값 가져오기
    const reason = reasonArea ? reasonArea.value.trim() : "";

    // 1. [검사] 사유가 비어있는지 체크
    if (reason === "") {
        alert("취소 사유를 입력해주세요.");
        if(reasonArea) reasonArea.focus();
        return;
    }

    // 2. [검사] 1024자 제한 체크 (DB 에러 방지)
    if (reason.length > 1024) {
        alert("취소 사유는 1024자를 초과할 수 없습니다. (현재: " + reason.length + "자)");
        if(reasonArea) reasonArea.focus();
        return;
    }

    // 3. 사용자 최종 확인
    if (confirm("정말로 매칭을 취소 신청하시겠습니까?")) {
        const contextPath = window.location.pathname.substring(0, window.location.pathname.indexOf("/", 2));
        
        // 가상의 폼 생성
        const form = document.createElement('form');
        form.method = 'POST';
        form.action = contextPath + "/mvc/auth/mentor/matching.my"; 

        // 데이터 1: 매칭 번호 추가
        const numInput = document.createElement('input');
        numInput.type = 'hidden';
        numInput.name = 'matchinNumber'; 
        numInput.value = matchNum;
        form.appendChild(numInput);

        // 데이터 2: 취소 사유 추가 (이게 꼭 들어가야 DB에 저장됩니다!)
        const reasonInput = document.createElement('input');
        reasonInput.type = 'hidden';
        reasonInput.name = 'matchingCanReason'; // 컨트롤러의 getParameter와 일치
        reasonInput.value = reason;
        form.appendChild(reasonInput);

        document.body.appendChild(form);
        form.submit();
        
        alert("매칭 취소 신청이 완료되었습니다.");
    }
}

// 4. 모달 바깥쪽(어두운 배경) 클릭 시 닫기 처리
window.onclick = function(event) {
    // 클릭한 대상의 클래스가 'matingCancel'(배경 레이어)이면 닫기
    if (event.target.classList.contains('matingCancel')) {
        event.target.style.display = "none";
    }
};