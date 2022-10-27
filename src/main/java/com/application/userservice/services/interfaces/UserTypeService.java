package com.application.userservice.services.interfaces;

import com.application.userservice.entities.UserType;

import java.util.List;

public interface UserTypeService
{
    void createUserTypes();
    void deleteUserTypes(List<UserType> userTypes);
    List<String> getUserTypes();

    List<UserType> findUserTypeByType (String type);

    void deleteAllUserTypes();
}
