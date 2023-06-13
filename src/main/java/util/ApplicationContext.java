package util;

import config.DBConfig;
import repository.UserRepository;
import repository.impl.UserRepositoryImpl;
import service.UserService;
import service.impl.UserServiceImpl;

import java.sql.Connection;

public class ApplicationContext {

    private static Connection connection=new DBConfig().getConnection();

    private static UserRepository userRepository;

    private static UserService userService;



    static {
        userRepository=new UserRepositoryImpl();

        userService=new UserServiceImpl(userRepository);
    }

    public static UserService getUserService(){
        return userService;
    }


    public static Connection getConnection() {
        return connection;
    }
}
