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
            <span>alimentação</span>
          </div>
    </header>
    <main>
        <section class="edit">
          <form id="formulario-alimentacao" method="POST" action="../refeicao?act=add">
          <input type="hidden" name="idUsuario" value="${idUsuario}">
            <label for="dt_alimentacao" class="label-padrao">data da refeição</label>
            <input type="date" id="dt_refeicao" class="input-padrao" name="dt_refeicao">
            <label for="refeicao" class="label-padrao">refeição</label>
            <input type="text" id="refeicao" class="input-padrao" name="refeicao">
            <label for="kcalIngerido" class="label-padrao">kcal da refeição</label>
            <input type="number" id="kcalIngerido" class="input-padrao" name="kcalIngerido">
            <div class="center">
                <button type="submit" class="btn btn-success">salvar</button>
          </div>
        </form>
      </section>
    </main>
    <%@ include file="../resources/includes/footer_include_forms.jsp"%>
</body>
</html>