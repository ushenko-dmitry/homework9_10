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
import ru.mail.dimaushenko.utils.PropertyUtil;
import ru.mail.dimaushenko.utils.impl.PropertyUtilConstantsImpl;

import static ru.mail.dimaushenko.constants.ErrorConstants.ERROR_DATA_IS_INCORRECT;
import static ru.mail.dimaushenko.constants.ErrorConstants.MESSAGE_USER_IS_NOT_FOUND;
import static ru.mail.dimaushenko.constants.PropertyConstants.FORM_PARAMETER_ID;
import static ru.mail.dimaushenko.constants.Pages.PAGE_REMOVE_USER;
import static ru.mail.dimaushenko.constants.Attributes.ATTRIBUTE_NAME_MESSAGE;
import static ru.mail.dimaushenko.constants.Attributes.ATTRIBUTE_PROPERTY_USER_NOT_REMOVED;
import static ru.mail.dimaushenko.constants.Attributes.ATTRIBUTE_PROPERTY_USER_REMOVED;

public class RemoveUserServlet extends ManagerServlet {

    private final PropertyUtil propertyUtil = PropertyUtilConstantsImpl.getInstance();
    private final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    private final UserService userService = UserServiceImpl.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String message;

        String idStr = req.getParameter(propertyUtil.getProperty(FORM_PARAMETER_ID));

        try {
            Integer id = Integer.parseInt(idStr);

            boolean isUserFound = userService.isUserFoundById(id);

            if (isUserFound) {
                boolean isUserRemoved = userService.removeUser(id);
                if (isUserRemoved) {
                    message = ATTRIBUTE_PROPERTY_USER_REMOVED;
                } else {
                    message = ATTRIBUTE_PROPERTY_USER_NOT_REMOVED;
                }
            } else {
                logger.error(MESSAGE_USER_IS_NOT_FOUND);
                message = ATTRIBUTE_PROPERTY_USER_NOT_REMOVED;
            }

        } catch (NumberFormatException nfe) {
            logger.error(ERROR_DATA_IS_INCORRECT);
            message = ATTRIBUTE_PROPERTY_USER_NOT_REMOVED;
        }

        req.setAttribute(ATTRIBUTE_NAME_MESSAGE, message);
        forward(PAGE_REMOVE_USER, req, resp);

    }

}
