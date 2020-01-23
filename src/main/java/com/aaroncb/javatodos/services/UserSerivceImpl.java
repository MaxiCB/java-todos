package com.aaroncb.javatodos.services;

import com.aaroncb.javatodos.models.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value="userService")
public class UserSerivceImpl implements UserService
{
    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public User findUserById(long id) {
        return null;
    }

    @Override
    public User findByName(String name) {
        return null;
    }

    @Override
    public User save(User user) {
        return null;
    }

    @Override
    public User update(User user) {
        return null;
    }

    @Override
    public void delete(long id) {

    }
}
