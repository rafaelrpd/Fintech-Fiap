package servlet;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import dao.MetaFinanceiraDAO;
import model.MetaFinanceira;

@WebServlet("/metaFinanceira")
public class MetaFinanceiraServlet extends HttpServlet {

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
            MetaFinanceiraDAO metaDAO = new MetaFinanceiraDAO();

            if ("Adicionar".equals(action)) {
                String descricao = request.getParameter("descricao");
                double valorObjetivo = Double.parseDouble(request.getParameter("valorObjetivo"));
                double valorAtual = Double.parseDouble(request.getParameter("valorAtual"));

                MetaFinanceira meta = new MetaFinanceira();
                meta.setDescricao(descricao);
                meta.setValorObjetivo(valorObjetivo);
                meta.setValorAtual(valorAtual);
                meta.setUsuarioId(usuarioId);

                metaDAO.insert(meta);

            } else if ("Atualizar".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                String descricao = request.getParameter("descricao");
                double valorObjetivo = Double.parseDouble(request.getParameter("valorObjetivo"));
                double valorAtual = Double.parseDouble(request.getParameter("valorAtual"));

                MetaFinanceira meta = new MetaFinanceira();
                meta.setId(id);
                meta.setDescricao(descricao);
                meta.setValorObjetivo(valorObjetivo);
                meta.setValorAtual(valorAtual);
                meta.setUsuarioId(usuarioId);

                metaDAO.update(meta);
            }

            response.sendRedirect("metasFinanceiras.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("metasFinanceiras.jsp?erro=true");
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
            MetaFinanceiraDAO metaDAO = new MetaFinanceiraDAO();

            if ("Deletar".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                metaDAO.delete(id, usuarioId);
            }

            response.sendRedirect("metasFinanceiras.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("metasFinanceiras.jsp?erro=true");
        }
    }
}
