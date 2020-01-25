/*
 * AaronCB - Created: 2020.
 */

/*
 * AaronCB - Created: 2020.
 */

package com.aaroncb.javatodos.services;

import com.aaroncb.javatodos.models.Todo;
import com.aaroncb.javatodos.models.User;
import com.aaroncb.javatodos.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service(value = "todoService")
public class TodoServiceImpl implements TodoService
{

    @Autowired
    TodoRepository todoRepository;

    @Autowired
    UserService userService;

    @Override
    public List<Todo> findAll() {
        List<Todo> todos = new ArrayList<>();
        todoRepository.findAll()
                .iterator()
                .forEachRemaining(todos::add);
        return todos;
    }

    @Override
    public Todo findTodoById(long id) throws EntityNotFoundException {
        return todoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Todo id " + id + " not found"));
    }

    @Transactional
    @Override
    public Todo save(Todo todo, long userID) {

        User user = userService.findUserById(userID);

        Todo newTodo = new Todo(user,
                todo.getDescription(),
                todo.getDatestarted(),
                todo.isCompleted());

        return todoRepository.save(newTodo);
    }

    @Transactional
    @Override
    public Todo update(Todo todo,
                       long todoId) {

        Todo currTodo = findTodoById(todoId);

        if (todo.getDescription() != null) {
            currTodo.setDescription(todo.getDescription()
                    .toLowerCase());
        }

        if (todo.isCompleted()) {
            currTodo.setCompleted(todo.isCompleted());
        }

        if (todo.getUser() != null) {
            currTodo.setUser(userService.findUserById(todo.getUser().getUserid()));
        }
        return todoRepository.save(currTodo);
    }

    @Transactional
    @Override
    public void delete(long id) {
        todoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Todo " + id + " not found"));
        todoRepository.deleteById(id);
    }
}
