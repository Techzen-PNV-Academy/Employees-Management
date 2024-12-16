//package com.student.pnv.repository.impl;
//
//import com.student.pnv.entity.Department;
//import com.student.pnv.repository.IDepartmentRepository;
//import org.springframework.stereotype.Repository;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//@Repository
//public class DepartmentRepository implements IDepartmentRepository {
//
//    private List<Department> departments = new ArrayList<>(
//            Arrays.asList(
//                    new Department(1, "Quản lý"),
//                    new Department(2, "Kế toán"),
//                    new Department(3, "Sale Marketing"),
//                    new Department(4, "Sản xuất")
//            )
//    );
//
//    public List<Department> findAll() {
//        return departments;
//    }
//
//    public Department findById(Integer id) {
//        for (Department department : departments) {
//            if (department.getId() == (id)) {
//                return department;
//            }
//        }
//        return null;
//    }
//
//    public Department save(Department department) {
//        for (Department dep : departments) {
//            if (dep.getId() == (department.getId())) {
//                departments.add(dep);
//                return dep;
//            }
//        }
//        return null;
//    }
//
//    public Department update(Department department) {
//        for (Department dep : departments) {
//            if (dep.getId() == (department.getId())) {
//                departments.add(dep);
//                return dep;
//            }
//        }
//        return null;
//    }
//
//    public Department delete(Integer id) {
//        for (Department dep : departments) {
//            if (dep.getId() == (id)) {
//                departments.remove(dep);
//                return dep;
//            }
//        }
//        return null;
//    }
//}