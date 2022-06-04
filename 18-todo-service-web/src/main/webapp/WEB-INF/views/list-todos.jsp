<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>

<html>
<head>
	<title> List Todos </title>
	
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
	
</head>


<body>

<div id="wrapper">
	<div id="header">
		<h2> List Todos </h2>
	</div>
</div>


<div id="container">

	<div id="content">
	
		<input type="button" value="Add Todo" onclick="window.location.href='/users/todos/showFormForAdd'; return false;" class="add-button"/>
		
		<p></p>
		
		<table>
			<tr>
				<th>Description</th>
				<th>Date</th>
				<th> Is Done ? </th>
			</tr>
			
			<c:forEach var="tempTodo" items="${todos}"> 
			
			<tr>
				<td>${tempTodo.description}</td>
				<td><fmt:formatDate type="date" value="${tempTodo.targetDate}"/></td>
				<td>${tempTodo.isDone}</td>
			</tr>
			</c:forEach>
		</table>
		
		
	</div>
</div>
</body>
</html>