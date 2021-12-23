package gui;

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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ComboBoxController implements Initializable {
    @FXML
    private ComboBox<String> comb;

    @FXML
    private Button startCompBut;
    private Stage stage;
    private Scene scene;
    private Parent root;
    public static int number;
    public static String dark;
    @FXML
    public void start(ActionEvent e) throws IOException {
        //int opponentNum = (int) choiceOpp.getValue();
        System.out.println(comb.getSelectionModel().getSelectedItem());
        String selection = comb.getSelectionModel().getSelectedItem();
        dark = "";
        if(LoginController.db.getDarkThemeDB(LoginController.user))
        {
            dark = "dark";
        }
        if(selection.equals("2"))
        {
            number = 2;
            root = FXMLLoader.load(getClass().getResource(dark+"Game2bot.fxml"));
            dark = "Dark";
        }
        else if (selection.equals("1"))
        {
            number = 1;
            root = FXMLLoader.load(getClass().getResource(dark+"Game1bot.fxml"));
            dark = "Dark";
        }
        else if (selection.equals("4"))
        {
            number = 4;
            root = FXMLLoader.load(getClass().getResource(dark+"Game4bot.fxml"));
            dark = "Dark";
        }
        else
        {
            number = 3;
            root = FXMLLoader.load(getClass().getResource(dark+"Game.fxml"));
            dark = "Dark";
        }
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        stage.close();
        stage = LoginController.getLobbyStage();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.show();
    }

    public void select(MouseEvent e) throws IOException{
        number = Integer.parseInt(comb.getSelectionModel().getSelectedItem());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb){
        ObservableList<String> list = FXCollections.observableArrayList("1","2","3","4");
        comb.setItems(list);
        comb.getSelectionModel().selectFirst();
    }

}
