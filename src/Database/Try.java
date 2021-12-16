package Database;

import java.util.ArrayList;

public class Try {
    public static void main(String[] args)
    {
        User user = new User("blockerst");
        User user1 = new User("oguz");
        User user2 = new User("goktugC");
        User user3 = new User("göktuğYilmaz", "xyz6789");
        ConnectionSql DB = ConnectionSql.getInstance();

        DB.friendRequestOperation(user,user1,true);
        DB.incrementPoint(user,31);
        ArrayList<User> l;
        l = DB.getTop100();
        for(int i = 0; i < l.size(); i++)
        {
            System.out.println(l.get(i).getUsername());
        }

    }
}
