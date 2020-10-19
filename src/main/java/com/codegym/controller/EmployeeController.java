package com.codegym.controller;

import com.codegym.model.Employee;
import com.codegym.model.Mobile;
import com.codegym.service.IEmployeeService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class EmployeeController {

    // injection
    @Autowired
    private IEmployeeService employeeService;


    // list
    @GetMapping("/admin/list-employee-by-name")
    public ModelAndView showListEmployeeName(@RequestParam ("search") Optional<String> search ,
                                           @PageableDefault(size = 20, direction = Sort.Direction.ASC) Pageable pageable){
        Page<Employee>  employees;
        if (search.isPresent()) {
            employees = employeeService.findAllByEmployeeName(search.get(), pageable);
        } else {
            employees = employeeService.findAll(pageable);
        }
        ModelAndView modelAndView = new ModelAndView("employee/list");
        modelAndView.addObject("employees", employees);
        return modelAndView;
    }

    @GetMapping("/admin/list-employee")
    public ModelAndView showListEmployee() {
        Iterable<Employee> employees = employeeService.findAll();
        ModelAndView modelAndView = new ModelAndView("employee/list");
        modelAndView.addObject("employees", employees);
        return modelAndView;
    }


    // create
    @GetMapping("/admin/create-employee")
    public ModelAndView showCreateEmployee() {
        ModelAndView modelAndView = new ModelAndView("employee/create");
        modelAndView.addObject("employees", new Employee());
        return modelAndView;
    }

    @PostMapping("/admin/save-employee")
    public ModelAndView saveMaterial(@ModelAttribute ("employee") Employee employee) {
        employeeService.save(employee);
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/list-employee");
        modelAndView.addObject("employees", new Employee());
        return  modelAndView;
    }


    // edit
    @GetMapping("/admin/edit-employee/{id}")
    public ModelAndView showEditFormEmployee(@PathVariable ("id") Long id) {
        Employee employee = employeeService.findById(id);
        ModelAndView modelAndView = new ModelAndView("employee/edit");
        modelAndView.addObject("employees", employee);
        return modelAndView;
    }

    @PostMapping("/admin/update-employee")
    public ModelAndView updatePhone(@ModelAttribute Employee employee) {
        employeeService.save(employee);
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/list-employee");
        modelAndView.addObject("employees", employee);
        modelAndView.addObject("message", "Updated successfully");
        return modelAndView;
    }


    // delete
    @GetMapping ("/admin/delete-employee/{id}")
    public ModelAndView showDeleteFormEmployee(@PathVariable ("id") Long id) {
        Employee employee = employeeService.findById(id);
        ModelAndView modelAndView = new ModelAndView("employee/delete");
        modelAndView.addObject("employees", employee);
        return modelAndView;
    }

    @PostMapping("/admin/remove-employee")
    public String deleteEmployee(Employee employee) {
        employeeService.remove(employee.getEmployeeId());
        return "redirect:/admin/list-employee";
    }
}
