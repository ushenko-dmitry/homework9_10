package ru.mail.dimaushenko.service;

import ru.mail.dimaushenko.service.model.FullUserInformationDTO;

public interface UserInformationService {

    public boolean updateUserInformationAddress(FullUserInformationDTO fullUserInformation);

}
