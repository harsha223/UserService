package com.application.userservice.services;

import com.application.userservice.entities.UserType;
import com.application.userservice.repositories.UserTypeRepository;
import com.application.userservice.services.interfaces.UserTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class UserTypeServiceImpl implements UserTypeService
{
    @Autowired
    UserTypeRepository utRepo;

    private List<String> userTypesMetadata = Collections.unmodifiableList(List.of("PATIENT", "DOCTOR"));
    @Override
    public void createUserTypes() {
        for (String uType : getUserTypes()) {
            UserType userType = new UserType(uType);
            utRepo.save(userType);
        }
    }

    @Override
    public void deleteUserTypes(List<UserType> userTypes) {
        for (UserType ut: userTypes) {
            utRepo.delete(ut);
        }
    }

    @Override
    public List<String> getUserTypes() {
        return userTypesMetadata;
    }

    @Override
    public List<UserType> findUserTypeByType(String type) {
        return utRepo.findUserTypesByType(type);
    }

    @Override
    public void deleteAllUserTypes() {
        for (UserType ut : utRepo.findAll()) {
            utRepo.delete(ut);
        }
    }

}
