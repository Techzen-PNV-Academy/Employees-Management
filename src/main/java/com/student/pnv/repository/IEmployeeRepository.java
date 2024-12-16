package com.student.pnv.repository;

import com.student.pnv.ENUM.GENDER;
import com.student.pnv.entity.Department;
import com.student.pnv.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface IEmployeeRepository extends JpaRepository<Employee, Integer> {

    @Query("SELECT e FROM Employee e WHERE " +
            "(:name IS NULL OR e.name = :name) AND " +
            "(:gender IS NULL OR e.gender = :gender) AND " +
            "(:dob IS NULL OR e.dob = :dob) AND " +
            "(:minSalary IS NULL OR e.salary >= :minSalary) AND " +
            "(:maxSalary IS NULL OR e.salary <= :maxSalary) AND " +
            "(:department IS NULL OR e.department.id = :department)")
    List<Employee> findByAttr(
            @Param("name") String name,
            @Param("gender") GENDER gender,
            @Param("dob") LocalDate dob,
            @Param("minSalary") Double minSalary,
            @Param("maxSalary") Double maxSalary,
            @Param("department") Department department
    );

}