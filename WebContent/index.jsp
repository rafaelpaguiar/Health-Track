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
    <%@ include file="resources/includes/header_include.jsp" %>
</head>
<body>
    <header class="fixed-top caixa-header">
        <nav class="navbar navbar-expand-lg navbar-dark">
          <a class="titulo" href="#">Heath Track</a>
        </nav>
    </header>
    <main class="center">
        <section class="caixa-central">
            
            <form id="formulario-index" method="POST" action="login">
                <img src="resources/images/undraw_workout_gcgu.svg" class="entry-img">
                <label for="usuario" class="label-padrao">usuário</label>
                <input type="text" id="usuario" class="input-padrao" name="usuario" placeholder="usuário">
                <label for="senha" class="label-padrao">senha</label>
                <input type="password" id="senha" class="input-padrao" name="senha" placeholder="senha">
               <div style="color: red; text-align:center;"> ${erro}</div>
                <div class="btn-div">
                    <button type="submit" class="btn btn-primary btn-customized">Entrar</button>
                </div>
                
                <p class="txt-invite">
                    Não é membro? <a href="novoUsuario.jsp" class="sign-in">Cadastre-se</a>
                </p>
            </form>
        </section>
        
    </main>
    <%@ include file="resources/includes/footer_include.jsp" %>
</body>
</html>