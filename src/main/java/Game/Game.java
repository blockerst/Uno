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

    public Game(ArrayList<Player> users){
        players = users;
        stackedPlus = 0;
        clockwise = true;
        cardsOnTable = new ArrayList<Card>(52);
        deck = new Deck(true);
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
        System.out.println("Test " + topCard);
        topCard = deck.getTopCard();
        deck.add(temp);
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

    public void botPlay(int index){
        if( index > 0) {//index 0 is real player
            if(clockwise){
                if(index == players.size()-1 && isUno(0)){
                    if( hasPlus(index) ){
                        //play(index, players.get(index).getHand().get(indexPlusOrSkip(index)));
                    }
                }
            }
        }
    }


    public boolean play(int player,int index){
        if( this.playerNo == player){
            Card temp = players.get(player).getHand().get(index);
            if( topCard.isPlayable(temp)) {
                if( players.get(player).checkUno() ){ isOver = true; return true;}
                if( temp.getSpecial()) {
                    if (temp.getNumber() == 10) {
                        stackedPlus += 2;
                    }
                    if( temp.getNumber() == 12){
                        clockwise = !clockwise;
                    }
                    if( temp.getNumber() == 13) {
                        wildPlayed(temp);
                    }
                    if (temp.getNumber() == 14) {
                        stackedPlus += 4;
                        wildPlayed(temp);
                    }

                }
                topCard = temp;
                cardsOnTable.add(temp);
                players.get(player).playCard(temp);
                increasePlayerNo();
                if(clockwise){
                    if( temp.getNumber() == 11){
                        increasePlayerNo();
                    }
                }
                else {
                    if(temp.getNumber() ==11) increasePlayerNo();
                }
                return true;
            }
        }
        return false;
    }

    public void draw(){
        int i;
        for(i = 0; i < stackedPlus; i++) {
            players.get(playerNo).addCard(deck.getTopCard());
        }
        if( stackedPlus == 0){
            players.get(playerNo).addCard(deck.getTopCard());
        }
        if( i != 0){ stackedPlus = 0; }
        players.get(playerNo).checkUno();
        increasePlayerNo();
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

    public boolean hasPlus(int playerNo){
        for( Card c  : players.get(playerNo).getHand() ){
            if(c.getNumber() == 14 || c.getNumber() == 11){ //if user has +4
                return true;
            }
            else if(c.getNumber() == 10 ){//if user has approproite +2
                return true;
            }
        }
        return false;
    }

    public void wildPlayed(Card c){
        Scanner sc = new Scanner(System.in);
        String choice;
        System.out.println("Pick a color (1)Red/(2)Blue/(3)Yellow/(4)Green");
        choice = sc.next();

        if( choice.equals("1")){
            c.setColor("Red");
        }
        else if( choice.equals("2")){
            c.setColor("Blue");
        }
        else if( choice.equals("3")){
            c.setColor("Yellow");
        }
        else if( choice.equals("4")){
            c.setColor("Green");
        }
        else{
            System.out.println("Wrong input try again");
            wildPlayed(c);
        }
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
