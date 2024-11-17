<script setup>
import { onMounted } from 'vue';
import { useTodoStore } from './stores/todo';
import TodoForm from './components/TodoForm.vue';
import TodoItem from './components/TodoItem.vue';

const todoStore = useTodoStore();

onMounted(() => {
  todoStore.fetchTodos();
});
</script>

<template>
  <div class="min-h-screen bg-gray-100 py-8">
    <div class="max-w-2xl mx-auto px-4">
      <h1 class="text-3xl font-bold text-gray-900 mb-8">Todo List</h1>
      
      <TodoForm />
      
      <div v-if="todoStore.loading" class="text-center py-4">
        加载中...
      </div>
      
      <div v-else-if="todoStore.error" class="text-center py-4 text-red-500">
        {{ todoStore.error }}
      </div>
      
      <div v-else-if="todoStore.todos.length === 0" class="text-center py-4 text-gray-500">
        暂无待办事项
      </div>
      
      <div v-else class="space-y-2">
        <TodoItem
          v-for="todo in todoStore.todos"
          :key="todo.id"
          :todo="todo"
        />
      </div>
    </div>
  </div>
</template>