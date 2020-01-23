package com.aaroncb.javatodos;

import com.aaroncb.javatodos.models.User;
import com.aaroncb.javatodos.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.ExecutionException;

@Transactional
@Component
public class SeedData implements CommandLineRunner
{
    @Autowired
    UserService userService;

    @Override
    public void run(String[] args) throws Exception
    {
        User u1 = new User("Test User", "Test Password", "test@test.com");

        userService.save(u1);
    }
}
