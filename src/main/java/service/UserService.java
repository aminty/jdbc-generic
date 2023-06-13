package service;

import base.service.BaseService;
import exception.BadCredentialException;
import exception.NatCodeExistsException;
import exception.PhoneExistsException;
import exception.UserNotFoundException;
import model.User;

import java.sql.SQLException;

public interface UserService extends BaseService<Integer , User> {

    boolean isExistsUsername(String username) throws Exception;

    boolean isExistsNatCode(String natCode) throws SQLException, NatCodeExistsException;

    boolean isExistsPhone(String phone) throws SQLException, PhoneExistsException;

    boolean isValidEntryData(String regex,String data);

    User checkCredentialInfoForLogin(String username,String password) throws Exception, BadCredentialException, UserNotFoundException;

}
