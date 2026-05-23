package com.model;

import jakarta.persistence.*;

@Entity
@Table(name = "admin")

public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;

    @Column(name = "admin_name")
    private String adminName;

    @OneToOne
    @JoinColumn(name = "users_id")

    private User user;

    public int getId() {
        return id;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}