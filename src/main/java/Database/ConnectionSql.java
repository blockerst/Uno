package Database;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ConnectionSql
{
    //database connection variables
    private final String usernameDB = "ufbjjrhnkiyrctys";
    private final String passwordDB = "ug6SZ5SwengqaGHNsw69";
    private final String nameDB = "brfmdx4omkiunzgyfhye";
    private final String hostDB = "brfmdx4omkiunzgyfhye-mysql.services.clever-cloud.com";
    private final int portDB = 3306;

    private static ConnectionSql instance = new ConnectionSql();
    public static ConnectionSql getInstance() { return instance; }

    protected static Connection con;
    protected static Statement st;

    // Creates a connection with given parameters and set statement to st
    public ConnectionSql()
    {
        String url = "jdbc:mysql://" + hostDB + ":" + portDB +"/" + nameDB + "?autoReconnect=true&useSSL=false";
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex)
        {
            System.out.println("Driver cannot be found");
        }
        try {
            con = DriverManager.getConnection(url, usernameDB, passwordDB);
            System.out.println("Connected ");

        }catch (SQLException ex)
        {
            ex.printStackTrace();
            System.out.println("Connection is failed");
        }
        createStatementDB();
    }

    /**
     * Crates a user in database
     * return true: when created successfully
     * return false: username already exists
     * */
    public boolean signUp(User user)
    {
        String username = user.getUsername();
        String password = user.getPassword();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/uuuu");
        LocalDate localDate = LocalDate.now();
        String date = dtf.format(localDate);
<<<<<<< HEAD
=======

>>>>>>> main
        //first check there is a user who has that username and return false if it does
        if(checkUsername(user.getUsername())) return false;

        //create user in database
        try {

            st.executeUpdate("Insert Into Users VALUES (null ,'"
                    + username +"', '"
<<<<<<< HEAD
                    + password +"',0,0,0,0,0,0,0,'" +date +"');");
=======
                    + password +"',0,0,0,0,0,0,0,'" + date +"');");
>>>>>>> main
            isOnlineOperation(user,true);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * return true : when given username is exists in Users table
     * */
    public boolean checkUsername(String username)
    {
        try {
            ResultSet rs = st.executeQuery("Select * from Users where username = '"+ username +"'");
            if(rs.next()) return true;
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * return -1: user cannot be found
     * return  0: it is in process(you or your friend doesn't accept request) confirmed = 0
     * return  1: successfully done
     * return -2: some other errors in database (sql)
     * */
    public int addFriend(User user, User friend)
    {
        //check user exists or not
        if(!checkUsername(friend.getUsername())) return -1;

        //check they are already friend or request already sent
        try {
            ResultSet rs = st.executeQuery("Select * from Friends where (username1 = '"+ friend.getUsername() +"' AND " +
                    "username2 = '" + user.getUsername() + "') OR (username2 = '"+ friend.getUsername() + "' AND username1 = '" +
                    user.getUsername()+"')");
            if(rs.next()) return 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return -2;
        }

        //insert values into table
        try {
            st.executeUpdate("Insert Into Friends (username1, username2, confirmed) VALUES ('"
                    + user.getUsername() +"', '"
                    + friend.getUsername() +"', 0);");
            return 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return -2;
        }
    }

    /**
     * add reward to table
     * */
    public boolean addReward(User user, Reward reward)
    {
        try {
            st.executeUpdate("Insert Into UserSRewards (username, rewardId) VALUES ('"
                    + user.getUsername() +"', '"
                    + reward.getId() +"');");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * return : given user's friends list
     * User object just have two attribute : username, isOnline
     * */
    public ArrayList<User> getFriends(User user)
    {
        ArrayList<User> friends = new ArrayList<>();

        //check first condition at sql query
        try {
            ResultSet rs = st.executeQuery("Select * from Friends where username1 = '"+ user.getUsername() +"' AND " +
                    "confirmed = 1");
            while(rs.next())
            {
                String username = rs.getString("username2");
                Statement st2 = con.createStatement();
                ResultSet rs2 = st2.executeQuery("Select isOnline from Users where username = '" +
                        username + "';");
                rs2.next();
                int isOnline = rs2.getInt("isOnline");
                friends.add(new User(username,isOnline));

            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        //check second condition at sql query
        try {
            ResultSet rs = st.executeQuery("Select username1 from Friends where username2 = '"+ user.getUsername() +"' AND " +
                    "confirmed = 1");
            while(rs.next())
            {
                String username = rs.getString("username1");
                Statement st2 = con.createStatement();
                ResultSet rs2 = st2.executeQuery("Select isOnline from Users where username = '" +
                        username + "';");
                rs2.next();
                int isOnline = rs2.getInt("isOnline");
                friends.add(new User(username,isOnline));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return friends;
    }

    /**
     * makes appropriate friend request operation by given values
     * */
    public boolean friendRequestOperation(User user, User friend, boolean isAccepted)
    {
        int confirmed = 0;
        if(isAccepted) confirmed = 1;

        try {
            st.executeUpdate("Update Friends SET Confirmed = " + confirmed + " where (username1 = '"
                    + friend.getUsername() +"' AND username2 = '" + user.getUsername() + "');");
            if(!isAccepted)
            {
                st.executeUpdate("Delete FROM Friends where (username1 = '"
                        + friend.getUsername() +"' AND username2 = '" + user.getUsername() + "');");
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * return : given user's friendship requests as list (User type)
     * */
    public ArrayList<User> getFriendRequests(User user)
    {
        ArrayList<User> friends = new ArrayList<>();
        try {
            ResultSet rs = st.executeQuery("Select username1 from Friends where username2 = '"+ user.getUsername() +"' AND " +
                    "confirmed = 0");
            while(rs.next())
            {
                String username = rs.getString("username1");
                friends.add(new User(username));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return friends;
    }

    /**
     * return : given user's reward requests as list (Reward(id, name) type )
     * */
    public ArrayList<Reward> getRewards(User user)
    {
        ArrayList<Reward> rewards = new ArrayList<>();
        try {
            ResultSet rs = st.executeQuery("Select rewardId from UserSRewards where username = '"+ user.getUsername() +"'");
            while(rs.next())
            {
                int id = rs.getInt("rewardId");
                Statement st2 = con.createStatement();
                ResultSet rs2 = st2.executeQuery("Select Name from Reward where idReward = " + id + ";");
                rs2.next();
                String name = rs2.getString("Name");
                rewards.add(new Reward(id,name));
            }
            return rewards;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * increase number of game by 1
     * return : final NOGame
     * */
    public int incrementNOGame(User user)
    {
        int NOGame;
        NOGame = getNOGame(user);
        NOGame++;
        try {
            st.executeUpdate("Update Users SET NOGame = " + NOGame + " where username='" + user.getUsername() + "';");
            return NOGame;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * increase number of win and game
     * !!!CAREFUL!!!
     * it also increases number of game
     * */
    public int incrementNOWin(User user)
    {
        int NOWin;
        NOWin = getNOWin(user);
        NOWin++;
        try {
            st.executeUpdate("Update Users SET NOWin = " + NOWin + " where username='" + user.getUsername() + "';");
            incrementNOGame(user);
            return NOWin;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * return : given user's number of game
     * */
    public int getNOGame(User user) {


        try {
            ResultSet rs = st.executeQuery("Select NOGame from Users where username = '"+ user.getUsername() +"'");
            if(rs.next())
            {
                return Integer.parseInt(rs.getString("NOGame"));
            }
            closeSt();
            closeCon();

        } catch (SQLException e) {

            e.printStackTrace();
            return 0;
        }
        return 0;
    }

    /**
     * return : given user's number of win
     * */
    public int getNOWin(User user) {


        try {
            ResultSet rs = st.executeQuery("Select NOWin from Users where username = '"+ user.getUsername() +"'");
            if(rs.next())
            {
                return Integer.parseInt(rs.getString("NOWin"));
            }

        } catch (SQLException e){
            e.printStackTrace();
            return 0;
        }
        return 0;
    }

    /**
     * return : given user's point
     * */
    public int getPoint(User user) {
        try {
            ResultSet rs = st.executeQuery("Select Point from Users where username = '"+ user.getUsername() +"'");
            if(rs.next())
            {
                return Integer.parseInt(rs.getString("Point"));
            }

        } catch (SQLException e) {

            e.printStackTrace();
            return 0;
        }
        return 0;
    }

    /**
     * return : given user's rank
     * */
    public int getRankUser(User user) {

        try {
            ResultSet rs = st.executeQuery("SELECT row_number() over(order by point desc) AS numRow, username, point from Users;");
            while (rs.next())
            {
                if(rs.getString(2).equals(user.getUsername())) return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
        return -1;
    }

    /**
     * return : user for profile page information
     * */
    public User userPPage(User user)
    {
        try {
            ResultSet rs = st.executeQuery("Select * from Users where username = '" + user.getUsername() + "';");
            rs.next();
            user.setNOGame(rs.getInt("NOGame"));
            user.setNOWin(rs.getInt("NOWin"));
            user.setPoint(rs.getInt("point"));
            user.setWinStreak(rs.getInt("winStreak"));
            user.setImageId(rs.getInt("ProfileImageId"));
            user.setIsOnline(rs.getInt("isOnline"));
            user.setRank(getRankUser(user));
            return user;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * return -2: some database problem
     * return -1: username does not exist
     * return  0: password is not correct
     * return  1: correct!!
     * */
    public int singInCheck(User user)
    {
        String username = user.getUsername();
        String password;

        try {
            ResultSet rs = st.executeQuery("Select password from Users where username = '" + username + "';");
            if(!rs.next()) return -1;
            password = rs.getString("password");
            if(password.equals(user.getPassword())) return 1;
            return 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return -2;
        }
    }

    /**
     * increase user's point by given point
     * return : final point
     * */
    public int incrementPoint(User user, int takenPoint)
    {
        int point = 0;
        point = getPoint(user);
        point += takenPoint;
        try {
            st.executeUpdate("Update Users Set Point = " + point + " where username = '" + user.getUsername() + "';");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return point;
    }

    /**
     * return : top 100 user as a list
     * User : username, point, rank, NOGame, NOWin
     * */
    public ArrayList<User> getTop100()
    {
        ArrayList<User> topPlayers = new ArrayList<>();
        ResultSet rs;
        try {
            rs = st.executeQuery("SELECT row_number() over(order by point desc) AS numRow, username, point, NOGame, NoWin from Users limit 100;");
            while (rs.next())
            {

                String username = rs.getString("username");
                User user = new User(username);
                int point = rs.getInt("point");
                int NoGame = rs.getInt("NOGame");
                int NOWin = rs.getInt("NOWin");
                int rowNum = rs.getInt("numRow");
                user.setPoint(point);
                user.setRank(rowNum);
                user.setNOGame(NoGame);
                user.setNOWin(NOWin);
                topPlayers.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return topPlayers;
    }

    /**
     * when this method called make given user online/offline
     * */
    public boolean isOnlineOperation(User user, boolean isOnline)
    {
        int situ = 0;
        if(isOnline) situ = 1;
        try {
            st.executeUpdate("Update Users SET isOnline = " + situ + " where username = '" + user.getUsername() + "';");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean imageIdOperation(User user, int id)
    {
        try {
            st.executeUpdate("Update Users SET ProfileImageId = " + id + " where username = '" + user.getUsername() + "';");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public int getImageId(User user)
    {
        try {
            ResultSet rs = st.executeQuery("Select ProfileImageId from Users where username = '" + user.getUsername() + "';");
            rs.next();
            return rs.getInt("ProfileImageId");
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public boolean changePassword(User oldOne, User newOne)
    {
        String oldPassword = "";
        try {
            ResultSet rs = st.executeQuery("Select password FROM Users where username = '" + oldOne.getUsername() + "';");
            rs.next();
            oldPassword = rs.getString("password");

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        if(oldPassword.equals(newOne.getPassword()))
        {
            try {
                st.executeUpdate("Update Users SET password = '" + newOne.getPassword()
                        + "' where username = '" + oldOne.getUsername() + "';");
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }

    public boolean changeUsername(User userOld, String newUsername)
    {
        try {
            st.executeUpdate("Update Users SET username = '" + newUsername
                    + "' where username = '" + userOld.getUsername() + "';");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void createStatementDB()
    {
        try {
            st = con.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void closeCon(){
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void closeSt()
    {
        try {
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getCon() {
        return con;
    }
}
