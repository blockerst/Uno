package gui;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Competitor {

    private SimpleIntegerProperty rank;
    private SimpleStringProperty name;
    private SimpleIntegerProperty playedGameNum;
    private SimpleIntegerProperty gamesWon;
    private SimpleIntegerProperty point;

    public Competitor(SimpleIntegerProperty rank, SimpleStringProperty name, SimpleIntegerProperty playedGameNum, SimpleIntegerProperty gamesWon, SimpleIntegerProperty point){
        this.rank = rank;
        this.name = name;
        this.playedGameNum = playedGameNum;
        this.gamesWon = gamesWon;
        this.point = point;
    }

    public int getRank() {
        return rank.get();
    }

    public SimpleIntegerProperty rankProperty() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank.set(rank);
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public int getPlayedGameNum() {
        return playedGameNum.get();
    }

    public SimpleIntegerProperty playedGameNumProperty() {
        return playedGameNum;
    }

    public void setPlayedGameNum(int playedGameNum) {
        this.playedGameNum.set(playedGameNum);
    }

    public int getGamesWon() {
        return gamesWon.get();
    }

    public SimpleIntegerProperty gamesWonProperty() {
        return gamesWon;
    }

    public void setGamesWon(int gamesWon) {
        this.gamesWon.set(gamesWon);
    }

    public int getPoint() {
        return point.get();
    }

    public SimpleIntegerProperty pointProperty() {
        return point;
    }

    public void setPoint(int point) {
        this.point.set(point);
    }
}
