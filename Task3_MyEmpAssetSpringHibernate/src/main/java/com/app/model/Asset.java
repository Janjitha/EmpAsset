package com.app.model;

import com.app.enums.AssetStatus;
import jakarta.persistence.*;

@Entity
@Table(name = "asset")

public class Asset {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "asset_no")
    private String assetNo;

    @Column(name = "asset_name")
    private String assetName;

    @Column(name = "asset_value")
    private double assetValue;

    @Enumerated(EnumType.STRING)
    @Column(name = "asset_status")
    private AssetStatus assetStatus;
    public int getId() {
        return id;
    }
    public String getAssetNo() {
        return assetNo;
    }
    public void setAssetNo(String assetNo) {
        this.assetNo = assetNo;
    }
    public String getAssetName() {
        return assetName;
    }
    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }
    public double getAssetValue() {
        return assetValue;
    }
    public void setAssetValue(double assetValue) {
        this.assetValue = assetValue;
    }
    public AssetStatus getAssetStatus() {
        return assetStatus;
    }
    public void setAssetStatus(AssetStatus assetStatus) {
        this.assetStatus = assetStatus;
    }
}