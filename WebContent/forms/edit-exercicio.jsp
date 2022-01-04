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
            <span>exercícios</span>
          </div>
    </header>
    <main>
        <section class="edit">
          <form id="formulario-exercicio" method="POST" action="exercicio">
          <input type="hidden" name="idUsuario" value="${exercicio.idUsuario}">
          	<input type="hidden" name="act" value="edit">
          	<input type="hidden" name="idExercicio" value="${exercicio.idExercicio}">  
            <label for="dt_exercicio" class="label-padrao">data do exercício</label>
            <input type="date" id="dt_exercicio" class="input-padrao" name="dt_exercicio" value="<fmt:formatDate value="${exercicio.dtExercicio}" pattern="yyyy-MM-dd"/>">
            <label for="exercicio" class="label-padrao">exercício</label>
            <input type="text" name="exercicio" id="exercicio" class="input-padrao" value="${exercicio.nmExercicio}">
            <label for="duracao" class="label-padrao">duração (em minutos)</label>
            <input type="number" id="duracao" class="input-padrao"  name="duracao" value="${exercicio.vlDuracao}">
            <label for="kcalGasto" class="label-padrao">kcal gasto</label>
            <input type="number" id="kcalGasto" class="input-padrao"  name="kcalGasto"  value="${exercicio.gastoCalorico}">
            <div class="center">
                <button type="submit" class="btn btn-success">salvar</button>
          </div>
        </form>
      </section>
    </main>
    <%@ include file="footer_include_forms.jsp"%>
</body>
</html>