package com.login.loginService.controller;

import com.login.loginService.dto.Employee;
import com.login.loginService.service.LoginDetails;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    private static final Logger log = LoggerFactory.getLogger(LoginController.class);
    @Autowired
    private LoginDetails loginService;

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String userName,
                        @RequestParam String password,
                        @RequestParam String userRole,
                        Model model) {
        String validUserRole = loginService.authenticate(userName, password, userRole);
        if ("emp".equalsIgnoreCase(validUserRole)) {
            return "redirect:/dashboard";
        } else if ("admin".equalsIgnoreCase(validUserRole)) {
            return "redirect:/adminDashboard";
        } else {
            model.addAttribute("message", "Invalid credentials");
            return "login";
        }
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard";
    }

    @GetMapping("/adminDashboard")
    public String adminDashboard() {
        return "adminDashboard";
    }

    @GetMapping("/behavioral")
    public String behavioralEmployee(Model model) {
        return "behavioral/behavioral-assessment";
    }

    @PostMapping("/behavioral")
    @CircuitBreaker(name = "addBehavioralBreaker", fallbackMethod = "addBehavioralFallbackBreaker")
    public String addBehavioralEmployee(@ModelAttribute Employee employee,
                                        Model model) {
        loginService.getBehavioralEmployee(employee);
        model.addAttribute("message", "Added Successfully.");
        return "behavioral/behavioral-assessment";
    }

    //fall back for circuit breaker
    public String addBehavioralFallbackBreaker(@ModelAttribute Employee employee,
                                               Model model,
                                               Exception exception) {
        log.info("Fallback is executed because Behavioral service is down {}:", exception.getMessage());
        model.addAttribute("message", "Fallback is executed because Behavioral service is down {}:" + exception.getMessage());
        return "behavioral/behavioral-assessment";
    }

    @GetMapping("/technical")
    public String technicalEmployee() {
        return "technical/technical-assessment";
    }

    @PostMapping("/technical")
    @CircuitBreaker(name = "addTechnicalBreaker", fallbackMethod = "addTechnicalFallbackBreaker")
    public String addTechnicalEmployee(@ModelAttribute Employee employee,
                                       Model model) {
        loginService.getTechnicalEmployee(employee);
        model.addAttribute("message", "Added Successfully.");
        return "technical/technical-assessment";
    }

    //fall back for circuit breaker
    public String addTechnicalFallbackBreaker(@ModelAttribute Employee employee,
                                              Model model,
                                              Exception exception) {
        log.info("Fallback is executed because Technical service is down {}:", exception.getMessage());
        model.addAttribute("message", "Fallback is executed because Technical service is down {}:" + exception.getMessage());
        return "behavioral/behavioral-assessment";
    }

    @GetMapping("/admin/dashboard")
    @CircuitBreaker(name = "allAdminDetailsBreaker", fallbackMethod = "allAdminDetailsFallBackBreaker")
    public String mainAdminDashboard(Model model) {
        model.addAttribute("employees", loginService.viewEmployees());
        return "admin/dashboard";
    }

    public String allAdminDetailsFallBackBreaker(Model model,
                                                 Exception exception) {
        log.info("Fallback is executed because admin service is down {}:", exception.getMessage());
        model.addAttribute("message", "Fallback is executed because admin service is down {}:" + exception.getMessage());
        return "admin/dashboard";
    }
}
