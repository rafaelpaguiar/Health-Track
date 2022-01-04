<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Health Track</title>
    <%@ include file="header_include_forms.jsp"%>
</head>
<body>
    <header class="fixed-top caixa-header">
        <%@ include file="navbar_forms.jsp"%>
          <div class="subtitulo">
            <span>pressão arterial</span>
          </div>
    </header>
    <main>
        <section class="edit">
          <form id="formulario-prsarterial" method="POST" action="prsarterial">
          <input type="hidden" name="idUsuario" value="${prsArterial.idUsuario}">
          <input type="hidden" name="act" value="edit">
          	<input type="hidden" name="idPrsArterial" value="${prsArterial.idPrsArterial}">
            <label for="dt_prsarterial" class="label-padrao">data da aferição</label>
            <input type="date" id="dt_prsarterial" class="input-padrao" name="dt_prsarterial" value="<fmt:formatDate value="${prsArterial.dtPrsArterial}" pattern="yyyy-MM-dd"/>">
            <label for="prsarterial_alta" class="label-padrao">Pressão Sistólica<br>(Número mais alto)</label>
            <input type="text" id="prsarterial_alta" class="input-padrao"  name="prsarterial_alta" value="${prsArterial.vlPrsArterialMax}">
            <label for="prsarterial_baixa" class="label-padrao">Pressão Diastólica<br>(Número mais baixo)</label>
            <input type="number" id="prsarterial_baixa" class="input-padrao"  name="prsarterial_baixa"  value="${prsArterial.vlPrsArterialMin}">
            <div class="center">
                <button type="submit" class="btn btn-success">salvar</button>
          </div>
        </form>
      </section>
    </main>
    <%@ include file="footer_include_forms.jsp"%>
</body>
</html>