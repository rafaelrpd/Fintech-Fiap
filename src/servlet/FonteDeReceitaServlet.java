package servlet;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import dao.FonteDeReceitaDAO;
import model.FonteDeReceita;

@WebServlet("/fonteDeReceita")
public class FonteDeReceitaServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("usuarioId") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        int usuarioId = (Integer) session.getAttribute("usuarioId");
        String action = request.getParameter("action");
        FonteDeReceitaDAO receitaDAO = new FonteDeReceitaDAO();

        try {
            if ("Adicionar".equals(action)) {
                String descricao = request.getParameter("descricao");
                String valorStr = request.getParameter("valor").trim();

                // Verifica se a descrição e o valor estão preenchidos
                if (descricao == null || descricao.isEmpty() || valorStr.isEmpty()) {
                    throw new IllegalArgumentException("Descrição e valor são obrigatórios.");
                }

                // Remover espaços extras e garantir que o valor esteja no formato correto
                valorStr = valorStr.replaceAll(",", ".").replaceAll("[^\\d.]", "");

                // Converter o valor para double
                double valor = Double.parseDouble(valorStr);

                // Criar e inserir a nova receita
                FonteDeReceita receita = new FonteDeReceita(descricao, valor, usuarioId);
                receitaDAO.insert(receita);

            } else if ("Atualizar".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                String descricao = request.getParameter("descricao");
                String valorStr = request.getParameter("valor").trim();

                valorStr = valorStr.replaceAll(",", ".").replaceAll("[^\\d.]", "");
                double valor = Double.parseDouble(valorStr);

                FonteDeReceita receita = new FonteDeReceita(id, descricao, valor, usuarioId);
                receitaDAO.update(receita);
            }

            response.sendRedirect("fonteDeReceita.jsp");
        } catch (NumberFormatException e) {
            System.out.println("Erro ao converter valor para double: " + e.getMessage());
            response.sendRedirect("fonteDeReceita.jsp?erro=true");
        } catch (Exception e) {
            System.out.println("Erro ao processar a solicitação.");
            e.printStackTrace();
            response.sendRedirect("fonteDeReceita.jsp?erro=true");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("usuarioId") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        int usuarioId = (Integer) session.getAttribute("usuarioId");
        String action = request.getParameter("action");
        FonteDeReceitaDAO receitaDAO = new FonteDeReceitaDAO();

        try {
            if ("Deletar".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                receitaDAO.delete(id, usuarioId);
            }
            response.sendRedirect("fonteDeReceita.jsp");
        } catch (Exception e) {
            response.sendRedirect("fonteDeReceita.jsp?erro=true");
        }
    }
}
