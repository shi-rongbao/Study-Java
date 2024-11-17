<script setup>
import { ref } from 'vue';
import { useTodoStore } from '../stores/todo';

const todoStore = useTodoStore();
const newTodo = ref('');

const handleSubmit = async () => {
  if (newTodo.value.trim()) {
    await todoStore.addTodo(newTodo.value.trim());
    newTodo.value = '';
  }
};
</script>

<template>
  <form @submit.prevent="handleSubmit" class="mb-4">
    <div class="flex gap-2">
      <input
        v-model="newTodo"
        type="text"
        placeholder="添加新的待办事项..."
        class="flex-1 px-4 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
      >
      <button
        type="submit"
        class="px-6 py-2 bg-blue-500 text-white rounded-lg hover:bg-blue-600 focus:outline-none focus:ring-2 focus:ring-blue-500"
      >
        添加
      </button>
    </div>
  </form>
</template>