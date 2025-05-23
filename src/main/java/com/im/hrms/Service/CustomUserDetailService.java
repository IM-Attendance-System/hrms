package com.im.hrms.Service;

import com.im.hrms.Entity.AdminEntity;
import com.im.hrms.Entity.Employee;
import com.im.hrms.Repository.AdminRepo;
import com.im.hrms.Repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    public AdminRepo adminRepo;

    @Autowired
    public EmployeeRepo employeeRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails userDetails = null;

        if ("admin@im.in".equals(username)) {
            AdminEntity adminEntity = adminRepo.findByAdminEmailId(username)
                    .orElseThrow(() -> new UsernameNotFoundException("Admin user not found"));
            userDetails = adminEntity;
        } else {
            Optional<Employee> employee = employeeRepo.findByEmail(username);
            if (employee.isPresent()) {
                userDetails = employee.get();
            }

        }
        return userDetails;
    }
}
