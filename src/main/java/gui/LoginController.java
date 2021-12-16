package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.io.IOException;

public class LoginController {
    @FXML
    private TextField tf;
    @FXML
    private PasswordField pf;
    @FXML
    private Button signin;
    @FXML
    private Button signup;
    @FXML
    private Button returnlogin;
    @FXML
    private Button returnFromExists;

    private Stage stage;
    private Scene scene;
    private Parent root;
    private static Stage lobbyStage;
    private Stage priorStage;

    public void updatePriorStage(Stage st){
        priorStage = st;
        if(st == null){
            System.out.println("null");
        }
        System.out.println(1);
    }
    public static Stage getLobbyStage() throws IOException{
        return lobbyStage;
    }
    @FXML
    public void signUp(ActionEvent e) throws IOException{
        updatePriorStage((Stage)((Node)e.getSource()).getScene().getWindow());
        System.out.println("6");
        String userName = tf.getText();
        String userPass = pf.getText();
        System.out.println(userName + userPass);
        //switch to signup confirmation scene then return to the original
        //if the account exists, then pop up a scene that simply says the account exists
        /*root = FXMLLoader.load(getClass().getResource("AccountExists.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();*/
        //if it doesn't exist
        root = FXMLLoader.load(getClass().getResource("SignedUp.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void signIn(ActionEvent e) throws IOException {
        System.out.println("3");
        String userName = tf.getText();
        String userPass = pf.getText();
        System.out.println(userName + userPass);
        //if usernam and pass is correct, scene and stage changes to the lobby
        root = FXMLLoader.load(getClass().getResource("Lobby.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        lobbyStage = stage;
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void returnLogin(ActionEvent e) throws IOException{
        updatePriorStage((Stage)((Node)e.getSource()).getScene().getWindow());
        root = FXMLLoader.load(getClass().getResource("LoginPage.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void toProfilePicSelection(ActionEvent e) throws IOException{
        root = FXMLLoader.load(getClass().getResource("ProfilePicSelection.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void selectProfilePic(MouseEvent e) throws IOException{
        //assign profilepic to chosen pic by e.getSource(); in database
        root = FXMLLoader.load(getClass().getResource("Lobby.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
