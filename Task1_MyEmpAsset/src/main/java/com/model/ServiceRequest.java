package com.model;

import com.enums.IssueType;
import com.enums.ServiceStatus;
import jakarta.persistence.*;

@Entity
@Table(name = "service_request")

public class ServiceRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;

    @ManyToOne
    @JoinColumn(name = "asset_id")

    private Asset asset;

    @ManyToOne
    @JoinColumn(name = "employee_id")

    private Employee employee;

    @Enumerated(EnumType.STRING)

    @Column(name = "issue_type")

    private IssueType issueType;

    private String description;

    @Enumerated(EnumType.STRING)

    @Column(name = "service_status")

    private ServiceStatus serviceStatus;

    public int getId() {
        return id;
    }

    public Asset getAsset() {
        return asset;
    }

    public void setAsset(Asset asset) {
        this.asset = asset;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public IssueType getIssueType() {
        return issueType;
    }

    public void setIssueType(IssueType issueType) {
        this.issueType = issueType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ServiceStatus getServiceStatus() {
        return serviceStatus;
    }

    public void setServiceStatus(ServiceStatus serviceStatus) {
        this.serviceStatus = serviceStatus;
    }
}