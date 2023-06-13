package repository;

import base.repository.BaseRepository;
import exception.NatCodeExistsException;
import exception.PhoneExistsException;
import exception.UsernameExistException;
import model.User;

import java.sql.SQLException;

public interface UserRepository
        extends BaseRepository<Integer, User> {

    boolean isExistsUsername(String username) throws SQLException, UsernameExistException;

    boolean isExistsNatCode(String natCode) throws SQLException, NatCodeExistsException;

    boolean isExistsPhone(String phone) throws SQLException, PhoneExistsException;

    User findUserByUsername(String username) throws SQLException;




}
