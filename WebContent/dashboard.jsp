<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Health Track - Dashboard</title>
<%@ include file="resources/includes/header_include.jsp"%>
</head>
<body>
	<header class="fixed-top caixa-header">
		<%@ include file="resources/includes/navbar.jsp"%>
		<div class="subtitulo">
			<span>dashboard - Olá, ${nmUsuario} </span>
		</div>
	</header>
	<main class="deck">
		<br> <br> <br> <br>
		<section class="cards">
			<div class="box-black">Seu peso atual</div>
			<div class="box-pink">${ultimoPeso.vlPeso} Kg</div>
		</section>
		<section class="cards">
			<div class="box-black">Seu último exercício</div>
			<div class="box-pink">
				<span class="small-text">${ultimoExercicio.nmExercicio}<br><fmt:formatDate value="${ultimoExercicio.dtExercicio}" pattern="dd/MM/yyyy"/>
				</span>
			</div>
		</section>
		<section class="cards">
			<div class="box-black">Sua útlima refeição</div>
			<div class="box-pink">
				<span class="small-text">${ultimoRefeicao.vlCaloriasConsumidas} KCal<br><fmt:formatDate value="${ultimoRefeicao.dtRefeicao}" pattern="dd/MM/yyyy"/>
				</span>
			</div>
		</section>
		<section class="cards">
			<div class="box-black">
				<span class="small-text">Sua útlima prs. arterial</span>
			</div>
			<div class="box-pink">
				<span class="small-text">${ultimoPrsArterial.vlPrsArterialMax}/${ultimoPrsArterial.vlPrsArterialMin}<br><fmt:formatDate value="${ultimoPrsArterial.dtPrsArterial}" pattern="dd/MM/yyyy"/>
				</span>
			</div>
		</section>
	</main>
	<%@ include file="resources/includes/footer_include.jsp"%>
</body>
</html>