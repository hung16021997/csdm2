package com.codegym.controller;

import com.codegym.model.*;
import com.codegym.service.*;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class BillDetailController {

    // injection
    @Autowired
    private IBillService billService;
    @Autowired
    private IBillDetailService billDetailService;
    @Autowired
    private ICustomerService customerService;
    @Autowired
    private IpaymentService paymentService;
    @Autowired
    private IMobileService mobileService;


    // model attribute
    @ModelAttribute("customer")
    public Iterable<Customer> customers () {
        return customerService.findAll();
    }
    @ModelAttribute("payment")
    public Iterable<Payment> payments () {
        return paymentService.findAll();
    }
    @ModelAttribute("bill")
    public Iterable<Bill> bills () {
        return billService.findAll();
    }
    @ModelAttribute("mobile")
    public Iterable<Mobile> mobiles () {
        return mobileService.findAll();
    }



    // sort
    @GetMapping("/admin/sort-by-total-price")
    public String sortByTotalPrice(Model model, @PageableDefault(size = 15, sort = "totalPrice") Pageable pageable) {
        Page<BillDetail> billDetails = billDetailService.findAll(pageable);
        model.addAttribute("billDetails",billDetails);
        return "billDetail/list";

    }
    @GetMapping("/admin/sort-by-date")
    public String sortByBillDetailDate (Model model, @PageableDefault(size = 15, sort = "billDetailDate") Pageable pageable) {
        Page<BillDetail> billDetails = billDetailService.findAll(pageable);
        model.addAttribute("billDetails",billDetails);
        return "billDetail/list";
    }


    // search
    @GetMapping("/admin/search-by-customer")
    public ModelAndView searchByCustomer(@RequestParam("search") Long customerId, Pageable pageable) {
        Page<BillDetail> billDetails;
        if (customerId == -1) {
            billDetails = billDetailService.findAll(pageable);
        } else {
            Customer customer = customerService.findById(customerId);
            billDetails = billDetailService.findAllByCustomer(customer, pageable);
        }

        ModelAndView modelAndView = new ModelAndView("billDetail/list");
        modelAndView.addObject("billDetails",billDetails);
        modelAndView.addObject("search",customerId);
        return modelAndView;
    }


    // list
    @GetMapping("/admin/list-billDetail")
    public ModelAndView showListBillDetail() {
        Iterable<BillDetail> billDetails = billDetailService.findAll();
        ModelAndView modelAndView = new ModelAndView("billDetail/list");
        modelAndView.addObject("billDetails", billDetails);
        return modelAndView;
    }


    // create
    @GetMapping("/admin/create-billDetail")
    public ModelAndView showCreateBillDetail() {
        ModelAndView modelAndView = new ModelAndView("billDetail/create");
        modelAndView.addObject("billDetails", new BillDetail());
        return modelAndView;
    }

    @PostMapping("/admin/save-billDetail")
    public ModelAndView saveBillDetail(@ModelAttribute ("billDetail") BillDetail billDetail) {
        billDetailService.save(billDetail);
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/list-billDetail");
        modelAndView.addObject("billDetails", new BillDetail());
        return  modelAndView;
    }


    // edit
    @GetMapping("/admin/edit-billDetail/{id}")
    public ModelAndView showEditFormBillDetail(@PathVariable ("id") Long id) {
        BillDetail billDetail = billDetailService.findById(id);
        ModelAndView modelAndView = new ModelAndView("billDetail/edit");
        modelAndView.addObject("billDetails", billDetail);
        return modelAndView;
    }

    @PostMapping("/admin/update-billDetail")
    public ModelAndView updateBillDetail(@ModelAttribute BillDetail billDetail) {
        billDetailService.save(billDetail);
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/list-billDetail");
        modelAndView.addObject("billDetails", billDetail);
        modelAndView.addObject("message", "Updated successfully");
        return modelAndView;
    }


    // delete
    @GetMapping ("/admin/delete-billDetail/{id}")
    public ModelAndView showDeleteFormBillDetail(@PathVariable ("id") Long id) {
        BillDetail billDetail = billDetailService.findById(id);
        ModelAndView modelAndView = new ModelAndView("billDetail/delete");
        modelAndView.addObject("billDetails", billDetail);
        return modelAndView;
    }

    @PostMapping("/admin/remove-billDetail")
    public String deleteBillDetail(BillDetail billDetail) {
        billDetailService.remove(billDetail.getBillDetailId());
        return "redirect:/admin/list-billDetail";
    }
}
