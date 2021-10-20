/**
 * @(#)Deck.java
 *
 *
 * @author 
 * @version 1.00 2021/10/20
 */
import java.lang.Math;
import java.util.ArrayList;
public class Deck {
	ArrayList<Card> deck;
    public Deck(boolean fullDeck){
    	deck = new ArrayList<Card>();
    	if(fullDeck){
    		initialize();
    		shuffle();
    	}
    }
    public void initialize(){
    	for(int i = 0; i < 4; i++){
    		Card c = new Card(0,i);
    		deck.add(c);
    	}
    	for(int i = 0; i < 4; i++){
    		for(int k = 1; k <= 12; k++){
    			Card c = new Card(k,i);
    			deck.add(c);
    			deck.add(c);
    		}
    	}
    	for(int i = 0; i < 4; i ++){
    		Card c = new Card(13,4);
    		Card k = new Card(14,5);
    		deck.add(c);
    		deck.add(k);
    	}
    }
    public void shuffle(){
    	for(int i = 0; i < 1000; i++){
    		int random1 = (int) (Math.random() * (deck.size() - 1));
    		int random2 = (int) (Math.random() * (deck.size() - 1));
    		Card c = deck.remove(random1);
    		deck.add(random2,c);
    	}
    }
    public String deckToString(){
    	String s = "";
    	for(int i = 0; i < deck.size(); i++){
    		s += deck.get(i).toString() + "\n";
    	}
    	return s;
    }
}