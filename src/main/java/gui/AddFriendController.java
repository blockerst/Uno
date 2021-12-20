package gui;

import Database.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddFriendController implements Initializable {

    @FXML
    private TextField friendField;
    @FXML
    private Button sendReq;
    @FXML
    private Label friendLabel;

    @FXML
    public void sendFriendshipRequest(MouseEvent e) throws IOException {
        String userName = friendField.getText();
        int state = LoginController.db.addFriend(LoginController.user,new User(userName));
        if(state == 1){
            friendLabel.setText("Request Sent!");
            friendLabel.setTextFill(Color.web("#12e149"));
        }
        else if(state == -1){
            friendLabel.setText("Account Does Not Exist!");
            friendLabel.setTextFill(Color.web("#c8de14"));
        }
        else if(state == 0){
            friendLabel.setText("Your Request Is Sent!");
            friendLabel.setTextFill(Color.web("#c8de14"));
        }
        else if(state == 2){
            friendLabel.setText("You Are Already Friends!");
            friendLabel.setTextFill(Color.web("#c8de14"));
        }
        friendLabel.setOpacity(1);
        friendLabel.setVisible(true);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        friendField.setStyle("-fx-focus-color: transparent;");
    }
}
