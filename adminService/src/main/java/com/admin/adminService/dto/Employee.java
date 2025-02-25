package com.admin.adminService.dto;

import java.time.LocalDate;

public class Employee {

    private Long id;
    private String name;
    private String assessment;
    private LocalDate localDate;

    public Employee() {
    }

    public Employee(Long id, String name, String assessment, LocalDate localDate) {
        this.id = id;
        this.name = name;
        this.assessment = assessment;
        this.localDate = localDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAssessment() {
        return assessment;
    }

    public void setAssessment(String assessment) {
        this.assessment = assessment;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }
}

