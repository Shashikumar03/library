package com.example.serviceImp;

import com.example.entities.Admin;
import com.example.repository.AdminRepository;
import com.example.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImplementation  implements AdminService {

    @Autowired
    private AdminRepository adminRepository;
    @Override
    public boolean MatchingAdminName(String adminName) {
        return adminRepository.existsByAdminName(adminName);
    }

    @Override
    public boolean MatchingAdminPassword(String password) {

        return adminRepository.existsByAdminPassword(password);
    }

    @Override
    public void save(Admin admin) {
    adminRepository.save(admin);
    }
}
