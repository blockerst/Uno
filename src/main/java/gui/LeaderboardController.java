package gui;

import Database.User;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LeaderboardController implements Initializable {
    @FXML
    private  TableColumn<Competitor,Integer> rank;
    @FXML
    private  TableColumn<Competitor,String> plyrnam;
    @FXML
    private  TableColumn<Competitor,Integer> plyrgamenum;
    @FXML
    private  TableColumn<Competitor,Integer> gameswon;
    @FXML
    private  TableColumn<Competitor,Integer> point;
    @FXML
    private TableView<Competitor> leaderboard;
    @FXML
    private Button addfriend;
    @FXML
    private Button ldrboard;
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
    private ImageView selfprofilepic;

    private final Image icon = new Image(getClass().getResourceAsStream("logo.png"));
    private int oppNum;
    private ObservableList list;
    private Stage stage;
    private Scene scene;
    private Parent root;

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
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ldrbrdprofile.setImage(LoginController.profilePic);
        rank.setCellValueFactory(new PropertyValueFactory<>("rank"));
        plyrnam.setCellValueFactory(new PropertyValueFactory<>("name"));
        plyrgamenum.setCellValueFactory(new PropertyValueFactory<>("playedGameNum"));
        gameswon.setCellValueFactory(new PropertyValueFactory<>("gamesWon"));
        point.setCellValueFactory(new PropertyValueFactory<>("point"));
        //here you add the friends from an array from the database
        ObservableList<Competitor> list = FXCollections.observableArrayList(new Competitor(new SimpleIntegerProperty(1),new SimpleStringProperty("asd"),new SimpleIntegerProperty(3),new SimpleIntegerProperty(5),new SimpleIntegerProperty(6)));
        leaderboard.setItems(list);
    }
}
