package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Divida;

public class DividaDAO {

    // Método para inserir uma nova dívida
    public void insert(Divida divida) {
        String sql = "INSERT INTO divida (descricao, valor, taxa_juros, meses_para_pagar, usuario_id) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, divida.getDescricao());
            stmt.setDouble(2, divida.getValor());
            stmt.setDouble(3, divida.getTaxaDeJuros());
            stmt.setInt(4, divida.getMesesParaPagar());
            stmt.setInt(5, divida.getUsuarioId());
            stmt.executeUpdate();

            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                divida.setId(generatedKeys.getInt(1));
            }

        } catch (SQLException e) {
            System.out.println("Erro ao inserir dívida.");
            e.printStackTrace();
        }
    }


    // Método para obter todas as dívidas
    public List<Divida> getAll() {
        List<Divida> dividas = new ArrayList<>();
        String sql = "SELECT * FROM divida";
        try (Connection connection = ConnectionFactory.getConnection();
             Statement stmt = connection.createStatement()) {

            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Divida divida = new Divida();
                divida.setId(rs.getInt("id"));
                divida.setDescricao(rs.getString("descricao"));
                divida.setValor(rs.getDouble("valor"));
                divida.setTaxaDeJuros(rs.getDouble("taxa_juros"));
                divida.setMesesParaPagar(rs.getInt("meses_para_pagar"));
                dividas.add(divida);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao obter dívidas.");
            e.printStackTrace();
        }
        return dividas;
    }

    // Método para obter uma dívida pelo ID
    public Divida getById(int id) {
        String sql = "SELECT * FROM divida WHERE id = ?";
        Divida divida = null;
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                divida = new Divida();
                divida.setId(rs.getInt("id"));
                divida.setDescricao(rs.getString("descricao"));
                divida.setValor(rs.getDouble("valor"));
                divida.setTaxaDeJuros(rs.getDouble("taxa_juros"));
                divida.setMesesParaPagar(rs.getInt("meses_para_pagar"));
            }

        } catch (SQLException e) {
            System.out.println("Erro ao obter dívida pelo ID.");
            e.printStackTrace();
        }
        return divida;
    }

    // Método para obter todas as dívidas pelo ID do usuário
    public List<Divida> getByUsuarioId(int usuarioId) {
        List<Divida> dividas = new ArrayList<>();
        String sql = "SELECT * FROM divida WHERE usuario_id = ?";
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, usuarioId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Divida divida = new Divida();
                divida.setId(rs.getInt("id"));
                divida.setDescricao(rs.getString("descricao"));
                divida.setValor(rs.getDouble("valor"));
                divida.setTaxaDeJuros(rs.getDouble("taxa_juros"));
                divida.setMesesParaPagar(rs.getInt("meses_para_pagar"));
                divida.setUsuarioId(rs.getInt("usuario_id"));
                dividas.add(divida);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao obter dívidas por usuário.");
            e.printStackTrace();
        }
        return dividas;
    }

    // Método para atualizar uma dívida
    public void update(Divida divida) {
        String sql = "UPDATE divida SET descricao = ?, valor = ?, taxa_juros = ?, meses_para_pagar = ? WHERE id = ? AND usuario_id = ?";
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, divida.getDescricao());
            stmt.setDouble(2, divida.getValor());
            stmt.setDouble(3, divida.getTaxaDeJuros());
            stmt.setInt(4, divida.getMesesParaPagar());
            stmt.setInt(5, divida.getId());
            stmt.setInt(6, divida.getUsuarioId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar dívida.");
            e.printStackTrace();
        }
    }


    // Método para deletar uma dívida
    public void delete(int id, int usuarioId) {
        String sql = "DELETE FROM divida WHERE id = ? AND usuario_id = ?";
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.setInt(2, usuarioId);
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Erro ao deletar dívida.");
            e.printStackTrace();
        }
    }

}
