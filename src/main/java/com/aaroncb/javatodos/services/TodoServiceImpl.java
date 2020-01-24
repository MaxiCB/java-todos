package com.aaroncb.javatodos.services;

import com.aaroncb.javatodos.models.Role;
import com.aaroncb.javatodos.models.Todo;
import com.aaroncb.javatodos.models.User;
import com.aaroncb.javatodos.models.UserRoles;
import com.aaroncb.javatodos.repository.TodoRepository;
import com.aaroncb.javatodos.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service(value = "todoService")
public class TodoServiceImpl implements TodoService
{

    @Autowired
    TodoRepository todoRepository;

    @Autowired
    UserService userService;

    @Override
    public Todo save(Todo todo, long userID) {

        User user = userService.findUserById(userID);

        Todo newTodo = new Todo(user,
                todo.getDescription(),
                todo.getDatestarted(),
                todo.isCompleted());

        return todoRepository.save(newTodo);
    }
}
