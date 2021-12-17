package gui;

import Database.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;

public class LobbyController{
    @FXML
    private Button addfriend;
    @FXML
    private Button crtGame;
    @FXML
    private Button ldrboard;
    @FXML
    private Button tutorial;
    @FXML
    private ImageView settings;
    @FXML
    private Button logout;
    @FXML
    private ImageView profile;
    @FXML
    private TextField friendField;
    @FXML
    private Button sendReq;
    @FXML
    private Label friendLabel;
    @FXML
    private Button startCompBut;
    @FXML
    private ComboBox choiceOpp;
    @FXML
    private Button leaderfriendbut;
    @FXML
    private ImageView ldrbrdprofile;
    @FXML
    private ImageView ledrbrdsettings;
    @FXML
    private ScrollPane profilescroll;
    @FXML
    private Button tolobby;
    @FXML
    private ScrollPane selfprofilescrollAnchorPane;

    private final Image icon = new Image(getClass().getResourceAsStream("logo.png"));
    private int oppNum;
    private ObservableList list;
    private Stage stage;
    private Scene scene;
    private Parent root;

    public static void checkOnline(Stage stage){
        if(!stage.isShowing()){
            LoginController.db.isOnlineOperation(LoginController.user, false);
        }
    }
    @FXML
    public void toLogin(ActionEvent e) throws IOException{
        LoginController.db.isOnlineOperation(LoginController.user,false);
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        stage.close();
        root = FXMLLoader.load(getClass().getResource("LoginPage.fxml"));
        stage = LoginController.getLobbyStage();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void toLobby(ActionEvent e) throws IOException{
        root = FXMLLoader.load(getClass().getResource("Lobby.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
    @FXML
    public void toSettings(MouseEvent e) throws IOException{
        root = FXMLLoader.load(getClass().getResource("Settings.fxml"));
        stage = new Stage();
        stage.setTitle("Settings");
        scene = new Scene(root);
        stage.setScene(scene);
        stage.getIcons().add(icon);
        stage.showAndWait();
    }
    @FXML
    public void toProfile(ActionEvent e) throws IOException{
        root = FXMLLoader.load(getClass().getResource("Profile.fxml"));
        scene = new Scene(root);
        stage = new Stage();
        stage.setTitle("Profile");
        stage.setScene(scene);
        stage.getIcons().add(icon);
        stage.showAndWait();
    }
    @FXML
    public void toSelfProfile(MouseEvent e) throws IOException{
        root = FXMLLoader.load(getClass().getResource("ProfileSelf.fxml"));
        stage = new Stage();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Your Profile");
        stage.initModality(Modality.APPLICATION_MODAL);
        if(e.getSource() == profile){
            stage.initOwner(profile.getScene().getWindow());
        }
        else if(e.getSource() == ldrbrdprofile){
            stage.initOwner(ldrbrdprofile.getScene().getWindow());
        }
        stage.getIcons().add(icon);
        stage.showAndWait();
    }
    @FXML
    public void addFriend(ActionEvent e) throws IOException{
        root = FXMLLoader.load(getClass().getResource("Add_Friends.fxml"));
        stage = new Stage();
        stage.setTitle("Add Friend");
        scene = new Scene(root);
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.getIcons().add(icon);
        stage.showAndWait();
    }
    @FXML
    public void sendFriendshipRequest(ActionEvent e) throws IOException{
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
    @FXML
    public void createGame(ActionEvent e) throws IOException{
        root = FXMLLoader.load(getClass().getResource("BotNumSelection.fxml"));

        stage = new Stage();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.showAndWait();
    }
    public void start(ActionEvent e) throws IOException{
        //int opponentNum = (int) choiceOpp.getValue();
        root = FXMLLoader.load(getClass().getResource("Game.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        stage.close();
        stage = LoginController.getLobbyStage();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        checkOnline(stage);
    }
    @FXML
    public void toLeaderBoard(ActionEvent e) throws IOException{
        System.out.println("gşrdş");
        root = FXMLLoader.load(getClass().getResource("Leaderboard.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        checkOnline(stage);
    }
    @FXML
    public void toTutorialMode(ActionEvent e) throws IOException{
        root = FXMLLoader.load(getClass().getResource("Tutorial.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        checkOnline(stage);
    }
}