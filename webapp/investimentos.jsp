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
    <title>Investimentos - Fintech</title>
</head>
<body>
    <h1>Investimentos</h1>
    <%
        if (request.getParameter("erro") != null) {
    %>
        <p style="color:red;">Ocorreu um erro ao processar sua solicitação. Por favor, tente novamente.</p>
    <%
        }
    %>
    <!-- Formulário para adicionar ou atualizar investimento -->
    <%
        String action = "Adicionar";
        String descricao = "";
        String valor = "";
        String rendimentoMensal = "";
        String id = request.getParameter("id");
        if (id != null) {
            InvestimentoDAO investimentoDAO = new InvestimentoDAO();
            Investimento investimento = investimentoDAO.getById(Integer.parseInt(id));
            if (investimento != null && investimento.getUsuarioId() == usuarioId) {
                action = "Atualizar";
                descricao = investimento.getDescricao();
                valor = String.valueOf(investimento.getValor());
                rendimentoMensal = String.valueOf(investimento.getRendimentoMensal());
            } else {
                id = null; // Reseta o ID se o investimento não pertencer ao usuário
            }
        }
    %>
    <form action="investimento" method="post">
        <input type="hidden" name="id" value="<%= id != null ? id : "" %>">
        Descrição: <input type="text" name="descricao" value="<%= descricao %>"><br>
        Valor: <input type="text" name="valor" value="<%= valor %>"><br>
        Rendimento Mensal (%): <input type="text" name="rendimentoMensal" value="<%= rendimentoMensal %>"><br>
        <input type="submit" name="action" value="<%= action %>">
    </form>

    <!-- Lista de investimentos -->
    <h2>Investimentos Existentes</h2>
    <%
        InvestimentoDAO investimentoDAO = new InvestimentoDAO();
        List<Investimento> investimentos = investimentoDAO.getByUsuarioId(usuarioId);
    %>
    <table border="1">
        <tr>
            <th>Descrição</th>
            <th>Valor</th>
            <th>Rendimento Mensal</th>
            <th>Ações</th>
        </tr>
        <%
            for (Investimento investimento : investimentos) {
        %>
        <tr>
            <td><%= investimento.getDescricao() %></td>
            <td><%= investimento.getValor() %></td>
            <td><%= investimento.getRendimentoMensal() %></td>
            <td>
                <a href="investimentos.jsp?id=<%= investimento.getId() %>">Editar</a>
                |
                <a href="investimento?action=Deletar&id=<%= investimento.getId() %>" onclick="return confirm('Tem certeza que deseja deletar?');">Deletar</a>
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
