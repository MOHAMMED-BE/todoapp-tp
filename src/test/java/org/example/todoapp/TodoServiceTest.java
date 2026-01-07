package org.example.todoapp;

import org.example.todoapp.entity.Todo;
import org.example.todoapp.service.TodoService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.*;

@SpringBootTest
class TodoServiceTest {

    TodoService todoService = new TodoService();

    // Tests pour chaque méthode
    @Test
    void addTodo() {
        todoService.addTodo(new Todo(1, "Todo"));
        assertEquals(1, todoService.getAllTodos().size());
    }

    @Test
    void getAllTodos() {
        todoService.addTodo(new Todo(1, "Todo 1"));
        todoService.addTodo(new Todo(2, "Todo 2"));
        assertEquals(2, todoService.getAllTodos().size());
    }

    @Test
    void completeTodo() {
        todoService.addTodo(new Todo(1, "Todo 1"));
        boolean result = todoService.completeTodo(1L);
        assertTrue(result);
        assertTrue(todoService.getAllTodos().get(0).isCompleted());

    }

    @Test
    void deleteTodo() {
        todoService.addTodo(new Todo(1, "Todo 1"));
        todoService.addTodo(new Todo(2, "Todo 2"));
        todoService.deleteTodo(1L);
        assertEquals(1, todoService.getAllTodos().size());
    }

    // Tests des cas d’erreur
    // @Test
    // void completeTodoWithError() {
    //     todoService.addTodo(new Todo(1, "Todo 1"));
    //     boolean result = todoService.completeTodo(2L);
    //     assertTrue(result);
    //     assertTrue(todoService.getAllTodos().get(0).isCompleted());

    // }

    // @Test
    // void deleteTodoWithError() {
    //     todoService.addTodo(new Todo(1, "Todo 1"));
    //     todoService.addTodo(new Todo(2, "Todo 2"));
    //     todoService.deleteTodo(3L);
    //     assertEquals(1, todoService.getAllTodos().size());
    // }
}
