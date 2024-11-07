<!DOCTYPE html>
<html>
<head>
    <title>Login - Fintech</title>
</head>
<body>
    <h1>Login</h1>
    <form action="login" method="post">
        Email: <input type="text" name="email"><br>
        Senha: <input type="password" name="senha"><br>
        <input type="submit" value="Entrar">
    </form>
    <%
        if (request.getParameter("erro") != null) {
    %>
        <p style="color:red;">Credenciais inválidas ou ocorreu um erro. Por favor, tente novamente.</p>
    <%
        }
    %>
</body>
</html>
