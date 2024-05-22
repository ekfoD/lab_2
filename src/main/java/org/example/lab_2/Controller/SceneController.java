package org.example.lab_2.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.lab_2.HelloApplication;

import java.io.IOException;

/**
 * @author Dovydas Girskas 5gr
 */
public class SceneController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    public void switchToInputScene(ActionEvent event) throws IOException {
        root = FXMLLoader.load(HelloApplication.class.getResource("View/input-view.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToOutputScene(ActionEvent event) throws IOException {
        root = FXMLLoader.load(HelloApplication.class.getResource("View/output-view.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
