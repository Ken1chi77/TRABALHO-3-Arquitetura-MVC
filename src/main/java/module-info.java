module com.example.trabalho {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.example.trabalho2 to javafx.fxml;
    exports com.example.trabalho2;

    opens com.example.trabalho2.model to javafx.base;
    exports com.example.trabalho2.model;
}