/*
 * AaronCB - Created: 2020.
 */

/*
 * AaronCB - Created: 2020.
 */

package com.aaroncb.javatodos.service;

import com.aaroncb.javatodos.JavaTodosApplicationTests;
import com.aaroncb.javatodos.models.User;
import com.aaroncb.javatodos.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = JavaTodosApplicationTests.class)
public class UserServiceImplUnitTest
{

    @Autowired
    private UserService userService;

    @Autowired
    private ObjectMapper mapper;

    @Before
    public void setUp() throws Exception
    {
        MockitoAnnotations.initMocks(this);
        mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
    }

    @After
    public void tearDown() throws Exception
    {
    }

    @Transactional
    @Test
    public void findAllUsers() throws Exception
    {
        List<User> userList =  userService.findAll();

        assertEquals(101, userList.size());

        for (User u : userList)
        {
            // System.out.println(r);
            System.out.println(mapper.writeValueAsString(u));
        }
    }

}
