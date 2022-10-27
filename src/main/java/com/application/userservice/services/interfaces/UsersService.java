package com.application.userservice.services.interfaces;

import com.application.userservice.entities.User;

import java.util.List;

public interface UsersService
{
    List<User> createTestUserForAllUserTypes();
    List<User> addUsers(List<User> userList);

    User addUser(User user);

    List<User> getListOfAllUsers();
    User getUser(Long userId);
    User updateUser(Long userId, User userWithUpdatedValues) throws Exception;
    void deleteUser(Long userId);
    List<User> searchUser(String fullName);

    void deleteAllUsers();
}
