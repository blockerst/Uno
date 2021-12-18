package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.io.IOException;

public class AddFriendController {

    @FXML
    private TextField friendField;
    @FXML
    private Button sendReq;
    @FXML
    private Label friendLabel;

    @FXML
    public void sendFriendshipRequest(ActionEvent e) throws IOException {
        //String userName = friendField.getText();
        //if the user exists and we are not friends with them
        friendLabel.setText("Request Sent!");
        friendLabel.setTextFill(Color.web("#12e149"));
        friendLabel.setOpacity(1);
        friendLabel.setVisible(true);
        System.out.println(88);
        //else if the user does not exist
        //friendLabel.setText("Account Does Not Exist!");
        //friendLabel.setTextFill(Color.web("#c8de14"));
        //friendLabel.setOpacity(1);
        //friendLabel.setVisible(true);
        //else the user and us are friends
        //friendLabel.setText("You Are Already Friends!");
        //friendLabel.setTextFill(Color.web("#c8de14"));
        //friendLabel.setOpacity(1);
        //friendLabel.setVisible(true);
    }
}
