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

        try {
            // Verifica se o usuário está autenticado
            HttpSession session = request.getSession(false);
            if (session == null || session.getAttribute("usuarioId") == null) {
                response.sendRedirect("login.jsp");
                return;
            }
            int usuarioId = (Integer) session.getAttribute("usuarioId");

            String action = request.getParameter("action");
            FonteDeReceitaDAO receitaDAO = new FonteDeReceitaDAO();

            if ("Adicionar".equals(action)) {
                String descricao = request.getParameter("descricao");
                double valor = Double.parseDouble(request.getParameter("valor"));

                FonteDeReceita receita = new FonteDeReceita();
                receita.setDescricao(descricao);
                receita.setValor(valor);
                receita.setUsuarioId(usuarioId);

                receitaDAO.insert(receita);

            } else if ("Atualizar".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                String descricao = request.getParameter("descricao");
                double valor = Double.parseDouble(request.getParameter("valor"));

                FonteDeReceita receita = new FonteDeReceita();
                receita.setId(id);
                receita.setDescricao(descricao);
                receita.setValor(valor);
                receita.setUsuarioId(usuarioId);

                receitaDAO.update(receita);
            }

            response.sendRedirect("fonteDeReceita.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("fonteDeReceita.jsp?erro=true");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Verifica se o usuário está autenticado
            HttpSession session = request.getSession(false);
            if (session == null || session.getAttribute("usuarioId") == null) {
                response.sendRedirect("login.jsp");
                return;
            }
            int usuarioId = (Integer) session.getAttribute("usuarioId");

            String action = request.getParameter("action");
            FonteDeReceitaDAO receitaDAO = new FonteDeReceitaDAO();

            if ("Deletar".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                receitaDAO.delete(id, usuarioId);
            }

            response.sendRedirect("fonteDeReceita.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("fonteDeReceita.jsp?erro=true");
        }
    }
}

