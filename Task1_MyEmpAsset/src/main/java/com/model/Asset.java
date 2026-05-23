package com.model;

import com.enums.AssetStatus;
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

    @Column(name = "asset_model")
    private String assetModel;

    @Column(name = "asset_value")
    private double assetValue;

    @Column(name = "image_url")
    private String imageUrl;

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

    public String getAssetModel() {
        return assetModel;
    }

    public void setAssetModel(String assetModel) {
        this.assetModel = assetModel;
    }

    public double getAssetValue() {
        return assetValue;
    }

    public void setAssetValue(double assetValue) {
        this.assetValue = assetValue;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public AssetStatus getAssetStatus() {
        return assetStatus;
    }

    public void setAssetStatus(AssetStatus assetStatus) {
        this.assetStatus = assetStatus;
    }
}