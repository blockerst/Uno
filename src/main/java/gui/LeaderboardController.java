package gui;

import Database.User;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
    private ImageView selfprofilepic;
    @FXML
    private ListView friendlistview;
    @FXML
    private ListView friendonlinelistview;
    @FXML
    private SplitPane split;
    @FXML
    private Label usernam;

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
        usernam.setText(LoginController.user.getUsername());
        ldrbrdprofile.setImage(LoginController.profilePic);
        Circle clip = new Circle();
        clip.setRadius(ldrbrdprofile.getFitHeight()/2);
        clip.setCenterX(ldrbrdprofile.getFitWidth()/2);
        clip.setCenterY(ldrbrdprofile.getFitHeight()/2);
        ldrbrdprofile.setClip(clip);
        Circle clip2 = new Circle();
        clip2.setRadius(ledrbrdsettings.getFitHeight()/2);
        clip2.setCenterX(ledrbrdsettings.getFitHeight()/2);
        clip2.setCenterY(ledrbrdsettings.getFitHeight()/2);
        ledrbrdsettings.setClip(clip2);
        leaderboard.setSelectionModel(null);
        rank.setCellValueFactory(new PropertyValueFactory<>("rank"));
        rank.setResizable(false);
        rank.setReorderable(false);
        plyrnam.setCellValueFactory(new PropertyValueFactory<>("name"));
        plyrnam.setResizable(false);
        plyrnam.setReorderable(false);
        plyrgamenum.setCellValueFactory(new PropertyValueFactory<>("playedGameNum"));
        plyrgamenum.setResizable(false);
        plyrgamenum.setReorderable(false);
        gameswon.setCellValueFactory(new PropertyValueFactory<>("gamesWon"));
        gameswon.setResizable(false);
        gameswon.setReorderable(false);
        point.setCellValueFactory(new PropertyValueFactory<>("point"));
        point.setResizable(false);
        point.setReorderable(false);
        //here you add the friends from an array from the database
        ObservableList<Competitor> lista = FXCollections.observableArrayList();
        ArrayList<User> users = LoginController.db.getTop100();
        friendonlinelistview.setMouseTransparent( true );

        friendonlinelistview.setStyle("-fx-background-color: transparent");
        for(int i = 0; i < users.size(); i++){
            User u = users.get(i);
            lista.add(i,new Competitor(new SimpleIntegerProperty(u.getRank()),new SimpleStringProperty(u.getUsername()),new SimpleIntegerProperty(u.getNOGame()),new SimpleIntegerProperty(u.getNOWin()),new SimpleIntegerProperty(u.getPoint())));
        }
        leaderboard.setItems(lista);
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

            friendlistview.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    LobbyController.friendname = (String)friendlistview.getSelectionModel().getSelectedItem();
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
