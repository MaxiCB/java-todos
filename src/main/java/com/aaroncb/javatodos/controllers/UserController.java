package com.aaroncb.javatodos.controllers;

import com.aaroncb.javatodos.models.Role;
import com.aaroncb.javatodos.models.Todo;
import com.aaroncb.javatodos.models.User;
import com.aaroncb.javatodos.repository.TodoRepository;
import com.aaroncb.javatodos.repository.UserRepository;
import com.aaroncb.javatodos.services.RoleService;
import com.aaroncb.javatodos.services.TodoService;
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
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController
{
    @Autowired
    UserService userService;

    @Autowired
    TodoService todoService;

    @Autowired
    TodoRepository todoRepository;

    @GetMapping(value="/users", produces = {"application/json"})
    public ResponseEntity<?> listAllUsers()
    {
        List<User> myUsers = userService.findAll();
        return new ResponseEntity<>(myUsers, HttpStatus.OK);
    }

    @GetMapping(value = "/users/{userId}", produces = {"application/json"})
    public ResponseEntity<?> findUserById(@PathVariable long userId)
    {
        User user = userService.findUserById(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping(value = "/users/name/{name}",
                produces = {"application/json"})
    public ResponseEntity<?> findUserByName(@PathVariable String name)
    {
        User user = userService.findByName(name);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping(value = "/todos/{userId}",
                produces = {"application/json"})
    public ResponseEntity<?> findUserTodos(@PathVariable Long userId)
    {
        List<Todo> todos = todoRepository.findTodosByCompletedFalseOrderByDatestarted();
        return new ResponseEntity<>(todos, HttpStatus.OK);
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

        return new ResponseEntity<>(newUser, responseHeaders, HttpStatus.CREATED);
    }

    @PutMapping(value = "/user/{id}",
            consumes = {"application/json"})
    public ResponseEntity<?> updateUser(
            @RequestBody
                    User updateUser,
            @PathVariable
                    Long id)
    {
        userService.update(updateUser,
                id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/user/{id}")
    public ResponseEntity<?> deleteUserById(
            @PathVariable
                    Long id)
    {
        User user = userService.findUserById(id);

        for(Todo td : user.getUserTodos()){
            todoService.delete(td.getTodoid());
        }

        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/user/{userid}/role/{roleid}")
    public ResponseEntity<?> deleteUserRoleByIds(
            @PathVariable
                    Long userid,
            @PathVariable
                    Long roleid)
    {
        userService.deleteUserRole(userid,
                roleid);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/user/{userid}/role/{roleid}")
    public ResponseEntity<?> postUserRoleByIds(
            @PathVariable
                    long userid,
            @PathVariable
                    long roleid)
    {
        userService.addUserRole(userid,
                roleid);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @PostMapping(value = "/todo/{userID}")
    public ResponseEntity<?> addUserTodo(@RequestBody
                                        Todo newTodo,
                                        @PathVariable
                                        Long userID) throws URISyntaxException
    {
        newTodo = todoService.save(newTodo, userID);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newUserURI = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{userid}")
                .buildAndExpand(newTodo.getTodoid())
                .toUri();
        responseHeaders.setLocation(newUserURI);

        return new ResponseEntity<>(newTodo, responseHeaders, HttpStatus.CREATED);
    }
}
