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
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@SessionAttributes("MyCounter")
public class MobileController {
    // injection
    @Autowired
    private IMobileTypeService mobileTypeService;
    @Autowired
    private IProducerService producerService;
    @Autowired
    private IMobileService mobileService;


    @ModelAttribute("producer")
    public Iterable<Producer> producers() {
        return producerService.findAll();
    }

    @ModelAttribute("mobileType")
    public Iterable<MobileType> mobileTypes() {
        return mobileTypeService.findAll();
    }

    @ModelAttribute("MyCounter")
    public MyCounter setupMycounter() {
        return new MyCounter();
    }


    // Search
    @GetMapping("/admin/search-by-mobileType")
    public ModelAndView searchByMobileType(@RequestParam("search") Long mobileTypeId, Pageable pageable) {
        Page<Mobile> mobiles;
        if (mobileTypeId == -1) {
            mobiles = mobileService.findAll(pageable);
        } else {
            MobileType mobileType = mobileTypeService.findById(mobileTypeId);
            mobiles = mobileService.findAllByMobileType(mobileType, pageable);
        }

        ModelAndView modelAndView = new ModelAndView("mobile/list");
        modelAndView.addObject("mobiles", mobiles);
        modelAndView.addObject("search", mobileTypeId);
        return modelAndView;
    }

    @GetMapping("/admin/search-by-producer")
    public ModelAndView searchByProducer(@RequestParam("search1") Long producerId, Pageable pageable) {
        Page<Mobile> mobiles;
        if (producerId == -1) {
            mobiles = mobileService.findAll(pageable);
        } else {
            Producer producer = producerService.findById(producerId);
            mobiles = mobileService.findAllByProducer(producer, pageable);
        }

        ModelAndView modelAndView = new ModelAndView("mobile/list");
        modelAndView.addObject("mobiles", mobiles);
        modelAndView.addObject("search1", producerId);
        return modelAndView;
    }


    // list
    @GetMapping("/admin/list-mobile-by-name")
    public ModelAndView showListMobileName(@ModelAttribute("MyCounter") MyCounter myCounter, @RequestParam("search11") Optional<String> search11,
                                           @PageableDefault(size = 8, direction = Sort.Direction.ASC) Pageable pageable) {
        Page<Mobile> mobiles;
        myCounter.increment();
        if (search11.isPresent()) {
            mobiles = mobileService.findByMobileNameContaining(search11.get(), pageable);
        } else {
            mobiles = mobileService.findAll(pageable);
        }
        ModelAndView modelAndView = new ModelAndView("mobile/list");
        modelAndView.addObject("mobiles", mobiles);
        return modelAndView;
    }

    @GetMapping("/admin/list-mobile-by-description")
    public ModelAndView showListMobileDescription(@RequestParam("search22") Optional<String> search22,
                                                  @PageableDefault(size = 8, direction = Sort.Direction.ASC) Pageable pageable) {
        Page<Mobile> mobiles;
        if (search22.isPresent()) {
            mobiles = mobileService.findByMobileDescriptionContaining(search22.get(), pageable);
        } else {
            mobiles = mobileService.findAll(pageable);
        }
        ModelAndView modelAndView = new ModelAndView("mobile/list");
        modelAndView.addObject("mobiles", mobiles);
        return modelAndView;
    }


    // sort
    @GetMapping("/admin/sort-by-price")
    public String sortByPrice(Model model, @PageableDefault(size = 30, sort = "mobilePrice") Pageable pageable) {
        Page<Mobile> mobiles = mobileService.findAll(pageable);
        model.addAttribute("mobiles", mobiles);
        return "mobile/list";
    }

    @GetMapping("/admin/sort-by-producer")
    public String sortByProducer(Model model, @PageableDefault(size = 20, sort = "producer") Pageable pageable) {
        Page<Mobile> mobiles = mobileService.findAll(pageable);
        model.addAttribute("mobiles", mobiles);
        return "mobile/list";
    }


    // create
    @GetMapping("/admin/create-mobile")
    public ModelAndView showCreateMobile() {
        ModelAndView modelAndView = new ModelAndView("mobile/create");
        modelAndView.addObject("mobiles", new Mobile());
        return modelAndView;
    }

    @PostMapping("/admin/save-mobile")
    public ModelAndView saveMobile(@Validated @ModelAttribute("mobile") Mobile mobile, BindingResult bindingResult) {
        if (!bindingResult.hasFieldErrors()) {
            mobileService.save(mobile);
            ModelAndView modelAndView = new ModelAndView("redirect:/admin/list-mobile-by-name");
            modelAndView.addObject("mobiles", new Mobile());
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("mobile/create");
            modelAndView.addObject("mobiles", new Mobile());
            return modelAndView;
        }

    }

    // edit
    @GetMapping("/admin/edit-mobile/{id}")
    public ModelAndView showEditFormMobile(@PathVariable ("id") Long id) {
        Mobile mobile = mobileService.findById(id);
        ModelAndView modelAndView = new ModelAndView("mobile/edit");
        modelAndView.addObject("mobiles", mobile);
        return modelAndView;
    }

    @PostMapping("/admin/update-mobile")
    public ModelAndView updateMobile(@ModelAttribute Mobile mobile) {
        mobileService.save(mobile);
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/list-mobile-by-name");
        modelAndView.addObject("mobiles", mobile);
        modelAndView.addObject("message", "Updated successfully");
        return modelAndView;
    }


    // delete
    @GetMapping ("/admin/delete-mobile/{id}")
    public ModelAndView showDeleteFormMobile(@PathVariable ("id") Long id) {
        Mobile mobile = mobileService.findById(id);
        ModelAndView modelAndView = new ModelAndView("mobile/delete");
        modelAndView.addObject("mobiles", mobile);
        return modelAndView;
    }

    @PostMapping("/admin/remove-mobile")
    public String deleteMobile(Mobile mobile) {
        mobileService.remove(mobile.getMobileId());
        return "redirect:/admin/list-mobile-by-name";
    }


    // view
    @GetMapping("/admin/detail-mobile-by-name/{id}")
    public ModelAndView viewMobileDetail(@PathVariable("id") Long id) {
        Mobile mobile = mobileService.findById(id);
        ModelAndView modelAndView = new ModelAndView("mobile/detail");
        modelAndView.addObject("mobiles", mobile);
        return modelAndView;
    }
}
