<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"  import="br.com.fiap.htrack.bean.UsuarioVip"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="navbar navbar-expand-lg navbar-light">
	<a class="titulo" href="dashboard?${idUsuario}">Heath Track</a>
	<button class="navbar-toggler" type="button" data-toggle="collapse"
		data-target="#navbarNav" aria-controls="navbarNav"
		aria-expanded="false" aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>
	<div class="collapse navbar-collapse justify-content-end"
		id="navbarNav">
		<ul class="navbar-nav">
			<li class="nav-item"><a class="nav-link small-text"
				href="peso?act=list&idUsuario=${idUsuario}">pesos</a></li>
			<li class="nav-item"><a class="nav-link small-text"
				href="exercicio?act=list&idUsuario=${idUsuario}">exercícios</a></li>
			<li class="nav-item"><a class="nav-link small-text"
				href="refeicao?act=list&idUsuario=${idUsuario}">alimentação</a></li>
			<li class="nav-item"><a class="nav-link small-text"
				href="prsarterial?act=list&idUsuario=${idUsuario}">pressão arterial</a></li>
			<li class="nav-item"><a class="nav-link small-text"
				href="usuario?act=alter&idUsuario=${idUsuario}">conta</a></li>
			<li class="nav-item"><a class="nav-link small-text"
				href="logout">logout</a></li>		
		</ul>
	</div>
</nav>