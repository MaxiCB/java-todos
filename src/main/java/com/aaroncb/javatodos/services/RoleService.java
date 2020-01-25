/*
 * AaronCB - Created: 2020.
 */

/*
 * AaronCB - Created: 2020.
 */

package com.aaroncb.javatodos.services;

import com.aaroncb.javatodos.models.Role;

import java.util.List;

public interface RoleService {
    List<Role> findAll();

    Role findRoleById(long id);

    Role findByName(String name);

    Role save(Role role);

    Role update(Role role, long id);

    void delete(long id);
}
