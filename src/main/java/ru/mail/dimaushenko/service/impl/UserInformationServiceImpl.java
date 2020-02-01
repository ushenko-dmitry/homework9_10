package ru.mail.dimaushenko.service.impl;

import java.lang.invoke.MethodHandles;
import java.sql.Connection;
import java.sql.SQLException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ru.mail.dimaushenko.repository.UserInformationRepository;
import ru.mail.dimaushenko.repository.impl.ConnectionPoolImpl;
import ru.mail.dimaushenko.repository.impl.UserInformationRepositoryImpl;
import ru.mail.dimaushenko.repository.model.UserInformation;
import ru.mail.dimaushenko.service.UserInformationService;
import ru.mail.dimaushenko.service.model.FullUserInformationDTO;
import ru.mail.dimaushenko.utils.PropertyUtil;
import ru.mail.dimaushenko.utils.impl.PropertyUtilConstantsImpl;

public class UserInformationServiceImpl implements UserInformationService {

    private static UserInformationService instance = null;

    private final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    private final PropertyUtil propertyUtil;
    private final UserInformationRepository userInformationRepository;

    private UserInformationServiceImpl() {
        propertyUtil = PropertyUtilConstantsImpl.getInstance();
        userInformationRepository = UserInformationRepositoryImpl.getInstance();
    }

    public static UserInformationService getInstance() {
        if (instance == null) {
            instance = new UserInformationServiceImpl();
        }
        return instance;
    }

    @Override
    public boolean updateUserInformationAddress(FullUserInformationDTO fullUserInformation) {

        try (Connection connection = ConnectionPoolImpl.getInstance().getConnection()) {
            connection.setAutoCommit(false);
            try {
                UserInformation userInformation = userInformationRepository.getEntityById(connection, fullUserInformation.getUserId());

                if (userInformation == null) {
                    userInformation = convertFullUserInformationDTOToUserInformation(fullUserInformation);
                    userInformationRepository.addEntity(connection, userInformation);
                } else {
                    userInformation.setAddres(fullUserInformation.getAddres());
                    userInformationRepository.updateEntity(connection, userInformation);
                }
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

    private UserInformation convertFullUserInformationDTOToUserInformation(FullUserInformationDTO fullUserInformation) {
        UserInformation userInformation = new UserInformation();

        userInformation.setUserId(fullUserInformation.getUserId());
        userInformation.setAddres(fullUserInformation.getAddres());
        userInformation.setTelephone(fullUserInformation.getTelephone());

        return userInformation;
    }

}
