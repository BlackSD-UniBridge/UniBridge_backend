<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
  <title>게시판 관리 - 게시글 작성</title>

  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/admin/adminBoard/boardWrite.css" />

  <script defer src="${pageContext.request.contextPath}/assets/js/admin/adminBoard/boardWrite.js"></script>
  </head>
<body>

  <div id="header-wrap"></div>

  <div class="form-wrap">
    <h1 class="page-title">게시판 관리</h1>
  
    <h2 class="form-section-title">게시글 작성</h2>
    
	<form id="writeForm" method ="post" action="${pageContext.request.contextPath}/mentorBoardWriteOk.admin">
      
      <!-- 제목 입력 -->
    <div class="form-title-row" style="display:block; margin-bottom:16px;">
      <input
      type="text"
      name="boardTitle"      
      id="inputTitle"
      class="form-title-input"
      style="width:100%;"
      placeholder="제목을 입력해주세요*(최대 50자)"
      maxlength="50"/>
    </div>
      
    <!-- 내용 입력 -->
    <textarea 
    id="inputContent" 
    class="form-content-area" 
    name="boardContent"></textarea>
  <!-- 버튼 -->
    <div class="form-actions">
     <button type="submit" class="btn btn-primary" id="btnSubmit">등록</button>
    </div>

   </form>
  </div> 


<script>
  fetch("${pageContext.request.contextPath}/header/adminHeader.jsp")
    .then(res => res.text())
    .then(html => {
      document.getElementById("header-wrap").innerHTML = html;
      const s = document.createElement("script");
      s.src = "${pageContext.request.contextPath}/assets/js/adminHeader.js";
      document.body.appendChild(s);
    });
</script>
</body>
</html>