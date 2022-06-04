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
		<h2>  Todos App</h2>
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
				<th> Action </th>
			</tr>
			
			<c:forEach var="tempTodo" items="${todos}"> 
			
			<c:url var="updateLink" value="/users/todos/showFormForUpdate">
				<c:param name="todoId" value="${tempTodo.id}"/>
			</c:url>
			
			<c:url var="deleteLink" value="/users/todos/delete">
				<c:param name="todoId" value="${tempTodo.id}"/>
			</c:url>
			
			<tr>
				<td>${tempTodo.description}</td>
				<td><fmt:formatDate type="date" value="${tempTodo.targetDate}"/></td>
				<td>${tempTodo.isDone}</td>
				
				<td>
					<a href="${updateLink}">Update</a>
					
					|
					
					<a href="${deleteLink}" onclick="if (!('are you sure you want to delete this todo?'))) return false">Delete</a>
				</td>
			</tr>
			</c:forEach>
		</table>
		
		
	</div>
</div>
</body>
</html>