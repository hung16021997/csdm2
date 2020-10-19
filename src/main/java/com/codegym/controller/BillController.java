package com.codegym.controller;

import com.codegym.model.Bill;
import com.codegym.model.Employee;
import com.codegym.model.Mobile;
import com.codegym.model.Producer;
import com.codegym.service.IBillService;
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
public class BillController {

    // injection
    @Autowired
    private IBillService billService;
    @Autowired
    private IEmployeeService employeeService;


    @ModelAttribute("employee")
    public Iterable<Employee> employees () {
        return employeeService.findAll();
    }



    // search
    @GetMapping("/admin/search-by-employee")
    public ModelAndView searchByEmployee(@RequestParam("search") Long employeeId, Pageable pageable) {
        Page<Bill> bills;
        if (employeeId == -1) {
            bills = billService.findAll(pageable);
        } else {
            Employee employee = employeeService.findById(employeeId);
            bills = billService.findAllByEmployee(employee, pageable);
        }

        ModelAndView modelAndView = new ModelAndView("bill/list");
        modelAndView.addObject("bills",bills);
        modelAndView.addObject("search",employeeId);
        return modelAndView;
    }

    @GetMapping("/admin/list-bill")
    public ModelAndView showListBill(@PageableDefault(size = 10) Pageable pageable) {
    Page<Bill> bills = billService.findAll(pageable);
    ModelAndView modelAndView = new ModelAndView("bill/list");
    modelAndView.addObject("bills", bills);
    return modelAndView;
}



    // create
    @GetMapping("/admin/create-bill")
    public ModelAndView showCreateBill() {
        ModelAndView modelAndView = new ModelAndView("bill/create");
        modelAndView.addObject("bills", new Bill());
        return modelAndView;
    }

    @PostMapping("/admin/save-bill")
    public ModelAndView saveBill(@ModelAttribute ("bill") Bill bill) {
        billService.save(bill);
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/list-bill");
        modelAndView.addObject("bills", new Bill());
        return  modelAndView;
    }


    // edit
    @GetMapping("/admin/edit-bill/{id}")
    public ModelAndView showEditFormBill(@PathVariable ("id") Long id) {
        Bill bill = billService.findById(id);
        ModelAndView modelAndView = new ModelAndView("bill/edit");
        modelAndView.addObject("bills", bill);
        return modelAndView;
    }

    @PostMapping("/admin/update-bill")
    public ModelAndView updateBill(@ModelAttribute Bill bill) {
        billService.save(bill);
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/list-bill");
        modelAndView.addObject("bills", bill);
        modelAndView.addObject("message", "Updated successfully");
        return modelAndView;
    }


    // delete
    @GetMapping ("/admin/delete-bill/{id}")
    public ModelAndView showDeleteFormBill(@PathVariable ("id") Long id) {
        Bill bill = billService.findById(id);
        ModelAndView modelAndView = new ModelAndView("bill/delete");
        modelAndView.addObject("bills", bill);
        return modelAndView;
    }

    @PostMapping("/admin/remove-bill")
    public String deleteBill(Bill bill) {
        billService.remove(bill.getBillId());
        return "redirect:/admin/list-bill";
    }
}
