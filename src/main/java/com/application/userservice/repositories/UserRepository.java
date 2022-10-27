package com.application.userservice.repositories;

import com.application.userservice.entities.EntityId;
import com.application.userservice.entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, EntityId>
{
    List<User> getUsersByFullNameContaining(String fullName);

    User getUserById(Long id);

    void deleteUserById(Long id);

    void deleteByEmail(String email);

    List<User> searchUsersByFullNameContaining(String fullName);

    User searchUserById(Long id);

}
