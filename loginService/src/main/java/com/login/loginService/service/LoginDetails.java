package com.login.loginService.service;

import com.login.loginService.dto.Employee;
import lombok.NonNull;

import java.util.List;

public interface LoginDetails {
    String authenticate(@NonNull String userName, @NonNull String password, @NonNull String userRole);
    void getBehavioralEmployee(Employee employee);
    void getTechnicalEmployee(Employee employee);
    List<Employee> viewEmployees();
}
