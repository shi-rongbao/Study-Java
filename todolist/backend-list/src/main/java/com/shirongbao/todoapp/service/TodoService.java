package com.shirongbao.todoapp.service;

import com.shirongbao.todoapp.model.Todo;

import java.util.List;

/**
 * @author ShiRongbao
 * @create 2024/11/17
 * @description:
 */
public interface TodoService {

    List<Todo> getAllTodos();

    Todo getTodo(String id);

    Todo createTodo(Todo todo);

    Todo updateTodo(String id, Todo todoDetails);

    void deleteTodo(String id);

}
