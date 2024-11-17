<script setup>
import { useTodoStore } from '../stores/todo';

const props = defineProps({
  todo: {
    type: Object,
    required: true
  }
});

const todoStore = useTodoStore();

const handleToggle = () => {
  todoStore.toggleTodo(props.todo);
};

const handleDelete = () => {
  todoStore.deleteTodo(props.todo.id);
};
</script>

<template>
  <div class="flex items-center justify-between p-4 bg-white rounded-lg shadow mb-2">
    <div class="flex items-center">
      <input
        type="checkbox"
        :checked="todo.completed"
        @change="handleToggle"
        class="w-4 h-4 text-blue-600 rounded focus:ring-blue-500"
      >
      <span
        class="ml-3"
        :class="{ 'line-through text-gray-500': todo.completed }"
      >
        {{ todo.title }}
      </span>
    </div>
    <button
      @click="handleDelete"
      class="text-red-500 hover:text-red-700 focus:outline-none"
    >
      删除
    </button>
  </div>
</template>