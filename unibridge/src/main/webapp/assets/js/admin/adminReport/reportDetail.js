/*const deleteConfirmBtn = document.querySelector(".delete-button");
deleteConfirmBtn.addEventListener('click', async () => {
	const curLearningReport = JSON.parse(sessionStorage.getItem("currentLeaningReport"));
	const reportNumber = curLearningReport.lrReportNumber;
	
	const redirectPath = await fetch(window.contextPath + "/admin/reportDelete.admin?reportNumber=" + reportNumber, { method: "POST" });
	location.href = redirectPath.;
});*/