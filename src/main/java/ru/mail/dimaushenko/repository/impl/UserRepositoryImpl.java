package ru.mail.dimaushenko.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ru.mail.dimaushenko.repository.UserRepository;
import ru.mail.dimaushenko.repository.model.User;
import ru.mail.dimaushenko.utils.PropertyUtil;
import ru.mail.dimaushenko.utils.impl.PropertyUtilConstantsImpl;

import static ru.mail.dimaushenko.constants.PropertyConstants.SQL_REQUEST_INSERT_USER;
import static ru.mail.dimaushenko.constants.PropertyConstants.SQL_REQUEST_SELECT_ALL_USERS;
import static ru.mail.dimaushenko.constants.PropertyConstants.SQL_REQUEST_REMOVE_USER_BY_ID;
import static ru.mail.dimaushenko.constants.PropertyConstants.SQL_REQUEST_UPDATE_USER;
import static ru.mail.dimaushenko.constants.PropertyConstants.SQL_COLUMN_USER_ID;
import static ru.mail.dimaushenko.constants.PropertyConstants.SQL_COLUMN_USER_USERNAME;
import static ru.mail.dimaushenko.constants.PropertyConstants.SQL_COLUMN_USER_PASSWORD;
import static ru.mail.dimaushenko.constants.PropertyConstants.SQL_COLUMN_USER_IS_ACTIVE;
import static ru.mail.dimaushenko.constants.PropertyConstants.SQL_COLUMN_USER_AGE;
import static ru.mail.dimaushenko.constants.PropertyConstants.SQL_REQUEST_IS_USER_FOUND_BY_ID;

public class UserRepositoryImpl extends GeneralRepositoryImpl<User> implements UserRepository {

    private static UserRepository instance = null;

    private final PropertyUtil propertyUtil;

    private UserRepositoryImpl() {
        propertyUtil = PropertyUtilConstantsImpl.getInstance();
    }

    public static UserRepository getInstance() {
        if (instance == null) {
            instance = new UserRepositoryImpl();
        }
        return instance;
    }

    @Override
    public void addEntity(Connection connection, User user) throws SQLException {
        try (PreparedStatement statement = connection.prepareCall(propertyUtil.getProperty(SQL_REQUEST_INSERT_USER))) {
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setBoolean(3, user.isIsActive());
            statement.setInt(4, user.getAge());
            statement.execute();
        }
    }

    @Override
    public User getEntityById(Connection connection, int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<User> getAll(Connection connection) throws SQLException {
        List<User> userGroups = new ArrayList();
        try (PreparedStatement statement = connection.prepareCall(propertyUtil.getProperty(SQL_REQUEST_SELECT_ALL_USERS))) {
            try (ResultSet result = statement.executeQuery()) {
                while (result.next()) {
                    userGroups.add(getUserGroup(connection, result));
                }
            }
        }
        return userGroups;
    }

    @Override
    public boolean removeEntity(Connection connection, Integer id) throws SQLException {
        try (PreparedStatement statement = connection.prepareCall(propertyUtil.getProperty(SQL_REQUEST_REMOVE_USER_BY_ID))) {
            statement.setInt(1, id);
            if (statement.executeUpdate() == 1) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void updateEntity(Connection connection, User user) throws SQLException {
        try (PreparedStatement statement = connection.prepareCall(propertyUtil.getProperty(SQL_REQUEST_UPDATE_USER))) {
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setBoolean(3, user.isIsActive());
            statement.setInt(4, user.getAge());
            statement.setInt(5, user.getId());
            statement.execute();
        }
    }

    @Override
    public boolean isEntityFoundById(Connection connection, Integer id) throws SQLException {
        try (PreparedStatement statement = connection.prepareCall(propertyUtil.getProperty(SQL_REQUEST_IS_USER_FOUND_BY_ID))) {
            statement.setInt(1, id);
            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    return true;
                }
            }
        }
        return false;
    }

    private User getUserGroup(Connection connection, ResultSet result) throws SQLException {
        User user = new User();

        user.setId(result.getInt(propertyUtil.getProperty(SQL_COLUMN_USER_ID)));
        user.setUsername(result.getString(propertyUtil.getProperty(SQL_COLUMN_USER_USERNAME)));
        user.setPassword(result.getString(propertyUtil.getProperty(SQL_COLUMN_USER_PASSWORD)));
        user.setAge(result.getInt(propertyUtil.getProperty(SQL_COLUMN_USER_AGE)));
        user.setIsActive(result.getBoolean(propertyUtil.getProperty(SQL_COLUMN_USER_IS_ACTIVE)));

        return user;
    }

}
