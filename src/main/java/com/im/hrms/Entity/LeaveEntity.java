package com.im.hrms.Entity;

import jakarta.persistence.*;
import lombok.Data;

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
    public Date fromDate;

    @Column(name="To_Date")
    public Date toDate;

    @Column(name="Reason")
    public String reason;

    @Column(name="Status")
    public String status;

    public LeaveEntity() {
    }
}
