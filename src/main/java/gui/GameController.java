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

import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class GameController implements Initializable {

    private Game g;
    private int botNumber;
    private Player currPlayer;
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
        timerlabel = new Label();
        timerlabel.setText("15");
        Bot b1 = new Bot("Artur");
        Bot b2 = new Bot("Thomas");
        Bot b3 = new Bot("Osman");
        Bot b4 = new Bot("Alexa");
        Bot b5 = new Bot("Siri");
        botNumber = 3;
        ArrayList<Player> list = new ArrayList<>(botNumber+1);
        int temp = botNumber;
        currPlayer = new Player("Anıl");
        list.add(currPlayer);
        if( temp == 5){ list.add(b5); temp--;}
        if( temp == 4){ list.add(b4); temp--;}
        if( temp == 3){ list.add(b3); temp--;}
        if( temp == 2){ list.add(b2); temp--;}
        if( temp == 1){ list.add(b1); temp--;}
        g = new Game(list);

        for( int i = 0; i < g.getPlayer(0).getHand().size();i++) {
            ImageView card = new ImageView();
            Rectangle rect = new Rectangle(57,79);
            rect.setArcHeight(15);
            rect.setArcWidth(15);
            card.setClip(rect);
            card.setImage(g.getPlayer(0).getHand().get(i).makeImage());
            card.setId(g.getPlayer(0).getHand().get(i).getImageID());
            System.out.println(card.getId());
            card.setFitHeight(cardHeight);
            card.setFitWidth(cardWidth);

            playercardsflowpane.getChildren().add(card);
            card.setOnMouseClicked(mouseHandler);
        }

        topcardimage.setImage(g.getTopCard().makeImage());

        Rectangle rect = new Rectangle(95,150);//ToDo
        rect.setArcHeight(20);
        rect.setArcWidth(20);
        topcardimage.setClip(rect);

        clip.setRadius(exit.getFitHeight()/2);
        clip.setCenterX(exit.getFitWidth()/2);
        clip.setCenterY(exit.getFitHeight()/2);
        exit.setClip(clip);
        PlayThread pt = new PlayThread();
        pt.start();
    }
    /*
    public void drawMany(int x){
        for(int i = 0; i < x; i++){
            ImageView iw = new ImageView();
            ImageView selected = backcard;
            iw.setFitWidth(selected.getFitWidth());
            iw.setFitHeight(selected.getFitHeight());
            iw.setLayoutX(selected.getLayoutX());
            iw.setLayoutY(selected.getLayoutY());
            iw.setImage(selected.getImage());
            iw.setId(selected.getId());
            pane.getChildren().add(iw);
            TranslateTransition translate = new TranslateTransition();
            translate.setDuration(Duration.seconds(1));
            translate.setToX(getLastCardImageView().getLayoutX()+playercardsflowpane.getLayoutX()-iw.getLayoutX()-cardWidth/2+67);
            translate.setToY(playercardsflowpane.getLayoutY()-iw.getLayoutY()-cardHeight/2);
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
                    iw2.setImage(g.getDeck().getTopCard().makeImage());
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
    }//ToDo*/

    public void playSelected(ImageView selected){
        boolean found = false;
        int i;
        for( i = 0; !found && i < g.getPlayer(0).getHand().size() ;i++){
            if(g.getPlayer(0).getHand().get(i).getImageID().equals(selected.getId())){ found = true; }
            System.out.println(selected.getId());
            System.out.println(g.getPlayer(0).getHand().get(i).getImageID());
        }
        System.out.println(g.getPlayer(0).getHand().get(i-1));
        //
        if( g.whoseTurn() == 0 && found && g.getTopCard().isPlayable(g.getPlayer(0).getHand().get(i-1))) {
            ImageView iw = new ImageView();
            iw.setImage(selected.getImage());

            iw.setX((selected).getLayoutX() + playercardsflowpane.getLayoutX());
            iw.setY(playercardsflowpane.getLayoutY());
            iw.setFitHeight(selected.getFitHeight());
            iw.setFitWidth(selected.getFitWidth());
            TranslateTransition transition = new TranslateTransition();

            transition.setDuration(Duration.seconds(1));
            transition.setNode(iw);

            System.out.println(topcardimage.getX());
            System.out.println(iw.getX());
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

            playercardsflowpane.getChildren().remove(selected);
            g.play(i-1,0);

            topcardimage.setImage(iw.getImage());
        }
    }//ToDo

    EventHandler mouseHandler = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {

            ImageView selected = (ImageView) mouseEvent.getSource();
            playSelected(selected);
            /*boolean found = false;
            int i;
            for( i = 0; !found && i < g.getPlayer(0).getHand().size() ;i++){
                if(g.getPlayer(0).getHand().get(i).getImageID().equals(selected.getId())){ found = true; }
                System.out.println(selected.getId());
                System.out.println(g.getPlayer(0).getHand().get(i).getImageID());
            }
            System.out.println(g.getPlayer(0).getHand().get(i-1));
            //
            if( g.whoseTurn() == 0 && found && g.getTopCard().isPlayable(g.getPlayer(0).getHand().get(i-1))) {
                ImageView iw = new ImageView();
                iw.setImage(selected.getImage());

                iw.setX(((ImageView) mouseEvent.getSource()).getLayoutX() + playercardsflowpane.getLayoutX());
                iw.setY(playercardsflowpane.getLayoutY());
                iw.setFitHeight(selected.getFitHeight());
                iw.setFitWidth(selected.getFitWidth());
                TranslateTransition transition = new TranslateTransition();

                transition.setDuration(Duration.seconds(1));
                transition.setNode(iw);

                System.out.println(topcardimage.getX());
                System.out.println(iw.getX());
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

                playercardsflowpane.getChildren().remove(mouseEvent.getSource());
                g.play(i-1,0);

                topcardimage.setImage(iw.getImage());
            }*/
        }
    };

    public void botPlay(){
        int cardIndex;
        cardIndex = g.botChooseCard();
        if(cardIndex == -1){
            g.draw();
        }
        if( cardIndex >= 0){
            if(g.botPlay(g.botChooseCard())){
                System.out.println("Card played");
            }
            else{
                System.out.println("Couldn't play card: " + g.botChooseCard());
            }
        }
        if (g.getStackedPlus() != 0) {
            if( !g.hasPlus(g.getPlayerNo())){
                g.draw();
            }
        }
    }
    /*
    while( !g.isOver() ){
        System.out.println(g.getPlayer(0).getHand());
        System.out.println(g.getPlayer(1).getHand());
        System.out.println(g.getPlayer(2).getHand());
        System.out.println(g.getTopCard());

        System.out.print("Turn of player: " + g.whoseTurn()+ "\n");
        if( g.whoseTurn() == 0) {
            System.out.print("Pick a card to play(-1 to draw): ");

            cardIndex = sc.nextInt();
            System.out.println();
        }
        else{
            cardIndex = g.botChooseCard();
        }
        if(cardIndex == -1){
            g.draw();
        }

        if( cardIndex >= 0 && g.whoseTurn() == 0){

            if (g.play(cardIndex, 0)) {
                System.out.println("Card played succesfully, next player: " + g.whoseTurn());
            }
            else{
                System.out.println("This card can not be played. Try again.");
            }
            if (g.getStackedPlus() != 0) {
                if( !g.hasPlus(g.getPlayerNo())){
                    g.draw();
                }
            }
        }
        else{
            if( cardIndex >= 0){

                if(g.botPlay(g.botChooseCard())){
                    System.out.println("Card played");
                }
                else{
                    System.out.println("Couldn't play card: " + g.botChooseCard());
                }
            }
            if (g.getStackedPlus() != 0) {
                if( !g.hasPlus(g.getPlayerNo())){
                    g.draw();
                }
            }
        }
    */

    public void play(){
        while (!g.isOver()) {
            if (g.whoseTurn() == 0) {
                notPlayed();
            }
            else{
                timerlabel.setText("15");
                botPlay();
            }
        }
    }

    private class PlayThread extends Thread{

        @Override
        public void run() {
            play();
        }
    }

    public void notPlayed() {
        long start = System.currentTimeMillis();
        long end = System.currentTimeMillis();
        long time = end - start;
        while(time < 15000){
            end = System.currentTimeMillis();
            time = end - start;
            timerlabel.setText(""+(15000-time)/1000);
        }
        if( time >= 15000){ g.draw();
            ImageView iw = new ImageView();
            iw.setImage(g.getPlayer(0).getHand().get(g.getPlayer(0).getHand().size()-1).makeImage());
            draw(iw); }
    }

    /*
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
    }*/

    public void draw(ImageView imageView){
        if( g.whoseTurn() == 0) {
            ImageView selected = imageView;
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
                    iw2.setImage(imageView.getImage());
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
            g.draw();
        }
    }

    @FXML
    public void drawCard(MouseEvent e) throws IOException{
        //if( g.whoseTurn() == 0) {
        ImageView selected = (ImageView) e.getSource();
        draw(selected);
            /*ImageView iw = new ImageView();
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
            g.draw();
        }*/
    }
    public ImageView getLastCardImageView(){
        return (ImageView) playercardsflowpane.getChildren().get(playercardsflowpane.getChildren().size()-1);
    }

    public void updateTopCard(){

    }
}