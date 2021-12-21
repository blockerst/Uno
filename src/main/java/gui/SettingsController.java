package gui;

import Database.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import org.controlsfx.control.action.Action;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SettingsController implements Initializable{

    @FXML
    private Button logout;
    @FXML
    private CheckBox darkmodethemecheckbox;
    @FXML
    private Button seefriendreq;
    @FXML
    private TextField oldpassfield;
    @FXML
    private TextField newpassfield;
    @FXML
    private TextField newpassfieldagain;
    @FXML
    private TextField newnamfield;
    @FXML
    private Button changepass;
    @FXML
    private Button changenam;
    @FXML
    private Label passcorrectionlabel;
    @FXML
    private Label changenamlabel;
    @FXML
    private TextField oldnamfield;
    @FXML
    private ImageView requımg;
    @FXML
    private ImageView userimg;
    @FXML
    private ImageView passimg;
    @FXML
    private ImageView logimg;

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    public void toLogin(MouseEvent e) throws IOException {
        LoginController.db.isOnlineOperation(LoginController.user, false);
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        stage.close();
        root = FXMLLoader.load(getClass().getResource("LoginPage.fxml"));
        stage = LoginController.getLobbyStage();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void setDarkMode(ActionEvent e) throws IOException{
        if(darkmodethemecheckbox.isSelected()){
            LoginController.db.setDarkThemeDB(LoginController.user,true);
        }
        else{
            LoginController.db.setDarkThemeDB(LoginController.user,false);
        }
    }
    @FXML
    public void seeFriendReq(MouseEvent e) throws IOException{
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("FriendshipRequest.fxml"));
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void changePass(MouseEvent e) throws IOException{
        String oldpass = oldpassfield.getText();
        String newpass = newpassfield.getText();
        String newpassagain = newpassfieldagain.getText();
        System.out.println(newpass.equals(newpassagain));
        if(newpass.equals(newpassagain) && LoginController.db.changePassword(new User(LoginController.user.getUsername(),oldpass),new User(LoginController.user.getUsername(),newpass))){
            passcorrectionlabel.setText("Password changed!");
            passcorrectionlabel.setTextFill(Color.web("#15d624"));
            passcorrectionlabel.setOpacity(1);
            passcorrectionlabel.setVisible(true);
        }
        else{
            passcorrectionlabel.setText("Correct your inputs!");
            passcorrectionlabel.setTextFill(Color.web("#d44217"));
            passcorrectionlabel.setOpacity(1);
            passcorrectionlabel.setVisible(true);
        }
    }
    @FXML
    public void changeUserNam(MouseEvent e) throws IOException{
        String nam = newnamfield.getText();
        if(LoginController.db.changeUsername(LoginController.user,nam)){
            passcorrectionlabel.setText("Username changed!");
            passcorrectionlabel.setVisible(true);
            LoginController.user.setUsername(nam);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(LoginController.db.getDarkThemeDB(LoginController.user)){
            darkmodethemecheckbox.setSelected(true);
        }
        else{
            darkmodethemecheckbox.setSelected(false);
        }
        Rectangle rec3 = new Rectangle(requımg.getFitWidth()-1, requımg.getFitHeight()-114);
        rec3.setArcHeight(10);
        rec3.setArcWidth(10);
        requımg.setClip(rec3);
        Rectangle rec1 = new Rectangle(requımg.getFitWidth()-1, requımg.getFitHeight()-114);
        rec1.setArcHeight(10);
        rec1.setArcWidth(10);
        passimg.setClip(rec1);
        Rectangle rec2 = new Rectangle(requımg.getFitWidth()-1, requımg.getFitHeight()-114);
        rec2.setArcHeight(10);
        rec2.setArcWidth(10);
        userimg.setClip(rec2);
        Rectangle rec4 = new Rectangle(logimg.getFitWidth()-11, logimg.getFitHeight()-213);
        rec4.setArcHeight(10);
        rec4.setArcWidth(10);
        logimg.setClip(rec4);



    }
}
