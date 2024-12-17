package com.student.pnv.repository;

import com.student.pnv.dto.employee.EmployeeSearchRequest;
import com.student.pnv.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IEmployeeRepository extends JpaRepository<Employee, Integer> {

    @Query("""
        FROM Employee 
        WHERE (:#{#employeeSearchRequest.name} IS NULL OR name LIKE CONCAT('%', :#{#employeeSearchRequest.name}, '%'))
        AND (:#{#employeeSearchRequest.dobFrom} IS NULL OR dob >= :#{#employeeSearchRequest.dobFrom})
        AND (:#{#employeeSearchRequest.dobTo} IS NULL OR dob <= :#{#employeeSearchRequest.dobTo})
        AND (:#{#employeeSearchRequest.gender} IS NULL OR gender = :#{#employeeSearchRequest.gender})
        AND (:#{#employeeSearchRequest.salaryRange} = 0 OR salary >= :#{#employeeSearchRequest.salaryRange})
        AND (:#{#employeeSearchRequest.phone} IS NULL OR phone LIKE CONCAT('%', :#{#employeeSearchRequest.phone}, '%'))
        AND (:#{#employeeSearchRequest.department} IS NULL OR department.id = :#{#employeeSearchRequest.department})
    """)
    Page<Employee> findByAttributes(EmployeeSearchRequest employeeSearchRequest, Pageable pageable);

}