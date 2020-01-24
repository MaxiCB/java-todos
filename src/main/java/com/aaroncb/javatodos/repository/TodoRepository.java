package com.aaroncb.javatodos.repository;

import com.aaroncb.javatodos.models.Todo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TodoRepository extends CrudRepository<Todo, Long>
{
    List<Todo> findTodosByCompletedFalseOrderByDatestarted();
}
