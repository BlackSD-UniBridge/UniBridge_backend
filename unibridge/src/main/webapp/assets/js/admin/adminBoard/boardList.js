document.addEventListener("DOMContentLoaded", () => {

  const path = window.location.pathname;
  const isMentor = path.includes("mentorBoard");
  const boardType = isMentor ? "mentor" : "mentee";

  /* ========================
     탭 링크
  ======================== */
  const tabMentee = document.getElementById("tab-mentee");
  const tabMentor = document.getElementById("tab-mentor");

  if (tabMentee) {
    tabMentee.href = `${contextPath}/app/admin/adminBoard/menteeBoard/menteeBoardList.admin`;
    if (!isMentor) tabMentee.classList.add("is-active");
  }
 
  if (tabMentor) {
    tabMentor.href = `${contextPath}/app/admin/adminBoard/mentorBoard/mentorBoardList.admin`;
    if (isMentor) tabMentor.classList.add("is-active");
  }

  
  
  const btnSearch = document.getElementById("btnSearch");
  btnSearch.addEventListener("click", () => {
        const dateFrom = Number(document.getElementById("dateFrom").value.replace(/-/g, ""));	//랜더링 시작일
        const dateTo = Number(document.getElementById("dateTo").value.replace(/-/g, ""));	//랜더링 마지막일
		
        if (dateFrom > dateTo) {
          alert("시작 날짜가 종료 날짜보다 클 수 없습니다.");
          return;
        }
		
		window.location.href = `${contextPath}/app/admin/adminBoard/${boardType}Board/${boardType}BoardList.admin?dateFrom=${dateFrom}&dateTo=${dateTo}`;	
		
/*		fetch(`/app/admin/adminBoard/${boardType}Board/${boardType}BoardList.admin?dateFrom=${dateFrom}&dateTo=${dateTo}`)
		.then(response => response.json())
		.then(data => console.log(data))
		.catch(error => console.error(error));
*/
      });



 const btnWrite = document.getElementById("btnWrite");
  if (btnWrite) {
    btnWrite.addEventListener("click", () => {
      window.location.href = `${contextPath}/app/admin/adminBoard/${boardType}Board/${boardType}BoardWrite.admin`;
    });
  }

});
