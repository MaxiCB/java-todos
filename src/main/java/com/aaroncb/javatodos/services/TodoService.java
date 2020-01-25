/*
 * AaronCB - Created: 2020.
 */

/*
 * AaronCB - Created: 2020.
 */

package com.aaroncb.javatodos.services;

import com.aaroncb.javatodos.models.Todo;

import java.util.List;

public interface TodoService {
    List<Todo> findAll();

    Todo save(Todo todo, long userID);

    Todo findTodoById(long id);

    Todo update(Todo todo, long id);

    void delete(long id);
}
