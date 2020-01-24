package com.aaroncb.javatodos.controllers;

import com.aaroncb.javatodos.models.Todo;
import com.aaroncb.javatodos.services.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/todos")
public class TodoController
{

    @Autowired
    TodoService todoService;

    @PutMapping(value = "/todo/{todoId}",
            consumes = {"application/json"})
    public ResponseEntity<?> updateTodo(
            @RequestBody
                    Todo updateTodo,
            @PathVariable
                    Long todoId)
    {
        todoService.update(updateTodo,
                todoId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
