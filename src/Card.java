public class Card {
    private int color;
    private int value;

    public Card( int val, int c ){
        value = val;
        color = c;
    }
    // getters
    public int getValue(){ return value; }
    public int getColor(){ return color; }
    //setters
    public void setValue(int value){this.value = value; }
    public void setColor(int color){this.color = color; }

    public String toString(){
        String out = "";

        if( color == 0 ){
            out = "Yellow ";
        }
        else if( color == 1 ){
            out = "Blue ";
        }
        else if( color == 2 ){
            out = "Green";
        }
        else if( color == 3 ){
            out = "Red ";
        }

        if( value < 10 ){ out += "" + value; }
        else if( value == 10 ){ out += "+2"; }
        else if( value == 11 ){ out += "+4"; }
        else if( value == 12 ){ out += "Skip"; }
        else if( value == 13 ){out += "Wild"; }

        return out;
    }
}
