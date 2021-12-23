package Game;

import javafx.scene.image.Image;

import java.util.Scanner;

public class Card {
    private String color;
    private int number;
    private boolean special;
    private String imageID;

    public Card(String color, int value, boolean isSpecial, String ID){
        this.color = color;
        number = value;
        special = isSpecial;
        imageID = ID;
    }
    // getters
    public String getColor(){ return color; }
    public int getNumber(){ return number; }
    public boolean getSpecial(){ return special; }
    public String getImageID(){ return imageID; }
    //setters
    public void setColor(String color){this.color = color; }
    public void setNumber(int value){number = value; }
    public void setSpecial( boolean special ){ this.special = special; }
    public void setSpecialColor(String color){ this.color = color; }
    public void setImageID( String ID){ this.imageID = ID; }

    public boolean isPlayable(Card card){//card is being played
        if( this.color == card.getColor()){ return true; }
        if( this.number < 13 && this.number == card.getNumber()){ return true; }
        if( (card.number == 13 || card.number == 14)){ return true; }
        return false;
    }

    public Image makeImage(){
        return new Image("file:"+ getImageID());
    }

    public boolean equals( Card c){
        if( c.getImageID() == this.getImageID()){
            return true;
        }
        return false;
    }

    public String toString(){
        String out = "";
        if( special ) {
            if (number == 10) {
                out = color + " +2";
            } else if (number == 11) {
                out = color + " Skip";
            } else if (number == 12) {
                out = color + " Reverse";
            } else if (number == 13) {
                out = "Wild";
            } else if (number == 14) {
                out = "+4";
            }
        }
        else{ out += color + " " + number; }
        return out;
    }
}
