package com.codegym.service;

import com.codegym.model.Mobile;
import com.codegym.model.MobileType;
import com.codegym.model.Producer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

public interface IMobileService {
    Iterable<Mobile> findAll();
    Page<Mobile> findAll(Pageable pageable);
    Mobile findById(Long id);
    void save(Mobile mobile);
    void remove (Long id);
    Page<Mobile> findAllByProducer (Producer producer, Pageable pageable);
    Page<Mobile> findAllByMobileType (MobileType mobileType, Pageable pageable);
    Page<Mobile> findByMobileNameContaining (String mobileName, Pageable pageable);
    Page<Mobile> findByMobileDescriptionContaining (String mobileDescription, Pageable pageable);


}
