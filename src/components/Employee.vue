<template>
  <div>
    <!-- Existing Table Section -->
    <h1 class="text-2xl font-bold mb-4">Danh sách nhân viên</h1>
    <button @click="fetchEmployees" class="bg-blue-500 text-white py-2 px-4 rounded">
      Load Employees
    </button>

    <table class="table-auto w-full mt-4 border-collapse border">
      <thead>
        <tr class="bg-gray-200">
          <th class="border px-4 py-2">STT</th>
          <th class="border px-4 py-2">Tên</th>
          <th class="border px-4 py-2">Ngày sinh</th>
          <th class="border px-4 py-2">Giới tính</th>
          <th class="border px-4 py-2">Lương</th>
          <th class="border px-4 py-2">SĐT</th>
          <th class="border px-4 py-2">DEPARTERMENT</th>
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
          <td class="border px-4 py-2">{{ employee.departerment }}</td>
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
            <button
              @click="viewDetail(employee)"
              class="bg-blue-500 text-white py-1 px-2 rounded"
            >
              View Detail
            </button>
          </td>
        </tr>
      </tbody>
    </table>

    <div v-if="showDetailModal" class="fixed inset-0 bg-gray-800 bg-opacity-75 flex justify-center items-center">
      <div class="bg-white p-6 rounded-lg w-1/3">
        <h2 class="text-xl font-bold mb-4">Chi tiết nhân viên</h2>
        <p><strong>Tên:</strong> {{ selectedEmployee.name }}</p>
        <p><strong>Ngày sinh:</strong> {{ selectedEmployee.dob }}</p>
        <p><strong>Giới tính:</strong> {{ selectedEmployee.gender }}</p>
        <p><strong>Lương:</strong> {{ formatSalary(selectedEmployee.salary) }} ₫</p>
        <p><strong>SĐT:</strong> {{ selectedEmployee.phone }}</p>
        <div class="mt-4 flex justify-end">
          <button
            @click="closeDetailModal"
            class="bg-gray-500 text-white px-4 py-2 rounded"
          >
            Close
          </button>
        </div>
      </div>
    </div>

    <div class="bg-gray-100 mt-8 p-4 rounded-lg shadow">
      <h2 class="text-lg font-bold mb-4">Tìm kiếm nhân viên</h2>
      <form @submit.prevent="searchEmployees" class="grid grid-cols-3 gap-4">
        <div>
          <label class="block mb-1 text-gray-700">Tên (Tìm kiếm gần đúng)</label>
          <input
            v-model="searchForm.name"
            type="text"
            class="w-full border rounded p-2"
            placeholder="Nhập tên"
          />
        </div>

        <div>
          <label class="block mb-1 text-gray-700">Ngày sinh từ</label>
          <input
            v-model="searchForm.dobFrom"
            type="date"
            class="w-full border rounded p-2"
          />
        </div>

        <div>
          <label class="block mb-1 text-gray-700">Ngày sinh đến</label>
          <input
            v-model="searchForm.dobTo"
            type="date"
            class="w-full border rounded p-2"
          />
        </div>

        <div>
          <label class="block mb-1 text-gray-700">Giới tính</label>
          <select
            v-model="searchForm.gender"
            class="w-full border rounded p-2"
          >
            <option value="">Tất cả</option>
            <option value="MALE">Nam</option>
            <option value="FEMALE">Nữ</option>
          </select>
        </div>

        <div>
          <label class="block mb-1 text-gray-700">Mức lương</label>
          <select
            v-model="searchForm.salaryRange"
            class="w-full border rounded p-2"
          >
            <option value="">Tất cả</option>
            <option value="0-1000">Dưới 1000</option>
            <option value="1000-2000">1000 - 2000</option>
            <option value="2000+">Trên 2000</option>
          </select>
        </div>

        <div>
          <label class="block mb-1 text-gray-700">Số điện thoại (Tìm kiếm gần đúng)</label>
          <input
            v-model="searchForm.phone"
            type="text"
            class="w-full border rounded p-2"
            placeholder="Nhập số điện thoại"
          />
        </div>

        <div>
          <label class="block mb-1 text-gray-700">Tìm bộ phận</label>
          <select
            v-model="searchForm.departerment"
            class="w-full border rounded p-2"
          >
            <option value="0">Tất cả</option>
            <option value="1">Quản lí</option>
            <option value="2">Kế toán</option>
            <option value="3">Sản xuất</option>
            <option value="4">Sale</option>
          </select>
        </div>

        <div class="col-span-3 flex justify-end gap-2 mt-4">
          <button
            type="button"
            @click="resetSearch"
            class="bg-gray-500 text-white px-4 py-2 rounded"
          >
            Đặt lại
          </button>
          <button
            type="submit"
            class="bg-blue-500 text-white px-4 py-2 rounded"
          >
            Tìm kiếm
          </button>
        </div>
      </form>
    </div>
  </div>
</template>

<script>
import { getEmployees, deleteEmployee } from "../services/employeeService";

export default {
  data() {
    return {
      employees: [],
      searchForm: {
        name: "",
        dobFrom: "",
        dobTo: "",
        gender: "",
        salaryRange: "",
        phone: "",
        departerment: "",
      },
    };
  },
  methods: {
    async fetchEmployees() {
      try {
        this.employees = await getEmployees();
      } catch (error) {
        console.error("Failed to fetch employees:", error);
      }
    },

    searchEmployees() {
      console.log("Search criteria:", this.searchForm);
    },

    resetSearch() {
      this.searchForm = {
        name: "",
        dobFrom: "",
        dobTo: "",
        gender: "",
        salaryRange: "",
        phone: "",
        departerment: "",
      };
      this.fetchEmployees();
    },

    async deleteEmployee(employee) {
      try {
        await deleteEmployee(employee);
        this.employees = this.employees.filter((emp) => emp.id !== employee.id);
      } catch (error) {
        console.error("Failed to delete employee:", error);
      }
    },

    formatSalary(salary) {
      return salary.toLocaleString();
    },
  },
  mounted() {
    this.fetchEmployees();
  },
};
</script>

<style scoped>
</style>
