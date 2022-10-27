package com.application.userservice;

import com.application.userservice.entities.User;
import com.application.userservice.entities.UserType;
import com.application.userservice.services.interfaces.UserTypeService;
import com.application.userservice.services.interfaces.UsersService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;


@SpringBootTest
class UserServiceApplicationTests {
    @Autowired
    UserTypeService utService;

    @Autowired
    UsersService uService;

    @Test
    void testLoadUserTypes() {
        //load test data
        utService.createUserTypes();
        List<UserType> userTypes;
        List<UserType> userTypesToBeDeleted = new ArrayList<>();
        for (String uType : utService.getUserTypes()) {
            userTypes = utService.findUserTypeByType(uType);
            if (userTypes != null) {
                userTypesToBeDeleted.addAll(userTypes);
            }
            Assert.noNullElements(userTypes, uType+" UserType entity is not created");
        }
    }

    @Test
    void testUserCreationAddition() {
        //load test data
        List<User> users = uService.createTestUserForAllUserTypes();
        uService.addUsers(users);

        for (User persistedUser: uService.getListOfAllUsers()) {
            boolean foundUser = false;
            User createdUser = new User();
            for (int i = 0; i < users.size(); i++) {
                createdUser = users.get(i);
                if (createdUser.getFullName().equals(persistedUser.getFullName())) {
                    foundUser = true;
                    break;
                }
            }
            Assert.isTrue(foundUser, "User not found for "+createdUser.getFullName());
        }
    }


}
