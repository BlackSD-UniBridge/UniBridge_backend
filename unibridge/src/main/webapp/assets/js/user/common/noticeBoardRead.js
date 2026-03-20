document.addEventListener("DOMContentLoaded", () => {
	//엘리먼트 선택(DOM 요소 선택)
	const listBtn = document.querySelector(".list-btn");
	const modifyBtn = document.querySelector(".modify-btn");
	const deleteBtn = document.querySelector(".delete-btn");
	const submitBtn = document.querySelector(".submit-btn");
	
	//데이터 읽기(data- 속성 사용)
	const noticeBoardNumber = listBtn?.dataset.noticeBoardNumber??window.noticeBoardNumber;
	console.log("확인 boardNumber : ", noticeBoardNumber);
	
	// 목록 버튼 → 목록 페이지로 이동
	listBtn?.addEventListener("click", ()=>{
		window.location.href = "/unibridge/noticeBoardList.ntb";
	});

});











