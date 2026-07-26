package com.keshav.ems.employeeservice.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity
@Table(name = "employee")
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Employee{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", unique = true,nullable = false,length = 100)
    private String name;

    @Column(name = "salary",nullable = false)
    private double salary;

    @Column(name = "departmentId",nullable = false,length = 100)
    private String departmentId;

    private String designation;

    private String address;

    @CreationTimestamp
    private Date createdDate;

    @UpdateTimestamp
    private Date updatedDate;
}
