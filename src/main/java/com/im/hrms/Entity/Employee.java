package com.im.hrms.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Collection;
import java.util.Date;
import java.util.List;
@Data
@Entity
@Table(name = "Employee")
public class Employee implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id")
    public int id;

    @Column(name = "Full_name")
    public String fullName;

    @Column(name = "Email")
    public String email;

    @Column(name = "Official_Email")
    public String officialEmail;

    @Column(name = "Password")
    public String password;

    @Column(name = "Phone")
    public String phone;

    @Column(name = "DOB")
    public Date dob;

    @Column(name = "Gender")
    public String gender;

    @Column(name = "Marital_Status")
    public String maritalStatus;

    @Column(name = "Address")
    public String address;

//    @Column(name = "Photo")
//    public String photo;

    @Column(unique = true)
    public int employeeId;

    @Column(name = "Joining_Date")
    public Date joiningDate;

//    @Column(name = "Department")
//    public String department;

    @Column(name = "Position")
    public String position;

    @Column(name = "Employee_Type")
    public String employeeType;

    @Column(name = "Reporting_Manager")
    public String reportingManager;

    @Column(name = "Work_Location")
    public String workLocation;

//    @Column(name = "Bank_Account_No")
//    public String bankAccountNo;
//
//    @Column(name = "IFSC_code")
//    public String ifscCode;
//
//    @Column(name = "PAN")
//    public String pan;
//
//    @Column(name = "Aadhar_Number")
//    public String aadharNumber;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "Admin_Id", insertable = false, updatable = false)
    private AdminEntity adminEntity;

    public Employee() {
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }


    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


    }

