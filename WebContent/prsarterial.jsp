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
            <span>Seu histórico de pressões arteriais ${nmUsuario}</span>
          </div>
    </header>
    <main>
        <section class="edit">
          <table class="table table-hover">
            <thead>
              <tr>
                <th scope="col">Data</th>
                <th scope="col">Pressão Sistólica<br>(Número mais alto)</th>
                <th scope="col">Pressão Diastólica<br>(Número mais baixo)</th>
                <th scope="col"></th>
              </tr>
            </thead>
            <tbody>
              <c:forEach items="${prsArteriais}" var="p">
						<tr>
							<td><fmt:formatDate value="${p.dtPrsArterial}" pattern="dd/MM/yyyy" /></td>
							<td>${p.vlPrsArterialMax}</td>
							<td>${p.vlPrsArterialMin}</td>
							<td><c:url value="prsarterial" var="edit">
									<c:param name="act" value="edit" />
									<c:param name="idPrsArterial" value="${p.idPrsArterial}" />
								</c:url> <a href="${edit}" class="badge badge-primary form_edit">edit</a>
								- <c:url value="prsarterial" var="delete">
									<c:param name="act" value="delete" />
									<c:param name="idPrsArterial" value="${p.idPrsArterial}" />
									<c:param name="idUsuario" value="${p.idUsuario}" />
								</c:url> <a href="${delete}" class="badge badge-danger form_delete">delete</a></td>
						</tr>
					</c:forEach>
            </tbody>
          </table>
			<div class="container">
				<div class="row center btn-space">
					<form method="POST" action="forms/add-prsarterial.jsp">
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