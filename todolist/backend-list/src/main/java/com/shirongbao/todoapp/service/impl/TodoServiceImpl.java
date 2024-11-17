package com.shirongbao.todoapp.service.impl;

import com.shirongbao.todoapp.model.Todo;
import com.shirongbao.todoapp.service.TodoService;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author ShiRongbao
 * @create 2024/11/17
 * @description:
 */
@Service
public class TodoServiceImpl implements TodoService {

    private static final String TODO_KEY_PREFIX = "todo:";

    @Resource
    private RedisTemplate<String, Todo> redisTemplate;

    private String generateKey(String id) {
        return TODO_KEY_PREFIX + id;
    }

    @Override
    public List<Todo> getAllTodos() {
        Set<String> keys = redisTemplate.keys(TODO_KEY_PREFIX + "*");
        if (keys.isEmpty()) {
            return new ArrayList<>();
        }

        return Objects.requireNonNull(redisTemplate.opsForValue()
                        .multiGet(keys))
                .stream()
                .filter(Objects::nonNull)
                .toList();
    }

    @Override
    public Todo createTodo(Todo todo) {
        String id = UUID.randomUUID().toString();
        todo.setId(id);
        redisTemplate.opsForValue().set(generateKey(id), todo);
        return todo;
    }

    @Override
    public Todo updateTodo(String id, Todo todoDetails) {
        String key = generateKey(id);
        Todo existingTodo = redisTemplate.opsForValue().get(key);
        if (existingTodo == null) {
            throw new RuntimeException("Todo not found");
        }

        todoDetails.setId(id);
        redisTemplate.opsForValue().set(key, todoDetails);
        return todoDetails;
    }

    @Override
    public void deleteTodo(String id) {
        redisTemplate.delete(generateKey(id));
    }

    @Override
    public Todo getTodo(String id) {
        return redisTemplate.opsForValue().get(generateKey(id));
    }

}
