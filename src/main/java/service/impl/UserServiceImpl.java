package service.impl;
import base.service.impl.BaseServiceImpl;
import exception.*;
import model.User;
import repository.UserRepository;
import service.UserService;
import util.Constant;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserServiceImpl
        extends BaseServiceImpl<Integer, User, UserRepository>
        implements UserService {

    public UserServiceImpl(UserRepository repository) {
        super(repository);
    }

    @Override
    public boolean isExistsUsername(String username) throws SQLException, UsernameExistException {
        //Using delegation
        return repository.isExistsUsername(username);
    }

    @Override
    public boolean isExistsNatCode(String natCode) throws SQLException, NatCodeExistsException {
        //Using delegation
        return repository.isExistsNatCode(natCode);
    }

    @Override
    public boolean isExistsPhone(String phone) throws SQLException, PhoneExistsException {
        //Using delegation
        return repository.isExistsPhone(phone);
    }

    @Override
    public boolean isValidEntryData(String regex, String data) {
        //todo: you can use if you need
        Pattern pt=Pattern.compile(regex);
        Matcher mt= pt.matcher(data);
        return mt.matches();
    }

    @Override
    public User checkCredentialInfoForLogin(String username, String password) throws Exception {
        User foundUser=repository.findUserByUsername(username);
        if (foundUser!=null){
            if (username.equals(foundUser.getUsername()) &&
                    password.equals(foundUser.getPassword()))
                return foundUser;
            else
                throw new BadCredentialException(Constant.BAD_CREDENTIAL);
        }
        throw new UserNotFoundException(Constant.USER_NOT_FOUND);
    }
}
