package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.FonteDeReceita;
import model.Gasto;

public class GastoDAO {

    // Método para inserir um novo gasto
    public void insert(Gasto gasto) {
        String sql = "INSERT INTO gasto (descricao, valor, usuario_id) VALUES (?, ?, ?)";
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql, new String[]{"id"})) {

            stmt.setString(1, gasto.getDescricao());
            stmt.setDouble(2, gasto.getValor());
            stmt.setInt(3, gasto.getUsuarioId());
            stmt.executeUpdate();

            // Obter o ID gerado automaticamente
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    gasto.setId(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao inserir gasto.");
            e.printStackTrace();
            throw new RuntimeException("Erro ao inserir gasto", e);
        }
    }

    // Método para obter todos os gastos
    public List<Gasto> getAll() {
        List<Gasto> gastos = new ArrayList<>();
        String sql = "SELECT * FROM gasto";
        try (Connection connection = ConnectionFactory.getConnection();
             Statement stmt = connection.createStatement()) {

            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Gasto gasto = new Gasto();
                gasto.setId(rs.getInt("id"));
                gasto.setDescricao(rs.getString("descricao"));
                gasto.setValor(rs.getDouble("valor"));
                gastos.add(gasto);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao obter gastos.");
            e.printStackTrace();
        }
        return gastos;
    }

    // Método para obter um gasto pelo ID
    public Gasto getById(int id) {
        String sql = "SELECT * FROM gasto WHERE id = ?";
        Gasto gasto = null;
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                gasto = new Gasto();
                gasto.setId(rs.getInt("id"));
                gasto.setDescricao(rs.getString("descricao"));
                gasto.setValor(rs.getDouble("valor"));
                gasto.setUsuarioId(rs.getInt("usuario_id"));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao obter gasto pelo ID.");
            e.printStackTrace();
        }
        return gasto;
    }

    // Método para obter todos os gastos pelo ID do usuário
    public List<Gasto> getByUsuarioId(int usuarioId) {
        List<Gasto> gastos = new ArrayList<>();
        String sql = "SELECT * FROM gasto WHERE usuario_id = ?";
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, usuarioId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Gasto gasto = new Gasto();
                gasto.setId(rs.getInt("id"));
                gasto.setDescricao(rs.getString("descricao"));
                gasto.setValor(rs.getDouble("valor"));
                gasto.setUsuarioId(rs.getInt("usuario_id"));
                gastos.add(gasto);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao obter gastos por usuário.");
            e.printStackTrace();
        }
        return gastos;
    }


    // Método para atualizar um gasto
    public void update(Gasto gasto) {
        String sql = "UPDATE gasto SET descricao = ?, valor = ? WHERE id = ? AND usuario_id = ?";
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, gasto.getDescricao());
            stmt.setDouble(2, gasto.getValor());
            stmt.setInt(3, gasto.getId());
            stmt.setInt(4, gasto.getUsuarioId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar gasto.");
            e.printStackTrace();
        }
    }


    // Método para deletar um gasto
    public void delete(int id, int usuarioId) {
        String sql = "DELETE FROM gasto WHERE id = ? AND usuario_id = ?";
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.setInt(2, usuarioId);
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Erro ao deletar gasto.");
            e.printStackTrace();
        }
    }

}
