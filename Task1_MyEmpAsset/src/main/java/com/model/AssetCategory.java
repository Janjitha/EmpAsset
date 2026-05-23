package com.model;

import jakarta.persistence.*;

@Entity
@Table(name = "asset_category")

public class AssetCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;

    @Column(name = "category_name")
    private String categoryName;

    private String description;

    public AssetCategory() {
    }

    public AssetCategory(String categoryName,
                         String description) {

        this.categoryName = categoryName;
        this.description = description;
    }
}