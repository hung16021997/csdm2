package com.codegym.controller;

import com.codegym.model.Employee;
import com.codegym.model.Mobile;
import com.codegym.model.Producer;
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
public class ProducerController {

    // injection
    @Autowired
    private IProducerService producerService;

    // list
    @GetMapping("/admin/list-producer-by-name")
    public ModelAndView showListMobileName(@RequestParam ("search") Optional<String> search ,
                                           @PageableDefault(size = 8, direction = Sort.Direction.ASC) Pageable pageable){
        Page<Producer>  producers;
        if (search.isPresent()) {
            producers = producerService.findAllByProducerName(search.get(), pageable);
        } else {
            producers = producerService.findAll(pageable);
        }
        ModelAndView modelAndView = new ModelAndView("producer/list");
        modelAndView.addObject("producers", producers);
        return modelAndView;
    }

    @GetMapping("/admin/list-producer")
    public ModelAndView showListProducer() {
        Iterable<Producer> producers = producerService.findAll();
        ModelAndView modelAndView = new ModelAndView("producer/list");
        modelAndView.addObject("producers", producers);
        return modelAndView;
    }


    // create
    @GetMapping("/admin/create-producer")
    public ModelAndView showCreateProducer() {
        ModelAndView modelAndView = new ModelAndView("producer/create");
        modelAndView.addObject("producers", new Producer());
        return modelAndView;
    }

    @PostMapping("/admin/save-producer")
    public ModelAndView saveProducer(@ModelAttribute ("producer") Producer producer) {
        producerService.save(producer);
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/list-producer");
        modelAndView.addObject("producer", new Producer());
        return  modelAndView;
    }


    // edit
    @GetMapping("/admin/edit-producer/{id}")
    public ModelAndView showEditFormProducer(@PathVariable ("id") Long id) {
        Producer producer = producerService.findById(id);
        ModelAndView modelAndView = new ModelAndView("producer/edit");
        modelAndView.addObject("producers", producer);
        return modelAndView;
    }

    @PostMapping("/admin/update-producer")
    public ModelAndView updateProducer(@ModelAttribute Producer producer) {
        producerService.save(producer);
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/list-producer");
        modelAndView.addObject("producers", producer);
        modelAndView.addObject("message", "Updated successfully");
        return modelAndView;
    }


    // delete
    @GetMapping ("/admin/delete-producer/{id}")
    public ModelAndView showDeleteFormProducer(@PathVariable ("id") Long id) {
        Producer producer = producerService.findById(id);
        ModelAndView modelAndView = new ModelAndView("producer/delete");
        modelAndView.addObject("producers", producer);
        return modelAndView;
    }

    @PostMapping("/admin/remove-producer")
    public String deleteProducer(Producer producer) {
        producerService.remove(producer.getProducerId());
        return "redirect:/admin/list-producer";
    }
}
