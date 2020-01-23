package com.aaroncb.javatodos;

import com.aaroncb.javatodos.models.Role;
import com.aaroncb.javatodos.models.User;
import com.aaroncb.javatodos.models.UserRoles;
import com.aaroncb.javatodos.services.RoleService;
import com.aaroncb.javatodos.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

@Transactional
@Component
public class SeedData implements CommandLineRunner
{
    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @Override
    public void run(String[] args) throws Exception
    {

        Role r1 = new Role("ADMIN");
        Role r2 = new Role("USER");
        Role r3 = new Role("DATA");

        r1 = roleService.save(r1);
        r2 = roleService.save(r2);
        r3 = roleService.save(r3);

        ArrayList<UserRoles> adminRoles = new ArrayList<>();
        adminRoles.add(new UserRoles(new User(), r1));
        adminRoles.add(new UserRoles(new User(), r2));
        adminRoles.add(new UserRoles(new User(), r3));

        User u1 = new User("Test User", "Test Password", "test@test.com", adminRoles);

        userService.save(u1);

    }
}
