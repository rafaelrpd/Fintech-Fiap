package servlet;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import dao.UsuarioDAO;
import model.Usuario;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            String email = request.getParameter("email");
            String senha = request.getParameter("senha");

            UsuarioDAO usuarioDAO = new UsuarioDAO();
            Usuario usuario = usuarioDAO.validarUsuario(email, senha);

            if (usuario != null) {
                HttpSession session = request.getSession();
                session.setAttribute("usuarioLogado", email);
                session.setAttribute("usuarioNome", usuario.getNome());
                session.setAttribute("usuarioId", usuario.getId());
                response.sendRedirect("home.jsp");
            } else {
                response.sendRedirect("login.jsp?erro=true");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("login.jsp?erro=true");
        }
    }

}
