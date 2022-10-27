package com.application.userservice.unittests;

import java.util.List;

import com.application.userservice.entities.User;
import com.application.userservice.entities.UserType;
import com.application.userservice.repositories.UserRepository;
import com.application.userservice.services.interfaces.UsersService;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;

import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.test.context.junit.jupiter.SpringExtension;

import org.testng.annotations.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest
{
//    @InjectMocks
//    UsersService uService;
//
//    @MockBean
//    UserRepository uRepo;

//    @Test
//    public void testSearchUser() {
//        when(uRepo.searchUsersByFullNameContaining("Adam"))
//                .thenReturn(List.of(
//                        new User("Adam adam", "adam@gmail.com", new UserType("PATIENT"))
//                ));
//
//        assertEquals("Adam adam", uService.searchUser("Adam").get(0).getFullName());
//    }

}
