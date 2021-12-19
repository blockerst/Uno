package Game;

import java.util.ArrayList;

public class Player {
    private String name;
    private ArrayList<Card> hand;
    private boolean isUno;
    private int greenCards;
    private int redCards;
    private int blueCards;
    private int yellowCards;

    public Player(String name){
        hand = new ArrayList<>(50);
        this.name = name;
        isUno = false;
        greenCards = 0;
        redCards = 0;
        blueCards = 0;
        yellowCards = 0;
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
        if( card.getNumber() != 13 && card.getNumber() != 14){
            if( card.getColor().equals("Green")){ greenCards++;}
            else if( card.getColor().equals("Red")){ redCards++;}
            else if( card.getColor().equals("Blue")){ blueCards++;}
            else if( card.getColor().equals("Yellow")){ yellowCards++;}
        }
    }

    public void playCard(Card c){
        if( hand.indexOf(c) != -1) {
            hand.remove(hand.indexOf(c));
        }
        if( c.getNumber() != 13 && c.getNumber() != 14){
            if( c.getColor().equals("Green")){ greenCards--;}
            else if( c.getColor().equals("Red")){ redCards--;}
            else if( c.getColor().equals("Blue")){ blueCards--;}
            else if( c.getColor().equals("Yellow")){ yellowCards--;}
        }
    }

    public String getMostColor(){
        int max = greenCards;
        if( max < redCards){ max = redCards;}
        if( max < blueCards){ max = blueCards;}
        if( max < yellowCards){ max = yellowCards;}

        if( max == greenCards){ return "Green";}
        if( max == redCards){ return "Red";}
        if( max == blueCards){ return "Blue";}
        return "Yellow";
    }

    public void setHand(ArrayList<Card> hand) {
        this.hand = hand;
    }

}
