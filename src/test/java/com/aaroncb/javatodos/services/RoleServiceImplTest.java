package com.aaroncb.javatodos.services;/*
 * AaronCB - Created: 2020.
 */

/*
 * AaronCB - Created: 2020.
 */


import com.aaroncb.javatodos.JavaTodosApplicationTests;
import com.aaroncb.javatodos.models.Role;
import com.aaroncb.javatodos.models.User;
import com.aaroncb.javatodos.models.UserRoles;
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

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
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

    @Test
    public void findAllRoles() throws Exception
    {
        List<Role> roleList =  roleService.findAll();

        assertEquals(3, roleList.size());

        for (Role r : roleList)
        {
            System.out.println(mapper.writeValueAsString(r));
        }
    }

    @Test
    public void findByID()
    {
        assertEquals("ADMIN", roleService.findRoleById(1).getName());
    }

    @Test
    public void findByName()
    {
        assertEquals("ADMIN", roleService.findByName("ADMIN").getName());
    }

    @Test (expected = EntityNotFoundException.class)
    public void findByNameThrow()
    {
        assertEquals("ERROR", roleService.findByName("ERROR").getName());
    }

    @Transactional
    @Test
    public void update()
    {
        Role role = new Role("UPDATE");
        roleService.update(role, 1);
        assertEquals("UPDATE", roleService.findRoleById(1).getName());
    }

    @Transactional
    @Test (expected = EntityNotFoundException.class)
    public void updateNoName()
    {
        Role role = new Role();
        roleService.update(role, 1);
        assertEquals("UPDATE", roleService.findRoleById(1).getName());
    }

    @Transactional
    @Test (expected = EntityNotFoundException.class)
    public void saveThrow()
    {
        Role role = new Role("UPDATE");
        Role role1 = new Role("TEST");

        role = roleService.save(role);
        role1 = roleService.save(role1);

        List<UserRoles> roles = new ArrayList<>();

        roles.add(new UserRoles(new User(), role));
        roles.add(new UserRoles(new User(), role1));

        role.setUserroles(roles);

        roleService.save(role);
    }

    @Transactional
    @Test (expected = EntityNotFoundException.class)
    public void updateUserRolesThrow()
    {
        Role role = new Role("UPDATE");
        Role role1 = new Role("TEST");

        role = roleService.save(role);
        role1 = roleService.save(role1);

        List<UserRoles> roles = new ArrayList<>();

        roles.add(new UserRoles(new User(), role));
        roles.add(new UserRoles(new User(), role1));

        role.setUserroles(roles);

        roleService.update(role, 1);
        assertEquals("UPDATE", roleService.findRoleById(1).getName());
    }

    @Transactional
    @Test (expected = EntityNotFoundException.class)
    public void deleteThrow()
    {
        roleService.delete(100);
        assertEquals(2, roleService.findAll().size());
    }

    @Transactional
    @Test
    public void delete()
    {
        roleService.delete(1);
        assertEquals(2, roleService.findAll().size());
    }
}