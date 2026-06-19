package com.example.trabalho2.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import com.example.trabalho2.model.Pessoa;

public class PessoaController {

    private ObservableList<Pessoa> listaPessoas = FXCollections.observableArrayList();

    public void salvarPessoa(
            String nome,
            String cpf,
            String email,
            String telefone) {

        Pessoa pessoa = new Pessoa(
                nome,
                cpf,
                email,
                telefone
        );

        listaPessoas.add(pessoa);
    }

    public ObservableList<Pessoa> getListaPessoas() {
        return listaPessoas;
    }
}