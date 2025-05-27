package com.im.hrms.Model;

import lombok.Data;

import java.util.Date;
@Data
public class EmployeeModel {

    public String fullName;
    public String email;
    public String phone;
    public Date dob;
    public String gender;
    public String maritalStatus;
    public String address;
    public int employeeId;
    public Date joiningDate;
    public String department;
    public String position;
    public String employeeType;
    public String reportingManager;
    public String workLocation;
    public String password;
    public String officialEmail;


}
