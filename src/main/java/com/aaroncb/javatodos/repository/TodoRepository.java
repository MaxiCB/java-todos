package com.aaroncb.javatodos.repository;

import com.aaroncb.javatodos.models.Todo;
import org.springframework.data.repository.CrudRepository;

public interface TodoRepository extends CrudRepository<Todo, Long>
{

}
