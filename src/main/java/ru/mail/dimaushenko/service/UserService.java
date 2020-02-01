package ru.mail.dimaushenko.service;

import java.util.List;

import ru.mail.dimaushenko.service.model.AddUserDTO;
import ru.mail.dimaushenko.service.model.FullUserDTO;
import ru.mail.dimaushenko.service.model.UserDTO;

public interface UserService {

    public void addUser(AddUserDTO addUserDTO);

    public List<UserDTO> getAllUser();

    public List<FullUserDTO> getAllFullUser();

    public boolean removeUser(Integer id);

    public void updateUser(FullUserDTO userDTO);

    public boolean isUserFoundById(Integer id);

}
