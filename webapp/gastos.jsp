<%@ page import="java.util.*,dao.*,model.*" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<%
    HttpSession session = request.getSession(false);
    if (session == null || session.getAttribute("usuarioLogado") == null) {
        response.sendRedirect("login.jsp");
        return;
    }
    int usuarioId = (Integer) session.getAttribute("usuarioId");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Gastos - Fintech</title>
</head>
<body>
    <h1>Gastos</h1>
    <%
        if (request.getParameter("erro") != null) {
    %>
        <p style="color:red;">Ocorreu um erro ao processar sua solicitação. Por favor, tente novamente.</p>
    <%
        }
    %>
    <!-- Formulário para adicionar ou atualizar gasto -->
    <%
        String action = "Adicionar";
        String descricao = "";
        String valor = "";
        String id = request.getParameter("id");
        if (id != null) {
            GastoDAO gastoDAO = new GastoDAO();
            Gasto gasto = gastoDAO.getById(Integer.parseInt(id));
            if (gasto != null && gasto.getUsuarioId() == usuarioId) {
                action = "Atualizar";
                descricao = gasto.getDescricao();
                valor = String.valueOf(gasto.getValor());
            } else {
                id = null; // Reseta o ID se o gasto não pertencer ao usuário
            }
        }
    %>
    <form action="gasto" method="post">
        <input type="hidden" name="id" value="<%= id != null ? id : "" %>">
        Descrição: <input type="text" name="descricao" value="<%= descricao %>"><br>
        Valor: <input type="text" name="valor" value="<%= valor %>"><br>
        <input type="submit" name="action" value="<%= action %>">
    </form>

    <!-- Lista de gastos -->
    <h2>Gastos Existentes</h2>
    <%
        GastoDAO gastoDAO = new GastoDAO();
        List<Gasto> gastos = gastoDAO.getByUsuarioId(usuarioId);
    %>
    <table border="1">
        <tr>
            <th>Descrição</th>
            <th>Valor</th>
            <th>Ações</th>
        </tr>
        <%
            for (Gasto gasto : gastos) {
        %>
        <tr>
            <td><%= gasto.getDescricao() %></td>
            <td><%= gasto.getValor() %></td>
            <td>
                <a href="gastos.jsp?id=<%= gasto.getId() %>">Editar</a>
                |
                <a href="gasto?action=Deletar&id=<%= gasto.getId() %>" onclick="return confirm('Tem certeza que deseja deletar?');">Deletar</a>
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
