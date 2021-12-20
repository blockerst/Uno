package gui;

import Database.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class FriendshipRequestController implements Initializable {
    @FXML
    private ListView listview;

    public void add(ActionEvent e) throws IOException{
        LoginController.db.friendRequestOperation(LoginController.user,new User((String)listview.getSelectionModel().getSelectedItem()),true);
        int selection = listview.getSelectionModel().getSelectedIndex();
        listview.getItems().remove(selection);
    }
    public void delete(ActionEvent e) throws IOException{
        LoginController.db.friendRequestOperation(LoginController.user,new User((String)listview.getSelectionModel().getSelectedItem()),false);
        int selection = listview.getSelectionModel().getSelectedIndex();
        listview.getItems().remove(selection);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<User> requ = LoginController.db.getFriendRequests(LoginController.user);
        if(requ.size() == 0) listview.setVisible(false);
        for(int i = 0; i < requ.size(); i++){
            listview.getItems().add(i,requ.get(i).getUsername());
        }

    }
}
