package Game;

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


        Player p1,p2,p3;
        p1 = new Player("Anıl");
        p2 = new Player("Oğuz");
        p3 = new Player("Göktuğ");
        ArrayList<Player> players = new ArrayList<>(3);
        players.add(p1);
        players.add(p2);
        players.add(p3);
        Game g = new Game(players);
        System.out.println(g.getPlayer(0).getHand());
        System.out.println(g.getPlayer(1).getHand());
        System.out.println(g.getPlayer(2).getHand());
        System.out.println(g.getTopCard());
    }
}
