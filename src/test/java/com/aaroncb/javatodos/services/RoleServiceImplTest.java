package com.aaroncb.javatodos.services;/*
 * AaronCB - Created: 2020.
 */

/*
 * AaronCB - Created: 2020.
 */


import com.aaroncb.javatodos.JavaTodosApplicationTests;
import com.aaroncb.javatodos.models.Role;
import com.aaroncb.javatodos.services.RoleService;
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

public class RoleServiceImplTest
{
    @Autowired
    private RoleService roleService;

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
    public void findAllRoles() throws Exception
    {
        List<Role> roleList =  roleService.findAll();

        assertEquals(3, roleList.size());

        for (Role r : roleList)
        {
            // System.out.println(r);
            System.out.println(mapper.writeValueAsString(r));
        }
    }
}