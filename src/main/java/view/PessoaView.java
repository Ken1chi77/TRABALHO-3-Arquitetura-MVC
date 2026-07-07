package com.example.trabalho2.view;

import com.example.trabalho2.controller.PessoaController;
import com.example.trabalho2.model.Pessoa;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;

import java.sql.SQLException;

public class PessoaView {

    private final PessoaController controller;
    private Pessoa pessoaSelecionada;

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
        Button btnListar = new Button("Listar");
        Button btnEditar = new Button("Editar");
        Button btnExcluir = new Button("Excluir");
        Button btnCancelar = new Button("Cancelar");

        TableView<Pessoa> tabela = new TableView<>();
        TableColumn<Pessoa, Integer> colId = new TableColumn<>("ID");
        TableColumn<Pessoa, String> colNome = new TableColumn<>("Nome");
        TableColumn<Pessoa, String> colCpf = new TableColumn<>("CPF");
        TableColumn<Pessoa, String> colEmail = new TableColumn<>("E-mail");
        TableColumn<Pessoa, String> colTelefone = new TableColumn<>("Telefone");

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));

        tabela.getColumns().addAll(colId, colNome, colCpf, colEmail, colTelefone);
        tabela.setItems(controller.getListaPessoas());

        listarComTratamento();

        tabela.getSelectionModel().selectedItemProperty().addListener((obs, antiga, nova) -> {
            pessoaSelecionada = nova;
            if (nova != null) {
                txtNome.setText(nova.getNome());
                txtCpf.setText(nova.getCpf());
                txtEmail.setText(nova.getEmail());
                txtTelefone.setText(nova.getTelefone());
            }
        });

        btnSalvar.setOnAction(e -> {
            try {
                controller.salvarPessoa(
                        txtNome.getText(),
                        txtCpf.getText(),
                        txtEmail.getText(),
                        txtTelefone.getText()
                );
                mostrarAlerta(Alert.AlertType.INFORMATION, "Sucesso", "Pessoa salva com sucesso!");
                limparCampos(txtNome, txtCpf, txtEmail, txtTelefone);
                listarComTratamento();
            } catch (SQLException ex) {
                mostrarAlerta(Alert.AlertType.ERROR, "Erro ao salvar", ex.getMessage());
            }
        });

        btnListar.setOnAction(e -> listarComTratamento());

        btnEditar.setOnAction(e -> {
            if (pessoaSelecionada == null) {
                mostrarAlerta(Alert.AlertType.WARNING, "Nenhuma seleção", "Selecione uma pessoa na tabela.");
                return;
            }
            pessoaSelecionada.setNome(txtNome.getText());
            pessoaSelecionada.setCpf(txtCpf.getText());
            pessoaSelecionada.setEmail(txtEmail.getText());
            pessoaSelecionada.setTelefone(txtTelefone.getText());

            try {
                controller.editarPessoa(pessoaSelecionada);
                mostrarAlerta(Alert.AlertType.INFORMATION, "Sucesso", "Pessoa atualizada com sucesso!");
                limparCampos(txtNome, txtCpf, txtEmail, txtTelefone);
                listarComTratamento();
            } catch (SQLException ex) {
                mostrarAlerta(Alert.AlertType.ERROR, "Erro ao editar", ex.getMessage());
            }
        });

        btnExcluir.setOnAction(e -> {
            if (pessoaSelecionada == null) {
                mostrarAlerta(Alert.AlertType.WARNING, "Nenhuma seleção", "Selecione uma pessoa na tabela.");
                return;
            }
            try {
                controller.excluirPessoa(pessoaSelecionada);
                mostrarAlerta(Alert.AlertType.INFORMATION, "Sucesso", "Pessoa excluída com sucesso!");
                limparCampos(txtNome, txtCpf, txtEmail, txtTelefone);
                listarComTratamento();
            } catch (SQLException ex) {
                mostrarAlerta(Alert.AlertType.ERROR, "Erro ao excluir", ex.getMessage());
            }
        });

        btnCancelar.setOnAction(e -> limparCampos(txtNome, txtCpf, txtEmail, txtTelefone));

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

        HBox botoes = new HBox(10, btnSalvar, btnListar, btnEditar, btnExcluir, btnCancelar);
        VBox root = new VBox(15, grid, botoes, tabela);
        root.setPadding(new Insets(10));

        return new Scene(root, 750, 500);
    }

    private void listarComTratamento() {
        try {
            controller.listarPessoas();
        } catch (SQLException ex) {
            mostrarAlerta(Alert.AlertType.ERROR, "Erro ao listar", ex.getMessage());
        }
    }

    private void limparCampos(TextField... campos) {
        for (TextField campo : campos) {
            campo.clear();
        }
        pessoaSelecionada = null;
    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensagem) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensagem);
        alerta.showAndWait();
    }
}