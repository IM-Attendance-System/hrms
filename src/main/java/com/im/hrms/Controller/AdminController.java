package com.im.hrms.Controller;

import com.im.hrms.Entity.AdminEntity;
import com.im.hrms.Repository.AdminRepo;
import com.im.hrms.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    public AdminRepo adminRepo;

    @Autowired
    public EmployeeService employeeService;


    @PostMapping("/createAdmin")
    public AdminEntity createAdmin(@RequestParam String emailId, @RequestParam String password)  {
        return employeeService.createAdmin(emailId, password);
    }

    @GetMapping("/adminUser")
    public List<AdminEntity> adminUser() {

        return this.employeeService.adminUser();
    }

}
