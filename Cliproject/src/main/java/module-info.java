module org.example.cliproject {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.cliproject to javafx.fxml;
    exports org.example.cliproject;
}