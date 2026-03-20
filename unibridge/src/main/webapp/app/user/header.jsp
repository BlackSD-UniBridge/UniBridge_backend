<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%--
    ※ 사용 방법: 각 JSP의 <body> 최상단에 include
       <%@ include file="/app/user/header.jsp" %>

    ※ Servlet+DAO 연동 후 로그인 세션 처리 방법
       - LoginServlet 또는 UserController 에서:
         session.setAttribute("loginUser", userDto);
         → UserDTO 필드: String userName, String role ("mentor" | "mentee")
       - 로그아웃 시:
         session.invalidate();
         response.sendRedirect(request.getContextPath() + "/user/login");
--%>
<link
	href="https://fonts.googleapis.com/css2?family=Playfair+Display:wght@600&family=Noto+Sans+KR:wght@300;400;500;700&display=swap"
	rel="stylesheet" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/user/header.css?v=1.1" />

<header class="headerWrap">
	<div class="headerInner">

		<!-- 로고 -->
		<a href="${pageContext.request.contextPath}/index.main"
			class="headerLogo"> <img
			src="${pageContext.request.contextPath}/assets/img/UniBridge.png"
			alt="UniBridge" />
		</a>

		<!-- 공통 네비게이션 -->
		<nav class="headerNav">
			<a
				href="${pageContext.request.contextPath}/mentor/mentorSearchOk.sch">멘토
				검색</a> 
			<a href="${pageContext.request.contextPath}/mentee/menteeBoard/MenteeBoardList.meb">게시판</a>
			<a href="${pageContext.request.contextPath}/mvc/auth/report.rep">학습보고서</a>
			<a href="${pageContext.request.contextPath}/common/noticeBoardList.ntb">공지사항</a>
		</nav>

		<!-- 인증 영역 -->
		<c:choose>
			<c:when test="${empty sessionScope.loginUser}">
				<%-- 비로그인 상태 --%>
				<div class="headerAuthGroup">
					<a href="${pageContext.request.contextPath}/signup.mem"
						class="headerBtnText">회원가입</a>
					<div class="headerDivider"></div>
					<a href="${pageContext.request.contextPath}/signin.mem"
						class="headerBtnSignIn">로그인</a>
				</div>
			</c:when>
			<c:otherwise>
				<%-- 로그인 상태 (Servlet에서 session.setAttribute("loginUser", userDto) 후 동작) --%>
				<div class="headerAuthGroup">
					<div class="userInfoWrap">
						<span class="userName">${sessionScope.loginUser.memberName}</span>
						<span class="userRoleDivider">/</span>
						<c:choose>
							<c:when
								test="${fn:toLowerCase(sessionScope.loginUser.memberType) eq 'mentor'}">
								<span class="userRoleBadge mentoRoleBadge">멘토</span>
							</c:when>
							<c:when
								test="${fn:toLowerCase(sessionScope.loginUser.memberType) eq 'mentee'}">
								<span class="userRoleBadge mentiRoleBadge">멘티</span>
							</c:when>
							<c:otherwise>
								<span class="userRoleBadge pending">미정</span>
							</c:otherwise>
						</c:choose>
					</div>
					<div class="headerDivider"></div>
					<c:choose>
						<c:when
							test="${fn:toLowerCase(sessionScope.loginUser.memberType) eq 'mentor'}">
							<a
								href="${pageContext.request.contextPath}/mvc/auth/mentor/myPage.my"
								class="headerBtnText">마이페이지</a>
						</c:when>
						<c:when
							test="${fn:toLowerCase(sessionScope.loginUser.memberType) eq 'mentee'}">
							<a
								href="${pageContext.request.contextPath}/mvc/auth/mentee/myPage.my"
								class="headerBtnText">마이페이지</a>
						</c:when>
						<c:otherwise>
							<a
								href="${pageContext.request.contextPath}/mvc/auth/undecided/myPage.my"
								class="headerBtnText">마이페이지</a>
						</c:otherwise>
					</c:choose>
					<div class="headerDivider"></div>
					<a href="${pageContext.request.contextPath}/signout.mem"
						class="headerBtnText logout">로그아웃</a>
				</div>
			</c:otherwise>
		</c:choose>
	</div>
</header>
