package com.employee.employeeService.service;

import com.employee.employeeService.model.Employee;

import java.util.List;

public interface EmployeeDetails {
    List<Employee> getAllEmployees();

    void addEmployee(Employee employee);
}
