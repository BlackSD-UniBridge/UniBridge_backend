<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>유저 관리</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/admin/adminUserManagement/user.css" />
  <script src="${pageContext.request.contextPath}/assets/js/admin/adminUserManagement/user.js" defer></script>
</head>
<body>

  <div id="header-wrap">
  	<jsp:include page="/header/adminHeader.jsp"/>
  </div>

  <div class="page-wrap">

    <!-- 상단: 타이틀 + 필터 -->
    <div class="list-header">
      <div class="list-title">
        <span class="list-title-icon">
          <img src="${pageContext.request.contextPath}/assets/img/admin/adminSearch/유저들 아이콘.png" alt="유저 아이콘" style="width:36px;height:36px;" />
        </span>
        전체 유저
      </div>

      <div class="filter-btns">
        <button class="btn-filter" id="btnFilterType">유형</button>
        <button class="btn-filter" id="btnFilterStatus">상태</button>
      </div>
    </div>

    <!-- 유저 카드 리스트 -->
    <div class="user-list" id="userList">
    	
    </div>

  </div>

  <script>
  	  window.contextPath = "${pageContext.request.contextPath}";
  	  
  	  // 유저 아바타 (유저_아이콘.png)
	  const avatarSVG = `<img 
	  	src="${pageContext.request.contextPath}/assets/img/admin/유저_아이콘.png" 
	  	alt="유저 아이콘" 
	  	style="width:40px;height:40px;" 
	  />`;
	
	  // 상세보기 아이콘 (상세보기_아이콘.png)
	  const detailSVG = `<img 
	  	src="${pageContext.request.contextPath}/assets/img/admin/상세보기_아이콘.png" 
	  	alt="상세보기 아이콘" 
	  	style="width:36px;height:36px;" 
	  />`;
  </script>
</body>
</html>
