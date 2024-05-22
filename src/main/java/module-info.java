module org.example.lab_2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.lab_2 to javafx.fxml;
    exports org.example.lab_2;
    exports org.example.lab_2.Controller;
    exports org.example.lab_2.Model;
    opens org.example.lab_2.Controller to javafx.fxml;
}