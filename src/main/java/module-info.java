module com.example.assignment06 {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;

    opens com.example.assignment06 to javafx.fxml;
    exports com.example.assignment06;
}