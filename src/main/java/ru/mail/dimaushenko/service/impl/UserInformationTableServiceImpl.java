package ru.mail.dimaushenko.service.impl;

import java.lang.invoke.MethodHandles;
import java.sql.Connection;
import java.sql.SQLException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ru.mail.dimaushenko.repository.impl.ConnectionPoolImpl;
import ru.mail.dimaushenko.service.TableService;
import ru.mail.dimaushenko.utils.PropertyUtil;
import ru.mail.dimaushenko.utils.impl.PropertyUtilConstantsImpl;
import ru.mail.dimaushenko.repository.TableRepository;
import ru.mail.dimaushenko.repository.impl.TableRepositoryImpl;

import static ru.mail.dimaushenko.constants.PropertyConstants.SQL_REQUEST_CREATE_TABLE_USER_INFORMATION;
import static ru.mail.dimaushenko.constants.PropertyConstants.SQL_REQUEST_DROP_TABLE_USER_INFORMATION;

public class UserInformationTableServiceImpl implements TableService {

    private static TableService instance = null;

    private final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    private final PropertyUtil propertyUtil;
    private final TableRepository tableRepository;

    private UserInformationTableServiceImpl() {
        propertyUtil = PropertyUtilConstantsImpl.getInstance();
        tableRepository = TableRepositoryImpl.getInstance();
    }

    public static TableService getInstance() {
        if (instance == null) {
            instance = new UserInformationTableServiceImpl();
        }
        return instance;
    }

    @Override
    public boolean createTable() {
        try (Connection connection = ConnectionPoolImpl.getInstance().getConnection()) {
            connection.setAutoCommit(false);
            try {
                tableRepository.executeQuery(connection, propertyUtil.getProperty(SQL_REQUEST_CREATE_TABLE_USER_INFORMATION));
                connection.commit();
                return true;
            } catch (SQLException ex) {
                logger.error(ex.getMessage(), ex);
                connection.rollback();
            }
        } catch (SQLException ex) {
            logger.error(ex.getMessage(), ex);
        }
        return false;
    }

    @Override
    public boolean removeTable() {
        try (Connection connection = ConnectionPoolImpl.getInstance().getConnection()) {
            connection.setAutoCommit(false);
            try {
                tableRepository.executeQuery(connection, propertyUtil.getProperty(SQL_REQUEST_DROP_TABLE_USER_INFORMATION));
                connection.commit();
                return true;
            } catch (SQLException ex) {
                logger.error(ex.getMessage(), ex);
                connection.rollback();
            }
        } catch (SQLException ex) {
            logger.error(ex.getMessage(), ex);
        }
        return false;
    }

}
