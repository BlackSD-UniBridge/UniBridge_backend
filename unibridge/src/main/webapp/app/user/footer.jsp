<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
    ※ 사용 방법: 각 JSP의 </body> 바로 위에 include
       <%@ include file="/app/user/footer.jsp" %>
--%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/user/footer.css" />

<footer class="footerWrap">
    <div class="footerInner">
        <a href="${pageContext.request.contextPath}/index.main" class="footerLogo">
            <img src="${pageContext.request.contextPath}/assets/img/UniBridge.png" alt="UniBridge" />
        </a>

        <div class="footerDivider"></div>

        <div class="footerInfoWrap">
            <p class="footerInfoItem"><span>전화번호 : </span>010-1234-5678</p>
            <p class="footerInfoItem"><span>사업번호 : </span>111-22-3333</p>
            <p class="footerInfoItem"><span>이메일 : </span>test@naver.com</p>
            <p class="footerInfoItem"><span>주소 : </span>서울특별시 강남구 테헤란로 146 번악빌딩 3,4층</p>
            <p class="footerInfoItem"><span>대표자 : </span>김재형</p>
        </div>
    </div>
</footer>
