/*
 * AaronCB - Created: 2020.
 */

/*
 * AaronCB - Created: 2020.
 */

/*
 * AaronCB - Created: 2020.
 */

/*
 * AaronCB - Created: 2020.
 */

package com.aaroncb.javatodos.services;

import com.aaroncb.javatodos.JavaTodosApplicationTests;
import com.aaroncb.javatodos.models.Todo;
import com.aaroncb.javatodos.models.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = JavaTodosApplicationTests.class)
public class TodoServiceImplUnitTest
{
    @Autowired
    private TodoService todoService;

    @Autowired
    private UserService userService;

    @Autowired
    private ObjectMapper mapper;

    @Before
    public void setUp() throws Exception
    {
        MockitoAnnotations.initMocks(this);
        mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
    }

    @After
    public void tearDown() throws Exception
    {
    }

    @Transactional
    @Test
    public void findAllTodos() throws Exception
    {
        List<Todo> todoList =  todoService.findAll();

        assertEquals(304, todoList.size());

        for (Todo t : todoList)
        {
            // System.out.println(r);
            System.out.println(mapper.writeValueAsString(t));
        }
    }
    @Transactional
    @Test
    public void findByID()
    {
        assertEquals("Example Todo!", todoService.findTodoById(5).getDescription());
    }

    @Transactional
    @Test
    public void save()
    {
        User user = userService.findUserById(4);

        Todo newTodo = new Todo(user, "ADD", new Date(), false);

        newTodo = todoService.save(newTodo, 4);

        user.getUserTodos().add(newTodo);

        assertEquals(5, user.getUserTodos().size());
    }

    @Transactional
    @Test
    public void update()
    {
        User user1 = userService.findUserById(4);
        User user2 = userService.findUserById(13);

        Todo newTodo = new Todo(user1, "update", new Date(), false);

        newTodo = todoService.save(newTodo, 4);

        long todoID = newTodo.getTodoid();

        newTodo.setUser(user2);
        newTodo.setCompleted(true);
        newTodo.setDescription("updated");

        newTodo = todoService.update(newTodo, todoID);

        todoID = newTodo.getTodoid();

        assertEquals("updated", todoService.findTodoById(todoID).getDescription());
        assertEquals(true, todoService.findTodoById(todoID).isCompleted());
        assertEquals(13, todoService.findTodoById(todoID).getUser().getUserid());
    }

    @Transactional
    @Test (expected = EntityNotFoundException.class)
    public void deleteThrow()
    {
        todoService.delete(9999);
        assertEquals(303, todoService.findAll().size());
    }

    @Transactional
    @Test
    public void delete()
    {
        todoService.delete(5);
        assertEquals(303, todoService.findAll().size());
    }
}
