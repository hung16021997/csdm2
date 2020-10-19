package com.codegym.repository;

import com.codegym.model.User;
import org.springframework.data.repository.CrudRepository;

public interface IUserRepository extends CrudRepository<User, Long> {
    User findByEmail(String email);
}
