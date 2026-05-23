package com.model;

import com.enums.AllocationStatus;
import jakarta.persistence.*;

@Entity
@Table(name = "asset_allocation")

public class AssetAllocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;

    @ManyToOne
    @JoinColumn(name = "employee_id")

    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "asset_id")

    private Asset asset;

    @Enumerated(EnumType.STRING)

    @Column(name = "allocation_status")

    private AllocationStatus allocationStatus;

    public int getId() {
        return id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Asset getAsset() {
        return asset;
    }

    public void setAsset(Asset asset) {
        this.asset = asset;
    }

    public AllocationStatus getAllocationStatus() {
        return allocationStatus;
    }

    public void setAllocationStatus(AllocationStatus allocationStatus) {
        this.allocationStatus = allocationStatus;
    }
}