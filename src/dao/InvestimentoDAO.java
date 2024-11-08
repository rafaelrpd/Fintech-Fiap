package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Investimento;

public class InvestimentoDAO {

    // Método para inserir um novo investimento
    public void insert(Investimento investimento) {
        String sql = "INSERT INTO investimento (descricao, valor, rendimento_mensal, usuario_id) VALUES (?, ?, ?, ?)";
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql, new String[]{"id"})) {

            stmt.setString(1, investimento.getDescricao());
            stmt.setDouble(2, investimento.getValor());
            stmt.setDouble(3, investimento.getRendimentoMensal());
            stmt.setInt(4, investimento.getUsuarioId());
            stmt.executeUpdate();

            // Obter o ID gerado automaticamente
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    investimento.setId(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao inserir investimento.");
            e.printStackTrace();
        }
    }


    // Método para obter todos os investimentos
    public List<Investimento> getAll() {
        List<Investimento> investimentos = new ArrayList<>();
        String sql = "SELECT * FROM investimento";
        try (Connection connection = ConnectionFactory.getConnection();
             Statement stmt = connection.createStatement()) {

            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Investimento investimento = new Investimento();
                investimento.setId(rs.getInt("id"));
                investimento.setDescricao(rs.getString("descricao"));
                investimento.setValor(rs.getDouble("valor"));
                investimento.setRendimentoMensal(rs.getDouble("rendimento_mensal"));
                investimentos.add(investimento);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao obter investimentos.");
            e.printStackTrace();
        }
        return investimentos;
    }

    // Método para obter um investimento pelo ID
    public Investimento getById(int id) {
        String sql = "SELECT * FROM investimento WHERE id = ?";
        Investimento investimento = null;
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                investimento = new Investimento();
                investimento.setId(rs.getInt("id"));
                investimento.setDescricao(rs.getString("descricao"));
                investimento.setValor(rs.getDouble("valor"));
                investimento.setRendimentoMensal(rs.getDouble("rendimento_mensal"));
                investimento.setUsuarioId(rs.getInt("usuario_id"));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao obter investimento pelo ID", e);
        }
        return investimento;
    }

    // Método para obter todos os investimentos pelo ID do usuário
    public List<Investimento> getByUsuarioId(int usuarioId) {
        List<Investimento> investimentos = new ArrayList<>();
        String sql = "SELECT * FROM investimento WHERE usuario_id = ?";
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, usuarioId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Investimento investimento = new Investimento();
                investimento.setId(rs.getInt("id"));
                investimento.setDescricao(rs.getString("descricao"));
                investimento.setValor(rs.getDouble("valor"));
                investimento.setRendimentoMensal(rs.getDouble("rendimento_mensal"));
                investimento.setUsuarioId(rs.getInt("usuario_id"));
                investimentos.add(investimento);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao obter investimentos por usuário.");
            e.printStackTrace();
        }
        return investimentos;
    }


    // Método para atualizar um investimento
    public void update(Investimento investimento) {
        String sql = "UPDATE investimento SET descricao = ?, valor = ?, rendimento_mensal = ? WHERE id = ? AND usuario_id = ?";
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, investimento.getDescricao());
            stmt.setDouble(2, investimento.getValor());
            stmt.setDouble(3, investimento.getRendimentoMensal());
            stmt.setInt(4, investimento.getId());
            stmt.setInt(5, investimento.getUsuarioId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar investimento.");
            e.printStackTrace();
        }
    }


    // Método para deletar um investimento
    public void delete(int id, int usuarioId) {
        String sql = "DELETE FROM investimento WHERE id = ? AND usuario_id = ?";
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.setInt(2, usuarioId);
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Erro ao deletar investimento.");
            e.printStackTrace();
        }
    }

}
