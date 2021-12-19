package Game;

import java.util.ArrayList;

public class Player {
    private String name;
    private ArrayList<Card> hand;
    private boolean isUno;

    public Player(String name){
        hand = new ArrayList<>(50);
        this.name = name;
        isUno = false;
    }
    public String getName() {
        return name;
    }

    public boolean checkUno(){
        if(hand.size() == 1){
            isUno = true;
            return true;
        }
        return false;
    }

    public boolean getUno(){
        return isUno;
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

    public void playCard(Card c){
        if( hand.indexOf(c) != -1) {
            hand.remove(hand.indexOf(c));
        }
    }

    public void setHand(ArrayList<Card> hand) {
        this.hand = hand;
    }

}
