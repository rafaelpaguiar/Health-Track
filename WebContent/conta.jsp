<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>     
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Health Track - Conta</title>
<%@ include file="resources/includes/header_include.jsp"%>
</head>
<body>
    <header class="fixed-top caixa-header">
        <%@ include file="resources/includes/navbar.jsp"%>
          <div class="subtitulo">
            <span>conta</span>
          </div>
    </header>
    <main>
        <section class="caixa-central-contato">
            <form id="formulario-contato" method="POST" action="usuario?act=alter&idUsuario=${usuario.idUsuario}&type=${type}">
            	<input type="hidden" value="alter" name="act">
            	<input type="hidden" value="${usuario.idUsuario}" name="idUsuario">
                 <label for="nome" class="label-padrao">nome</label>
                <input type="text" id="nome" class="input-padrao" name="nome" value="${usuario.nmUsuario}">
                <label for="dt_nasc" class="label-padrao">data de nascimento</label>
                <input type="date" id="dt_nasc" class="input-padrao" name="dt_nasc" value="<fmt:formatDate value="${usuario.dtNascimento}" pattern="yyyy-MM-dd"/>">
                <label for="altura" class="label-padrao">altura</label>
                <input type="number" id="altura" class="input-padrao" name="altura" step="0.01" value="${usuario.vlAltura}">
                <label for="email" class="label-padrao">email</label>
                <input type="email" id="email" class="input-padrao" placeholder="email@provedor.com" name="email" value="${usuario.txEmail }">
                <label for="login" class="label-padrao">login</label>
                <input type="text" id="login" class="input-padrao" name="login" value="${usuario.nmLogin}">
                <label for="senha" class="label-padrao">Atualizar senha</label>
                <input type="password" id="senha" class="input-padrao" name="senha" value="">
                <!--<label for="check-senha" class="label-padrao">confirmar senha</label>
                <input type="password" id="check_senha" class="input-padrao" name="check_senha">-->
                <label for="plano" class="label-padrao">plano</label>
                
                 <div class="row">
	                <div class="col-sm align-radius">
                <label for="gratuito">gratuito</label>
                <input type="radio" id="gratuito" class="input-padrao  align-radius" name="plano" value="gratuito" <c:if test="${usuario.vlPlano == 0}">checked</c:if>>
                </div>
                <div class="col-sm align-radius">
                <label for="premium">premium</label>
                <input type="radio" id="premium" class="input-padrao  align-radius" name="plano" value="premium" <c:if test="${usuario.vlPlano > 0}">checked</c:if>>
                </div>
                </div>
                
                
                <br>
                
                <div class="center">
                    <button type="submit" class="btn btn-primary">salvar</button>
              </div>
            </form>
        </section>
    </main>
    <%@ include file="resources/includes/footer_include.jsp"%>
</body>
</html>