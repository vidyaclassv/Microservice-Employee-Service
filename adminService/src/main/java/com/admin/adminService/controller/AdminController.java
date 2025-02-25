package com.admin.adminService.controller;

import com.admin.adminService.dto.Employee;
import com.admin.adminService.webClients.EmployeeClient;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
public class AdminController {

    private static final Logger log = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private EmployeeClient employeeClient;

    @GetMapping("/admin/employee/list")
    @CircuitBreaker(name = "viewEmployeesBreaker", fallbackMethod = "viewEmployeesFallBackBreaker")
    public List<Employee> viewEmployees() {
        return employeeClient.getEmployeeList();
    }

    public List<Employee> viewEmployeesFallBackBreaker(Exception exception) {
        log.info("Fallback is executed because admin service is down {}:", exception.getMessage());
        throw new RuntimeException("Fallback is executed because admin service is down :" + exception.getMessage());
    }
}
