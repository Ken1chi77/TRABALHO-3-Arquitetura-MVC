package com.example.trabalho2;

import com.example.trabalho2.controller.PessoaController;
import com.example.trabalho2.view.PessoaView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) {

        PessoaController controller = new PessoaController();
        PessoaView view = new PessoaView(controller);

        Scene scene = view.criarTela();

        stage.setTitle("Cadastro MVC");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}