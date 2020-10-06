package com.employeepayroll.crud.Controller;

import com.employeepayroll.crud.Repository.EmployeeRepository;
import com.employeepayroll.crud.Resource.Employee;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    private EmployeeRepository employeeRepository;


    
    EmployeeController(EmployeeRepository employeeRepository)
    {
        System.out.println("Initializing beans");
        this.employeeRepository=employeeRepository;

    }

    @GetMapping("/employees")
    public List<Employee> getEmployees()
    {
        return this.employeeRepository.findAll();
    }

    @GetMapping("/employees/{id}")
    public Employee getEmployeeById(@PathVariable Long id)
    {
        return this.employeeRepository.
                findById(id).
                orElseThrow(()->new RuntimeException("Can't find Employee"));
    }

    @PostMapping("/employees")
    public Employee registerNewEmployee(@RequestBody Employee employee)
    {
        return this.employeeRepository.save(employee);
    }

    @PutMapping("/employees/{id}")
    public Employee replaceEmployee(@RequestBody Employee newEmployee, @PathVariable Long id)
    {
        return this.employeeRepository.findById(id).map(employee1 -> {
            employee1.setEmployeeName(newEmployee.getEmployeeName());
            employee1.setRole(newEmployee.getRole());
            return this.employeeRepository.save(employee1);
        }).orElseGet(()-> {
            newEmployee.setId(id);
            return this.employeeRepository.save(newEmployee);
        });

    }

    @DeleteMapping("/employees/{id}")
    public void removeEmployee(@PathVariable Long id)
    {
        this.employeeRepository.deleteById(id);
    }
}
