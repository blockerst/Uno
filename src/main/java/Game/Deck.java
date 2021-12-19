package Game; /**
 * @(#)Deck.java
 *
 *
 * @author 
 * @version 1.00 2021/10/20
 */
import Database.ConnectionSql;

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
	//getters
	public void getID(){
    	for( Card c: deck){
    		System.out.println(c.getImageID() + " " + c.getSpecial());
		}
	}

	public Card getTopCard(){
		Card temp = deck.get(0);
		deck.remove(0);
		return temp;
	}

	//Methods
    public void initialize(){
		String color = "";
		boolean isSpecial;
		String ID = "";
		//ConnectionSql db = ConnectionSql.getInstance().getDa
		//getDark;
		//if( getDark ){ ID = Dark;}

    	for(int i = 0; i < 4; i++){
    		if( i == 0){ color = "Yellow";}
    		else if( i == 1){ color = "Green";}
    		else if( i == 2){ color = "Blue";}
    		else if( i == 3){ color = "Red";}
    		for( int j = 0; j <= 14; j++) {
				if( j < 10 ){
					isSpecial = false;
					ID += color+j;
					Card card = new Card(color, j, isSpecial, "@../cards/" + ID +".png");
					deck.add(card);
					if(j != 0){ deck.add(card); }
				}
				else {
					isSpecial = true;

					if( j < 13){ ID += color;}
					if( j == 10){ ID += "+2";}
					if( j == 11){ ID += "Ban";}
					if( j == 12){ ID += "Reverse";}
					if( j == 13){ ID = "color";}
					if( j == 14){ ID = "+4";}
					Card card = new Card(color, j, isSpecial, "@../cards/" + ID + ".png");
					if( j < 13){ deck.add(card); }
					deck.add(card);
				}
			}
    	}
    }

    public void add(Card c){
    	deck.add(c);
	}

    public void shuffle(){
    	for(int i = 0; i < 1000; i++){
    		int random1 = (int) (Math.random() * (deck.size() - 1));
    		int random2 = (int) (Math.random() * (deck.size() - 1));
    		Card c = deck.remove(random1);
    		deck.add(random2,c);
    	}
    }

    public String toString(){
    	String s = "";
    	for(int i = 0; i < deck.size(); i++){
    		s += deck.get(i).toString() + "\n";
    	}
    	return s;
    }
}