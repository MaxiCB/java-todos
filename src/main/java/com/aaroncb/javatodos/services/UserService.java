/*
 * AaronCB - Created: 2020.
 */

/*
 * AaronCB - Created: 2020.
 */

package com.aaroncb.javatodos.services;

import com.aaroncb.javatodos.models.User;

import java.util.List;

public interface UserService {
    List<User> findAll();

    User findUserById(long id);

    User findByName(String name);

    User save(User user);

    User update(User user, long id);

    void deleteUserRole(long userid,
                        long roleid);

    void addUserRole(long userid,
                     long roleid);

    void delete(long id);

//    Need to add Deleting && Adding user roles && todos
//    Need to add counting of user todos
}
