package com.example.repository;

import com.example.entities.Admin;
import org.springframework.data.repository.CrudRepository;

public interface AdminRepository  extends CrudRepository<Admin, Integer> {

    boolean existsByAdminName(String adminName);
    boolean existsByAdminPassword(String password);

}
