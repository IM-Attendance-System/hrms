package com.im.hrms.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
@Entity
@Table(name = "Leaves_Entity")
public class LeaveEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id")
    public Long id;

    @Column(name="Leave_Type")
    public String leaveType;

    @Column(name="From_Date")
    public LocalDate fromDate;

    @Column(name="To_Date")
    public LocalDate toDate;

    @Column(name="Reason")
    public String reason;

    @Column(name="Status")
    public String status;

    @Column(name="Leave_Duration")
    public String leaveDuration;

    public LeaveEntity() {
    }
}
