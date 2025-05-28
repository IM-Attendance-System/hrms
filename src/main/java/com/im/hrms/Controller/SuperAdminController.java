package com.im.hrms.Controller;

import com.im.hrms.Entity.AdminEntity;
import com.im.hrms.Entity.SuperAdmin;
import com.im.hrms.Repository.AdminRepo;
import com.im.hrms.Repository.SuperAdminRepo;
import com.im.hrms.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/superAdmin")
public class SuperAdminController {

    @Autowired
    public SuperAdminRepo superAdminRepo;

    @Autowired
    public EmployeeService employeeService;


    @PostMapping("/createSuperAdmin")
    public SuperAdmin createSuperAdmin(@RequestParam String emailId, @RequestParam String password)  {
        return employeeService.createSuperAdmin(emailId, password);
    }

    @GetMapping("/superAdminUser")
    public List<SuperAdmin> superAdminUser() {

        return this.employeeService.superAdminUser();
    }
}
