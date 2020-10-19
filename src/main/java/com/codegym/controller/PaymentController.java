package com.codegym.controller;

import com.codegym.model.Customer;
import com.codegym.model.Employee;
import com.codegym.model.Payment;
import com.codegym.model.Producer;
import com.codegym.service.ICustomerService;
import com.codegym.service.IEmployeeService;
import com.codegym.service.IProducerService;
import com.codegym.service.IpaymentService;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PaymentController {

    // injection
    @Autowired
    private IpaymentService paymentService;


    // list
    @GetMapping("/admin/list-payment")
    public ModelAndView showListPayment() {
        Iterable<Payment> payments = paymentService.findAll();
        ModelAndView modelAndView = new ModelAndView("payment/list");
        modelAndView.addObject("payments", payments);
        return modelAndView;
    }


    // create
    @GetMapping("/admin/create-payment")
    public ModelAndView showCreatePayment() {
        ModelAndView modelAndView = new ModelAndView("payment/create");
        modelAndView.addObject("payments", new Payment());
        return modelAndView;
    }

    @PostMapping("/admin/save-payment")
    public ModelAndView savePayment(@ModelAttribute ("payment") Payment payment) {
        paymentService.save(payment);
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/list-payment");
        modelAndView.addObject("payments", new Payment());
        return  modelAndView;
    }


    // edit
    @GetMapping("/admin/edit-payment/{id}")
    public ModelAndView showEditFormPayment(@PathVariable ("id") Long id) {
        Payment payment = paymentService.findById(id);
        ModelAndView modelAndView = new ModelAndView("payment/edit");
        modelAndView.addObject("payments", payment);
        return modelAndView;
    }

    @PostMapping("/admin/update-payment")
    public ModelAndView updatePayment(@ModelAttribute Payment payment) {
        paymentService.save(payment);
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/list-payment");
        modelAndView.addObject("payments", payment);
        modelAndView.addObject("message", "Updated successfully");
        return modelAndView;
    }


    // delete
    @GetMapping ("/admin/delete-payment/{id}")
    public ModelAndView showDeleteFormPayment(@PathVariable ("id") Long id) {
        Payment payment = paymentService.findById(id);
        ModelAndView modelAndView = new ModelAndView("payment/delete");
        modelAndView.addObject("payments", payment);
        return modelAndView;
    }

    @PostMapping("/admin/remove-payment")
    public String deletePayment(Payment payment) {
        paymentService.remove(payment.getPaymentId());
        return "redirect:/admin/list-payment";
    }
}
