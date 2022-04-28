module com.example.moorhuhn {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;

    opens com.example.moorhuhn to javafx.fxml;
    exports com.example.moorhuhn.MainProcess;
    opens com.example.moorhuhn.MainProcess to javafx.fxml;
    exports com.example.moorhuhn.Pom;
    opens com.example.moorhuhn.Pom to javafx.fxml;
}