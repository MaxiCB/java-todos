package com.aaroncb.javatodos.services;

import com.aaroncb.javatodos.models.User;
import com.aaroncb.javatodos.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service(value="userService")
public class UserSerivceImpl implements UserService
{
    @Autowired
    UserRepository userRepository;

//    Need to bring in Roles Repository when Implemented

    @Override
    public List<User> findAll()
    {
        List<User> list = new ArrayList<>();
        userRepository.findAll()
                .iterator()
                .forEachRemaining(list::add);
        return list;
    }

    public User findUserById(long id) throws EntityNotFoundException
    {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User id " + id + " not found!"));
    }

    @Override
    public User findByName(String name)
    {
        User uu = userRepository.findByUsername(name.toLowerCase());
        if (uu == null)
        {
            throw new EntityNotFoundException("User name " + name + " not found!");
        }
        return uu;
    }

    @Transactional
    @Override
    public User save(User user)
    {
        if (userRepository.findByUsername(user.getUsername()
                .toLowerCase()) != null)
        {
            throw new EntityNotFoundException(user.getUsername() + " is already taken!");
        }

        User newUser = new User();
        newUser.setUsername(user.getUsername()
                .toLowerCase());
        newUser.setPassword(user.getPassword());
        newUser.setPrimaryemail(user.getPrimaryemail()
                .toLowerCase());

        return userRepository.save(newUser);
    }

    @Transactional
    @Override
    public User update(User user,
                       long id)
    {

        User currentUser = findUserById(id);

        if (user.getUsername() != null)
        {
            currentUser.setUsername(user.getUsername()
                    .toLowerCase());
        }

        if (user.getPassword() != null)
        {
            currentUser.setPassword(user.getPassword());
        }

        if (user.getPrimaryemail() != null)
        {
            currentUser.setPrimaryemail(user.getPrimaryemail()
                    .toLowerCase());
        }

        return userRepository.save(currentUser);
    }

    @Transactional
    @Override
    public void delete(long id)
    {
        userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User id " + id + " not found!"));
        userRepository.deleteById(id);
    }
}
