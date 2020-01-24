package com.aaroncb.javatodos;

import com.aaroncb.javatodos.models.Role;
import com.aaroncb.javatodos.models.Todo;
import com.aaroncb.javatodos.models.User;
import com.aaroncb.javatodos.models.UserRoles;
import com.aaroncb.javatodos.services.RoleService;
import com.aaroncb.javatodos.services.UserService;
import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
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

        User u1 = new User("Test User", "Test Password", "test@test.com");
        u1.setUserroles(adminRoles);

        u1.getUserTodos()
                .add(new Todo(u1, "Example Todo!",
                        new Date(),
                        false));

        u1.getUserTodos()
                .add(new Todo(u1, "Finish java-orders-swagger",
                        new Date(),
                        false));
        u1.getUserTodos()
                .add(new Todo(u1, "Feed the turtles",
                        new Date(),
                        false));
        u1.getUserTodos()
                .add(new Todo(u1, "Complete the sprint challenge",
                        new Date(),
                        false));

        userService.save(u1);

        ArrayList<UserRoles> users = new ArrayList<>();

        FakeValuesService fakeValuesService = new FakeValuesService(new Locale("en-US"),
                                                                    new RandomService());
        Faker nameFaker = new Faker(new Locale("en-US"));

        for (int i = 0; i < 100; i++)
        {
            new User();
            User fakeUser;

            users = new ArrayList<>();
            users.add(new UserRoles(new User(), r2));

            fakeUser = new User(nameFaker.name().username(),
                                "password",
                                nameFaker.internet().emailAddress());
            fakeUser.setUserroles(users);
            fakeUser.getUserTodos()
                    .add(new Todo(fakeUser, nameFaker.elderScrolls().city(), new Date(), false));
            fakeUser.getUserTodos()
                    .add(new Todo(fakeUser, nameFaker.elderScrolls().creature(), new Date(), false));
            fakeUser.getUserTodos()
                    .add(new Todo(fakeUser, nameFaker.elderScrolls().dragon(), new Date(), false));

            userService.save(fakeUser);
        }
    }
}
