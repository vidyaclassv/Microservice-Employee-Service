package com.login.loginService.service;

import com.login.loginService.dto.Employee;
import com.login.loginService.model.Users;
import com.login.loginService.repository.UserRepository;
import com.login.loginService.webClients.AdminClient;
import com.login.loginService.webClients.EmployeeClient;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class LoginDetailsImpl implements LoginDetails {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EmployeeClient employeeClient;
    @Autowired
    private AdminClient adminClient;

    @Override
    @Transactional(readOnly = true)
    public String authenticate(@NonNull String userName, @NonNull String password, @NonNull String userRole) {
        Users users = userRepository.findByUserName(userName);
        boolean validateUser = Objects.nonNull(users) && users.getPassword().equals(password)
                && users.getRole().equalsIgnoreCase(userRole);
        if (validateUser) {
            return users.getRole();
        }
        return "";
    }

    @Override
    public void getBehavioralEmployee(Employee employee) {
        employeeClient.behavioralEmployee(employee);
    }

    @Override
    public void getTechnicalEmployee(Employee employee) {
        employeeClient.technicalEmployee(employee);
    }

    @Override
    public List<Employee> viewEmployees() {
        return adminClient.viewEmployees();
    }
}
