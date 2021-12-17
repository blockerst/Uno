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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
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
    private ImageView pic1;
    @FXML
    private ImageView pic2;
    @FXML
    private ImageView pic3;
    @FXML
    private ImageView pic4;
    @FXML
    private ImageView pic5;
    @FXML
    private ImageView pic6;
    @FXML
    private ImageView pic7;
    @FXML
    private ImageView pic8;

    private Stage stage;
    private Scene scene;
    private Parent root;
    private static Stage lobbyStage;
    public static ConnectionSql db;
    public static User user;
    public static Image profilePic;
    private Image p1 = new Image(getClass().getResourceAsStream("1.png"));
    private Image p2 = new Image(getClass().getResourceAsStream("2.png"));
    private Image p3 = new Image(getClass().getResourceAsStream("3.png"));
    private Image p4 = new Image(getClass().getResourceAsStream("4.png"));
    private Image p5 = new Image(getClass().getResourceAsStream("5.png"));
    private Image p6 = new Image(getClass().getResourceAsStream("6.png"));
    private Image p7 = new Image(getClass().getResourceAsStream("7.png"));
    private Image p8 = new Image(getClass().getResourceAsStream("8.png"));

    public static Stage getLobbyStage() throws IOException{
        return lobbyStage;
    }
    @FXML
    public void signUp(ActionEvent e) throws IOException{
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
            int id = db.getImageId(user);
            profilePic = checkProfilePic(id);
            root = FXMLLoader.load(getClass().getResource("Lobby.fxml"));
            stage = (Stage)((Node)e.getSource()).getScene().getWindow();
            lobbyStage = stage;
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }
    public Image checkProfilePic(int id){
        if(id == 1){
            return p1;
        }
        else if(id == 2){
            return p2;
        }
        else if(id == 3){
            return p3;
        }
        else if(id == 4){
            return p4;
        }
        else if(id == 5){
            return p5;
        }
        else if(id == 6){
            return p6;
        }
        else if(id == 7){
            return p7;
        }
        else{
            return p8;
        }
    }
    @FXML
    public void returnLogin(ActionEvent e) throws IOException{
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
        ImageView profilePic = (ImageView) e.getSource();
        int id = Integer.parseInt(profilePic.getId().substring(3));
        db.imageIdOperation(user,id);
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
