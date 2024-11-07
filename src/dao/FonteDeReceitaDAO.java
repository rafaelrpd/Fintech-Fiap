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
             PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, receita.getDescricao());
            stmt.setDouble(2, receita.getValor());
            stmt.setInt(3, receita.getUsuarioId());
            stmt.executeUpdate();

            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                receita.setId(generatedKeys.getInt(1));
            }

        } catch (SQLException e) {
            System.out.println("Erro ao inserir fonte de receita.");
            e.printStackTrace();
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
            System.out.println("Erro ao obter fontes de receita.");
            e.printStackTrace();
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
            }

        } catch (SQLException e) {
            System.out.println("Erro ao obter fonte de receita pelo ID.");
            e.printStackTrace();
        }
        return receita;
    }

    // Método para obter todas as fontes de receita pelo ID do usuário
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
                receitas.add(receita);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao obter fontes de receita por usuário.");
            e.printStackTrace();
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
            System.out.println("Erro ao atualizar fonte de receita.");
            e.printStackTrace();
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
            System.out.println("Erro ao deletar fonte de receita.");
            e.printStackTrace();
        }
    }


}
