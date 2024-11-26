package base.repository.impl;

import base.annotation.Table;
import base.model.BaseEntity;
import base.repository.BaseRepository;
import config.DBConfig;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class BaseRepositoryImpl<ID extends Serializable, TYPE extends BaseEntity<ID>>
        implements BaseRepository<ID, TYPE> {

    private final Class<TYPE> entityClass;

    @SuppressWarnings("unchecked")
    public BaseRepositoryImpl() {
        entityClass = (Class<TYPE>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }

    @Override
    public void save(TYPE entity) throws SQLException {
        String sql = "INSERT INTO " + getTableName() +" (" +getColumnsName()+ ") VALUES (" + getCountOfQuestionMarkForParams() + ")";
        try (PreparedStatement statement = new DBConfig().getConnection().prepareStatement(sql)) {

            fillParamForStatement(statement, entity);
            statement.executeUpdate();
        }
    }

    @Override
    public void delete(ID id) throws SQLException {
        String sql = "DELETE FROM " + getTableName() + " WHERE id = ? ";
        try (PreparedStatement statement = new DBConfig().getConnection().prepareStatement(sql)) {
            statement.setInt(1, (Integer) id);
            statement.executeUpdate();
        }
    }

    @Override
    public List<TYPE> findAll() throws SQLException {
        String sql = " SELECT * FROM " + getTableName();
        try (PreparedStatement statement = new DBConfig().getConnection().prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            List<TYPE> entities = new ArrayList<>();
            while (resultSet.next()) {
                entities.add(mapResultSetToEntity(resultSet));
            }
            return entities;
        }
    }

    @Override
    public TYPE findById(ID id) throws SQLException {
        String sql="SELECT * FROM "+getTableName()+ " WHERE id = ? ;";
        try (PreparedStatement statement=new DBConfig().getConnection().prepareStatement(sql)){
            statement.setInt(1, (Integer) id);
            ResultSet resultSet=statement.executeQuery();
            if (resultSet.next())
               return mapResultSetToEntity(resultSet);
        }
        return null;
    }

    @Override
    public void update(TYPE entity) throws SQLException {
        String sql = "UPDATE " + getTableName() + " SET " + getUpdateQueryParams() + " WHERE id = ?";
        try (PreparedStatement statement = new DBConfig().getConnection().prepareStatement(sql)) {
            fillParamForStatement(statement, entity);
            statement.executeUpdate();
        }
    }


    public String getTableName() {
        String defaultTableName = entityClass.getSimpleName().toLowerCase();
        if (entityClass.isAnnotationPresent(Table.class)) {
            String tableName = entityClass.getAnnotation(Table.class).name().trim();
            return (!tableName.isEmpty()) ? tableName : defaultTableName;
        }
         return defaultTableName;
    }

    public abstract String getColumnsName();

    public abstract String getUpdateQueryParams();

    public String getCountOfQuestionMarkForParams() {
        return Stream.of(getColumnsName().split(",")).map(colName -> "?").collect(Collectors.joining(","));
    }

    public abstract TYPE mapResultSetToEntity(ResultSet resultSet) throws SQLException;

    public abstract void fillParamForStatement(PreparedStatement preparedStatement, TYPE entity) throws SQLException;





}
