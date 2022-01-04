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
    <%@ include file="../resources/includes/header_include_forms.jsp"%>
</head>
<body>
    <header class="fixed-top caixa-header">
        <%@ include file="../resources/includes/navbar_forms.jsp"%>
          <div class="subtitulo">
            <span>press�o arterial</span>
          </div>
    </header>
    <main>
        <section class="edit">
          <form id="formulario-prsarterial" method="POST" action="../prsarterial?act=add">
          	<input type="hidden" name="idUsuario" value="${idUsuario}">
            <label for="dt_prsarterial" class="label-padrao">data da aferi��o</label>
            <input type="date" id="dt_prsarterial" class="input-padrao" name="dt_prsarterial">
            <label for="prsarterial_alta" class="label-padrao">Press�o Sist�lica<br>(N�mero mais alto)</label>
            <input type="text" id="prsarterial_alta" class="input-padrao"  name="prsarterial_alta">
            <label for="prsarterial_baixa" class="label-padrao">Press�o Diast�lica<br>(N�mero mais baixo)</label>
            <input type="number" id="prsarterial_baixa" class="input-padrao"  name="prsarterial_baixa">
            <div class="center">
                <button type="submit" class="btn btn-success">salvar</button>
          </div>
        </form>
      </section>
    </main>
    <%@ include file="../resources/includes/footer_include_forms.jsp"%>
</body>
</html>