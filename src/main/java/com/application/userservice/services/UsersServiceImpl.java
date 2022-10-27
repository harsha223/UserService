package com.application.userservice.services;

import com.application.userservice.entities.User;
import com.application.userservice.entities.UserType;
import com.application.userservice.exceptions.ResourceNotFoundException;
import com.application.userservice.repositories.UserRepository;
import com.application.userservice.services.interfaces.UserTypeService;
import com.application.userservice.services.interfaces.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class UsersServiceImpl implements UsersService
{
    @Autowired
    UserRepository uRepo;

    @Autowired
    UserTypeService utService;


    /**
     * Creates test users for integration tests.
     * TODO:// Best to be created in a separate test component.
     * In the interest of time creating it here.
     * @return List of users.
     */
    @Override
    public List<User> createTestUserForAllUserTypes() {
        utService.createUserTypes();
        List<User> users = new ArrayList<>();
        int i = 0;
        for (String uType : utService.getUserTypes()) {
            List<UserType> userTypes = utService.findUserTypeByType(uType);
            if (userTypes != null) {
                User user = new User("Jim Anderson "+i, "jim"+i+"@gmail.com", userTypes.get(0));
                users.add(user);
                i++;
            }
        }
        return users;
    }

    @Override
    public List<User> addUsers(List<User> userList) {
        List<User> users = new ArrayList<>();
        for (User user : userList) {
            users.add(addUser(user));
        }
        return users;
    }

    @Override
    public User addUser(User user) {
        uRepo.save(user);
        return user;
    }

    @Override
    public List<User> getListOfAllUsers() {
        return (List<User>) uRepo.findAll();
    }

    @Override
    public User getUser(Long userId) {
        return uRepo.getUserById(userId);
    }

    @Override
    public User updateUser(Long userId, User userWithUpdatedValues)
            throws Exception {
        if (userId != null && userWithUpdatedValues != null) {
            User user = getUser(userId);
            if (user != null) {
                if (userWithUpdatedValues.getFullName() != null) {
                    user.setFullName(userWithUpdatedValues.getFullName());
                }
                if (userWithUpdatedValues.getEmail() != null) {
                    user.setEmail(userWithUpdatedValues.getEmail());
                }
                // update audit field related to update
                user.setLastModifiedDate(LocalDateTime.now());

                uRepo.save(user);
                return user;
            }
            else {
                throw new ResourceNotFoundException("User not found : "+userId);
            }
        }
        else {
            throw new Exception("Update user failed since userId or update values are missing");
        }
    }


    @Override
    public void deleteUser(Long id) {
        User user = getUser(id);
        uRepo.deleteByEmail(user.getEmail());
    }

    @Override
    public List<User> searchUser(String fullName) {
        return uRepo.searchUsersByFullNameContaining(fullName);
    }


    @Override
    public void deleteAllUsers() {
        for(User user : getListOfAllUsers()) {
            deleteUser(user.getId());
        }
    }
}
