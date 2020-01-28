/*
 * AaronCB - Created: 2020.
 */

/*
 * AaronCB - Created: 2020.
 */

/*
 * AaronCB - Created: 2020.
 */

/*
 * AaronCB - Created: 2020.
 */

package com.aaroncb.javatodos.services;

import com.aaroncb.javatodos.JavaTodosApplicationTests;
import com.aaroncb.javatodos.models.Role;
import com.aaroncb.javatodos.models.User;
import com.aaroncb.javatodos.models.UserRoles;
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
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = JavaTodosApplicationTests.class)
public class UserServiceImplUnitTest
{

    @Autowired
    private UserService userService;

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

    @Test (expected = EntityNotFoundException.class)
    public void saveUserThrow() throws Exception
    {
        User u1 = new User("Test User", "Test Password", "test@test.com");

        userService.save(u1);
    }

    @Test
    public void findAllUsers() throws Exception
    {
        List<User> userList =  userService.findAll();

        assertEquals(101, userList.size());
    }

    @Test
    public void findUserById() throws Exception
    {
        assertEquals("test user", userService.findUserById(4).getUsername());
    }

    @Test (expected = EntityNotFoundException.class)
    public void findByUserIdThrow()
    {
        User errorUser = userService.findUserById(9999);
    }

    @Test
    public void findByUsername()
    {
        assertEquals("test user", userService.findByName("test user").getUsername());
    }

    @Test (expected = EntityNotFoundException.class)
    public void findByUsernameThrow()
    {
        assertEquals("test user", userService.findByName(""));
    }

    @Test
    public void updateUser()
    {
        User user = new User("asdfasdfasd", "asdfasdfasdf", "asdfasdf@test.com");

        User updateRestaurant = userService.update(user, 13);

        assertEquals(user.getUsername(), updateRestaurant.getUsername());
    }

    @Test
    @Transactional
    public void deleteUserRole()
    {
        User currUser = userService.findUserById(13);

        List<UserRoles> roles =  currUser.getUserroles();
        for(UserRoles ur : roles)
        {
            long id = ur.getRole().getRoleid();
            currUser = userService.deleteUserRole(13, id);
        }

        assertEquals(0, currUser.getUserroles().size());
    }

    @Test
    @Transactional
    public void addUserRole()
    {
        Role newRole = roleService.findRoleById(3);

        ArrayList<UserRoles> users = new ArrayList<>();

        users.add(new UserRoles(new User(), newRole));

        userService.findUserById(13).setUserroles(users);

        assertEquals(1, userService.findUserById(13).getUserroles().size());
    }
}
