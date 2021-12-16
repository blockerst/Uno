package Game;

import java.util.ArrayList;
public class Game {
    private ArrayList<Player> players;
    private int playerNo;
    private Deck deck;
    private Card topCard;
    private boolean clockwise;
    private ArrayList<Card> cardsOnTable;
    private int stackedPlus;

    public Game(ArrayList<Player> users){
        players = users;
        stackedPlus = 0;
        clockwise = true;
        cardsOnTable = new ArrayList<Card>();
        deck = new Deck(true);
        playerNo = 0;
        topCard = deck.getTopCard();
        distribute();
    }

    public void distribute(){
        for( int i = 0; i < players.size(); i++ ){
            for(int j = 0; j < 7; j++){
                players.get(i).addCard(topCard);
                topCard = deck.getTopCard();
            }
        }
    }
    public boolean isUno( int playerNo ){
        return players.get(playerNo).getHand().size() == 1;
    }

    public int whoseTurn(){
        return playerNo;
    }

    public Card getTopCard(){
        return topCard;
    }

    public Player getPlayer(int index){
        return players.get(index);
    }

    public void botPlay(int index){
        if( index > 0) {//index 0 is real player
            if(clockwise){
                if(index == players.size()-1 && isUno(0)){
                    if( hasPlusOrSkip(index) ){
                        play(index, players.get(index).getHand().get(indexPlusOrSkip(index)));
                    }
                }
            }
        }
    }

    public boolean play(int playerNo,Card c){
        if( this.playerNo == playerNo){
            Card temp = players.get(playerNo).playCard(c);
            if( topCard.isPlayable(temp)) {
                if( temp.getNumber() == 10){ stackedPlus += 2; }
                if( temp.getNumber() == 14){ stackedPlus += 4; }
                cardsOnTable.add(temp);
                if(clockwise){
                    playerNo++;
                    if(playerNo > players.size()){
                        playerNo = 0;
                    }
                    if( temp.getNumber() == 11){
                        playerNo++;
                    }
                }
                else {
                    playerNo--;
                    if(playerNo < 0){
                        playerNo += players.size();
                    }
                    if(temp.getNumber() ==11) playerNo--;
                }
                return true;
            }
            else{
                players.get(playerNo).getHand().add(0,temp);
            }
        }
        return false;
    }

    public void draw( int currPlayer, int number){
        int i;
        for(i = 0; i < number; i++) {
            players.get(currPlayer).addCard(cardsOnTable.get(cardsOnTable.size()));
            cardsOnTable.remove(cardsOnTable.size());
        }
        if( i != 0){ stackedPlus = 0; }
    }

    public boolean isOver(){
        for(int i = 0; i < players.size(); i++){
            if( players.get(i).getHand() == null){ return true; }
        }
        return false;
    }

    public boolean hasPlusOrSkip(int playerNo){
        for( Card c  : players.get(playerNo).getHand() ){
            if(c.getNumber() == 14 || c.getNumber() == 11){ //if user has +4
                return true;
            }
            else if(c.getNumber() == 10 && topCard.isPlayable(c)){//if user has approproite +2
                return true;
            }
        }
        return false;
    }

    /*public boolean hasSkip( int playerNo){
        for( Card c : players.get(playerNo).getHand()){
            if(c.getNumber() == 11){
                return true;
            }
        }
        return false;
    }*/

    public int indexPlusOrSkip(int playerNo){
        for( int i = 0; i < players.get(playerNo).getHand().size(); i++){
            if( players.get(playerNo).getHand().get(i).getNumber() == 10 ||
                    players.get(playerNo).getHand().get(i).getNumber() == 14){
                return i;
            }
        }
        for( int i = 0; i < players.get(playerNo).getHand().size(); i++){
            if( players.get(playerNo).getHand().get(i).getNumber() == 11){
                return i;
            }
        }
        return -1;
    }


}
