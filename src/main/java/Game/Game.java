package Game;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    private ArrayList<Player> players;
    private int playerNo;
    private Deck deck;
    private Card topCard;
    private boolean clockwise;
    private ArrayList<Card> cardsOnTable;
    private int stackedPlus;
    private boolean isOver;

    public Game(ArrayList<Player> users, String isDark){
        players = users;
        stackedPlus = 0;
        clockwise = true;
        cardsOnTable = new ArrayList<Card>(52);
        deck = new Deck(true, isDark);
        playerNo = 0;
        cardsOnTable.add(0,deck.getTopCard());
        topCard = cardsOnTable.get(0);
        distribute();
        isOver = false;
        while( topCard.getSpecial() ){
            changeFirstTopCard();
        }
    }

    public void changeFirstTopCard(){
        Card temp;
        temp = topCard;

        topCard = deck.getTopCard();
        deck.add(temp);
    }

    public void distribute(){
        for( int i = 0; i < players.size(); i++ ){
            for(int j = 0; j < 1; j++){
                players.get(i).addCard(topCard);
                topCard = deck.getTopCard();
            }
        }
    }

    /*public ArrayList<Card> stackedDraw(){

    }*/

    public boolean isUno( int playerNo ){
        return players.get(playerNo).checkUno();
    }

    public int whoseTurn(){
        return playerNo;
    }

    public Card getTopCard(){
        return topCard;
    }

    public Deck getDeck(){
        return deck;
    }

    public int getStackedPlus(){ return stackedPlus;}

    public Player getPlayer(int index){
        return players.get(index);
    }

    public int getPlayerNo(){
        return playerNo;
    }

    public boolean isPlayable(Card c){
        return topCard.isPlayable(c);
    }

    public boolean botPlay(int index){
        if( getPlayer(playerNo).getHand().get(index).getNumber() == 14 ||
            getPlayer(playerNo).getHand().get(index).getNumber() == 13)
        { botChooseColor(getPlayer(playerNo).getHand().get(index)); }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return play(index,playerNo);
    }

    public int botChooseCard(){
        if (isUno(nextPlayer())){
            if (hasPlus(playerNo) || hasSkip(playerNo)){
                return indexPlusOrSkip(playerNo);
            }
        }
        else{
            int i = 0;
            for( Card c: players.get(playerNo).getHand()){
                if( topCard.isPlayable(c) ){ return i;}
                i++;
            }
        }
        return -1;
    }

    public int nextPlayer(){
        int next = playerNo;
        if(clockwise){
            next ++;
            if(next > players.size()-1){
                next = 0;
            }

        }
        else {
            next --;
            if(next < 0){
                next += players.size();
            }
        }
        return next;
    }

    public void botChooseColor(Card c){
        c.setColor(players.get(playerNo).getMostColor());
    }

    public boolean play(int index,int player){
        if( player == playerNo) {
            Card temp = players.get(player).getHand().get(index);
            if (topCard.isPlayable(temp)) {
                if (players.get(player).checkUno()) {
                    isOver = true;
                    return true;
                }
                if (temp.getSpecial()) {
                    if (temp.getNumber() == 10) {
                        stackedPlus += 2;
                    }
                    if (temp.getNumber() == 12) {
                        clockwise = !clockwise;
                        if (players.size() == 2) increasePlayerNo();
                    }
                    if (temp.getNumber() == 13) {
                        if (playerNo == 0) {
                            wildPlayed(temp);
                        }
                    }
                    if (temp.getNumber() == 14) {
                        stackedPlus += 4;
                        if (playerNo == 0) {
                            wildPlayed(temp);
                        }
                    }
                }
                topCard = temp;
                cardsOnTable.add(temp);
                players.get(player).playCard(temp);
                increasePlayerNo();
                if (clockwise) {
                    if (temp.getNumber() == 11) {
                        increasePlayerNo();
                    }
                } else {
                    if (temp.getNumber() == 11) increasePlayerNo();
                }
                return true;
            }
        }
    return false;
    }

    public int draw(){
        int i;
        int drawed = stackedPlus;
        for(i = 0; i < stackedPlus; i++) {
            players.get(playerNo).addCard(deck.getTopCard());
        }
        if( stackedPlus == 0){
            players.get(playerNo).addCard(deck.getTopCard());
        }
        if( i != 0){ stackedPlus = 0; }
        players.get(playerNo).checkUno();
        increasePlayerNo();
        return drawed;
    }

    public void increasePlayerNo(){
        if(clockwise){
            playerNo ++;
            if(playerNo > players.size()-1){
                playerNo = 0;
            }

        }
        else {
            playerNo--;
            if(playerNo < 0){
                playerNo += players.size();
            }
        }
    }

    public boolean isOver(){
        return isOver;
    }

    public void setOver(boolean over){
        isOver = over;
    }

    public boolean hasPlus(int playerNo){
        for( Card c  : players.get(playerNo).getHand() ){
            if(c.getNumber() == 14 ){
                return true;
            }
            else if(c.getNumber() == 10 && topCard.isPlayable(c)){//if user has approproite +2
                return true;
            }
        }
        return false;
    }

    public boolean hasSkip(int playerNo){
        for( Card c : players.get(playerNo).getHand()){
            if(c.getNumber() == 11){
                return true;
            }
        }
        return false;
    }

    public void wildPlayed(Card c){
        botChooseColor(c);
    }

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