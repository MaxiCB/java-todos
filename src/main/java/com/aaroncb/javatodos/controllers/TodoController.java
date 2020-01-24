package com.aaroncb.javatodos.controllers;

import com.aaroncb.javatodos.models.Todo;
import com.aaroncb.javatodos.services.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todos")
public class TodoController
{

    @Autowired
    TodoService todoService;

    @GetMapping(value ="/todos",
                produces = {"application/json"})
    public ResponseEntity<?> findAll()
    {
        List<Todo> todoList =  todoService.findAll();
        return new ResponseEntity<>(todoList, HttpStatus.OK);
    }
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

        Todo todo = todoService.findTodoById(todoId);

        return new ResponseEntity<>(todo, HttpStatus.OK);
    }

    @DeleteMapping(value="/{todoId}")
    public ResponseEntity<?> deleteTodo(
            @PathVariable Long todoId
    )
    {
        todoService.delete(todoId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
