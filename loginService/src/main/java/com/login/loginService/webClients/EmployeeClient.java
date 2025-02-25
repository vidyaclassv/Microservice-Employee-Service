package com.login.loginService.webClients;

import com.login.loginService.dto.Employee;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

//@FeignClient(url = "http://localhost:4042", value = "employeeService")
@FeignClient(name = "EMPLOYEESERVICE")
public interface EmployeeClient {

    @PostMapping("/behavioral/add")
    void behavioralEmployee(@RequestBody Employee employee);

    @PostMapping("/technical/add")
    void technicalEmployee(@RequestBody Employee employee);
}
