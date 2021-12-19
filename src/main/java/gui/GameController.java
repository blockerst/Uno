package gui;

import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GameController implements Initializable {

    @FXML
    private ImageView exit;
    @FXML
    private ImageView topcardimage;
    @FXML
    private ImageView backcard;
    @FXML
    private ImageView card1;
    @FXML
    private ImageView card2;
    @FXML
    private ImageView card3;
    @FXML
    private ImageView card4;
    @FXML
    private ImageView card5;
    @FXML
    private ImageView card6;
    @FXML
    private ImageView card7;
    @FXML
    private Pane pane;
    @FXML
    private FlowPane playercardsflowpane;

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    public void toLobby(MouseEvent e) throws IOException{
        root = FXMLLoader.load(getClass().getResource("Lobby.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Circle clip = new Circle();
        clip.setRadius(exit.getFitHeight()/2);
        clip.setCenterX(exit.getFitWidth()/2);
        clip.setCenterY(exit.getFitHeight()/2);
        exit.setClip(clip);
    }
    @FXML
    public void cardSelection(MouseEvent e) throws IOException{
        ImageView iw = new ImageView();
        System.out.println("aaasd");
        ImageView selected = (ImageView) e.getSource();
        System.out.println("asdawda");
        System.out.println("aaaaaaaaaaaaaaaaaaaa");
        iw.setImage(selected.getImage());
        System.out.println("asasasd");
        System.out.println("e x: "+(selected.getX()));
        iw.setX(selected.getX()+597);
        System.out.println("iw x: " + iw.getX());
        iw.setY(678);
        System.out.println("iw y: " + iw.getY());
        iw.setFitHeight(selected.getFitHeight());
        iw.setFitWidth(selected.getFitWidth());
        TranslateTransition transition = new TranslateTransition();
        System.out.println("sddfasfasfasfaf");
        transition.setDuration(Duration.seconds(1));
        System.out.println("sdasdasasdf");
        transition.setNode(iw);
        System.out.println("zzzsddf");
        System.out.println(topcardimage.getX());
        System.out.println(iw.getX());
        Bounds boundsInScene = iw.localToScene(iw.getBoundsInLocal());
        System.out.println("bx:"+boundsInScene.getMinX());
        transition.setByX(696-boundsInScene.getMinX());
        transition.setByY(-295);
        ScaleTransition scaleTransition = new ScaleTransition();
        scaleTransition.setDuration(Duration.seconds(1));
        scaleTransition.setNode(iw);
        scaleTransition.setByX(.99);
        scaleTransition.setByY(.136);
        System.out.println("aasddf");
        transition.play();
        scaleTransition.play();
        System.out.println("xxxxsddf");
        pane.getChildren().add(iw);
        playercardsflowpane.getChildren().remove(e.getSource());
        System.out.println("qqqq");
        System.out.println(iw.getX());
    }
    @FXML
    public void drawCard(MouseEvent e) throws IOException{
        System.out.println("asd");
        ImageView iw = (ImageView) e.getSource();
        System.out.println("d");
        double fromx = iw.getX();
        double fromy = iw.getY();
        double tox = card1.getX();
        double toy = card1.getY();
        System.out.println("a");
        TranslateTransition translate = new TranslateTransition();
        translate.setDuration(Duration.millis(500));
        translate.setToX(tox);
        translate.setToY(toy);
        translate.setNode(iw);
        System.out.println("xx");
        translate.play();
        System.out.println("<<");
    }
}
