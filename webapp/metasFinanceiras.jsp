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
    <title>Metas Financeiras - Fintech</title>
</head>
<body>
    <h1>Metas Financeiras</h1>
    <%
        if (request.getParameter("erro") != null) {
    %>
        <p style="color:red;">Ocorreu um erro ao processar sua solicitação. Por favor, tente novamente.</p>
    <%
        }
    %>
    <!-- Formulário para adicionar ou atualizar meta financeira -->
    <%
        String action = "Adicionar";
        String descricao = "";
        String valorObjetivo = "";
        String valorAtual = "";
        String id = request.getParameter("id");
        if (id != null) {
            MetaFinanceiraDAO metaDAO = new MetaFinanceiraDAO();
            MetaFinanceira meta = metaDAO.getById(Integer.parseInt(id));
            if (meta != null && meta.getUsuarioId() == usuarioId) {
                action = "Atualizar";
                descricao = meta.getDescricao();
                valorObjetivo = String.valueOf(meta.getValorObjetivo());
                valorAtual = String.valueOf(meta.getValorAtual());
            } else {
                id = null; // Reseta o ID se a meta não pertencer ao usuário
            }
        }
    %>
    <form action="metaFinanceira" method="post">
        <input type="hidden" name="id" value="<%= id != null ? id : "" %>">
        Descrição: <input type="text" name="descricao" value="<%= descricao %>"><br>
        Valor Objetivo: <input type="text" name="valorObjetivo" value="<%= valorObjetivo %>"><br>
        Valor Atual: <input type="text" name="valorAtual" value="<%= valorAtual %>"><br>
        <input type="submit" name="action" value="<%= action %>">
    </form>

    <!-- Lista de metas financeiras -->
    <h2>Metas Financeiras Existentes</h2>
    <%
        MetaFinanceiraDAO metaDAO = new MetaFinanceiraDAO();
        List<MetaFinanceira> metas = metaDAO.getByUsuarioId(usuarioId);
    %>
    <table border="1">
        <tr>
            <th>Descrição</th>
            <th>Valor Objetivo</th>
            <th>Valor Atual</th>
            <th>Progresso (%)</th>
            <th>Ações</th>
        </tr>
        <%
            for (MetaFinanceira meta : metas) {
                double progresso = (meta.getValorAtual() / meta.getValorObjetivo()) * 100;
        %>
        <tr>
            <td><%= meta.getDescricao() %></td>
            <td><%= meta.getValorObjetivo() %></td>
            <td><%= meta.getValorAtual() %></td>
            <td><%= String.format("%.2f", progresso) %>%</td>
            <td>
                <a href="metasFinanceiras.jsp?id=<%= meta.getId() %>">Editar</a>
                |
                <a href="metaFinanceira?action=Deletar&id=<%= meta.getId() %>" onclick="return confirm('Tem certeza que deseja deletar?');">Deletar</a>
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
