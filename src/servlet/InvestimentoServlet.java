package servlet;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import dao.InvestimentoDAO;
import model.Investimento;

@WebServlet("/investimento")
public class InvestimentoServlet extends HttpServlet {

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
            InvestimentoDAO investimentoDAO = new InvestimentoDAO();

            if ("Adicionar".equals(action)) {
                String descricao = request.getParameter("descricao");
                double valor = Double.parseDouble(request.getParameter("valor"));
                double rendimentoMensal = Double.parseDouble(request.getParameter("rendimentoMensal"));

                Investimento investimento = new Investimento();
                investimento.setDescricao(descricao);
                investimento.setValor(valor);
                investimento.setRendimentoMensal(rendimentoMensal);
                investimento.setUsuarioId(usuarioId);

                investimentoDAO.insert(investimento);

            } else if ("Atualizar".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                String descricao = request.getParameter("descricao");
                double valor = Double.parseDouble(request.getParameter("valor"));
                double rendimentoMensal = Double.parseDouble(request.getParameter("rendimentoMensal"));

                Investimento investimento = new Investimento();
                investimento.setId(id);
                investimento.setDescricao(descricao);
                investimento.setValor(valor);
                investimento.setRendimentoMensal(rendimentoMensal);
                investimento.setUsuarioId(usuarioId);

                investimentoDAO.update(investimento);
            }

            response.sendRedirect("investimentos.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("investimentos.jsp?erro=true");
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
            InvestimentoDAO investimentoDAO = new InvestimentoDAO();

            if ("Deletar".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                investimentoDAO.delete(id, usuarioId);
            }

            response.sendRedirect("investimentos.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("investimentos.jsp?erro=true");
        }
    }
}
