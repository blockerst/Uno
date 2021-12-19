package gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.annotation.Inherited;
import java.net.URL;
import java.util.ResourceBundle;

public class TutorialController implements Initializable {
    @FXML
    private ImageView exit;

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    public void toLobby(MouseEvent e) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Lobby.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Circle clip = new Circle();
        clip.setRadius(exit.getFitHeight()/2);
        clip.setCenterX(exit.getFitWidth()/2);
        clip.setCenterY(exit.getFitHeight()/2);
        exit.setClip(clip);
    }
}
