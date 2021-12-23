package gui;

import Database.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class LobbyController implements Initializable {
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
    @FXML
    private ImageView profilepic;
    @FXML
    private ListView friendlistview;
    @FXML
    private ListView friendonlinelistview;
    @FXML
    private SplitPane splitLobby;
    @FXML
    private Label usernam;
    @FXML
    private ImageView crtgamebtn;
    @FXML
    private ImageView tutoimg;
    @FXML
    private ImageView ldrimg;
    @FXML
    private ImageView addfimg;

    private final Image icon = new Image(getClass().getResourceAsStream("logo.png"));
    private Stage stage;
    public static Stage lobbystage;
    public static String friendname;
    private Scene scene;
    private Parent root;


    @FXML
    public void toSettings(MouseEvent e) throws IOException {
        System.out.println("35");
        root = FXMLLoader.load(getClass().getResource("Settings.fxml"));
        System.out.println("4");
        stage = new Stage();
        stage.setTitle("Settings");
        scene = new Scene(root);
        stage.setScene(scene);
        stage.getIcons().add(icon);
        stage.showAndWait();
    }

    @FXML
    public void toProfile(ActionEvent e) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Profile.fxml"));
        scene = new Scene(root);
        stage = new Stage();
        stage.setTitle("Profile");
        stage.setScene(scene);
        stage.getIcons().add(icon);
        stage.showAndWait();
    }

    @FXML
    public void toSelfProfile(MouseEvent e) throws IOException {
        root = FXMLLoader.load(getClass().getResource("ProfileSelf.fxml"));
        stage = new Stage();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Your Profile");
        stage.initModality(Modality.APPLICATION_MODAL);
        if (e.getSource() == profile) {
            stage.initOwner(profile.getScene().getWindow());
        } else if (e.getSource() == ldrbrdprofile) {
            stage.initOwner(ldrbrdprofile.getScene().getWindow());
        }
        stage.getIcons().add(icon);
        stage.showAndWait();
    }

    @FXML
    public void addFriend(MouseEvent e) throws IOException {
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
    public void createGame(MouseEvent e) throws IOException {
        root = FXMLLoader.load(getClass().getResource("BotNumSelection.fxml"));
        stage = new Stage();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.getIcons().add(icon);
        stage.showAndWait();
    }

    public void start(ActionEvent e) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Game.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        stage.close();
        stage = LobbyController.lobbystage;
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void toLeaderBoard(MouseEvent e) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Leaderboard.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void toTutorialMode(MouseEvent e) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Tutorial.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        usernam.setText(LoginController.user.getUsername());
        profilepic.setImage(LoginController.profilePic);
        Circle clip = new Circle();
        clip.setRadius(profilepic.getFitHeight()/2);
        clip.setCenterX(profilepic.getFitWidth()/2);
        clip.setCenterY(profilepic.getFitHeight()/2);
        Rectangle rec1 = new Rectangle(crtgamebtn.getFitWidth()-9, crtgamebtn.getFitHeight());
        rec1.setArcHeight(15);
        rec1.setArcWidth(15);
        Rectangle rec2 = new Rectangle(tutoimg.getFitWidth()-13, tutoimg.getFitHeight());
        rec2.setArcHeight(15);
        rec2.setArcWidth(15);
        Rectangle rec3 = new Rectangle(ldrimg.getFitWidth()-25, ldrimg.getFitHeight());
        rec3.setArcHeight(15);
        rec3.setArcWidth(15);
        crtgamebtn.setClip(rec1);
        tutoimg.setClip(rec2);
        ldrimg.setClip(rec3);
        Rectangle rec4 = new Rectangle(addfimg.getFitWidth()-25, addfimg.getFitHeight());
        rec4.setArcHeight(15);
        rec4.setArcWidth(15);
        addfimg.setClip(rec4);

        profilepic.setClip(clip);
        Circle clip2 = new Circle();
        clip2.setRadius(settings.getFitHeight()/2);
        clip2.setCenterX(settings.getFitHeight()/2);
        clip2.setCenterY(settings.getFitHeight()/2);
        settings.setClip(clip2);
        friendonlinelistview.setMouseTransparent(true);
        friendlistview.setStyle("-fx-background-color: transparent");
        friendlistview.setStyle("-fx-background-insets: 0 ;");
        FriendView runnable = new FriendView();
        runnable.start();

    }
    private class FriendView extends Thread
    {
        @Override
        public void run() {
            ArrayList<User> list = LoginController.db.getFriends(LoginController.user);
            for(int i = 0; i < list.size(); i++){
                Label isOnline = new Label("");
                friendlistview.getItems().add(i,list.get(i).getUsername());
                isOnline.setText("Online");
                isOnline.setStyle("-fx-text-fill: green");
                if(list.get(i).getIsOnline() == 0){
                    isOnline.setText("Offline");
                    isOnline.setStyle("-fx-text-fill: red");
                }
                friendonlinelistview.getItems().add(i,isOnline);
            }
            friendonlinelistview.setStyle("-fx-background-insets: 0 ;" +
                    "-fx-padding: 0;");
            friendonlinelistview.setStyle("-fx-background-color: transparent");
            friendlistview.setStyle("-fx-selection-bar-non-focused: purple ;");
            friendlistview.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    friendname = (String)friendlistview.getSelectionModel().getSelectedItem();
                    try {
                        root = FXMLLoader.load(getClass().getResource("Profile.fxml"));
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    stage = new Stage();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.setTitle("Profile");
                    stage.initModality(Modality.APPLICATION_MODAL);
                    if (e.getSource() == profile) {
                        stage.initOwner(profile.getScene().getWindow());
                    } else if (e.getSource() == ldrbrdprofile) {
                        stage.initOwner(ldrbrdprofile.getScene().getWindow());
                    }
                    stage.getIcons().add(icon);
                    stage.showAndWait();
                }
            });
        }
    }
}