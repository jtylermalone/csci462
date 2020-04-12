package com.scpa.db.repository;

import com.scpa.db.model.Employees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeesRepository extends JpaRepository<Employees, Integer> {
    List<Employees> findByEmpnum(String empnum);

    @Query(value = "SELECT * FROM employees e WHERE e.DEPT=12 ORDER BY e.NAME", nativeQuery = true)
    List<Employees> findEmployees();

    @Query(value = "SELECT e.empnum FROM employees e WHERE e.NAME = :full_name", nativeQuery = true)
    String findBadgeNumber(
        @Param("full_name") String full_name);

}
