package com.im.hrms.Model;

import lombok.Data;

import java.util.Date;

@Data
public class LeaveModel {
    public String leaveType;
    public Date from;
    public Date to;
    public String reason;
    public String status;
}
