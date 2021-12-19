package gui;

import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class test implements Initializable {
    @FXML
    private ImageView im;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        System.out.println("sddf");
        TranslateTransition transition = new TranslateTransition();
        System.out.println("sddfasfasfasfaf");
        transition.setDuration(Duration.seconds(1));
        System.out.println("sdasdasasdf");
        transition.setNode(im);
        System.out.println("zzzsddf");
        transition.setByX(200);
        System.out.println("aasddf");
        transition.setAutoReverse(true);
        transition.setCycleCount(TranslateTransition.INDEFINITE);
        transition.play();
        System.out.println("xxxxsddf");
    }
}
