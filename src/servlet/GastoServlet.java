package servlet;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import dao.GastoDAO;
import model.Gasto;

@WebServlet("/gasto")
public class GastoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        try {
            // Verifica se o usuário está autenticado
            HttpSession session = request.getSession(false);
            if (session == null || session.getAttribute("usuarioId") == null) {
                response.sendRedirect("login.jsp");
                return;
            }
            int usuarioId = (Integer) session.getAttribute("usuarioId");

            String action = request.getParameter("action");
            GastoDAO gastoDAO = new GastoDAO();

            if ("Adicionar".equals(action)) {
                String descricao = request.getParameter("descricao");
                String valorStr = request.getParameter("valor").trim();

                // Remover espaços extras e garantir que o valor esteja no formato correto
                valorStr = valorStr.replaceAll(",", ".").replaceAll("[^\\d.]", "");

                // Converter o valor para double
                double valor = Double.parseDouble(valorStr);

                Gasto gasto = new Gasto();
                gasto.setDescricao(descricao);
                gasto.setValor(valor);
                gasto.setUsuarioId(usuarioId);

                gastoDAO.insert(gasto);

            } else if ("Atualizar".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                String descricao = request.getParameter("descricao");
                String valorStr = request.getParameter("valor").trim();

                valorStr = valorStr.replaceAll(",", ".").replaceAll("[^\\d.]", "");
                double valor = Double.parseDouble(valorStr);

                Gasto gasto = new Gasto();
                gasto.setId(id);
                gasto.setDescricao(descricao);
                gasto.setValor(valor);
                gasto.setUsuarioId(usuarioId);

                gastoDAO.update(gasto);
            }

            response.sendRedirect("gastos.jsp");
        } catch (NumberFormatException e) {
            System.out.println("Erro ao converter valor para double: " + e.getMessage());
            response.sendRedirect("gastos.jsp?erro=true");
        } catch (Exception e) {
            System.out.println("Erro ao processar a solicitação.");
            e.printStackTrace();
            response.sendRedirect("gastos.jsp?erro=true");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        try {
            // Verifica se o usuário está autenticado
            HttpSession session = request.getSession(false);
            if (session == null || session.getAttribute("usuarioId") == null) {
                response.sendRedirect("login.jsp");
                return;
            }
            int usuarioId = (Integer) session.getAttribute("usuarioId");

            String action = request.getParameter("action");
            GastoDAO gastoDAO = new GastoDAO();

            if ("Deletar".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                gastoDAO.delete(id, usuarioId);
            }

            response.sendRedirect("gastos.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("gastos.jsp?erro=true");
        }
    }
}
