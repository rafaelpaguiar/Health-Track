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
            <span>peso</span>
          </div>
    </header>
    <main>
        <section class="edit">
          <form id="formulario-peso" method="POST" action="peso">
          	<input type="hidden" name="idUsuario" value="${peso.idUsuario}">
          	<input type="hidden" name="act" value="edit">
          	<input type="hidden" name="idPeso" value="${peso.idPeso}">  
            <label for="dt_peso" class="label-padrao">data da pesagem</label>
            <input type="date" id="dt_peso" class="input-padrao" name="dt_peso" value="<fmt:formatDate value="${peso.dtPeso}" pattern="yyyy-MM-dd"/>">
            <label for="peso" class="label-padrao">peso</label>
            <input type="number" id="peso" class="input-padrao" name="peso" value="${peso.vlPeso}" step="0.01">
            <div class="center">
                <button type="submit" class="btn btn-success">salvar</button>
          </div>
        </form>
      </section>
    </main>
    <%@ include file="footer_include_forms.jsp"%>
</body>
</html>