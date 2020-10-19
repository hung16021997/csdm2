package com.codegym.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table (name = "employee")
public class Employee {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long employeeId;
    private String employeeName;
    private String employeePhone;
    private String employeeEmail;
    private String employeeUser;
    private String employeePassword;

    @OneToMany (targetEntity = Bill.class)
    private Set<Bill> bills;

    public Employee() {
    }

    public Employee(String employeeName, String employeePhone, String employeeEmail, String employeeUser, String employeePassword, Set<Bill> bills) {
        this.employeeName = employeeName;
        this.employeePhone = employeePhone;
        this.employeeEmail = employeeEmail;
        this.employeeUser = employeeUser;
        this.employeePassword = employeePassword;
        this.bills = bills;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeePhone() {
        return employeePhone;
    }

    public void setEmployeePhone(String employeePhone) {
        this.employeePhone = employeePhone;
    }

    public String getEmployeeEmail() {
        return employeeEmail;
    }

    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }

    public String getEmployeeUser() {
        return employeeUser;
    }

    public void setEmployeeUser(String employeeUser) {
        this.employeeUser = employeeUser;
    }

    public String getEmployeePassword() {
        return employeePassword;
    }

    public void setEmployeePassword(String employeePassword) {
        this.employeePassword = employeePassword;
    }

    public Set<Bill> getBills() {
        return bills;
    }

    public void setBills(Set<Bill> bills) {
        this.bills = bills;
    }
}
