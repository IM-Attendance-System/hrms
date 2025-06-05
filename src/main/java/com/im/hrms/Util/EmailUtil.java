package com.im.hrms.Util;

import com.im.hrms.Entity.Employee;
import com.im.hrms.Repository.AdminRepo;
import com.im.hrms.Repository.EmployeeRepo;
import com.im.hrms.Security.JwtHelper;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class EmailUtil {

    @Autowired
    public JavaMailSender javaMailSender;

    @Autowired
    public UserDetailsService userDetailsService;

    @Autowired
    public JwtHelper helper;

    @Autowired
    public AdminRepo adminRepo;

    @Autowired
    public EmployeeRepo employeeRepo;

    public void welcomeMail(String emailId) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        Employee employee = employeeRepo.findByEmail(emailId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        String entityName = employee.getFullName();
        String userId = employee.getEmail();
       // System.out.println("welcome email"+employee.getAdminEntity());

        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        mimeMessageHelper.setTo(emailId);
        mimeMessageHelper.setSubject("Welcome to IM HRMS");

        // Create the HTML content for the email body
        String emailBody = "<html><body>";
        emailBody += "<p>Hi " + entityName + ",</p>";
        emailBody += "<p>We are delighted to welcome you to the IM HRMS Portal!</p>";
        emailBody += "<p>Your login credentials are as follows:</p>";
        //  emailBody += "<p>Please Click the link Below</p>";
        emailBody += String.format("<a href=\"http://localhost:4200/login\">Click here to login</a>");
        emailBody += "<p>User ID: " + userId + "</p>";
        emailBody += "<p> OneTime Password: Mumbai@2025</p>";
        emailBody += "<br><p>Best regards,</p>";
        emailBody += "<p>IM HRMS</p>";
        emailBody += "</body></html>";

        mimeMessageHelper.setText(emailBody, true);
     //   FileSystemResource pdfAttachment = new FileSystemResource(new File("C:\\Users\\EC21\\OneDrive - Mitrisk Consulting LLP\\Desktop\\welcome.pdf"));
      //  mimeMessageHelper.addAttachment("welcome.pdf", pdfAttachment);

        javaMailSender.send(mimeMessage);
    }

    public void sendPasswordResetEmail(String emailId) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        Employee employee = employeeRepo.findByEmail(emailId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        String fullName = employee.getFullName();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
        mimeMessageHelper.setTo(emailId);
        mimeMessageHelper.setSubject("Password Reset Successfully!!");

        String emailBody = String.format("Hi %s,<br><br>", fullName);
        emailBody += "New password set successfully:<br>";
        //  emailBody += String.format("<a href=\"http://localhost:8097/setPassword?emailid=%s\" target=\"http://localhost:4200/resetpassword\">Click here to set your password</a>", emailId);
        mimeMessageHelper.setText(emailBody, true);
        javaMailSender.send(mimeMessage);
    }

    public void sendPasswordEmail(String emailId) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        Employee employee = employeeRepo.findByEmail(emailId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        String resetToken = generateResetToken(emailId);

        String fullName = employee.getFullName();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
        mimeMessageHelper.setTo(emailId);
        mimeMessageHelper.setSubject("set password");

        String emailBody = String.format("Hi %s,<br><br>", fullName);
        emailBody += "Please click the link below to set your password:<br>";
        emailBody += String.format("<a href=\"http://localhost:8097/auth/setPassword?emailId=%s&token=%s\" target=\"http://localhost:4200/resetpassword\">Click here to set your password</a>", emailId,resetToken);
        mimeMessageHelper.setText(emailBody, true);
        javaMailSender.send(mimeMessage);
    }

    private String generateResetToken(String email) {
        // Check if the user with the provided email exists
        employeeRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with this email: " + email));

        // Load user details
        UserDetails userDetails = userDetailsService.loadUserByUsername(email);

        // Generate a token
        String token = helper.generateToken(userDetails);

        return token;
    }




}
