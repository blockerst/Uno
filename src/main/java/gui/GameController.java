package gui;

import Game.*;

import java.awt.*;
import java.awt.Button;
import java.util.*;
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
import javax.swing.*;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.effect.Glow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Timer;


public class GameController implements Initializable {
    private Game g;
    private int botNumber;
    private Player currPlayer;
    private Glow glow;

    @FXML
    private Label bot1cardnum;
    @FXML
    private Label bot2cardnum;
    @FXML
    private Label bot3cardnum;
    @FXML
    private Label bot4cardnum;

    @FXML
    private Circle colorindicator;

    @FXML
    private Label blackimg;
    @FXML
    private Label redimg;
    @FXML
    private Label yellowimg;
    @FXML
    private Label greenimg;

    @FXML
    private ImageView profilepic;

    @FXML
    private Label usernam;

    @FXML
    private ImageView exit;
    @FXML
    private ImageView topcardimage;
    @FXML
    private ImageView backcard;


    @FXML
    private Label timerlabel;
    private final int cardWidth = 57;
    private final int cardHeight = 79;
    @FXML
    private Pane pane;
    @FXML
    private FlowPane playercardsflowpane;

    private Stage stage;
    private Scene scene;
    private Parent root;
    private boolean isDark;

    @FXML
    public void toLobby(MouseEvent e) throws IOException {
        if( g.getPlayer(0).getHand().size() == 0){
            LoginController.db.incrementNOWin(LoginController.user);
            System.out.println("Congrats you won!");
        }
        LoginController.db.incrementPoint(LoginController.user,-10);
        g.setOver(true);

        root = FXMLLoader.load(getClass().getResource("Lobby.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Circle clip = new Circle();
        usernam.setText(LoginController.user.getUsername());
        Player b1 = new Player("Artur");
        Player b2 = new Player("Thomas");
        Player b3 = new Player("Osman");
        Player b4 = new Player("Alexa");
        Player b5 = new Player("Siri");
        botNumber = ComboBoxController.number;

        ArrayList<Player> list = new ArrayList<>(botNumber + 1);
        int temp = botNumber;
        currPlayer = new Player("Anıl");
        list.add(currPlayer);
        if (temp == 5) {
            list.add(b5);
            temp--;
        }
        if (temp == 4) {
            list.add(b4);
            temp--;
        }
        if (temp == 3) {
            list.add(b3);
            temp--;
        }
        if (temp == 2) {
            list.add(b2);
            temp--;
        }
        if (temp == 1) {
            list.add(b1);
            temp--;
        }
        String dark;
        dark = "";
        if(ComboBoxController.dark.equals("dark")){ dark = "Dark";}
        System.out.println(dark);
        g = new Game(list, dark);
        profilepic.setImage(LoginController.profilePic);
        glow = new Glow();
        glow.setLevel(10);
        profilepic.setEffect(glow);
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

        try{
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


        clip.setRadius(exit.getFitHeight() / 2);
        clip.setCenterX(exit.getFitWidth() / 2);
        clip.setCenterY(exit.getFitHeight() / 2);
        exit.setClip(clip);
        PlayThread pt = new PlayThread();
        pt.start();
        Timer myTimer = new Timer();
        myTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                updateHand();
                if( exit.isPressed() ){ g.setOver( true); pt.stop();}
            }
        },1000);
    }

