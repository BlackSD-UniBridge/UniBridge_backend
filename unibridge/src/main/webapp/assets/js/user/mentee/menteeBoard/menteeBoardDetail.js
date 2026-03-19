
document.addEventListener('DOMContentLoaded', () => {
		//엘리먼트 선택(DOM 요소 선택)
		const listBtn = document.querySelector(".list-btn");
		const modifyBtn = document.querySelector(".modify-btn");
		const deleteBtn = document.querySelector(".delete-btn");
		const submitBtn = document.querySelector(".submit-btn");
		//데이터 읽기(data- 속성 사용)
		const boardNumber = listBtn?.dataset.boardNumber??window.boardNumber;
		const memberNumber = listBtn?.dataset.memberNumber??window.memberNumber;
		
		console.log("확인 boardNumber : ", boardNumber);
		console.log("확인 memberNumber : ", memberNumber);
		
		//이동버튼
		listBtn?.addEventListener("click", ()=>{
			window.location.href = "${contextPath}/mentee/menteeBoard/MenteeBoardList.meb";
		});
		
		//수정버튼
		modifyBtn?.addEventListener("click", () => {
			if(!boardNumber){
				return alert("boardNumber가 없습니다");
			}
			window.location.href=`${contextPath}/mentee/menteeBoard/MenteeBoardUpdate.meb?MenteeBoardNumber=${encodeURIComponent(boardNumber)}`;
		});
		
		//삭제시에는 fetch => await
		deleteBtn?.addEventListener("click", async () => {
			if (!boardNumber){
				return alert("boardNumber가 없습니다.");
			}
			if (!confirm("정말 삭제하시겠습니까?")){
				return;
			}
		
			try{	
				const res = await fetch(`${contextPath}/mentee/menteeBoard/MenteeBoardDelete.meb?MenteeBoardNumber=${encodeURIComponent(boardNumber)}`, {
					method:"POST", //조회 : GET, 데이터 변경 : POST
					headers:{"X-Requested-With" : "fetch"},
				});
				if(!res.ok){ 
					throw new Error("삭제 요청 실패");
				}
				
				alert("게시글이 삭제되었습니다");
				window.location.href=`${contextPath}/mentee/menteeBoard/MenteeBoardList.meb`;
			}catch(err){
				console.error("게시글 삭제 실패 : ", err);
				alert("게시글 삭제에 실패했습니다");
			}
		});

	});