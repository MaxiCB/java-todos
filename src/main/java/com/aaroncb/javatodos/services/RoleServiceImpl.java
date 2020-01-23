package com.aaroncb.javatodos.services;

import com.aaroncb.javatodos.models.Role;
import com.aaroncb.javatodos.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service(value="roleService")
public class RoleServiceImpl implements RoleService
{

    @Autowired
    RoleRepository roleRepository;
    @Override
    public List<Role> findAll() {
        List<Role> list = new ArrayList<>();
        roleRepository.findAll()
                .iterator()
                .forEachRemaining(list::add);
        return list;
    }

    @Override
    public Role findRoleById(long id) {
        return roleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Role id " + id + " not found!"));
    }

    @Override
    public Role findByName(String name) {
        Role rr = roleRepository.findByNameIgnoreCase(name);

        if (rr != null)
        {
            return rr;
        } else
        {
            throw new EntityNotFoundException(name);
        }
    }

    @Override
    public Role save(Role role) {
        Role newRole = new Role();
        newRole.setName(role.getName());
        if (role.getUserroles()
                .size() > 0)
        {
            throw new EntityNotFoundException("User Roles are not updated through Role. See endpoint POST: users/user/{userid}/role/{roleid}");
        }

        return roleRepository.save(role);
    }

    @Override
    public Role update(Role role, long id) {
        if (role.getName() == null)
        {
            throw new EntityNotFoundException("No role name found to update!");
        }

        if (role.getUserroles()
                .size() > 0)
        {
            throw new EntityNotFoundException("User Roles are not updated through Role. See endpoint POST: users/user/{userid}/role/{roleid}");
        }

        Role newRole = findRoleById(id); // see if id exists

//        roleRepository.updateRoleName(userAuditing.getCurrentAuditor().get(),
//                id,
//                role.getName());
        return findRoleById(id);
    }

    @Override
    public void delete(long id) {
        roleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Role id " + id + " not found!"));
        roleRepository.deleteById(id);
    }
}
