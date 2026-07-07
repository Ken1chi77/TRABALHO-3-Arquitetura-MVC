package com.example.trabalho2.dao;

import com.example.trabalho2.model.Pessoa;
import com.example.trabalho2.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PessoaDAO {

    public void salvar(Pessoa p) throws SQLException {
        String sql = "INSERT INTO pessoa (nome, cpf, email, telefone) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, p.getNome());
            stmt.setString(2, p.getCpf());
            stmt.setString(3, p.getEmail());
            stmt.setString(4, p.getTelefone());

            stmt.executeUpdate();
        }
    }

    public List<Pessoa> listar() throws SQLException {
        String sql = "SELECT * FROM pessoa ORDER BY id";
        List<Pessoa> pessoas = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Pessoa p = new Pessoa(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("cpf"),
                        rs.getString("email"),
                        rs.getString("telefone")
                );
                pessoas.add(p);
            }
        }

        return pessoas;
    }

    public void editar(Pessoa p) throws SQLException {
        String sql = "UPDATE pessoa SET nome = ?, cpf = ?, email = ?, telefone = ? WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, p.getNome());
            stmt.setString(2, p.getCpf());
            stmt.setString(3, p.getEmail());
            stmt.setString(4, p.getTelefone());
            stmt.setInt(5, p.getId());

            stmt.executeUpdate();
        }
    }

    public void excluir(Pessoa p) throws SQLException {
        String sql = "DELETE FROM pessoa WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, p.getId());
            stmt.executeUpdate();
        }
    }
}