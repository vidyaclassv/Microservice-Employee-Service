package com.login.loginService.webClients;

import com.login.loginService.dto.Employee;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

//@FeignClient(url = "http://localhost:4043", value = "adminService")
@FeignClient(name = "ADMINSERVICE")
public interface AdminClient {
    @GetMapping("/admin/employee/list")
    List<Employee> viewEmployees();
}
