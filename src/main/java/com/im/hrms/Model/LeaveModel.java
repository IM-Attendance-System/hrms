package com.im.hrms.Model;

import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class LeaveModel {
    public String leaveType;
    public LocalDate from;
    public LocalDate to;
    public String reason;
    public String status;
    public String leaveDuration;
}
