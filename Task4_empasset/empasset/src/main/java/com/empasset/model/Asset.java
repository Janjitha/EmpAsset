package com.empasset.model;

import com.empasset.enums.AssetStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Asset
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true)
    private String assetNo;

    @Column(nullable = false)
    private String assetName;
    private String assetModel;
    private LocalDate manufacturingDate;
    private LocalDate expiryDate;
    private double assetValue;
    private String imageUrl;

    @Enumerated(EnumType.STRING)
    private AssetStatus assetStatus;

    @CreationTimestamp
    @Column(updatable = false)
    private Instant createdAt;

    @UpdateTimestamp
    private Instant updatedAt;
}