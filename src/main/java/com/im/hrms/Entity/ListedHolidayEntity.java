package com.im.hrms.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "Listed_Holiday_Entity")
public class ListedHolidayEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id")
    public Long id;

    @Column(name="Date")
    public LocalDate date;
}
