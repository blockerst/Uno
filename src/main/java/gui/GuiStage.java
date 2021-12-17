package gui;

import Database.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class GuiStage extends Application {

    public static void main(String[] args) {
        launch(args);
        LoginController.db.isOnlineOperation(LoginController.user, false);
    }

    public void start(Stage stage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("LoginPage.fxml"));
        Scene scene = new Scene(root,600,365);
        stage.setTitle("UNO");
        stage.setScene(scene);
        Image icon = new Image(getClass().getResourceAsStream("logo.png"));
        stage.getIcons().add(icon);
        stage.show();

        LobbyController.checkOnline(stage);
    }
}
