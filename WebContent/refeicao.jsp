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
			<span>Seu histórico alimentar ${nmUsuario}</span>
		</div>
	</header>
	<main>
		<section class="edit">
			<table class="table table-hover">
				<thead>
					<tr>
						<th scope="col">Data</th>
						<th scope="col">Alimentos</th>
						<th scope="col">KCal ingeridos</th>
						<th scope="col"></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${refeicoes}" var="r">
						<tr>
							<td><fmt:formatDate value="${r.dtRefeicao}"
									pattern="dd/MM/yyyy" /></td>
							<td>${r.dsRefeicao}</td>
							<td>${r.vlCaloriasConsumidas}</td>
							<td><c:url value="refeicao" var="edit">
									<c:param name="act" value="edit" />
									<c:param name="idRefeicao" value="${r.idRefeicao}" />
								</c:url> <a href="${edit}" class="badge badge-primary form_edit">edit</a>
								- <c:url value="refeicao" var="delete">
									<c:param name="act" value="delete" />
									<c:param name="idRefeicao" value="${r.idRefeicao}" />
									<c:param name="idUsuario" value="${r.idUsuario}" />
								</c:url> <a href="${delete}" class="badge badge-danger form_delete">delete</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div class="container">
				<div class="row center btn-space">
					<form method="POST" action="forms/add-refeicao.jsp">
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