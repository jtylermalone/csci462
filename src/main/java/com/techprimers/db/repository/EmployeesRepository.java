package com.techprimers.db.repository;

import com.techprimers.db.model.Employees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;



import java.util.List;

public interface EmployeesRepository extends JpaRepository<Employees, Integer> {
    List<Employees> findById(Integer id);

    @Query(value = "SELECT * FROM employees", nativeQuery = true)
    List<Employees> findEmployees();

}
