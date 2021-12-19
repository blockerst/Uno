package gui;
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
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
public class CommonProfileController implements Initializable{

    @FXML
    private ImageView profilepic;
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
    @FXML
    private Button remove;

    private final Image icon = new Image(getClass().getResourceAsStream("logo.png"));
    private ObservableList list;
    private Stage stage;
    private Scene scene;
    private Parent root;
    private Image p1 = new Image(getClass().getResourceAsStream("1.png"));
    private Image p2 = new Image(getClass().getResourceAsStream("2.png"));
    private Image p3 = new Image(getClass().getResourceAsStream("3.png"));
    private Image p4 = new Image(getClass().getResourceAsStream("4.png"));
    private Image p5 = new Image(getClass().getResourceAsStream("5.png"));
    private Image p6 = new Image(getClass().getResourceAsStream("6.png"));
    private Image p7 = new Image(getClass().getResourceAsStream("7.png"));
    private Image p8 = new Image(getClass().getResourceAsStream("8.png"));

    @FXML
    public void removeFriend(ActionEvent e) throws IOException{
        String userName = namelabel.getText();
        boolean state = LoginController.db.removeFriend(LoginController.user,new User(userName));
        stage = (Stage) remove.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        User u = LoginController.db.userPPage(new User(LobbyController.friendname));
        profilepic.setImage(checkProfilePic(u.getImageId()));
        namelabel.setText(u.getUsername());
        joineddatelabel.setText(u.getDate());
        ranklabel.setText(String.valueOf(u.getRank()));
        point.setText(String.valueOf(u.getPoint()));
        games.setText(String.valueOf(u.getPoint()));
        win.setText(String.valueOf(u.getNOWin()));
    }
    public Image checkProfilePic(int id){
        System.out.println("x");
        if(id == 1){
            System.out.println("x");
            return p1;
        }
        else if(id == 2){
            System.out.println("x");
            return p2;
        }
        else if(id == 3){
            System.out.println("x");
            return p3;
        }
        else if(id == 4){
            System.out.println("x");
            return p4;
        }
        else if(id == 5){
            System.out.println("x");
            return p5;
        }
        else if(id == 6){
            System.out.println("x");
            return p6;
        }
        else if(id == 7){
            System.out.println("x");
            return p7;
        }
        else{
            System.out.println("x");
            return p8;
        }
    }
}
