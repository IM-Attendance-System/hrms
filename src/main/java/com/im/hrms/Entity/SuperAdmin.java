package com.im.hrms.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Super_Admin")
public class SuperAdmin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Super_Admin_Id")
    public Long superAdminId;

    @Column(name="Admin_Email_Id")
    public String superAdminEmailId;

    @Column(name="Password")
    public String password;

    public SuperAdmin() {
    }
}
