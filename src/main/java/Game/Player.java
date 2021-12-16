package Game;

import java.util.ArrayList;

public class Player {
    private String name;
    private ArrayList<Card> hand;

    public Player(String name){
        hand = new ArrayList<>(50);
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public void addCard( Card card){
        hand.add(card);
    }

    public Card playCard(Card c){
        if( hand.indexOf(c) != -1) {
            hand.remove(hand.indexOf(c));
            return c;
        }
        return null;
    }



    public void setHand(ArrayList<Card> hand) {
        this.hand = hand;
    }

}
