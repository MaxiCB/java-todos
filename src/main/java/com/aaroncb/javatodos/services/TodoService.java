package com.aaroncb.javatodos.services;

import com.aaroncb.javatodos.models.Todo;

public interface TodoService
{
    Todo save(Todo todo, long userID);
}
