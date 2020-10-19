package com.codegym.repository;

import com.codegym.model.Bill;
import com.codegym.model.BillDetail;
import com.codegym.model.Customer;
import com.codegym.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IBillDetailRepository extends PagingAndSortingRepository <BillDetail, Long> {
    Page<BillDetail> findAllByCustomer (Customer customer, Pageable pageable);
    Page<BillDetail> findAllByBill (Bill bill, Pageable pageable);
}
