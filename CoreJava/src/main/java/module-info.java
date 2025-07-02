module org.example.corejava {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.corejava to javafx.fxml;
    exports org.example.corejava;
}