package gui;

import Database.User;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SelfProfileController implements Initializable{
    @FXML
    private Button addfriend;
    @FXML
    private ImageView profile;
    @FXML
    private TextField friendField;
    @FXML
    private Button sendReq;
    @FXML
    private Label friendLabel;
    @FXML
    private ScrollPane profilescroll;
    @FXML
    private ScrollPane selfprofilescrollAnchorPane;
    @FXML
    private ImageView profilepic;
    @FXML
    private ImageView selfprofilepic;

    private final Image icon = new Image(getClass().getResourceAsStream("logo.png"));
    private ObservableList list;
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    public void sendFriendshipRequest(ActionEvent e) throws IOException {
        //String userName = friendField.getText();
        //if the user exists and we are not friends with them
        friendLabel.setText("Request sent!");
        friendLabel.setVisible(true);
        System.out.println(88);
        //else if the user does not exist
        //friendLabel.setText("User does not exist!");
        //else the user and us are friends
        //friendLabel.setText("You are friends!");
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        selfprofilepic.setImage(LoginController.profilePic);
    }
}
