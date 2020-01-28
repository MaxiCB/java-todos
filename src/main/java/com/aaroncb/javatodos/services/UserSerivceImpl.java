/*
 * AaronCB - Created: 2020.
 */

/*
 * AaronCB - Created: 2020.
 */

package com.aaroncb.javatodos.services;

import com.aaroncb.javatodos.models.Role;
import com.aaroncb.javatodos.models.Todo;
import com.aaroncb.javatodos.models.User;
import com.aaroncb.javatodos.models.UserRoles;
import com.aaroncb.javatodos.repository.RoleRepository;
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

    @Autowired
    RoleRepository roleRepository;

    @Override
    public List<User> findAll() {
        List<User> list = new ArrayList<>();
        userRepository.findAll()
                .iterator()
                .forEachRemaining(list::add);
        return list;
    }

    @Override
    public User findUserById(long id) throws EntityNotFoundException {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User id " + id + " not found!"));
    }

    @Override
    public User findByName(String name) {
        User u = userRepository.findByUsername(name.toLowerCase());
        if (u == null) {
            throw new EntityNotFoundException("User name " + name + " not found!");
        }
        return u;
    }

    @Transactional
    @Override
    public User save(User user) {
        if (userRepository.findByUsername(user.getUsername()
                .toLowerCase()) != null) {
            throw new EntityNotFoundException(user.getUsername() + " is already taken!");
        }

        User newUser = new User();
        newUser.setUsername(user.getUsername()
                .toLowerCase());
        newUser.setPassword(user.getPassword());
        newUser.setEmail(user.getEmail()
                .toLowerCase());

        ArrayList<UserRoles> newRoles = new ArrayList<>();

        for (UserRoles ur : user.getUserroles()) {
            long id = ur.getRole()
                    .getRoleid();
            Role role = roleRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Role id " + id + " not found!"));
            newRoles.add(new UserRoles(newUser,
                    role));
        }
        newUser.setUserroles(newRoles);

        for (Todo ue : user.getUserTodos()) {
            newUser.getUserTodos()
                    .add(new Todo(newUser,
                            ue.getDescription(),
                            ue.getDatestarted(),
                            ue.isCompleted()));
        }

        return userRepository.save(newUser);
    }

    @Transactional
    @Override
    public User update(User user,
                       long id) {

        User currentUser = findUserById(id);

        if (user.getUsername() != null) {
            currentUser.setUsername(user.getUsername()
                    .toLowerCase());
        }

        if (user.getPassword() != null) {
            currentUser.setPassword(user.getPassword());
        }

        if (user.getEmail() != null) {
            currentUser.setEmail(user.getEmail()
                    .toLowerCase());
        }

        if (user.getUserroles().size() > 0)
        {
            roleRepository.deleteUserRoles(currentUser.getUserid());

            // add the new ones
            for (UserRoles ur : user.getUserroles())
            {
                roleRepository.insertUserRoles(id, ur.getRole().getRoleid());
            }
        }

        return userRepository.save(currentUser);
    }

    @Transactional
    @Override
    public void delete(long id) {
        userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User id " + id + " not found!"));
        userRepository.deleteById(id);
    }
}
