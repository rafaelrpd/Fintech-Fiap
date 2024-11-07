package servlet;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import dao.DividaDAO;
import model.Divida;

@WebServlet("/divida")
public class DividaServlet extends HttpServlet {

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
            DividaDAO dividaDAO = new DividaDAO();

            if ("Adicionar".equals(action)) {
                String descricao = request.getParameter("descricao");
                double valor = Double.parseDouble(request.getParameter("valor"));
                double taxaJuros = Double.parseDouble(request.getParameter("taxaJuros"));
                int mesesParaPagar = Integer.parseInt(request.getParameter("mesesParaPagar"));

                Divida divida = new Divida();
                divida.setDescricao(descricao);
                divida.setValor(valor);
                divida.setTaxaDeJuros(taxaJuros);
                divida.setMesesParaPagar(mesesParaPagar);
                divida.setUsuarioId(usuarioId);

                dividaDAO.insert(divida);

            } else if ("Atualizar".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                String descricao = request.getParameter("descricao");
                double valor = Double.parseDouble(request.getParameter("valor"));
                double taxaJuros = Double.parseDouble(request.getParameter("taxaJuros"));
                int mesesParaPagar = Integer.parseInt(request.getParameter("mesesParaPagar"));

                Divida divida = new Divida();
                divida.setId(id);
                divida.setDescricao(descricao);
                divida.setValor(valor);
                divida.setTaxaDeJuros(taxaJuros);
                divida.setMesesParaPagar(mesesParaPagar);
                divida.setUsuarioId(usuarioId);

                dividaDAO.update(divida);
            }

            response.sendRedirect("dividas.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("dividas.jsp?erro=true");
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
            DividaDAO dividaDAO = new DividaDAO();

            if ("Deletar".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                dividaDAO.delete(id, usuarioId);
            }

            response.sendRedirect("dividas.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("dividas.jsp?erro=true");
        }
    }
}
