package Game; /**
 * @(#)Deck.java
 *
 *
 * @author 
 * @version 1.00 2021/10/20
 */
import Database.ConnectionSql;
import gui.ComboBoxController;

import java.lang.Math;
import java.util.ArrayList;
public class Deck {
	ArrayList<Card> deck;
	String dark;
    public Deck(boolean fullDeck, String isDark){
    	deck = new ArrayList<Card>();
    	if(fullDeck){
    		initialize();
    		shuffle();
    	}
    	dark = isDark;
    }
	//getters
	public void getID(){
		for( Card c: deck){
			System.out.println(c.getImageID());
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
		if( ComboBoxController.dark == null){ ComboBoxController.dark = ""; }
    	for(int i = 0; i < 4; i++){
    		if( i == 0){ color = "Yellow";}
    		else if( i == 1){ color = "Green";}
    		else if( i == 2){ color = "Blue";}
    		else if( i == 3){ color = "Red";}
    		for( int j = 0; j <= 14; j++) {
    			if( j == 10 || j == 14){ continue; }
				if( j < 10 ){
					isSpecial = false;
					ID = color+j;
					if(ComboBoxController.dark.equals("dark")){ ComboBoxController.dark = "Dark";}
					Card card = new Card(color, j, isSpecial, "src/main/resources/cards/"+ ComboBoxController.dark + ID +".png");
					deck.add(card);
					if(j != 0){ deck.add(card); }
				}
				else {
					isSpecial = true;

					if( j < 13){ ID = color;}
					if( j == 10){ ID += "+2";}
					if( j == 11){ ID += "Ban";}
					if( j == 12){ ID += "Reverse";}
					if( j == 13){ ID = "color";}
					if( j == 14){ ID = "+4";}
					Card card = new Card(color, j, isSpecial, "src/main/resources/cards/" + ComboBoxController.dark + ID + ".png");
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