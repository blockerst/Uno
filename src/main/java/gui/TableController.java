package gui;
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

public class TableController implements Initializable{
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<Competitor> list = FXCollections.observableArrayList(new Competitor(new SimpleIntegerProperty(1),new SimpleStringProperty("asd"),new SimpleIntegerProperty(3),new SimpleIntegerProperty(5),new SimpleIntegerProperty(6)));
        System.out.println("sdv");
        rank.setCellValueFactory(new PropertyValueFactory<>("rank"));
        System.out.println("sdv");
        plyrnam.setCellValueFactory(new PropertyValueFactory<>("name"));
        System.out.println("sdv");
        plyrgamenum.setCellValueFactory(new PropertyValueFactory<>("playedGameNum"));
        System.out.println("sdv");
        gameswon.setCellValueFactory(new PropertyValueFactory<>("gamesWon"));
        System.out.println("sdv");
        point.setCellValueFactory(new PropertyValueFactory<>("point"));
        System.out.println("sdv");
        leaderboard.setItems(list);
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
    public void toLobby(ActionEvent e) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Lobby.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
