package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.MetaFinanceira;

public class MetaFinanceiraDAO {

    // Método para inserir uma nova meta financeira
    public void insert(MetaFinanceira meta) {
        String sql = "INSERT INTO meta_financeira (descricao, valor_objetivo, valor_atual, usuario_id) VALUES (?, ?, ?, ?)";
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql, new String[]{"id"})) {

            stmt.setString(1, meta.getDescricao());
            stmt.setDouble(2, meta.getValorObjetivo());
            stmt.setDouble(3, meta.getValorAtual());
            stmt.setInt(4, meta.getUsuarioId());
            stmt.executeUpdate();

            // Obter o ID gerado automaticamente
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    meta.setId(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao inserir meta financeira.");
            e.printStackTrace();
        }
    }


    // Método para obter todas as metas financeiras
    public List<MetaFinanceira> getAll() {
        List<MetaFinanceira> metas = new ArrayList<>();
        String sql = "SELECT * FROM meta_financeira";
        try (Connection connection = ConnectionFactory.getConnection();
             Statement stmt = connection.createStatement()) {

            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                MetaFinanceira meta = new MetaFinanceira();
                meta.setId(rs.getInt("id"));
                meta.setDescricao(rs.getString("descricao"));
                meta.setValorObjetivo(rs.getDouble("valor_objetivo"));
                meta.setValorAtual(rs.getDouble("valor_atual"));
                metas.add(meta);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao obter metas financeiras.");
            e.printStackTrace();
        }
        return metas;
    }

    // Método para obter uma meta financeira pelo ID
    public MetaFinanceira getById(int id) {
        String sql = "SELECT * FROM meta_financeira WHERE id = ?";
        MetaFinanceira meta = null;
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                meta = new MetaFinanceira();
                meta.setId(rs.getInt("id"));
                meta.setDescricao(rs.getString("descricao"));
                meta.setValorObjetivo(rs.getDouble("valor_objetivo"));
                meta.setValorAtual(rs.getDouble("valor_atual"));
                meta.setUsuarioId(rs.getInt("usuario_id"));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao obter meta financeira pelo ID", e);
        }
        return meta;
    }

    // Método para obter todas as metas financeiras pelo ID do usuário
    public List<MetaFinanceira> getByUsuarioId(int usuarioId) {
        List<MetaFinanceira> metas = new ArrayList<>();
        String sql = "SELECT * FROM meta_financeira WHERE usuario_id = ?";
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, usuarioId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                MetaFinanceira meta = new MetaFinanceira();
                meta.setId(rs.getInt("id"));
                meta.setDescricao(rs.getString("descricao"));
                meta.setValorObjetivo(rs.getDouble("valor_objetivo"));
                meta.setValorAtual(rs.getDouble("valor_atual"));
                meta.setUsuarioId(rs.getInt("usuario_id"));
                metas.add(meta);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao obter metas financeiras por usuário.");
            e.printStackTrace();
        }
        return metas;
    }


    // Método para atualizar uma meta financeira
    public void update(MetaFinanceira meta) {
        String sql = "UPDATE meta_financeira SET descricao = ?, valor_objetivo = ?, valor_atual = ? WHERE id = ? AND usuario_id = ?";
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, meta.getDescricao());
            stmt.setDouble(2, meta.getValorObjetivo());
            stmt.setDouble(3, meta.getValorAtual());
            stmt.setInt(4, meta.getId());
            stmt.setInt(5, meta.getUsuarioId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar meta financeira.");
            e.printStackTrace();
        }
    }


    // Método para deletar uma meta financeira
    public void delete(int id, int usuarioId) {
        String sql = "DELETE FROM meta_financeira WHERE id = ? AND usuario_id = ?";
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.setInt(2, usuarioId);
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Erro ao deletar meta financeira.");
            e.printStackTrace();
        }
    }

}
