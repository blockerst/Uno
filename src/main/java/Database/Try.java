package Database;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Try {
    public static void main(String[] args)
    {
        User user = new User("blockerst");
        User user1 = new User("oguz");
        User user2 = new User("goktugC");
        User user3 = new User("göktuğYilmaz", "xyz6789");
        ConnectionSql DB = ConnectionSql.getInstance();

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/uuuu");
        LocalDate localDate = LocalDate.now();
        String date = dtf.format(localDate);
        System.out.println(dtf.format(localDate));

    }
}
