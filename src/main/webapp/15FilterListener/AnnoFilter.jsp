<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>AnnoFilter.jsp</title>
</head>
<body>
	<form>
		<select name="searchField">
			<option value="title">제목</option>
			<option value="content">내용</option>
		</select>
		<input type="text" name="searchWord" value="애너테이션" />
		<input type="submit" value="검색하기" />
	</form>
</body>
</html>