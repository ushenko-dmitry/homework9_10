package ru.mail.dimaushenko.controller;

import java.io.IOException;
import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import ru.mail.dimaushenko.service.TableService;
import ru.mail.dimaushenko.service.impl.UserInformationTableServiceImpl;
import ru.mail.dimaushenko.service.impl.UserTableServiceImpl;
import ru.mail.dimaushenko.utils.PrepareDataBase;

public class StartServlet extends GenericServlet {

    private final TableService userTableService = UserTableServiceImpl.getInstance();
    private final TableService userInformationTableService = UserInformationTableServiceImpl.getInstance();

    @Override
    public void init() {
        PrepareDataBase.getInstance().prepareDB();

        boolean isUserInformationTableRemoved = userInformationTableService.removeTable();
        if (isUserInformationTableRemoved) {
            userTableService.removeTable();
        }
        boolean isUserTableCreated = userTableService.createTable();
        if (isUserTableCreated) {
            userInformationTableService.createTable();
        }
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {

    }

}
