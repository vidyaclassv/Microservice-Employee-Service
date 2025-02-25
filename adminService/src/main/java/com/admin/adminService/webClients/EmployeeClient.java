package com.admin.adminService.webClients;

import com.admin.adminService.dto.Employee;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

//@FeignClient(url = "http://localhost:4042", value = "employeeService")
@FeignClient(name = "EMPLOYEESERVICE")
public interface EmployeeClient {

    @GetMapping("/employee/list")
    List<Employee> getEmployeeList();

}
