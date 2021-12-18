package gui;

import Database.Reward;
import Database.User;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ProfileController implements Initializable{
    @FXML
    private ImageView selfprofilepic;
    @FXML
    private Label namelabel;
    @FXML
    private Label joineddatelabel;
    @FXML
    private Label ranklabel;
    @FXML
    private Label point;
    @FXML
    private Label games;
    @FXML
    private ScrollPane scrollPpane;
    @FXML
    private AnchorPane anchorpane;
    @FXML
    private Label win;

    private final Image icon = new Image(getClass().getResourceAsStream("logo.png"));
    private ObservableList list;
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    public void seeFriendshipRequest(ActionEvent e) throws IOException {
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("FriendshipRequest.fxml"));
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void sendFriendshipRequest(ActionEvent e) throws IOException {
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("Add_Friend.fxml"));
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        selfprofilepic.setImage(LoginController.profilePic);
        User u = LoginController.db.userPPage(LoginController.user);
        namelabel.setText(u.getUsername());
        joineddatelabel.setText(u.getDate());
        ranklabel.setText(String.valueOf(u.getRank()));
        point.setText(String.valueOf(u.getPoint()));
        games.setText(String.valueOf(u.getPoint()));
        win.setText(String.valueOf(u.getNOWin()));
        ArrayList<Reward> rewards = LoginController.db.getRewards(LoginController.user);
        int x = 0;
        for(int i = 0; i < rewards.size(); i++){
            ImageView iw = new ImageView();
            iw.setFitHeight(108);
            iw.setFitWidth(78);
            iw.setImage(new Image("file:C:\\Users\\Asus\\Desktop\\UNO\\src\\main\\resources\\cards\\"+ rewards.get(i).getName()+".png"));
            iw.setLayoutX(x);
            anchorpane.getChildren().add(iw);
            x += 88;
        }
    }
}
