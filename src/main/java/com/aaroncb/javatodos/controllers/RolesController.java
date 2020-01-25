/*
 * AaronCB - Created: 2020.
 */

/*
 * AaronCB - Created: 2020.
 */

package com.aaroncb.javatodos.controllers;

import com.aaroncb.javatodos.models.Role;
import com.aaroncb.javatodos.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RolesController {
    @Autowired
    RoleService roleService;

    @GetMapping(value = "/roles",
            produces = {"application/json"})
    public ResponseEntity<?> listRoles() {
        List<Role> allRoles = roleService.findAll();
        return new ResponseEntity<>(allRoles,
                HttpStatus.OK);
    }

    @GetMapping(value = "/role/{roleId}",
            produces = {"application/json"})
    public ResponseEntity<?> getRoleById(
            @PathVariable
                    Long roleId) {
        Role r = roleService.findRoleById(roleId);
        return new ResponseEntity<>(r.toString(),
                HttpStatus.OK);
    }
}
