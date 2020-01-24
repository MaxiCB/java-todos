package com.aaroncb.javatodos.controllers;

import com.aaroncb.javatodos.models.User;
import com.aaroncb.javatodos.repository.UserRepository;
import com.aaroncb.javatodos.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController
{
    @Autowired
    UserService userService;

    @GetMapping(value="users", produces = {"application/json"})
    public ResponseEntity<?> listAllUsers()
    {
        List<User> myUsers = userService.findAll();
        return new ResponseEntity<>(myUsers, HttpStatus.OK);
    }

    @GetMapping(value = "users/{userid}", produces = {"application/json"})
    public ResponseEntity<?> findUserById(@PathVariable long userId)
    {
        User user = userService.findUserById(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping(value = "users/{name}",
                produces = {"application/json"})
    public ResponseEntity<?> findUserByName(@PathVariable String name)
    {
        User user = userService.findByName(name);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping(value = "/user",
                consumes = {"application/json"})
    public ResponseEntity<?> addNewUser(@Valid
                                        @RequestBody
                                            User newUser) throws URISyntaxException
    {
        newUser = userService.save(newUser);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newUserURI = ServletUriComponentsBuilder.fromCurrentRequest()
                                                    .path("/{userid}")
                                                    .buildAndExpand(newUser.getUserid())
                                                    .toUri();
        responseHeaders.setLocation(newUserURI);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }
}
