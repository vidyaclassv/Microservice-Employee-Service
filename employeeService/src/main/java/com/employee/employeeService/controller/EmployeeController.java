package com.employee.employeeService.controller;

import com.employee.employeeService.model.Employee;
import com.employee.employeeService.service.EmployeeDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeDetails employeeDetails;

    @GetMapping("/employee/list")
    public List<Employee> getEmployeeList() {
        return employeeDetails.getAllEmployees();
    }

    @PostMapping("/behavioral/add")
    public void addBehavioralEmployee(@RequestBody Employee employee) {
        this.employeeDetails.addEmployee(employee);
    }

    @PostMapping("/technical/add")
    public void addTechnicalEmployee(@RequestBody Employee employee) {
        this.employeeDetails.addEmployee(employee);
    }
}
