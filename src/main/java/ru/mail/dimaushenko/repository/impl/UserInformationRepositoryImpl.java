package ru.mail.dimaushenko.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import ru.mail.dimaushenko.repository.UserInformationRepository;
import ru.mail.dimaushenko.repository.model.UserInformation;
import ru.mail.dimaushenko.utils.PropertyUtil;
import ru.mail.dimaushenko.utils.impl.PropertyUtilConstantsImpl;

import static ru.mail.dimaushenko.constants.PropertyConstants.SQL_REQUEST_INSERT_USER_INFORMATION;
import static ru.mail.dimaushenko.constants.PropertyConstants.SQL_REQUEST_IS_USER_INFORMATION_FOUND_BY_ID;
import static ru.mail.dimaushenko.constants.PropertyConstants.SQL_REQUEST_SELECT_USER_INFORMATION_BY_ID;
import static ru.mail.dimaushenko.constants.PropertyConstants.SQL_COLUMN_USER_INFORMATION_ADDRESS;
import static ru.mail.dimaushenko.constants.PropertyConstants.SQL_COLUMN_USER_INFORMATION_ID;
import static ru.mail.dimaushenko.constants.PropertyConstants.SQL_COLUMN_USER_INFORMATION_TELEPHONE;
import static ru.mail.dimaushenko.constants.PropertyConstants.SQL_REQUEST_UPDATE_USER_INFORMATION;

public class UserInformationRepositoryImpl extends GeneralRepositoryImpl<UserInformation> implements UserInformationRepository {

    private static UserInformationRepository instance = null;

    private final PropertyUtil propertyUtil;

    private UserInformationRepositoryImpl() {
        propertyUtil = PropertyUtilConstantsImpl.getInstance();
    }

    public static UserInformationRepository getInstance() {
        if (instance == null) {
            instance = new UserInformationRepositoryImpl();
        }
        return instance;
    }

    @Override
    public void addEntity(Connection connection, UserInformation userInformation) throws SQLException {
        try (PreparedStatement statement = connection.prepareCall(propertyUtil.getProperty(SQL_REQUEST_INSERT_USER_INFORMATION))) {
            statement.setInt(1, userInformation.getUserId());
            statement.setString(2, userInformation.getAddres());
            statement.setString(3, userInformation.getTelephone());
            statement.execute();
        }
    }

    @Override
    public UserInformation getEntityById(Connection connection, int id) throws SQLException {
        UserInformation userInformation = null;
        try (PreparedStatement statement = connection.prepareCall(propertyUtil.getProperty(SQL_REQUEST_SELECT_USER_INFORMATION_BY_ID))) {
            statement.setInt(1, id);
            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    userInformation = new UserInformation();
                    userInformation.setUserId(result.getInt(propertyUtil.getProperty(SQL_COLUMN_USER_INFORMATION_ID)));
                    userInformation.setAddres(result.getString(propertyUtil.getProperty(SQL_COLUMN_USER_INFORMATION_ADDRESS)));
                    userInformation.setTelephone(result.getString(propertyUtil.getProperty(SQL_COLUMN_USER_INFORMATION_TELEPHONE)));
                }
            }
        }
        return userInformation;
    }

    @Override
    public List<UserInformation> getAll(Connection connection) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean removeEntity(Connection connection, Integer id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateEntity(Connection connection, UserInformation userInformation) throws SQLException {
        try (PreparedStatement statement = connection.prepareCall(propertyUtil.getProperty(SQL_REQUEST_UPDATE_USER_INFORMATION))) {
            statement.setString(1, userInformation.getAddres());
            statement.setString(2, userInformation.getTelephone());
            statement.setInt(3, userInformation.getUserId());
            statement.execute();
        }
    }

    @Override
    public boolean isEntityFoundById(Connection connection, Integer id) throws SQLException {
        try (PreparedStatement statement = connection.prepareCall(propertyUtil.getProperty(SQL_REQUEST_IS_USER_INFORMATION_FOUND_BY_ID))) {
            statement.setInt(1, id);
            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    return true;
                }
            }
        }
        return false;
    }

}
