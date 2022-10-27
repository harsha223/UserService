package com.application.userservice.controllers;

import com.application.userservice.entities.User;
import com.application.userservice.exceptions.ResourceNotFoundException;
import com.application.userservice.services.interfaces.UsersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Users resource controller
 * @author Hari Haran Chandra Sekaran
 */
@RestController
@RequestMapping(value = "/userservice")

public class UserServiceController
{
    private final Logger logger = LoggerFactory.getLogger(UserServiceController.class);
    @Autowired
    UsersService userService;


    /**
     * Gets all users list
     * @return the list
     */
    @GetMapping("/users")
    public List<User> showListOfUsers() {
        return userService.getListOfAllUsers();
    }

    /**
     * Gets user by userId
     * @param userId
     * @return response entity with user in body
     * @throws ResourceNotFoundException
     */
    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUsersById(@PathVariable(value = "id") Long userId) throws
            ResourceNotFoundException {
        User user = userService.getUser(userId);
        if (user == null) {
            if(logger.isDebugEnabled()) {
                logger.info("User not found :"+userId);
            }
            throw new ResourceNotFoundException("User not found on :: "+userId);
        }
        return ResponseEntity.ok().body(user);
    }

    /**
     * Updated user with userId using values in request body
     * For this usecase, only FullName and Email can be updated in User Entity.
     * @param userId
     * @param user
     */
    @PutMapping("/updateusers/{id}")
    public ResponseEntity<User> updateUser (@PathVariable(value = "id") Long userId, @RequestBody User user)
            throws Exception {
        User updatedUser = userService.updateUser(userId, user);
        return ResponseEntity.ok().body(updatedUser);

    }

    /**
     * Create user user.
     *
     * @param user the user
     * @return the user
     */
    @Transactional
    @PostMapping("/users")
    public User createUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    /**
     * Create list of users from request body.
     *
     * @param users to be created
     * @return the list of user
     */
    @Transactional
    @PostMapping("/bulkusers")
    public ResponseEntity<List<User>> createBulkUsers(@RequestBody List<User> users) {
        return ResponseEntity.ok().body(userService.addUsers(users));
    }

    /**
     * Delete user by using userId field
     *
     * @param userId the user id
     * @return the map
     * @throws Exception the exception
     */
    @Transactional
    @DeleteMapping("/deleteusers/{id}")
    public Map<String, Boolean> deleteUser(@PathVariable(value = "id") Long userId) throws Exception {
        userService.deleteUser(userId);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    /**
     * Gets user by userId
     * @param user
     * @return response entity with user in body
     * @throws ResourceNotFoundException
     */
    @GetMapping("/searchusers/")
    public List<User> searchUsersByFullName(@RequestBody User user) throws
            ResourceNotFoundException {
        List<User> users = userService.searchUser(user.getFullName());
        if (users == null || users.isEmpty()) {
            if (logger.isDebugEnabled()) {
                logger.info("User not found :"+user.getId());
            }
            throw new ResourceNotFoundException("User not found on :: "+user.getId());
        }
        return users;
    }
}
