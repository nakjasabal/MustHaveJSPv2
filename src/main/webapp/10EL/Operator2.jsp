<%@ page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
// 예시에서 사용할 변수 선언
pageContext.setAttribute("num1", 9);
pageContext.setAttribute("num2", "10");

pageContext.setAttribute("nullStr", null);
pageContext.setAttribute("emptyStr", "");
pageContext.setAttribute("lengthZero", new Integer[0]);
pageContext.setAttribute("sizeZero", new ArrayList<Object>());
%>    
<html>
<meta charset="UTF-8">
<head><title>표현 언어(EL) - 연산자</title></head>
<body>
    <h3>empty 연산자</h3>   
    empty nullStr : ${ empty nullStr } <br />
    empty emptyStr : ${ empty emptyStr } <br />
    empty lengthZero : ${ empty lengthZero } <br />
    empty sizeZero : ${ empty sizeZero }
    
    <h3>삼항 연산자</h3>
    num1 gt num2 ? "참" : "거짓" => ${ num1 gt num2 ? "num1이 크다" : "num2가 크다" }
    
    <h3>null 연산</h3>
    <!-- 이클립스는 null과의 연산을 에러로 표시하므로 주석으로 처리했습니다. 
    실행에는 아무런 문제가 없음을 알려드립니다. 실습시 주석을 제거해주세요.-->
    <%-- 
    null + 10 : ${ null + 10 } <br />
    nullStr + 10 : ${ nullStr + 10 } <br />
    param.noVar > 10 : ${ param.noVar > 10 } 
    --%>
</body>
</html>