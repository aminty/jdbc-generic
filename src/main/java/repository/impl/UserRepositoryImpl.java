package repository.impl;

import base.repository.BaseRepository;
import base.repository.impl.BaseRepositoryImpl;
import model.User;
import repository.UserRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepositoryImpl extends BaseRepositoryImpl<Integer, User> implements UserRepository {
    @Override
    public String getTableName() {
        return "user_table";
    }

    @Override
    public void insertParamForStatement(PreparedStatement preparedStatement, User entity) throws SQLException {

        preparedStatement.setInt(1, entity.getId());
        preparedStatement.setString(2, entity.getFirstName());
        preparedStatement.setString(3, entity.getLastName());
        preparedStatement.setString(4, entity.getUsername());
        preparedStatement.setString(5, entity.getPassword());
        preparedStatement.setString(6, entity.getNatCode());
        preparedStatement.setString(7, entity.getPhone());


    }

    @Override
    public String getCountOfQuestionMarkForParams() {
        return "?,?,?,?,?,?,?";
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

    @Override
    public String getUpdateQueryParams() {
        return "first_name=? , last_name=? , username=? , password=? , national_code=? , phone=?";
    }

    @Override
    public void setUpdateStatementParams(PreparedStatement preparedStatement, User entity) throws SQLException {
        preparedStatement.setString(1, entity.getFirstName());
        preparedStatement.setString(2, entity.getLastName());
        preparedStatement.setString(3, entity.getUsername());
        preparedStatement.setString(4, entity.getPassword());
        preparedStatement.setString(5, entity.getNatCode());
        preparedStatement.setString(6, entity.getPhone());
    }
}
