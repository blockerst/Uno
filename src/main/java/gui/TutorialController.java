package gui;

import Game.*;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.Glow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.lang.annotation.Inherited;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class TutorialController implements Initializable {
    @FXML
    private ImageView exit;
    @FXML
    private FlowPane botcardsflowpane;
    @FXML
    private FlowPane playercardsflowpane;
    @FXML
    private Label usernam;
    @FXML
    private Label botcardnum;
    @FXML
    private Circle colorindicator;
    @FXML
    private ImageView topcardimage;
    @FXML
    private Pane pane;
    @FXML
    private ImageView backcard;
    @FXML
    private ImageView profilepic;
    @FXML
    private ImageView botprofilepic;

    private Glow glow;
    private final int cardWidth = 57;
    private final int cardHeight = 79;

    private Stage stage;
    private Scene scene;
    private Parent root;

    private Game g;
    private Player currPlayer;


    @FXML
    public void toLobby(MouseEvent e) throws IOException {
        g.setOver(true);

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
        Player b1 = new Player("Bot");
        usernam.setText(LoginController.user.getUsername());
        profilepic.setImage(LoginController.profilePic);
        glow = new Glow();
        glow.setLevel(10);
        profilepic.setEffect(glow);
        ArrayList<Player> myList = new ArrayList<>(2);
        currPlayer = new Player("Anıl");
        myList.add(currPlayer);
        myList.add(b1);
        String darkk;
        darkk = "Dark";

        g = new Game(myList,darkk);

        for (int i = 0; i < g.getPlayer(1).getHand().size(); i++) {
            ImageView card = new ImageView();
            card.setImage(g.getPlayer(1).getHand().get(i).makeImage());
            card.setId(g.getPlayer(1).getHand().get(i).getImageID());

            card.setFitHeight(cardHeight);
            card.setFitWidth(cardWidth);

            botcardsflowpane.getChildren().add(card);
            card.setOnMouseClicked(mouseHandler);
        }

        for (int i = 0; i < g.getPlayer(0).getHand().size(); i++) {
            ImageView card = new ImageView();
            card.setImage(g.getPlayer(0).getHand().get(i).makeImage());
            card.setId(g.getPlayer(0).getHand().get(i).getImageID());

            card.setFitHeight(cardHeight);
            card.setFitWidth(cardWidth);

            playercardsflowpane.getChildren().add(card);
            card.setOnMouseClicked(mouseHandler);
        }

        topcardimage.setImage(g.getTopCard().makeImage());
        indicatorUpdate();
        PlayThread pt = new PlayThread();
        pt.start();
    }

    private class PlayThread extends Thread {
        @Override
        public void run() {
            play();
            if( g.isOver()){
                System.out.println("Congrats you won!");
                //LoginController.db.incrementPoint(LoginController.user,100);
                stop();
                exit.getOnMouseClicked();
            }
        }
    }

    public void play() {
        while (!g.isOver()) {
            if (g.whoseTurn() == 0) {
                profilepic.setEffect(glow);
                botprofilepic.setImage(null);
                //notPlayed();
                //updateHand();
            } else {
                profilepic.setEffect(null);
                botprofilepic.setEffect(glow);
                //timerlabel.setText("15");
                botPlay();
            }
        }
    }

    public void cardSelection(MouseEvent e) throws IOException{
        ImageView selected = (ImageView) e.getSource();
        playSelected(selected);
        boolean found = false;
        int i;
        for (i = 0; !found && i < g.getPlayer(0).getHand().size(); i++) {
            if (g.getPlayer(0).getHand().get(i).getImageID().equals(selected.getId())) {
                found = true;
            }
        }
        System.out.println("30");
        //
        if (g.whoseTurn() == 0 && found && g.getTopCard().isPlayable(g.getPlayer(0).getHand().get(i - 1))) {
            ImageView iw = new ImageView();
            selected = (ImageView) e.getSource();
            iw.setImage(selected.getImage());
            System.out.println("23");
            iw.setX(((ImageView) e.getSource()).getLayoutX() + playercardsflowpane.getLayoutX());
            iw.setY(playercardsflowpane.getLayoutY());
            iw.setFitHeight(selected.getFitHeight());
            iw.setFitWidth(selected.getFitWidth());
            TranslateTransition transition = new TranslateTransition();
            transition.setDuration(Duration.seconds(1));
            transition.setNode(iw);
            Bounds boundsInScene = iw.localToScene(iw.getBoundsInLocal());
            transition.setToX(topcardimage.getLayoutX() - boundsInScene.getMinX() + 21);
            transition.setToY(topcardimage.getLayoutY() - playercardsflowpane.getLayoutY() + 28.5);
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
            playercardsflowpane.getChildren().remove(selected);
            g.play(i - 1, 0);
            indicatorUpdate();
        }
    }

    public void botPlay() {
        int cardIndex;
        cardIndex = g.botChooseCard();
        if (cardIndex == -1) {
            g.draw();
            //updateHand();
        }
        if (cardIndex >= 0) {
            if (g.botPlay(g.botChooseCard())) {
                System.out.println("Card played");
                topcardimage.setImage(g.getTopCard().makeImage());
                indicatorUpdate();
                updateHand();
            } else {
                System.out.println("Couldn't play card: " + g.botChooseCard());
            }
        }
        if (g.getStackedPlus() != 0) {
            if (!g.hasPlus(g.getPlayerNo())) {
                g.draw();
            }
        }
        updateLastCard();
    }

    EventHandler mouseHandler = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            ImageView selected = (ImageView) mouseEvent.getSource();
            playSelected(selected);
        }
    };

    @FXML
    public void drawCard(MouseEvent e) throws IOException {
        //if( g.whoseTurn() == 0) {
        ImageView selected = (ImageView) e.getSource();
        selected.setImage(backcard.getImage());
        draw(selected);
    }

    public void draw(ImageView imageView) {
        if (g.whoseTurn() == 0) {
                ImageView selected = imageView;
                ImageView iw = new ImageView();
                g.draw();
                iw.setFitWidth(selected.getFitWidth());
                iw.setFitHeight(selected.getFitHeight());
                iw.setLayoutX(selected.getLayoutX());
                iw.setLayoutY(selected.getLayoutY());
                iw.setImage(selected.getImage());

                selected.setId(g.getPlayer(0).getHand().get(g.getPlayer(0).getHand().size() - 1).getImageID());
                iw.setId(selected.getId());
                pane.getChildren().add(iw);
                TranslateTransition translate = new TranslateTransition();
                translate.setDuration(Duration.seconds(1));
                translate.setToX(getLastCardImageView().getLayoutX() + playercardsflowpane.getLayoutX() - iw.getLayoutX() - 21 + 67);
                translate.setToY(playercardsflowpane.getLayoutY() - iw.getLayoutY() - 28.5);
                translate.setNode(iw);
                ScaleTransition scaleTransition = new ScaleTransition();
                scaleTransition.setDuration(Duration.millis(500));
                scaleTransition.setNode(iw);
                scaleTransition.setToX(0.57);
                scaleTransition.setToY(0.58);
                translate.play();
                scaleTransition.play();
                translate.setOnFinished(new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent e) {
                        ImageView iw2 = new ImageView();
                        //iw2.setImage(topcardimage.getImage());
                        iw2.setImage(g.getPlayer(0).getHand().get(g.getPlayer(0).getHand().size() - 1).makeImage());
                        //g.getPlayer(0).getHand().get(g.getPlayer(0).getHand().size()-1).getImage()
                        iw2.setFitWidth(cardWidth);
                        iw2.setFitHeight(cardHeight);
                        iw2.setId(selected.getId());
                        playercardsflowpane.getChildren().add(iw2);
                        pane.getChildren().remove(iw);
                        iw2.setOnMouseClicked(new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent mouseEvent) {
                                mouseHandler.handle(mouseEvent);
                            }
                        });
                    }
                });
        }
    }

    public ImageView getLastCardImageView(){
        return (ImageView) playercardsflowpane.getChildren().get(playercardsflowpane.getChildren().size()-1);
    }

    public void updateLastCard(){
        topcardimage.setImage(g.getTopCard().makeImage());
    }

    public void playSelected(ImageView selected) {
        boolean found = false;
        int i;
        for (i = 0; !found && i < g.getPlayer(0).getHand().size(); i++) {
            if (g.getPlayer(0).getHand().get(i).getImageID().equals(selected.getId())) {
                found = true;
            }
        }
        //
        if (g.whoseTurn() == 0 && found && g.getTopCard().isPlayable(g.getPlayer(0).getHand().get(i - 1))) {
            ImageView iw = new ImageView();
            iw.setImage(selected.getImage());

            iw.setX((selected).getLayoutX() + playercardsflowpane.getLayoutX());
            iw.setY(playercardsflowpane.getLayoutY());
            iw.setFitHeight(selected.getFitHeight());
            iw.setFitWidth(selected.getFitWidth());
            TranslateTransition transition = new TranslateTransition();

            transition.setDuration(Duration.seconds(1));
            transition.setNode(iw);

            Bounds boundsInScene = iw.localToScene(iw.getBoundsInLocal());

            transition.setToX(topcardimage.getLayoutX() - boundsInScene.getMinX() + 21);
            transition.setToY(topcardimage.getLayoutY() - playercardsflowpane.getLayoutY() + 28.5);
            ScaleTransition scaleTransition = new ScaleTransition();
            scaleTransition.setDuration(Duration.seconds(1));
            scaleTransition.setNode(iw);
            scaleTransition.setToX(1.73);
            scaleTransition.setToY(1.72);
            pane.getChildren().add(iw);
            transition.play();
            scaleTransition.play();
            transition.setOnFinished(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    //pane.getChildren().remove(iw);
                    //topcardimage.setImage(iw.getImage());
                    iw.setDisable(true);
                }
            });

            playercardsflowpane.getChildren().remove(selected);
            g.play(i - 1, 0);
            indicatorUpdate();
        }

    }
    public void indicatorUpdate(){
        if( g.getTopCard().getColor().equals("Red")){
            colorindicator.setFill(javafx.scene.paint.Color.RED);
        }
        else if(g.getTopCard().getColor().equals("Yellow")){
            colorindicator.setFill(javafx.scene.paint.Color.YELLOW);
        }
        else if( g.getTopCard().getColor().equals("Green")){
            colorindicator.setFill(javafx.scene.paint.Color.GREEN);
        }
        else{
            colorindicator.setFill(Color.BLUE);
        }
    }

    public void updateHand(){

        if( botcardsflowpane.getChildren().size() < g.getPlayer(0).getHand().size()){
            for( int i = botcardsflowpane.getChildren().size(); i < g.getPlayer(1).getHand().size(); i ++){
                ImageView image = new ImageView();

                image.setImage(g.getPlayer(1).getHand().get(i).makeImage());
                image.setFitHeight(cardHeight);
                image.setFitWidth(cardWidth);

                botcardsflowpane.getChildren().add(image);
            }
        }
    }
}
