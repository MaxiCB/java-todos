package com.aaroncb.javatodos.services;

import com.aaroncb.javatodos.models.Todo;

public interface TodoService
{
    Todo save(Todo todo, long userID);

    Todo findTodoById(long id);

    Todo update(Todo todo, long id);
}
