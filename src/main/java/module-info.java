module com.example.trabalho {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.example.trabalho2 to javafx.fxml;
    exports com.example.trabalho2;

    // Esta linha é crucial para que a TableView funcione sem erros de reflexão
    opens com.example.trabalho2.model to javafx.base;
    exports com.example.trabalho2.model;
}