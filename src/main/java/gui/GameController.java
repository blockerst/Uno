package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;

public class GameController {

    @FXML
    private Button exit;

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    public void toLobby(ActionEvent e) throws IOException{
        root = FXMLLoader.load(getClass().getResource("Lobby.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
