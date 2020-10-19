package com.codegym.controller;

import com.codegym.model.*;
import com.codegym.service.IEmployeeService;
import com.codegym.service.IMobileService;
import com.codegym.service.IMobileTypeService;
import com.codegym.service.IProducerService;
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
@SessionAttributes("MyCounter1")
public class ViewCustomerController {
    // injection
    @Autowired
    private IMobileTypeService mobileTypeService;
    @Autowired
    private IProducerService producerService;
    @Autowired
    private IMobileService mobileService;


    // model attribute
    @ModelAttribute("producer")
    public Iterable<Producer> producers () {
        return producerService.findAll();
    }
    @ModelAttribute("mobileType")
    public Iterable<MobileType> mobileTypes () {
        return mobileTypeService.findAll();
    }
    @ModelAttribute("MyCounter1")
    public MyCounter setupMycount(){
        return new MyCounter();
    }

    // Search
    @GetMapping("/user/view-search-by-mobileType")
    public ModelAndView searchByMobileType(@RequestParam("search") Long mobileTypeId, Pageable pageable) {

        Page<Mobile> mobiles;
        if (mobileTypeId == -1) {
            mobiles = mobileService.findAll(pageable);
        } else {
            MobileType mobileType = mobileTypeService.findById(mobileTypeId);
            mobiles = mobileService.findAllByMobileType(mobileType, pageable);
        }

        ModelAndView modelAndView = new ModelAndView("mobile/view");
        modelAndView.addObject("mobiles",mobiles);
        modelAndView.addObject("search",mobileTypeId);
        return modelAndView;
    }

    @GetMapping("/user/view-search-by-producer")
    public ModelAndView searchByProducer(@RequestParam("search1") Long producerId, Pageable pageable) {
        Page<Mobile> mobiles;
        if (producerId == -1) {
            mobiles = mobileService.findAll(pageable);
        } else {
            Producer producer = producerService.findById(producerId);
            mobiles = mobileService.findAllByProducer(producer, pageable);
        }

        ModelAndView modelAndView = new ModelAndView("mobile/view");
        modelAndView.addObject("mobiles",mobiles);
        modelAndView.addObject("search1",producerId);
        return modelAndView;
    }


    // list
    @GetMapping("/user/view-list-mobile-by-name")
    public ModelAndView viewListMobileName(@ModelAttribute("MyCounter1") MyCounter myCounter,@RequestParam ("search11") Optional<String> search11 ,
                                           @PageableDefault(size = 8, direction = Sort.Direction.ASC)Pageable pageable){
        myCounter.increment();
        Page<Mobile>  mobiles;
        if (search11.isPresent()) {
            mobiles = mobileService.findByMobileNameContaining(search11.get(), pageable);
        } else {
            mobiles = mobileService.findAll(pageable);
        }
        ModelAndView modelAndView = new ModelAndView("mobile/view");
        modelAndView.addObject("mobiles", mobiles);
        return modelAndView;
    }

    @GetMapping("/user/view-list-mobile-by-description")
    public ModelAndView viewListMobileDescription(@RequestParam ("search22") Optional<String> search22 ,
                                                  @PageableDefault(size = 8, direction = Sort.Direction.ASC)Pageable pageable){
        Page<Mobile>  mobiles;
        if (search22.isPresent()) {
            mobiles = mobileService.findByMobileDescriptionContaining(search22.get(), pageable);
        } else {
            mobiles = mobileService.findAll(pageable);
        }
        ModelAndView modelAndView = new ModelAndView("mobile/view");
        modelAndView.addObject("mobiles", mobiles);
        return modelAndView;
    }



    // sort
    @GetMapping("/user/view-sort-by-price")
    public String sortByPrice(Model model, @PageableDefault(size = 30, sort = "mobilePrice") Pageable pageable) {
        Page<Mobile> mobiles = mobileService.findAll(pageable);
        model.addAttribute("mobiles",mobiles);
        return "mobile/view";
    }
    @GetMapping("/user/view-sort-by-producer")
    public String sortByProducer (Model model, @PageableDefault(size = 20, sort = "producer") Pageable pageable) {
        Page<Mobile> mobiles = mobileService.findAll(pageable);
        model.addAttribute("mobiles",mobiles);
        return "mobile/view";
    }


}
