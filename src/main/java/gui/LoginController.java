package gui;

import Database.ConnectionSql;
import Database.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
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
    public static ConnectionSql db;
    public static User user;

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
        if(db.signUp(new User(userName,userPass))){
            //online version of this is isOnlineOperation(User user, boolean isOnline)
            user = new User(userName,userPass);
            root = FXMLLoader.load(getClass().getResource("SignedUp.fxml"));
            stage = (Stage)((Node)e.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        else{
            root = FXMLLoader.load(getClass().getResource("AccountExists.fxml"));
            stage = (Stage)((Node)e.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        System.out.println(userName + userPass);
    }
    @FXML
    public void signIn(ActionEvent e) throws IOException {
        System.out.println("3");
        String userName = tf.getText();
        String userPass = pf.getText();
        int check = db.singInCheck(new User(userName,userPass));
        db.isOnlineOperation(new User(userName), true);
        if(check == -1){
            root = FXMLLoader.load(getClass().getResource("AccountDoesNotExist.fxml"));
            stage = (Stage)((Node)e.getSource()).getScene().getWindow();
            lobbyStage = stage;
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        else if(check == 0){
            root = FXMLLoader.load(getClass().getResource("IncorrectPass.fxml"));
            stage = (Stage)((Node)e.getSource()).getScene().getWindow();
            lobbyStage = stage;
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        else{
            //go to lobby
            user = new User(userName);
            root = FXMLLoader.load(getClass().getResource("Lobby.fxml"));
            stage = (Stage)((Node)e.getSource()).getScene().getWindow();
            lobbyStage = stage;
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        System.out.println(userName + userPass);
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        db = ConnectionSql.getInstance();
    }
}
