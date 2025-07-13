module org.example.cliproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires jdk.jsobject;
    requires org.json;
    requires java.naming;
    requires java.sql;
    requires jdk.compiler;


    opens org.example.cliproject to javafx.fxml;
//    exports org.example.cliproject;
}