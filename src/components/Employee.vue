<template>
  <div>
    <h1 class="text-2xl font-bold mb-4">Danh sách nhân viên</h1>
    <button @click="fetchEmployees" class="bg-blue-500 text-white py-2 px-4 rounded">
      Load Employees
    </button>

    <!-- Employee Table -->
    <table class="table-auto w-full mt-4 border-collapse border">
      <thead>
        <tr class="bg-gray-200">
          <th class="border px-4 py-2">STT</th>
          <th class="border px-4 py-2">Tên</th>
          <th class="border px-4 py-2">Ngày sinh</th>
          <th class="border px-4 py-2">Giới tính</th>
          <th class="border px-4 py-2">Lương</th>
          <th class="border px-4 py-2">SĐT</th>
          <th class="border px-4 py-2">Actions</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="(employee, index) in employees" :key="employee.id">
          <td class="border px-4 py-2">{{ index + 1 }}</td>
          <td class="border px-4 py-2">{{ employee.name }}</td>
          <td class="border px-4 py-2">{{ employee.dob }}</td>
          <td class="border px-4 py-2">{{ employee.gender }}</td>
          <td class="border px-4 py-2">{{ formatSalary(employee.salary) }} ₫</td>
          <td class="border px-4 py-2">{{ employee.phone }}</td>
          <td class="border px-4 py-2">
            <button
              @click="openEditModal(employee)"
              class="bg-yellow-500 text-white py-1 px-2 rounded mr-1"
            >
              Update
            </button>
            <button
              @click="deleteEmployee(employee)"
              class="bg-red-500 text-white py-1 px-2 rounded"
            >
              Delete
            </button>
          </td>
        </tr>
      </tbody>
    </table>

    <!-- Update Modal -->
    <div v-if="showModal" class="fixed inset-0 flex items-center justify-center bg-gray-800 bg-opacity-75">
      <div class="bg-white p-6 rounded shadow-lg w-1/2">
        <h2 class="text-xl font-bold mb-4">Cập nhật thông tin nhân viên</h2>
        <form @submit.prevent="submitUpdate">
          <div class="mb-2">
            <label class="block">Tên</label>
            <input v-model="editEmployee.name" class="w-full border p-2 rounded" />
          </div>
          <div class="mb-2">
            <label class="block">Ngày sinh</label>
            <input type="date" v-model="editEmployee.dob" class="w-full border p-2 rounded" />
          </div>
          <div class="mb-2">
            <label class="block">Giới tính</label>
            <select v-model="editEmployee.gender" class="w-full border p-2 rounded">
              <option value="MALE">Nam</option>
              <option value="FEMALE">Nữ</option>
            </select>
          </div>
          <div class="mb-2">
            <label class="block">Lương</label>
            <input v-model.number="editEmployee.salary" class="w-full border p-2 rounded" />
          </div>
          <div class="mb-2">
            <label class="block">SĐT</label>
            <input v-model="editEmployee.phone" class="w-full border p-2 rounded" />
          </div>
          <div class="flex justify-end mt-4">
            <button type="button" @click="closeModal" class="bg-gray-500 text-white px-4 py-2 rounded mr-2">
              Hủy
            </button>
            <button type="submit" class="bg-blue-500 text-white px-4 py-2 rounded">
              Cập nhật
            </button>
          </div>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
import {
  getEmployees,
  updateEmployee,
  deleteEmployee,
} from "../services/employeeService";

export default {
  name: "EmployeeTable",
  data() {
    return {
      employees: [],
      showModal: false,
      editEmployee: {
        id: null,
        name: "",
        dob: "",
        gender: "",
        salary: 0,
        phone: "",
      },
    };
  },
  methods: {
    // Fetch all employees
    async fetchEmployees() {
      try {
        this.employees = await getEmployees();
      } catch (error) {
        console.error("Failed to load employees:", error);
      }
    },

    // Open the modal and fill with selected employee data
    openEditModal(employee) {
      this.editEmployee = { ...employee };
      this.showModal = true;
    },

    // Submit updated employee data
    async submitUpdate() {
      try {
        const result = await updateEmployee(this.editEmployee);
        const index = this.employees.findIndex((emp) => emp.id === result.id);
        if (index !== -1) {
          this.employees.splice(index, 1, result);
        }
        this.closeModal();
      } catch (error) {
        console.error("Failed to update employee:", error);
      }
    },

    // Close modal
    closeModal() {
      this.showModal = false;
    },

    // Delete an employee
    async deleteEmployee(employee) {
      try {
        await deleteEmployee(employee);
        this.employees = this.employees.filter((emp) => emp.id !== employee.id);
      } catch (error) {
        console.error("Failed to delete employee:", error);
      }
    },

    // Format salary to display with commas
    formatSalary(salary) {
      return salary.toLocaleString();
    },
  },
  mounted() {
    this.fetchEmployees();
  },
};
</script>

<style>
/* Optional: Style for modal backdrop */
</style>
