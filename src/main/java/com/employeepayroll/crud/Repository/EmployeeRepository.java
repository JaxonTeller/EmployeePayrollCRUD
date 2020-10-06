package com.employeepayroll.crud.Repository;

import com.employeepayroll.crud.Resource.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
