package org.example.todoapp.service;

import org.example.todoapp.entity.Todo;

import java.util.ArrayList;
import java.util.List;

public class TodoService {

    // init list
    private final List<Todo> todos = new ArrayList<>();

    // addTodo
    public void addTodo(Todo todo) {
        todos.add(todo);
    }

    // getAllTodos
    public List<Todo> getAllTodos() {
        return todos;
    }

    // completeTodo(Long id)
    public boolean completeTodo(Long id) {
        try {
            for (Todo todo : todos) {
                if (todo.getId() == id) {
                    if (todo.isCompleted()) {
                        return false;
                    }
                    todo.setCompleted(true);
                    return true;
                }
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    //deleteTodo(Long id)
    public boolean deleteTodo(Long id) {
        try {
            for (Todo todo : todos) {
                if (todo.getId() == id) {
                    todos.remove(todo);
                    return true;
                }
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }
}
