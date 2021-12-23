package Game;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * @(#)deneme1.java
 *
 *
 * @author 
 * @version 1.00 2021/10/20
 */

public class deneme1 {
    public static void main(String[] args) {
        /*Deck deck = new Deck(true);
        System.out.println(deck.toString());
        System.out.println( "**********" );
        deck.getTopCard();
        System.out.println(deck.toString());*/

        /*Card c = new Card( "Yellow", 5, false, 0);
        Card l;
        Deck m = new Deck(false);
        for(int i = 0; i < 15; i++){
            l = new Card("Green", i,i>10,0);
            m.add(l);
        }

        System.out.println(m);
        for( int i = 0; i <15; i++){
            System.out.println(c.isPlayable(m.getTopCard()));
        }*/
        Scanner sc = new Scanner(System.in);
        Player p1,p2,p3;
        Player b1,b2,b3;
        p1 = new Player("Anıl");
        b1 = new Player("Artur");
        b2 = new Player("Thomas");
        b3 = new Player("Bot");
        p2 = new Player("Oğuz");
        p3 = new Player("Göktuğ");
        ArrayList<Player> players = new ArrayList<>(2);
        players.add(p1);
        players.add(b1);
        players.add(b2);

        //Game g = new Game(players);
        //g.getDeck().getID();
        //System.out.println(g.getDeck());

        /*int cardIndex;
        while( !g.isOver() ){
            /*System.out.println(g.getPlayer(0).getHand());
            System.out.println(g.getPlayer(1).getHand());
            System.out.println(g.getPlayer(2).getHand());
            System.out.println(g.getTopCard());*/

            /*System.out.print("Turn of player: " + g.whoseTurn()+ "\n");
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

                if (g.play(cardIndex,0)) {
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

        }*/
    }
}
