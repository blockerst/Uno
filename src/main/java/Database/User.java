package Database;

import java.util.ArrayList;

public class User
{
    private String username;
    private String password;
    private int NOGame;
    private int NOWin;
    private int point;
    private int rank;
    private int winStreak;
    private int imageId;
    private int isOnline;
    private ArrayList<User> friends;
    private ArrayList<Reward> rewards;
    private String date;



    public User(String username, String password) {
        this.username = username;
        this.password = password;
        NOGame = 0;
        NOWin = 0;
        point = 0;
        rank = 0;
        winStreak = 0;
        friends = new ArrayList<>();
        rewards = new ArrayList<>();

    }

    //cons without password
    public User(String username)  {
        this.username = username;
        NOGame = 0;
        NOWin = 0;
        point = 0;
        rank = 0;
        winStreak = 0;
        friends = new ArrayList<User>();
        rewards = new ArrayList<Reward>();
    }

    public User(String username, int NOGame, int NOWin, int point, int winStreak) {
        this.username = username;
        this.NOGame = NOGame;
        this.NOWin = NOWin;
        this.point = point;
        this.winStreak = winStreak;
    }

    public User(String username, int isOnline) {
        this.username = username;
        this.isOnline = isOnline;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getNOGame() {
        return NOGame;
    }

    public void setNOGame(int NOGame) {
        this.NOGame = NOGame;
    }

    public int getNOWin() {
        return NOWin;
    }

    public void setNOWin(int NOWin) {
        this.NOWin = NOWin;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getWinStreak() {
        return winStreak;
    }

    public void setWinStreak(int winStreak) {
        this.winStreak = winStreak;
    }

    public ArrayList<User> getFriends() {
        return friends;
    }

    public void setFriends(ArrayList<User> friends) {
        this.friends = friends;
    }

    public ArrayList<Reward> getRewards() {
        return rewards;
    }

    public void setRewards(ArrayList<Reward> rewards) {
        this.rewards = rewards;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public int getIsOnline() {
        return isOnline;
    }

    public void setIsOnline(int isOnline) {
        this.isOnline = isOnline;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
