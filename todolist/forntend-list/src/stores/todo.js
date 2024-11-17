import { defineStore } from 'pinia';
import { todoApi } from '../api/todo';

export const useTodoStore = defineStore('todo', {
  state: () => ({
    todos: [],
    loading: false,
    error: null
  }),

  actions: {
    async fetchTodos() {
      this.loading = true;
      try {
        this.todos = await todoApi.getAllTodos();
      } catch (error) {
        this.error = error.message;
      } finally {
        this.loading = false;
      }
    },

    async addTodo(title) {
      try {
        const newTodo = await todoApi.createTodo({
          title,
          completed: false
        });
        this.todos.push(newTodo);
      } catch (error) {
        this.error = error.message;
      }
    },

    async toggleTodo(todo) {
      try {
        const updatedTodo = await todoApi.updateTodo(todo.id, {
          ...todo,
          completed: !todo.completed
        });
        const index = this.todos.findIndex(t => t.id === todo.id);
        if (index !== -1) {
          this.todos[index] = updatedTodo;
        }
      } catch (error) {
        this.error = error.message;
      }
    },

    async deleteTodo(id) {
      try {
        await todoApi.deleteTodo(id);
        this.todos = this.todos.filter(todo => todo.id !== id);
      } catch (error) {
        this.error = error.message;
      }
    }
  }
});