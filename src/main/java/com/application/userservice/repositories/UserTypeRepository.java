package com.application.userservice.repositories;

import com.application.userservice.entities.UserType;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserTypeRepository extends CrudRepository<UserType, Long>
{
    List<UserType> findUserTypesByUserTypeId(Long id);
    List<UserType> findUserTypesByType(String type);
}
