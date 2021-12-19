package gui;

import Database.User;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class RemoveFriendController {
    @FXML
    private Button remove;
    @FXML
    private TextField removeField;
    @FXML
    private Label removal;

    private final Image icon = new Image(getClass().getResourceAsStream("logo.png"));
    private Stage stage;
    public static Stage lobbystage;
    public static String friendname;
    private Scene scene;
    private Parent root;

    public void removeFriend(){
        String userName = removeField.getText();
        boolean state = LoginController.db.removeFriend(LoginController.user,new User(userName));
        if(state){
            removal.setText("You Are No Longer Friends!");
            removal.setTextFill(Color.web("#c8de14"));
        }
        else{
            removal.setText("You Are Already Not Friends!");
            removal.setTextFill(Color.web("#c8de14"));
        }
        removal.setOpacity(1);
        removal.setVisible(true);
    }
}
