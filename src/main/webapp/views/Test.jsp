<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<c:forEach var="i" items="${list}">
${i.id} <br>
${i.productDesc} <br>
${i.price} <br>
${i.manufactureDate} <br>
</c:forEach>
</body>
</html>