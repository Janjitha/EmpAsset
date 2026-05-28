package com.empasset.service;

import com.empasset.model.Asset;
import com.empasset.exception.ResourceNotFoundException;
import com.empasset.repository.AssetRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AssetService
{
    private final AssetRepository assetRepository;

    // retrieve all asset data
    public List<Asset> getAll() {
        return assetRepository.findAll();
    }

    // insert asset into db
    public void addAsset(Asset asset) {
        assetRepository.save(asset);
    }

    // get asset by id
    public Asset getById(int id) {
        return assetRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Invalid ID given"));
    }

    // delete asset by id
    public void deleteById(int id) {
        getById(id);

        assetRepository.deleteById(id);
    }

    // update asset details
    public void update(int id, Asset newAsset) {
        Asset existingAsset = getById(id);

        existingAsset.setAssetNo(newAsset.getAssetNo());
        existingAsset.setAssetName(newAsset.getAssetName());
        existingAsset.setAssetModel(newAsset.getAssetModel());
        existingAsset.setManufacturingDate(newAsset.getManufacturingDate());
        existingAsset.setExpiryDate(newAsset.getExpiryDate());
        existingAsset.setAssetValue(newAsset.getAssetValue());
        existingAsset.setImageUrl(newAsset.getImageUrl());
        existingAsset.setAssetStatus(newAsset.getAssetStatus());

        assetRepository.save(existingAsset);
    }
}