package com.codegym.repository;

import com.codegym.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IEmployeeRepository extends PagingAndSortingRepository<Employee,Long> {
    Page<Employee> findAllByEmployeeName (String employeeName, Pageable pageable);

}
