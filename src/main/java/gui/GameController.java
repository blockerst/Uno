package gui;

import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
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
    @FXML
    private ImageView profilepic;
    @FXML
    private Label usernam;
    @FXML
    private Label blackimg;
    @FXML
    private Label redimg;
    @FXML
    private Label yellowimg;
    @FXML
    private Label greenimg;

    private Stage stage;
    private Scene scene;
    private Parent root;
    private final double cardwidth = 57;
    private final double cardheight = 79;

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
        usernam.setText(LoginController.user.getUsername());
        profilepic.setImage(LoginController.profilePic);
        try {
            redimg.setText("goktug");
        }catch (Exception e){}
        try {
            blackimg.setText("Sarp");
        }catch (Exception e){}
        try {
            greenimg.setText("eoguz");
        }catch (Exception e){}
        try {
            yellowimg.setText("anil");
        }catch (Exception e){}


    }
    @FXML
    public void cardSelection(MouseEvent e) throws IOException{
        ImageView iw = new ImageView();
        ImageView selected = (ImageView) e.getSource();
        iw.setImage(selected.getImage());
        iw.setX(((ImageView) e.getSource()).getLayoutX()+playercardsflowpane.getLayoutX());
        iw.setY(playercardsflowpane.getLayoutY());
        iw.setFitHeight(selected.getFitHeight());
        iw.setFitWidth(selected.getFitWidth());
        TranslateTransition transition = new TranslateTransition();
        transition.setDuration(Duration.seconds(1));
        transition.setNode(iw);
        Bounds boundsInScene = iw.localToScene(iw.getBoundsInLocal());
        transition.setToX(topcardimage.getLayoutX()-boundsInScene.getMinX()+21);
        transition.setToY(topcardimage.getLayoutY()-playercardsflowpane.getLayoutY()+28.5);
        ScaleTransition scaleTransition = new ScaleTransition();
        scaleTransition.setDuration(Duration.seconds(1));
        scaleTransition.setNode(iw);
        scaleTransition.setToX(1.73);
        scaleTransition.setToY(1.72);
        transition.play();
        scaleTransition.play();
        pane.getChildren().add(iw);
        playercardsflowpane.getChildren().remove(e.getSource());
        transition.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                topcardimage.setImage(iw.getImage());
            }
        });
    }
    @FXML
    public void drawCard(MouseEvent e) throws IOException{
        ImageView iw = new ImageView();
        ImageView selected = (ImageView) e.getSource();
        iw.setFitWidth(selected.getFitWidth());
        iw.setFitHeight(selected.getFitHeight());
        iw.setLayoutX(selected.getLayoutX());
        iw.setLayoutY(selected.getLayoutY());
        iw.setImage(selected.getImage());
        iw.setId(selected.getId());
        pane.getChildren().add(iw);
        TranslateTransition translate = new TranslateTransition();
        translate.setDuration(Duration.seconds(1));
        translate.setToX(getLastCardImageView().getLayoutX()+playercardsflowpane.getLayoutX()-iw.getLayoutX()-cardwidth/2+67);
        translate.setToY(playercardsflowpane.getLayoutY()-iw.getLayoutY()-cardheight/2);
        translate.setNode(iw);
        ScaleTransition scaleTransition = new ScaleTransition();
        scaleTransition.setDuration(Duration.millis(500));
        scaleTransition.setNode(iw);
        scaleTransition.setToX(0.57);
        scaleTransition.setToY(0.58);
        translate.play();
        scaleTransition.play();
        translate.setOnFinished(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent e){
                ImageView iw2 = new ImageView();
                iw2.setImage(card1.getImage());
                iw2.setFitWidth(cardwidth);
                iw2.setFitHeight(cardheight);
                playercardsflowpane.getChildren().add(iw2);
                pane.getChildren().remove(iw);
                iw2.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        try {
                            cardSelection(mouseEvent);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                });
            }
        });
    }
    public ImageView getLastCardImageView(){
        return (ImageView) playercardsflowpane.getChildren().get(playercardsflowpane.getChildren().size()-1);
    }
}
