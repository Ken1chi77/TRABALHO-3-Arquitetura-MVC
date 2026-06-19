package com.example.trabalho2.view;

import com.example.trabalho2.controller.PessoaController;
import com.example.trabalho2.model.Pessoa;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;

public class PessoaView {

    private PessoaController controller;

    public PessoaView(PessoaController controller) {
        this.controller = controller;
    }

    public Scene criarTela() {

        Label lblNome = new Label("Nome:");
        Label lblCpf = new Label("CPF:");
        Label lblEmail = new Label("E-mail:");
        Label lblTelefone = new Label("Telefone:");

        TextField txtNome = new TextField();
        TextField txtCpf = new TextField();
        TextField txtEmail = new TextField();
        TextField txtTelefone = new TextField();

        Button btnSalvar = new Button("Salvar");
        Button btnCancelar = new Button("Cancelar");

        TableView<Pessoa> tabela = new TableView<>();

        TableColumn<Pessoa, String> colNome = new TableColumn<>("Nome");
        TableColumn<Pessoa, String> colCpf = new TableColumn<>("CPF");
        TableColumn<Pessoa, String> colEmail = new TableColumn<>("E-mail");
        TableColumn<Pessoa, String> colTelefone = new TableColumn<>("Telefone");

        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));

        tabela.getColumns().addAll(colNome, colCpf, colEmail, colTelefone);
        tabela.setItems(controller.getListaPessoas());

        btnSalvar.setOnAction(e -> {
            controller.salvarPessoa(
                    txtNome.getText(),
                    txtCpf.getText(),
                    txtEmail.getText(),
                    txtTelefone.getText()
            );
            txtNome.clear();
            txtCpf.clear();
            txtEmail.clear();
            txtTelefone.clear();
        });

        btnCancelar.setOnAction(e -> {
            txtNome.clear();
            txtCpf.clear();
            txtEmail.clear();
            txtTelefone.clear();
        });

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        grid.add(lblNome, 0, 0);
        grid.add(txtNome, 1, 0);
        grid.add(lblCpf, 0, 1);
        grid.add(txtCpf, 1, 1);
        grid.add(lblEmail, 0, 2);
        grid.add(txtEmail, 1, 2);
        grid.add(lblTelefone, 0, 3);
        grid.add(txtTelefone, 1, 3);

        HBox botoes = new HBox(10, btnSalvar, btnCancelar);
        VBox root = new VBox(15, grid, botoes, tabela);

        root.setPadding(new Insets(10));

        return new Scene(root, 700, 500);
    }
}