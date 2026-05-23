package com.model;

import jakarta.persistence.*;

@Entity
@Table(name = "employee")

public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;

    @Column(name = "employee_name")
    private String employeeName;

    private String gender;

    private String phone;

    private String address;

    private String department;

    @OneToOne
    @JoinColumn(name = "users_id")
    private User user;

    public Employee() {
    }

    public Employee(String employeeName,
                    String gender,
                    String phone,
                    String address,
                    String department,
                    User user) {

        this.employeeName = employeeName;
        this.gender = gender;
        this.phone = phone;
        this.address = address;
        this.department = department;
        this.user = user;
    }
}