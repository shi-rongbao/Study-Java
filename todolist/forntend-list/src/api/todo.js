import axios from 'axios';

const API_URL = 'http://localhost:8080/api/todos';

export const todoApi = {
  async getAllTodos() {
    const response = await axios.get(API_URL);
    return response.data;
  },

  async createTodo(todo) {
    const response = await axios.post(API_URL, todo);
    return response.data;
  },

  async updateTodo(id, todo) {
    const response = await axios.put(`${API_URL}/${id}`, todo);
    return response.data;
  },

  async deleteTodo(id) {
    await axios.delete(`${API_URL}/${id}`);
  },

  async getTodoById(id) {
    const response = await axios.get(`${API_URL}/${id}`);
    return response.data;
  }
};