package com.im.hrms.Service;

import com.im.hrms.Entity.AdminEntity;
import com.im.hrms.Entity.Employee;
import com.im.hrms.Entity.SuperAdmin;
import com.im.hrms.Model.EmployeeModel;
import com.im.hrms.Repository.AdminRepo;
import com.im.hrms.Repository.EmployeeRepo;
import com.im.hrms.Repository.SuperAdminRepo;
import com.im.hrms.Util.EmailUtil;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.query.JSqlParserUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    public EmployeeRepo employeeRepo;

    @Autowired
    public AdminRepo adminRepo;

    @Autowired
    public SuperAdminRepo superAdminRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmailUtil emailUtil;

   // private final String photoPath = "C:\\Users\\EC21\\OneDrive - Mitrisk Consulting LLP\\Documents\\samruddhi\\photo\\";


    public Employee addEmployee(EmployeeModel employeeModel) throws MessagingException {
      //  String filePath = photoPath+photos.getOriginalFilename();

        Employee employeeEntity = new Employee();
     //   List<Employee> employeeId = employeeRepo.findByEmployeeId(employeeModel.getEmployeeId());
       // if(employeeId!=null){

            employeeEntity.setFullName(employeeModel.getFullName());
            employeeEntity.setEmail(employeeModel.getEmail());
            employeeEntity.setPhone(employeeModel.getPhone());
            employeeEntity.setDob(employeeModel.getDob());
            employeeEntity.setGender(employeeModel.getGender());
            employeeEntity.setMaritalStatus(employeeModel.getMaritalStatus());
            employeeEntity.setAddress(employeeModel.getAddress());
           // employeeEntity.setPhoto(filePath);
            employeeEntity.setEmployeeId(employeeModel.getEmployeeId());
            employeeEntity.setJoiningDate(employeeModel.getJoiningDate());
           // employeeEntity.setDepartment(employeeModel.getDepartment());
            employeeEntity.setPosition(employeeModel.getPosition());
            employeeEntity.setEmployeeType(employeeModel.getEmployeeType());
            employeeEntity.setReportingManager(employeeModel.getReportingManager());
            employeeEntity.setWorkLocation(employeeModel.getWorkLocation());
            employeeEntity.setOfficialEmail(employeeModel.getOfficialEmail());
//            employeeEntity.setBankAccountNo(employeeModel.getBankAccountNo());
//            employeeEntity.setIfscCode(employeeModel.getIfscCode());
//            employeeEntity.setAadharNumber(employeeModel.getAadharNumber());
//            employeeEntity.setPan(employeeModel.getPan());
           // employeeEntity.setPassword(employeeModel.getPassword());
            employeeRepo.save(employeeEntity);
            emailUtil.welcomeMail(employeeModel.getEmail());
      //  }
//        else{
//            Employee createEmployee = new Employee();
//            System.out.println("else");
//            createEmployee.setFullName(employeeModel.getFullName());
//            createEmployee.setEmail(employeeModel.getEmail());
//            createEmployee.setPhone(employeeModel.getPhone());
//            createEmployee.setDob(employeeModel.getDob());
//            createEmployee.setGender(employeeModel.getGender());
//            createEmployee.setMaritalStatus(employeeModel.getMaritalStatus());
//            createEmployee.setAddress(employeeModel.getAddress());
//            createEmployee.setPhoto(filePath);
//
//            createEmployee.setEmployeeId(employeeModel.getEmployeeId());
//            createEmployee.setJoiningDate(employeeModel.getJoiningDate());
//            createEmployee.setDepartment(employeeModel.getDepartment());
//            createEmployee.setPosition(employeeModel.getPosition());
//            createEmployee.setEmployeeType(employeeModel.getEmployeeType());
//            createEmployee.setReportingManager(employeeModel.getReportingManager());
//            createEmployee.setWorkLocation(employeeModel.getWorkLocation());
//            createEmployee.setBankAccountNo(employeeModel.getBankAccountNo());
//            createEmployee.setIfscCode(employeeModel.getIfscCode());
//            createEmployee.setAadharNumber(employeeModel.getAadharNumber());
//            createEmployee.setPan(employeeModel.getPan());
//            employeeRepo.save(createEmployee);
//
//
//        }
        return employeeEntity;



    }

    public List<Employee> getEmployee(Long employeeId) {
        return employeeRepo.findAll();
    }

    public AdminEntity createAdmin(String emailId, String password) {
        AdminEntity adminEntity = new AdminEntity();
        adminEntity.setAdminEmailId(emailId);
        adminEntity.setPassword(password);
        return adminRepo.save(adminEntity);
    }

    public List<AdminEntity> adminUser() {
        return adminRepo.findAll();
    }

    public SuperAdmin createSuperAdmin(String emailId, String password) {

        SuperAdmin superAdmin = new SuperAdmin();
        superAdmin.setSuperAdminEmailId(emailId);
        superAdmin.setPassword(password);
        return superAdminRepo.save(superAdmin);
    }

    public List<SuperAdmin> superAdminUser() {
        return superAdminRepo.findAll();
    }

    public String resetPassword(String emailId, String newPassword, String confirmPassword) throws MessagingException {

        Employee employee = employeeRepo.findByEmail(emailId)
                .orElseThrow(() -> new RuntimeException("User not found with this email: " + emailId));

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        // Old password matches, update with new hashed password
        String newHashedPassword = passwordEncoder.encode(newPassword);
        // String confirmHashedPassword = passwordEncoder.encode(confirmPassword);
        if (newPassword.equals(confirmPassword)) {
            employee.setPassword(newHashedPassword);
            employee.setLoginCount("1");
            employeeRepo.save(employee);
            emailUtil.sendPasswordResetEmail(emailId);
            return "New password set successfully";
        } else {
            throw new RuntimeException("new password and confirm password both are not same!!");
        }
    }

    public String forgotPassword(String email) {

        employeeRepo.findByEmail(email).
                orElseThrow(() -> new RuntimeException("user not found with this email" + email));
        try {

            emailUtil.sendPasswordEmail(email);
        } catch (MessagingException e) {
            throw new RuntimeException("unable to set password try again!");
        }
        return "please check your mail to reset password";


    }
}
