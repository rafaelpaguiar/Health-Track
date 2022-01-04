<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Health Track</title>
<%@ include file="resources/includes/header_include.jsp"%>
</head>
<body>
	<header class="fixed-top caixa-header">
		<%@ include file="resources/includes/navbar.jsp"%>
		<div class="subtitulo">
			<span>Seu histórico de pesos ${nmUsuario}</span>
		</div>
	</header>
	<main>
		<section class="edit">
			<table class="table table-hover">
				<thead>
					<tr>
						<th scope="col">Data</th>
						<th scope="col">Peso</th>
						<th scope="col"></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${pesos}" var="p">
						<tr>
							<td><fmt:formatDate value="${p.dtPeso}" pattern="dd/MM/yyyy" /></td>
							<td>${p.vlPeso}Kg</td>
							<td><c:url value="peso" var="edit">
									<c:param name="act" value="edit" />
									<c:param name="idPeso" value="${p.idPeso}" />
								</c:url> <a href="${edit}" class="badge badge-primary form_edit">edit</a>
								- <c:url value="peso" var="delete">
									<c:param name="act" value="delete" />
									<c:param name="idPeso" value="${p.idPeso}" />
									<c:param name="idUsuario" value="${p.idUsuario}" />
								</c:url> <a href="${delete}" class="badge badge-danger form_delete">delete</a></td>
						</tr>
					</c:forEach>
					</tbody>
			</table>
			<div class="container">
				<div class="row center btn-space">
					<form method="POST" action="forms/add-peso.jsp">
						<button type="submit" class="btn btn-primary">adicionar</button>
					</form>
					<button class="btn btn-danger" onclick="location.href='dashboard';">voltar</button>

				</div>
			</div>
		</section>
	</main>
	<%@ include file="resources/includes/footer_include.jsp"%>
</body>
</html>