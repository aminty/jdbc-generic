package repository.impl;

import base.repository.BaseRepository;
import base.repository.impl.BaseRepositoryImpl;
import config.DBConfig;
import exception.NatCodeExistsException;
import exception.PhoneExistsException;
import exception.UsernameExistException;
import model.User;
import repository.UserRepository;
import util.Constant;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepositoryImpl
        extends BaseRepositoryImpl<Integer, User>
        implements UserRepository {

    /**
     * These methods have been overridden from "UserRepository.Class"
     * These methods are specific for User Entity.
     */


    @Override
    public boolean isExistsUsername(String username) throws SQLException, UsernameExistException {
        String sql="SELECT username FROM "+getTableName()+" WHERE username = ?";
        try (PreparedStatement preparedStatement=new DBConfig().getConnection().prepareStatement(sql)){
            preparedStatement.setString(1,username);
            ResultSet foundUsername = preparedStatement.executeQuery();
            if (foundUsername.next())
                throw new UsernameExistException(Constant.USER_EXISTS);//username is exists by this value, use another username!
        }
        return false;
    }

    @Override
    public boolean isExistsNatCode(String natCode) throws SQLException, NatCodeExistsException {
        String sql="SELECT national_code FROM "+getTableName()+" WHERE national_code = ?";
        try (PreparedStatement preparedStatement=new DBConfig().getConnection().prepareStatement(sql)){
            preparedStatement.setString(1,natCode);
            ResultSet foundNatCode = preparedStatement.executeQuery();
            if (foundNatCode.next())
                throw new NatCodeExistsException(Constant.NAT_CODE_EXISTS);//nationalCode is exists by this value, use another nationalCode!
        }
        return false;
    }


    @Override
    public boolean isExistsPhone(String phone) throws SQLException, PhoneExistsException {
        String sql = "SELECT phone FROM " + getTableName() + " WHERE phone = ?";
        try (PreparedStatement preparedStatement = new DBConfig().getConnection().prepareStatement(sql)) {
            preparedStatement.setString(1, phone);
            ResultSet foundPhone = preparedStatement.executeQuery();
            if (foundPhone.next())
                throw new PhoneExistsException(Constant.PHONE_EXISTS);//phone is exists by this value, use another phone!
        }
        return false;
    }

    @Override
    public User findUserByUsername(String username) throws SQLException {
        //if user exists by this username ,return it
        String sql = "SELECT * FROM " + getTableName() + " WHERE username = ?";
        try (PreparedStatement preparedStatement = new DBConfig().getConnection().prepareStatement(sql)) {
            preparedStatement.setString(1, username);
            ResultSet foundUser = preparedStatement.executeQuery();
            if (foundUser.next())
                return new User(
                        foundUser.getInt(1),
                        foundUser.getString(2),
                        foundUser.getString(3),
                        foundUser.getString(4),
                        foundUser.getString(5),
                        foundUser.getString(6),
                        foundUser.getString(7)
                );
        }
        return null;
    }


//---------------------------------------------------------------------------------

    /**
     * These methods have been overridden by "UserRepositoryImpl.Class" from "BaseRepository" for its own specification
     * NOTE(keep in mind): some of these methods return a String value for using in the query
     * NOTE(keep in mind): These methods are "common behaviour" for any entity.
     */

    @Override
    public String getColumnsName() {
        return "first_name,last_name,username,password,national_code,phone";
    }


    @Override
    public String getUpdateQueryParams() {
        return "first_name=? , last_name=? , username=? , password=? , national_code=? , phone=?";
    }


    @Override
    public void fillParamForStatement(PreparedStatement preparedStatement, User entity) throws SQLException {

        preparedStatement.setString(1, entity.getFirstName());
        preparedStatement.setString(2, entity.getLastName());
        preparedStatement.setString(3, entity.getUsername());
        preparedStatement.setString(4, entity.getPassword());
        preparedStatement.setString(5, entity.getNatCode());
        preparedStatement.setString(6, entity.getPhone());

    }

    @Override
    public User mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        return new User(resultSet.getInt(1),
                resultSet.getString(2),
                resultSet.getString(3),
                resultSet.getString(4),
                resultSet.getString(5),
                resultSet.getString(6),
                resultSet.getString(7));
    }

}
