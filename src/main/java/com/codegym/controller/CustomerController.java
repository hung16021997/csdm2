package com.codegym.controller;

import com.codegym.model.Customer;
import com.codegym.model.Employee;
import com.codegym.model.Mobile;
import com.codegym.model.Producer;
import com.codegym.service.ICustomerService;
import com.codegym.service.IEmployeeService;
import com.codegym.service.IProducerService;
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
public class CustomerController {

    // injection
    @Autowired
    private ICustomerService customerService;


    // list
    @GetMapping("/admin/list-customer-by-name")
    public ModelAndView showListCustomerName(@RequestParam ("search") Optional<String> search ,
                                           @PageableDefault(size = 8, direction = Sort.Direction.ASC) Pageable pageable){
        Page<Customer>  customers;
        if (search.isPresent()) {
            customers = customerService.findAllByCustomerNameContaining(search.get(), pageable);
        } else {
            customers = customerService.findAll(pageable);
        }
        ModelAndView modelAndView = new ModelAndView("customer/list");
        modelAndView.addObject("customers", customers);
        return modelAndView;
    }

    @GetMapping("/admin/list-customer")
    public ModelAndView showListCustomer() {
        Iterable<Customer> customers = customerService.findAll();
        ModelAndView modelAndView = new ModelAndView("customer/list");
        modelAndView.addObject("customers", customers);
        return modelAndView;
    }


    // create
    @GetMapping("/admin/create-customer")
    public ModelAndView showCreateCustomer() {
        ModelAndView modelAndView = new ModelAndView("customer/create");
        modelAndView.addObject("customers", new Customer());
        return modelAndView;
    }

    @PostMapping("/admin/save-customer")
    public ModelAndView saveCustomer(@ModelAttribute ("customer") Customer customer) {
        customerService.save(customer);
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/list-customer");
        modelAndView.addObject("customers", new Customer());
        return  modelAndView;
    }


    // edit
    @GetMapping("/admin/edit-customer/{id}")
    public ModelAndView showEditFormCustomer(@PathVariable ("id") Long id) {
        Customer customer = customerService.findById(id);
        ModelAndView modelAndView = new ModelAndView("customer/edit");
        modelAndView.addObject("customers", customer);
        return modelAndView;
    }

    @PostMapping("/admin/update-customer")
    public ModelAndView updateCustomer(@ModelAttribute Customer customer) {
        customerService.save(customer);
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/list-customer");
        modelAndView.addObject("customers", customer);
        modelAndView.addObject("message", "Updated successfully");
        return modelAndView;
    }


    // delete
    @GetMapping ("/admin/delete-customer/{id}")
    public ModelAndView showDeleteFormCustomer(@PathVariable ("id") Long id) {
        Customer customer = customerService.findById(id);
        ModelAndView modelAndView = new ModelAndView("customer/delete");
        modelAndView.addObject("customers", customer);
        return modelAndView;
    }

    @PostMapping("/admin/remove-customer")
    public String deleteCustomer(Customer customer) {
        customerService.remove(customer.getCustomerId());
        return "redirect:/admin/list-customer";
    }
}
