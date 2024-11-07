<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" %>
<%
    if (session == null || session.getAttribute("usuarioLogado") == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Home - Fintech</title>
</head>
<body>
    <h1>Bem-vindo, <%= session.getAttribute("usuarioNome") %></h1>
    <!-- Links para funcionalidades -->
    <a href="fonteDeReceita.jsp">Gerenciar Fontes de Receita</a><br>
    <a href="gastos.jsp">Gerenciar Gastos</a><br>
    <a href="dividas.jsp">Gerenciar Dívidas</a><br>
    <a href="investimentos.jsp">Gerenciar Investimentos</a><br>
    <a href="metasFinanceiras.jsp">Gerenciar Metas Financeiras</a><br>
    <!-- Outros links -->
    <a href="logout">Sair</a>
</body>
</html>
