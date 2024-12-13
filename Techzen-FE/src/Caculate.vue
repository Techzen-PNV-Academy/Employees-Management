<template xmlns="http://www.w3.org/1999/html">
  <div>
    <h1>Máy tính đơn giản</h1>
    <form @submit.prevent="submitForm" class="form-caculator" id="form">
      <input v-model="fNum" type="number" />
      <input v-model="sNum" type="number"/>
      <select v-model="selected">
        <option v-for="option in options" :value="option.value">{{ option.text }}</option>
      </select>
      <button type="submit" class="bg-primary-500">Tính Toán</button>
    </form>

    <div v-if="fNum"> {{ fNum }}</div>
    <div v-if="sNum"> {{ sNum }}</div>
    <div v-if="error" style="color: red;">{{ error }}</div>
    <div v-if="selected">{{selected}}</div>
    <div v-if="results">Result: {{ results }}</div>
  </div>

</template>

<script setup>
import { ref } from "vue";

const results = ref(null);
const error = ref(null);
const selected = ref('')
const fNum = ref(null);
const sNum = ref(null);

const options = ref([
  { text: 'Cộng', value: '+' },
  { text: 'Trừ', value: '-' },
  { text: 'Nhân', value: '*' },
  { text: 'Chia', value: '/' }
])

const submitForm = async () => {
  try {
    const params = new URLSearchParams({
      fNum: fNum.value,
      sNum: sNum.value,
      operator: selected.value,
    });

    const response = await fetch(`http://localhost:8080/caculator?${params.toString()}`, {
      method: "GET",
      headers: {
        'Content-Type': 'application/json',
      },
    });

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
.form-caculator{
  width: 300px;
  display: flex;
  justify-content:  space-evenly;
}

.bg-primary-500 {
  background-color: #3b82f6;
  color: white;
  border: none;
  border-radius: 2px;
  cursor: pointer;
}
</style>
