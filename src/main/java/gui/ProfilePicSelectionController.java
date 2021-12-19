package gui;

import Database.ConnectionSql;
import Database.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ProfilePicSelectionController implements Initializable {

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
    private Image p1 = new Image(getClass().getResourceAsStream("1.png"));
    private Image p2 = new Image(getClass().getResourceAsStream("2.png"));
    private Image p3 = new Image(getClass().getResourceAsStream("3.png"));
    private Image p4 = new Image(getClass().getResourceAsStream("4.png"));
    private Image p5 = new Image(getClass().getResourceAsStream("5.png"));
    private Image p6 = new Image(getClass().getResourceAsStream("6.png"));
    private Image p7 = new Image(getClass().getResourceAsStream("7.png"));
    private Image p8 = new Image(getClass().getResourceAsStream("8.png"));

    public void selectProfilePic(MouseEvent e) throws IOException {
        ImageView profilepic = (ImageView) e.getSource();
        int id = Integer.parseInt(profilepic.getId().substring(3));
        LoginController.db.imageIdOperation(LoginController.user,id);
        id = LoginController.db.getImageId(LoginController.user);
        LoginController.profilePic = profilepic.getImage();
        LoginController.profilePic = checkProfilePic(id);
        root = FXMLLoader.load(getClass().getResource("Lobby.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Circle clip = new Circle();
        clip.setRadius(pic1.getFitHeight()/2);
        clip.setCenterX(pic1.getFitWidth()/2);
        clip.setCenterY(pic1.getFitHeight()/2);
        pic1.setClip(clip);
        Circle clip2 = new Circle();
        clip2.setRadius(pic1.getFitHeight()/2);
        clip2.setCenterX(pic1.getFitWidth()/2);
        clip2.setCenterY(pic1.getFitHeight()/2);
        pic2.setClip(clip2);
        Circle clip3 = new Circle();
        clip3.setRadius(pic1.getFitHeight()/2);
        clip3.setCenterX(pic1.getFitWidth()/2);
        clip3.setCenterY(pic1.getFitHeight()/2);
        pic3.setClip(clip3);
        Circle clip4 = new Circle();
        clip4.setRadius(pic1.getFitHeight()/2);
        clip4.setCenterX(pic1.getFitWidth()/2);
        clip4.setCenterY(pic1.getFitHeight()/2);
        pic4.setClip(clip4);
        Circle clip5 = new Circle();
        clip5.setRadius(pic1.getFitHeight()/2);
        clip5.setCenterX(pic1.getFitWidth()/2);
        clip5.setCenterY(pic1.getFitHeight()/2);
        pic5.setClip(clip5);
        Circle clip6 = new Circle();
        clip6.setRadius(pic1.getFitHeight()/2);
        clip6.setCenterX(pic1.getFitWidth()/2);
        clip6.setCenterY(pic1.getFitHeight()/2);
        pic6.setClip(clip6);
        Circle clip7 = new Circle();
        clip7.setRadius(pic1.getFitHeight()/2);
        clip7.setCenterX(pic1.getFitWidth()/2);
        clip7.setCenterY(pic1.getFitHeight()/2);
        pic7.setClip(clip7);
        Circle clip8 = new Circle();
        clip8.setRadius(pic1.getFitHeight()/2);
        clip8.setCenterX(pic1.getFitWidth()/2);
        clip8.setCenterY(pic1.getFitHeight()/2);
        pic8.setClip(clip8);
    }
}
