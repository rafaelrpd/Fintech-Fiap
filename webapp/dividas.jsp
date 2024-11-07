<%@ page import="java.util.*,dao.*,model.*" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" %>
<%
    if (session == null || session.getAttribute("usuarioLogado") == null) {
        response.sendRedirect("login.jsp");
        return;
    }
    int usuarioId = (Integer) session.getAttribute("usuarioId");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Dívidas - Fintech</title>
</head>
<body>
    <h1>Dívidas</h1>
    <%
        if (request.getParameter("erro") != null) {
    %>
        <p style="color:red;">Ocorreu um erro ao processar sua solicitação. Por favor, tente novamente.</p>
    <%
        }
    %>
    <!-- Formulário para adicionar ou atualizar dívida -->
    <%
        String action = "Adicionar";
        String descricao = "";
        String valor = "";
        String taxaJuros = "";
        String mesesParaPagar = "";
        String id = request.getParameter("id");
        if (id != null) {
            DividaDAO dividaDAO = new DividaDAO();
            Divida divida = dividaDAO.getById(Integer.parseInt(id));
            if (divida != null && divida.getUsuarioId() == usuarioId) {
                action = "Atualizar";
                descricao = divida.getDescricao();
                valor = String.valueOf(divida.getValor());
                taxaJuros = String.valueOf(divida.getTaxaDeJuros());
                mesesParaPagar = String.valueOf(divida.getMesesParaPagar());
            } else {
                id = null; // Reseta o ID se a dívida não pertencer ao usuário
            }
        }
    %>
    <form action="divida" method="post">
        <input type="hidden" name="id" value="<%= id != null ? id : "" %>">
        Descrição: <input type="text" name="descricao" value="<%= descricao %>"><br>
        Valor: <input type="text" name="valor" value="<%= valor %>"><br>
        Taxa de Juros (%): <input type="text" name="taxaJuros" value="<%= taxaJuros %>"><br>
        Meses para Pagar: <input type="text" name="mesesParaPagar" value="<%= mesesParaPagar %>"><br>
        <input type="submit" name="action" value="<%= action %>">
    </form>

    <!-- Lista de dívidas -->
    <h2>Dívidas Existentes</h2>
    <%
        DividaDAO dividaDAO = new DividaDAO();
        List<Divida> dividas = dividaDAO.getByUsuarioId(usuarioId);
    %>
    <table border="1">
        <tr>
            <th>Descrição</th>
            <th>Valor</th>
            <th>Taxa de Juros</th>
            <th>Meses para Pagar</th>
            <th>Ações</th>
        </tr>
        <%
            for (Divida divida : dividas) {
        %>
        <tr>
            <td><%= divida.getDescricao() %></td>
            <td><%= divida.getValor() %></td>
            <td><%= divida.getTaxaDeJuros() %></td>
            <td><%= divida.getMesesParaPagar() %></td>
            <td>
                <a href="dividas.jsp?id=<%= divida.getId() %>">Editar</a>
                |
                <a href="divida?action=Deletar&id=<%= divida.getId() %>" onclick="return confirm('Tem certeza que deseja deletar?');">Deletar</a>
            </td>
        </tr>
        <%
            }
        %>
    </table>
    <br>
    <a href="home.jsp">Voltar para a Home</a>
</body>
</html>
