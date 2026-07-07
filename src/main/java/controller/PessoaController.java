package com.example.trabalho2.controller;

import com.example.trabalho2.dao.PessoaDAO;
import com.example.trabalho2.model.Pessoa;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public class PessoaController {

    private final PessoaDAO pessoaDAO = new PessoaDAO();
    private final ObservableList<Pessoa> listaPessoas = FXCollections.observableArrayList();

    public void salvarPessoa(String nome, String cpf, String email, String telefone) throws SQLException {
        Pessoa pessoa = new Pessoa(nome, cpf, email, telefone);
        pessoaDAO.salvar(pessoa);
    }

    public void editarPessoa(Pessoa pessoa) throws SQLException {
        pessoaDAO.editar(pessoa);
    }

    public void excluirPessoa(Pessoa pessoa) throws SQLException {
        pessoaDAO.excluir(pessoa);
    }

    public void listarPessoas() throws SQLException {
        listaPessoas.setAll(pessoaDAO.listar());
    }

    public ObservableList<Pessoa> getListaPessoas() {
        return listaPessoas;
    }
}