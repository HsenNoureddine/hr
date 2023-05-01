module com.example.hr {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.example.hr to javafx.fxml;
    exports com.example.hr;
    exports com.example.hr.controller;
    opens com.example.hr.controller to javafx.fxml;
}