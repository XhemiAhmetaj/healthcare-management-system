package com.ikub.healthcare.service;

import com.ikub.healthcare.BaseTest;
import com.ikub.healthcare.domain.dto.UserDTO;
import com.ikub.healthcare.domain.entity.User;
import com.ikub.healthcare.domain.entity.enums.Department;
import com.ikub.healthcare.domain.entity.enums.UserRole;
import com.ikub.healthcare.domain.exception.ResourceNotFoundException;
import com.ikub.healthcare.repository.UserRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTest extends BaseTest {
    @Autowired
    private UserService toTest;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private PasswordEncoder encoder;
    private User user;

    @Test
    public void test_findUserById_ok(){
        Mockito.doReturn(Optional.of(new User())).when(userRepository).findById(Mockito.anyInt());
        User out = toTest.findById(1);
        assertNotNull(out);
    }

    @Test
    public void test_findUserById_ko(){
        Mockito.doThrow(new ResourceNotFoundException("User not found"))
                .when(userRepository).findById(Mockito.anyInt());
        Throwable throwable=assertThrows(Throwable.class,()-> toTest.findById(1));
        assertEquals(ResourceNotFoundException.class,throwable.getClass());
    }

    @Test
    public void test_registerUser_ok(){
        Mockito.doReturn("anyPass").when(encoder).encode(Mockito.anyString());
        Mockito.doReturn(new User ()).when(userRepository).save(Mockito.any());
        UserDTO out = toTest.registerUser(new UserDTO(),"DOCTOR","ORL" );
        assertNotNull(out);
    }

    @Test
    public void test_findAllUsers_ok(){
        List<User> users = new ArrayList<>();
        Mockito.doReturn(users).when(userRepository).findAll();
        List<UserDTO> out =toTest.findAllUsers();
        assertNotNull(out);
    }
    @Test
    public void test_deleteUser_ok(){
        Mockito.doNothing().when(userRepository).deleteById(Mockito.anyInt());
        toTest.deleteUser(1);
    }

}
