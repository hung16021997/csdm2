package com.codegym.service.impl;

import com.codegym.model.Mobile;
import com.codegym.model.MobileType;
import com.codegym.model.Producer;
import com.codegym.repository.IMobileRepository;
import com.codegym.service.IMobileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class MobileServiceImpl implements IMobileService {

    // injection
    @Autowired
    private IMobileRepository mobileRepository;

    // method
    @Override
    public Iterable<Mobile> findAll() {
        return mobileRepository.findAll();
    }

    @Override
    public Page<Mobile> findAll(Pageable pageable) {
        return mobileRepository.findAll(pageable);
    }

    @Override
    public Mobile findById(Long id) {
        return mobileRepository.findOne(id);
    }

    @Override
    public void save(Mobile mobile) {
        mobileRepository.save(mobile);
    }

    @Override
    public void remove(Long id) {
        mobileRepository.delete(id);
    }

    @Override
    public Page<Mobile> findAllByProducer(Producer producer, Pageable pageable) {
        return mobileRepository.findAllByProducer(producer,pageable);
    }

    @Override
    public Page<Mobile> findAllByMobileType(MobileType mobileType, Pageable pageable) {
        return mobileRepository.findAllByMobileType(mobileType,pageable);
    }

    @Override
    public Page<Mobile> findByMobileNameContaining(String mobileName, Pageable pageable) {
        return mobileRepository.findByMobileNameContaining(mobileName,pageable);
    }

    @Override
    public Page<Mobile> findByMobileDescriptionContaining(String mobileDescription, Pageable pageable) {
        return mobileRepository.findByMobileDescriptionContaining(mobileDescription,pageable);
    }



}
