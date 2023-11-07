module com.example.studaid {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.studaid to javafx.fxml;
    exports com.example.studaid;
}