    public class myTimerListener implements ActionListener {
        @Override
        public void actionPerformed(java.awt.event.ActionEvent e) {
            updateHand();
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

    public void drawMany(int cardNum) {
        g.draw();
        int handSize = g.getPlayer(0).getHand().size();
        for (int i = 0; i < cardNum; i++) {
            int temp = i;
            ImageView iw = new ImageView();
            iw.setFitHeight(cardHeight);
            iw.setFitWidth(cardWidth);
            iw.setImage(g.getPlayer(0).getHand().get(handSize-cardNum + temp).makeImage());
            iw.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    mouseHandler.handle(mouseEvent);
                }
            });

        }
    }//ToDo

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
                    pane.getChildren().remove(iw);
                    topcardimage.setImage(iw.getImage());
                    iw.setDisable(true);
                }
            });

            playercardsflowpane.getChildren().remove(selected);
            g.play(i - 1, 0);
            indicatorUpdate();
            profilepic.setEffect(null);
        }

    }//ToDo

    EventHandler mouseHandler = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            ImageView selected = (ImageView) mouseEvent.getSource();
            playSelected(selected);
        }
    };

    public void botPlay() {
        int cardIndex;
        cardIndex = g.botChooseCard();
        if (cardIndex == -1) {
            g.draw();
        }
        if (cardIndex >= 0) {
            if (g.botPlay(g.botChooseCard())) {
                System.out.println("Card played");
                topcardimage.setImage(g.getTopCard().makeImage());
                indicatorUpdate();
            } else {
                System.out.println("Couldn't play card: " + g.botChooseCard());
            }
        }
        if (g.getStackedPlus() != 0) {
            if (!g.hasPlus(g.getPlayerNo())) {
                g.draw();
            }
        }
        /*if(g.whoseTurn() == 1){
            System.out.println(1);
            bot1cardnum.setText((g.getPlayer(g.whoseTurn()).getHand().size() +""));
        }
        if(g.whoseTurn() == 2){
            bot2cardnum.setText((g.getPlayer(g.whoseTurn()).getHand().size() +""));
        }
        if(g.whoseTurn() == 3){
            bot3cardnum.setText((g.getPlayer(g.whoseTurn()).getHand().size() +""));
        }
        if(g.whoseTurn() == 4){
            bot4cardnum.setText((g.getPlayer(g.whoseTurn()).getHand().size() +""));
        }*/
    }

    public void unoClicked(MouseEvent e){
        //if( )
    }

    public void play() {
        while (!g.isOver()) {
            if (g.whoseTurn() == 0) {
                profilepic.setEffect(glow);
                //notPlayed();
                //updateHand();
            } else {
                profilepic.setEffect(null);
                //timerlabel.setText("15");
                botPlay();

            }
        }
    }

    private class PlayThread extends Thread {
        @Override
        public void run() {
            play();
            if( g.getPlayer(0).getHand().size() == 0){
                System.out.println("Congrats you won!");
                LoginController.db.incrementPoint(LoginController.user,100);
                stop();
                exit.getOnMouseClicked();
            }
        }
    }

    public void updateCardNumber(){

    }

    public void notPlayed() {
        long start = System.currentTimeMillis();
        long end = System.currentTimeMillis();
        long time = end - start;
        while (time < 15000 && g.whoseTurn() == 0) {
            end = System.currentTimeMillis();
            time = end - start;
            timerlabel.setText("" + (15000 - time) / 1000);
        }
        if (time >= 15000) {
            if (g.getStackedPlus() != 0) {
                drawMany(g.getStackedPlus());
            }
            ImageView iw = new ImageView();
            iw.setImage(g.getPlayer(0).getHand().get(g.getPlayer(0).getHand().size() - 1).makeImage());
            drawWithoutEvent();
        }
    }

    @FXML
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


    public void draw(ImageView imageView) {
        if (g.whoseTurn() == 0) {
            if (g.getStackedPlus() != 0) {
                drawMany(g.getStackedPlus());
            } else {
                ImageView selected = imageView;
                ImageView iw = new ImageView();
                g.draw();
                iw.setFitWidth(selected.getFitWidth());
                iw.setFitHeight(selected.getFitHeight());
                iw.setLayoutX(selected.getLayoutX());
                iw.setLayoutY(selected.getLayoutY());
                iw.setImage(selected.getImage());

                selected.setId(g.getPlayer(0).getHand().get(g.getPlayer(0).getHand().size()-1).getImageID());
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
    }


    @FXML
    public void drawCard(MouseEvent e) throws IOException {
        //if( g.whoseTurn() == 0) {
        ImageView selected = (ImageView) e.getSource();
        selected.setImage(backcard.getImage());
        draw(selected);
    }
    public void drawWithoutEvent(){
        ImageView selected = (ImageView) backcard;
        selected.setImage(backcard.getImage());
        ImageView iw = new ImageView();
        g.draw();
        iw.setFitWidth(selected.getFitWidth());
        iw.setFitHeight(selected.getFitHeight());
        iw.setLayoutX(selected.getLayoutX());
        iw.setLayoutY(selected.getLayoutY());
        iw.setImage(selected.getImage());
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

    public ImageView getLastCardImageView(){
        return (ImageView) playercardsflowpane.getChildren().get(playercardsflowpane.getChildren().size()-1);
    }

    public void updateHand(){
        System.out.println(playercardsflowpane.getChildren().size());
        if( playercardsflowpane.getChildren().size() < g.getPlayer(0).getHand().size()){
            for( int i = playercardsflowpane.getChildren().size(); i < g.getPlayer(0).getHand().size(); i ++){
                System.out.println("Updated");
                ImageView image = new ImageView();
                System.out.println( g.getPlayer(0).getHand().get(i).getImageID());
                image.setImage(g.getPlayer(0).getHand().get(i).makeImage());
                image.setFitHeight(cardHeight);
                image.setFitWidth(cardWidth);

                playercardsflowpane.getChildren().add(image);
            }
        }
    }
}