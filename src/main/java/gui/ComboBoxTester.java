package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class ComboBoxTester extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        System.out.println("35");
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Settings.fxml")));
        System.out.println("4");
        stage = new Stage();
        stage.setTitle("Settings");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.showAndWait();
    }

    public static void main(String[] args){
        launch(args);
    }
}
