package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Usuario;

public class UsuarioDAO {

    // Método para inserir um novo usuário
    public void insert(Usuario usuario) {
        String sql = "INSERT INTO usuario (nome, email, senha) VALUES (?, ?, ?)";
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getSenha());
            stmt.executeUpdate();

            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                usuario.setId(generatedKeys.getInt(1));
            }

        } catch (SQLException e) {
            System.out.println("Erro ao inserir usuário.");
            e.printStackTrace();
        }
    }

    // Método para obter todos os usuários
    public List<Usuario> getAll() {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuario";
        try (Connection connection = ConnectionFactory.getConnection();
             Statement stmt = connection.createStatement()) {

            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));
                usuario.setSenha(rs.getString("senha"));
                usuarios.add(usuario);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao obter usuários.");
            e.printStackTrace();
        }
        return usuarios;
    }

    // Método para obter um usuário pelo ID
    public Usuario getById(int id) {
        String sql = "SELECT * FROM usuario WHERE id = ?";
        Usuario usuario = null;
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));
                usuario.setSenha(rs.getString("senha"));
            }

        } catch (SQLException e) {
            System.out.println("Erro ao obter usuário pelo ID.");
            e.printStackTrace();
        }
        return usuario;
    }

    // Método para obter um usuário pelo email
    public Usuario getByEmail(String email) {
        String sql = "SELECT * FROM usuario WHERE email = ?";
        Usuario usuario = null;
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));
                usuario.setSenha(rs.getString("senha"));
            }

        } catch (SQLException e) {
            System.out.println("Erro ao obter usuário pelo email.");
            e.printStackTrace();
        }
        return usuario;
    }

    // Método para atualizar um usuário
    public void update(Usuario usuario) {
        String sql = "UPDATE usuario SET nome = ?, email = ?, senha = ? WHERE id = ?";
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getSenha());
            stmt.setInt(4, usuario.getId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar usuário.");
            e.printStackTrace();
        }
    }

    // Método para deletar um usuário
    public void delete(int id) {
        String sql = "DELETE FROM usuario WHERE id = ?";
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Erro ao deletar usuário.");
            e.printStackTrace();
        }
    }

    // Método para validar login de usuário
    public Usuario validarUsuario(String email, String senha) {
        String sql = "SELECT * FROM usuario WHERE email = ? AND senha = ?";
        Usuario usuario = null;
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, email);
            stmt.setString(2, senha);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));
                usuario.setSenha(rs.getString("senha"));
            }

        } catch (SQLException e) {
            System.out.println("Erro ao validar usuário.");
            e.printStackTrace();
        }
        return usuario;
    }

}
