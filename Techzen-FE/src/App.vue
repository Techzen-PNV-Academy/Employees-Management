<template>
  <div>
    <form @submit.prevent="submitForm">
      <input v-model="searchQuery" type="text" placeholder="Enter a word" />
      <button type="submit" class="bg-primary-500">Search</button>
    </form>

    <div v-if="results">Result: {{ results }}</div>
    <div v-if="error" style="color: red;">{{ error }}</div>
  </div>
</template>


<script setup>
import { ref } from "vue";

const searchQuery = ref('');
const results = ref(null);
const error = ref(null);

const submitForm = async () => {
  const query = searchQuery.value.trim();

  try {
    const response = await fetch(`http://localhost:8080/dictionary/${query}`, {
      method: "GET",
      headers: {
        'Content-Type': 'application/json',
      },
    });

    if (!response.ok) {
      throw new Error(`Error: Không tìm thấy từ này trong từ điển`);
    }

    const data = await response.text();
    results.value = data;
    error.value = null;
  } catch (err) {
    console.error('Error fetching data:', err);
    error.value = err.message;
    results.value = null;
  }
};
</script>


<style scoped>
.bg-primary-500 {
  background-color: #3b82f6;
  color: white;
  border: none;
  border-radius: 2px;
  cursor: pointer;
}
</style>
