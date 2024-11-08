package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.FonteDeReceita;

public class FonteDeReceitaDAO {

    // Método para inserir uma nova fonte de receita
    public void insert(FonteDeReceita receita) {
        String sql = "INSERT INTO fonte_de_receita (descricao, valor, usuario_id) VALUES (?, ?, ?)";
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql, new String[]{"id"})) {

            // Definir os parâmetros de entrada
            stmt.setString(1, receita.getDescricao());
            stmt.setDouble(2, receita.getValor());
            stmt.setInt(3, receita.getUsuarioId());

            // Executar a inserção
            stmt.executeUpdate();

            // Obter o ID gerado automaticamente
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    receita.setId(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir fonte de receita", e);
        }
    }

    // Método para obter todas as fontes de receita
    public List<FonteDeReceita> getAll() {
        List<FonteDeReceita> receitas = new ArrayList<>();
        String sql = "SELECT * FROM fonte_de_receita";
        try (Connection connection = ConnectionFactory.getConnection();
             Statement stmt = connection.createStatement()) {

            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                FonteDeReceita receita = new FonteDeReceita();
                receita.setId(rs.getInt("id"));
                receita.setDescricao(rs.getString("descricao"));
                receita.setValor(rs.getDouble("valor"));
                receitas.add(receita);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao obter fontes de receita", e);
        }
        return receitas;
    }

    // Método para obter uma fonte de receita pelo ID
    public FonteDeReceita getById(int id) {
        String sql = "SELECT * FROM fonte_de_receita WHERE id = ?";
        FonteDeReceita receita = null;
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                receita = new FonteDeReceita();
                receita.setId(rs.getInt("id"));
                receita.setDescricao(rs.getString("descricao"));
                receita.setValor(rs.getDouble("valor"));
                receita.setUsuarioId(rs.getInt("usuario_id"));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao obter fonte de receita pelo ID", e);
        }
        return receita;
    }

    // Método para obter uma fonte de receita pelo ID do usuário
    public List<FonteDeReceita> getByUsuarioId(int usuarioId) {
        List<FonteDeReceita> receitas = new ArrayList<>();
        String sql = "SELECT * FROM fonte_de_receita WHERE usuario_id = ?";
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, usuarioId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                FonteDeReceita receita = new FonteDeReceita();
                receita.setId(rs.getInt("id"));
                receita.setDescricao(rs.getString("descricao"));
                receita.setValor(rs.getDouble("valor"));
                receita.setUsuarioId(rs.getInt("usuario_id"));
                receitas.add(receita);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao obter fontes de receita por usuário", e);
        }
        return receitas;
    }

    // Método para atualizar uma fonte de receita
    public void update(FonteDeReceita receita) {
        String sql = "UPDATE fonte_de_receita SET descricao = ?, valor = ? WHERE id = ? AND usuario_id = ?";
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, receita.getDescricao());
            stmt.setDouble(2, receita.getValor());
            stmt.setInt(3, receita.getId());
            stmt.setInt(4, receita.getUsuarioId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar fonte de receita", e);
        }
    }

    // Método para deletar uma fonte de receita
    public void delete(int id, int usuarioId) {
        String sql = "DELETE FROM fonte_de_receita WHERE id = ? AND usuario_id = ?";
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.setInt(2, usuarioId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar fonte de receita", e);
        }
    }
}
