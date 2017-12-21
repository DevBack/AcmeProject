<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Departamentos</title>
</head>
<body>

	<jsp:include page="/resourses/templates/navbar.jsp"></jsp:include>

	<div class="container">
		<div class="page-header">
			<h1>Departamentos</h1>
			<br />
			<hr>
		</div>
	
		<table class="table">
			<thead>
				<tr>
					<th>Código</th>
					<th>Nome</th>
					<th>Ação</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${departamentos}" var="departamento">
					<tr>
						<td>${departamento.id}</td>
						<td>${departamento.nome}</td>
						<td>...</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

	</div>

	<jsp:include page="/resourses/templates/footer.jsp"></jsp:include>

</body>
</html>