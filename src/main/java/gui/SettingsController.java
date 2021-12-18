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
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.controlsfx.control.action.Action;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SettingsController implements Initializable {

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

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    public void toLogin(ActionEvent e) throws IOException {
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
    public void seeFriendReq(ActionEvent e) throws IOException{
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("FriendshipRequest.fxml"));
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void changePass(ActionEvent e) throws IOException{
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
    public void changeUserNam(ActionEvent e) throws IOException{
        String nam = newnamfield.getText();
        if(LoginController.db.changeUsername(LoginController.user,nam)){
            changenamlabel.setText("Username changed!");
            changenamlabel.setVisible(true);
            LoginController.user.setUsername(nam);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList list = FXCollections.observableArrayList();
    }
}
