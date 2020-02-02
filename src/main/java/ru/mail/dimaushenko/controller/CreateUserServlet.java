package ru.mail.dimaushenko.controller;

import java.io.IOException;
import java.lang.invoke.MethodHandles;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ru.mail.dimaushenko.service.UserService;
import ru.mail.dimaushenko.service.impl.UserServiceImpl;
import ru.mail.dimaushenko.service.model.AddUserDTO;
import ru.mail.dimaushenko.utils.PropertyUtil;
import ru.mail.dimaushenko.utils.impl.PropertyUtilConstantsImpl;
import ru.mail.dimaushenko.utils.Validator;
import ru.mail.dimaushenko.utils.impl.ValidatorImpl;

import static ru.mail.dimaushenko.constants.PropertyConstants.FORM_PARAMETER_AGE;
import static ru.mail.dimaushenko.constants.PropertyConstants.FORM_PARAMETER_IS_ACTIVE;
import static ru.mail.dimaushenko.constants.PropertyConstants.FORM_PARAMETER_PASSWORD;
import static ru.mail.dimaushenko.constants.PropertyConstants.FORM_PARAMETER_USERNAME;
import static ru.mail.dimaushenko.constants.Attributes.ATTRIBUTE_ERR_AGE;
import static ru.mail.dimaushenko.constants.Attributes.ATTRIBUTE_ERR_PASSWORD;
import static ru.mail.dimaushenko.constants.Attributes.ATTRIBUTE_ERR_USERNAME;
import static ru.mail.dimaushenko.constants.Pages.PAGE_CREATE_USER;
import static ru.mail.dimaushenko.constants.Attributes.ATTRIBUTE_NAME_MESSAGE;
import static ru.mail.dimaushenko.constants.Attributes.ATTRIBUTE_PROPERTY_ERROR_VALIDATION_AGE;
import static ru.mail.dimaushenko.constants.Attributes.ATTRIBUTE_PROPERTY_ERROR_VALIDATION_PASSWORD;
import static ru.mail.dimaushenko.constants.Attributes.ATTRIBUTE_PROPERTY_ERROR_VALIDATION_USERNAME;
import static ru.mail.dimaushenko.constants.Attributes.ATTRIBUTE_PROPERTY_USER_CREATED;

public class CreateUserServlet extends ManagerServlet {

    private final PropertyUtil propertyUtil = PropertyUtilConstantsImpl.getInstance();
    private final UserService userService = UserServiceImpl.getInstance();
    private final Validator validator = ValidatorImpl.getInstaice();
    private final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String message;

        String username = req.getParameter(propertyUtil.getProperty(FORM_PARAMETER_USERNAME));
        String password = req.getParameter(propertyUtil.getProperty(FORM_PARAMETER_PASSWORD));
        String isActiveStr = req.getParameter(propertyUtil.getProperty(FORM_PARAMETER_IS_ACTIVE));
        String ageStr = req.getParameter(propertyUtil.getProperty(FORM_PARAMETER_AGE));

        boolean isValid = true;

        if (!validator.isStringLengthValid(username, 40)) {
            isValid = false;
            req.setAttribute(ATTRIBUTE_ERR_USERNAME, ATTRIBUTE_PROPERTY_ERROR_VALIDATION_USERNAME);
        }

        if (!validator.isStringContainsUsernameSymbols(username)) {
            isValid = false;
            req.setAttribute(ATTRIBUTE_ERR_USERNAME, ATTRIBUTE_PROPERTY_ERROR_VALIDATION_USERNAME);
        }

        if (!validator.isStringLengthValid(password, 40)) {
            isValid = false;
            req.setAttribute(ATTRIBUTE_ERR_PASSWORD, ATTRIBUTE_PROPERTY_ERROR_VALIDATION_PASSWORD);
        }

        if (!validator.isInteger(ageStr)) {
            isValid = false;
            req.setAttribute(ATTRIBUTE_ERR_AGE, ATTRIBUTE_PROPERTY_ERROR_VALIDATION_AGE);
        }

        if (isValid) {
            AddUserDTO addUserDTO = new AddUserDTO();
            addUserDTO.setUsername(username);
            addUserDTO.setPassword(password);

            boolean isActive = !(isActiveStr == null);
            addUserDTO.setIsActive(isActive);

            Integer age = Integer.parseInt(ageStr);
            addUserDTO.setAge(age);

            userService.addUser(addUserDTO);
            message = ATTRIBUTE_PROPERTY_USER_CREATED;
        } else {
            message = ATTRIBUTE_PROPERTY_USER_CREATED;
        }

        req.setAttribute(ATTRIBUTE_NAME_MESSAGE, message);

        forward(PAGE_CREATE_USER, req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        forward(PAGE_CREATE_USER, req, resp);

    }

}
