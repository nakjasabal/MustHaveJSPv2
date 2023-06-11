<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>     
<html>
<head><title>JSTL - redirect</title></head>
<body>
    <c:set var="requestVar" value="MustHave" scope="request" />
    <c:redirect url="/11JSTL/inc/OtherPage.jsp">
        <c:param name="user_param1" value="출판사" />
        <c:param name="user_param2" value="골든래빗" />
    </c:redirect>
</body>
</html>