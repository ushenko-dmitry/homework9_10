package ru.mail.dimaushenko.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ru.mail.dimaushenko.service.UserService;
import ru.mail.dimaushenko.service.impl.UserServiceImpl;
import ru.mail.dimaushenko.service.model.FullUserDTO;

import static ru.mail.dimaushenko.constants.Attributes.ATTRIBUTE_NAME_USERS;
import static ru.mail.dimaushenko.constants.Pages.VIEW_ALL_USERS;

public class ViewAllUserServlet extends ManagerServlet {

    private final UserService userService = UserServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<FullUserDTO> users = userService.getAllFullUser();
        
        req.setAttribute(ATTRIBUTE_NAME_USERS, users);
        forward(VIEW_ALL_USERS, req, resp);
    }

}
