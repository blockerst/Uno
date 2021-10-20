public class Card {
    private int color;
    private int value;

    public Card( int val, int c ){
        value = val;
        color = c;
    }

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

        if( value < 10){
            out += "" + value;
        }
        else if( value == 11){
            out += "+2";
        }
        else if( value == 12){
            out += "+4";
        }
        else if( value == 13){
            out += "Skip";
        }
        else if( value == 14 ){
            out += "Wild";
        }

        return out;
    }
}
