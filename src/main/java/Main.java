import model.User;
import util.ApplicationContext;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {

        User newUser=new User(1,"amin","tavakkoli","0020411741","aminty","1234","0938");
        ApplicationContext.getUserService().delete(1);
    }
}
