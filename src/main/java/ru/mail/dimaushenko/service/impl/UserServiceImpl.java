package ru.mail.dimaushenko.service.impl;

import java.lang.invoke.MethodHandles;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ru.mail.dimaushenko.repository.ConnectionPool;
import ru.mail.dimaushenko.repository.UserRepository;
import ru.mail.dimaushenko.repository.impl.ConnectionPoolImpl;
import ru.mail.dimaushenko.repository.impl.UserRepositoryImpl;
import ru.mail.dimaushenko.repository.model.User;
import ru.mail.dimaushenko.service.UserService;
import ru.mail.dimaushenko.service.model.AddUserDTO;
import ru.mail.dimaushenko.service.model.FullUserDTO;
import ru.mail.dimaushenko.service.model.UserDTO;

public class UserServiceImpl implements UserService {

    private static UserService instance = null;

    private final ConnectionPool connectionPool;
    private final UserRepository userRepository;
    private final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    private UserServiceImpl() {
        connectionPool = ConnectionPoolImpl.getInstance();
        userRepository = UserRepositoryImpl.getInstance();
    }

    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserServiceImpl();
        }
        return instance;
    }

    @Override
    public void addUser(AddUserDTO addUserDTO) {
        try (Connection connection = connectionPool.getConnection()) {
            connection.setAutoCommit(false);
            try {
                User user = convertUserDTOToUser(addUserDTO);
                userRepository.addEntity(connection, user);
                connection.commit();
            } catch (SQLException ex) {
                logger.error(ex.getMessage(), ex);
                connection.rollback();
            }
        } catch (SQLException ex) {
            logger.error(ex.getMessage(), ex);
        }
    }

    @Override
    public List<UserDTO> getAllUser() {
        List<UserDTO> usersDTO = new ArrayList();

        try (Connection connection = connectionPool.getConnection()) {
            connection.setAutoCommit(false);
            try {
                List<User> users = userRepository.getAll(connection);
                usersDTO = convertUsersToUsersDTO(users);
                connection.commit();
            } catch (SQLException ex) {
                logger.error(ex.getMessage(), ex);
                connection.rollback();
            }
        } catch (SQLException ex) {
            logger.error(ex.getMessage(), ex);
        }

        return usersDTO;
    }

    @Override
    public List<FullUserDTO> getAllFullUser() {
        List<FullUserDTO> usersDTO = new ArrayList();

        try (Connection connection = connectionPool.getConnection()) {
            connection.setAutoCommit(false);
            try {
                List<User> users = userRepository.getAll(connection);
                usersDTO = convertUsersToFullUsersDTO(users);
                connection.commit();
            } catch (SQLException ex) {
                logger.error(ex.getMessage(), ex);
                connection.rollback();
            }
        } catch (SQLException ex) {
            logger.error(ex.getMessage(), ex);
        }

        return usersDTO;
    }

    @Override
    public boolean removeUser(Integer id) {
        try (Connection connection = connectionPool.getConnection()) {
            connection.setAutoCommit(false);
            try {
                if (userRepository.removeEntity(connection, id)) {
                    connection.commit();
                    return true;
                }
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
    public void updateUser(FullUserDTO userDTO) {
        try (Connection connection = connectionPool.getConnection()) {
            connection.setAutoCommit(false);
            try {
                User user = convertFullUserDTOToUser(userDTO);
                userRepository.updateEntity(connection, user);
                connection.commit();
            } catch (SQLException ex) {
                logger.error(ex.getMessage(), ex);
                connection.rollback();
            }
        } catch (SQLException ex) {
            logger.error(ex.getMessage(), ex);
        }
    }

    @Override
    public boolean isUserFoundById(Integer id) {
        try (Connection connection = connectionPool.getConnection()) {
            try {
                return userRepository.isEntityFoundById(connection, id);
            } catch (SQLException ex) {
                logger.error(ex.getMessage(), ex);
            }
        } catch (SQLException ex) {
            logger.error(ex.getMessage(), ex);
        }
        return false;
    }

    private User convertUserDTOToUser(AddUserDTO addUserDTO) {
        User user = new User();

        user.setUsername(addUserDTO.getUsername());
        user.setPassword(addUserDTO.getPassword());
        user.setIsActive(addUserDTO.isIsActive());
        user.setAge(addUserDTO.getAge());

        return user;
    }

    private User convertFullUserDTOToUser(FullUserDTO fullUserDTO) {
        User user = new User();

        user.setId(fullUserDTO.getId());
        user.setUsername(fullUserDTO.getUsername());
        user.setPassword(fullUserDTO.getPassword());
        user.setIsActive(fullUserDTO.isIsActive());
        user.setAge(fullUserDTO.getAge());

        return user;
    }

    private List<UserDTO> convertUsersToUsersDTO(List<User> users) {
        List<UserDTO> usersDTO = new ArrayList();
        for (User user : users) {
            UserDTO userDTO = new UserDTO();

            userDTO.setUsername(user.getUsername());
            userDTO.setPassword(user.getPassword());
            userDTO.setIsActive(user.isIsActive());
            userDTO.setAge(user.getAge());

            usersDTO.add(userDTO);
        }
        return usersDTO;
    }

    private List<FullUserDTO> convertUsersToFullUsersDTO(List<User> users) {
        List<FullUserDTO> usersDTO = new ArrayList();
        for (User user : users) {
            FullUserDTO userDTO = new FullUserDTO();

            userDTO.setId(user.getId());
            userDTO.setUsername(user.getUsername());
            userDTO.setPassword(user.getPassword());
            userDTO.setIsActive(user.isIsActive());
            userDTO.setAge(user.getAge());

            usersDTO.add(userDTO);
        }
        return usersDTO;
    }

}
