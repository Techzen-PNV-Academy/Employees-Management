<template>
  <div class="container-fluid">
    <!-- Tiêu đề và nút thêm mới -->
    <div class="d-flex justify-content-between align-items-center mb-4">
      <h1 class="h3 text-dark">Danh sách nhân viên</h1>
      <button class="btn btn-add" @click="showAddForm">+ Thêm Mới</button>
    </div>

    <!-- Bảng danh sách nhân viên -->
    <div class="table-responsive">
      <table class="table table-striped table-bordered">
        <thead class="table-primary">
        <tr>
          <th>STT</th>
          <th>Tên</th>
          <th>Ngày sinh</th>
          <th>Giới tính</th>
          <th>Lương</th>
          <th>Thao tác</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="(employee, index) in employees" :key="employee.id">
          <td>{{ index + 1 }}</td>
          <td>{{ employee.name }}</td>
          <td>{{ formatDate(employee.birthDate) }}</td>
          <td>{{ employee.gender === 'MALE' ? 'Nam' : 'Nữ' }}</td>
          <td>{{ formatCurrency(employee.salary) }}</td>
          <td>
            <button class="btn btn-primary btn-sm mr-3" @click="showUpdateForm(employee)">Cập nhật</button>
            <button class="btn btn-danger btn-sm mr-3" @click="deleteEmployee(employee.id)">Xóa</button>
            <button class="btn btn-info btn-sm mr-3" @click="detailEmployee(employee.id)">Xem</button>
          </td>
        </tr>
        </tbody>
      </table>
    </div>

    <!-- Form Thêm/Cập nhật Nhân viên -->
    <div v-if="showForm" class="modal fade show" tabindex="-1" style="display: block;" aria-modal="true">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title">{{ formMode === 'add' ? 'Thêm Mới Nhân Viên' : 'Cập Nhật Thông Tin' }}</h5>
            <button type="button" class="btn-close" @click="closeForm" aria-label="Close"></button>
          </div>
          <div class="modal-body">
            <form @submit.prevent="handleSubmit">
              <div class="mb-3">
                <label for="name" class="form-label">Tên</label>
                <input type="text" class="form-control" id="name" v-model="formData.name" required />
              </div>
              <div class="mb-3">
                <label for="birthDate" class="form-label">Ngày Sinh</label>
                <input type="date" class="form-control" id="birthDate" v-model="formData.birthDate" required />
              </div>
              <div class="mb-3">
                <label for="gender" class="form-label">Giới tính</label>
                <select class="form-control" id="gender" v-model="formData.gender" required>
                  <option value="MALE">Nam</option>
                  <option value="FEMALE">Nữ</option>
                </select>
              </div>
              <div class="mb-3">
                <label for="salary" class="form-label">Lương</label>
                <input type="number" class="form-control" id="salary" v-model="formData.salary" required />
              </div>
              <div class="mb-3">
                <label for="phone" class="form-label">Số điện thoại</label>
                <input type="text" class="form-control" id="phone" v-model="formData.phone" required />
              </div>
              <div class="modal-footer">
                <button type="button" class="btn btn-secondary" @click="closeForm">Hủy</button>
                <button type="submit" class="btn btn-primary">{{ formMode === 'add' ? 'Thêm' : 'Cập Nhật' }}</button>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>

    <!-- Nút Thêm Mới -->
    <button class="btn btn-add" @click="showAddForm">+ Thêm Mới</button>
  </div>

</template>
-->
<!--<script>-->
<!--import { createApp } from 'vue';  // Import the createApp function from Vue 3-->
<!--import axios from 'axios';  // Import Axios-->

<!--const app = createApp({-->
<!--  data() {-->
<!--    return {-->
<!--      employees: [], // List of employees-->
<!--      showForm: false, // To control form visibility-->
<!--      formMode: 'add', // Form mode (add or update)-->
<!--      formData: { id: '', name: '', birthDate: '', gender: 'MALE', salary: 0, phone: '' }, // Form data-->
<!--    };-->
<!--  },-->
<!--  mounted() {-->
<!--    this.fetchEmployees(); // Fetch employees when component is mounted-->
<!--  },-->
<!--  methods: {-->
<!--    async fetchEmployees() {-->
<!--      try {-->
<!--        const response = await axios.get("http://localhost:8080/employees");-->
<!--        this.employees = response.data;-->
<!--      } catch (error) {-->
<!--        console.error(error);-->
<!--      }-->
<!--    },-->
<!--    async detailEmployee(id) {-->
<!--      const response = await axios.get(`http://localhost:8080/employees/${id}`);-->
<!--      this.detailData = response.data;-->
<!--      this.showDetail = true;-->
<!--    },-->
<!--    showAddForm() {-->
<!--      this.formMode = 'add';-->
<!--      this.formData = { id: '', name: '', birthDate: '', gender: 'MALE', salary: 0, phone: '' };-->
<!--      this.showForm = true;-->
<!--    },-->
<!--    showUpdateForm(employee) {-->
<!--      this.formMode = 'update';-->
<!--      this.formData = { ...employee };-->
<!--      this.showForm = true;-->
<!--    },-->
<!--    async handleSubmit() {-->
<!--      if (this.formMode === 'add') {-->
<!--        await axios.post("http://localhost:8080/employees", this.formData);-->
<!--      } else if (this.formMode === 'update') {-->
<!--        await axios.put(`http://localhost:8080/employees/${this.formData.id}`, this.formData);-->
<!--      }-->
<!--      this.closeForm();-->
<!--      this.fetchEmployees();-->
<!--    },-->
<!--    async deleteEmployee(id) {-->
<!--      if (confirm("Bạn có chắc chắn muốn xóa nhân viên này không?")) {-->
<!--        await axios.delete(`http://localhost:8080/employees/${id}`);-->
<!--        this.fetchEmployees();-->
<!--      }-->
<!--    },-->
<!--    closeForm() {-->
<!--      this.showForm = false;-->
<!--    },-->
<!--    formatDate(date) {-->
<!--      const formattedDate = new Date(date);-->
<!--      return formattedDate instanceof Date && !isNaN(formattedDate)-->
<!--        ? formattedDate.toLocaleDateString("vi-VN")-->
<!--        : "Invalid Date";-->
<!--    },-->
<!--    formatCurrency(value) {-->
<!--      return new Intl.NumberFormat("vi-VN", { style: "currency", currency: "VND" }).format(value);-->
<!--    },-->
<!--  },-->
<!--});-->

<!--app.mount('#app');  // Mount the Vue app to the element with id 'app'-->

<!--</script>

<style>
.table th {
  text-align: center;
  background-color: #007bff;
  color: white;
}
.table td {
  text-align: center;
  vertical-align: middle;
}
.table-striped tbody tr:nth-of-type(odd) {
  background-color: #f8f9fa;
}
.btn-custom {
  border-radius: 50px;
  padding: 8px 20px;
}
.btn-update {
  background-color: #4caf50;
  color: white;
}
.btn-delete {
  background-color: #f44336;
  color: white;
}
.btn-detail {
  background-color: #2196f3;
  color: white;
}
.btn-add {
  background-color: #28a745;
  color: white;
}
.container-fluid {
  margin-top: 20px;
}
</style>
