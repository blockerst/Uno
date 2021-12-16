package Game;

public class Card {
    private String color;
    private int number;
    private boolean special;
    private int imageID;

    public Card(String color, int value, boolean isSpecial, int ID){
        this.color = color;
        number = value;
        special = isSpecial;
        imageID = ID;
    }
    // getters
    public String getColor(){ return color; }
    public int getNumber(){ return number; }
    public boolean getSpecial(){ return special; }
    public int getImageID(){ return imageID; }
    //setters
    public void setColor(String color){this.color = color; }
    public void setNumber(int value){number = value; }
    public void setSpecial( boolean special ){ this.special = special; }
    public void setSpecialColor(String color){ this.color = color; }

    public boolean isPlayable(Card card){
        if( this.color == card.getColor()){ return true; }
        if( this.number < 13 && this.number == card.getNumber()){ return true; }
        if( (card.number == 13 || card.number == 14)){ return true; }
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
