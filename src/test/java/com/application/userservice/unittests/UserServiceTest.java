package com.application.userservice.unittests;

import java.util.List;

import com.application.userservice.entities.User;
import com.application.userservice.entities.UserType;
import com.application.userservice.repositories.UserRepository;
import com.application.userservice.services.UsersServiceImpl;
import com.application.userservice.services.interfaces.UsersService;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;

import org.mockito.Mock;

import org.springframework.boot.test.context.SpringBootTest;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
@SpringBootTest
public class UserServiceTest
{
    @InjectMocks
    UsersService uService = new UsersServiceImpl();

    @Mock
    UserRepository uRepo;

    @Test
    public void testSearchUser() {

        when(uRepo.searchUsersByFullNameContaining("Adam"))
                .thenReturn(List.of(
                        new User("Adam adam", "adam@gmail.com", new UserType("PATIENT"))
                ));

        assertEquals("Adam adam", uService.searchUser("Adam").get(0).getFullName());
    }

}
