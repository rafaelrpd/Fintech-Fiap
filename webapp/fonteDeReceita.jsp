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
    <title>Fontes de Receita - Fintech</title>
</head>
<body>
    <h1>Fontes de Receita</h1>
    <%
        if (request.getParameter("erro") != null) {
    %>
        <p style="color:red;">Ocorreu um erro ao processar sua solicitação. Por favor, tente novamente.</p>
    <%
        }
    %>
    <!-- Formulário para adicionar ou atualizar receita -->
    <%
        String action = "Adicionar";
        String descricao = "";
        String valor = "";
        String id = request.getParameter("id");
        if (id != null) {
            FonteDeReceitaDAO receitaDAO = new FonteDeReceitaDAO();
            FonteDeReceita receita = receitaDAO.getById(Integer.parseInt(id));
            if (receita != null && receita.getUsuarioId() == usuarioId) {
                action = "Atualizar";
                descricao = receita.getDescricao();
                valor = String.valueOf(receita.getValor());
            } else {
                id = null; // Reseta o ID se a receita não pertencer ao usuário
            }
        }
    %>
    <form action="fonteDeReceita" method="post">
        <input type="hidden" name="id" value="<%= id != null ? id : "" %>">
        Descrição: <input type="text" name="descricao" value="<%= descricao %>"><br>
        Valor: <input type="text" name="valor" value="<%= valor %>"><br>
        <input type="submit" name="action" value="<%= action %>">
    </form>

    <!-- Lista de receitas -->
    <h2>Receitas Existentes</h2>
    <%
        FonteDeReceitaDAO receitaDAO = new FonteDeReceitaDAO();
        List<FonteDeReceita> receitas = receitaDAO.getByUsuarioId(usuarioId);
    %>
    <table border="1">
        <tr>
            <th>Descrição</th>
            <th>Valor</th>
            <th>Ações</th>
        </tr>
        <%
            for (FonteDeReceita receita : receitas) {
        %>
        <tr>
            <td><%= receita.getDescricao() %></td>
            <td><%= receita.getValor() %></td>
            <td>
                <a href="fonteDeReceita.jsp?id=<%= receita.getId() %>">Editar</a>
                |
                <a href="fonteDeReceita?action=Deletar&id=<%= receita.getId() %>" onclick="return confirm('Tem certeza que deseja deletar?');">Deletar</a>
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
