package com.empasset.controller;

import com.empasset.model.Asset;
import com.empasset.exception.ResourceNotFoundException;
import com.empasset.service.AssetService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
 * In controller if you are creating REST APIs
 * then add
 * @RestController annotation which is a combo of
 * @Controller & @ResponseBody
 *
 * But if you are using this controller to load java UI
 * then use only @Controller
 * */

@RestController
@AllArgsConstructor
public class AssetController
{
    private final AssetService assetService;

    // retrieve all asset data
    @GetMapping("/api/asset/getall")
    public List<Asset> getAll() {
        return assetService.getAll();
    }

    // insert asset into db
    @PostMapping("/api/asset/insert")
    public void addAsset(@RequestBody Asset asset) {
        assetService.addAsset(asset);
    }

    // get asset by id
    @GetMapping("/api/asset/getById/{id}")
    public ResponseEntity<Object> getById(@PathVariable int id) {
        try {
            Asset asset = assetService.getById(id);
            return ResponseEntity
                    .ok(asset);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }

    // delete asset by id
    @DeleteMapping("/api/asset/delete/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable int id) {
        try {
            assetService.deleteById(id);
            return ResponseEntity
                    .ok()
                    .build();
        } catch (ResourceNotFoundException e) {
            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }

    // update asset details
    @PutMapping("/api/asset/update/{id}")
    public ResponseEntity<Object> update(@PathVariable int id,
                                         @RequestBody Asset newAsset) {
        try {
            assetService.update(id, newAsset);
            return ResponseEntity
                    .ok()
                    .build();
        } catch (ResourceNotFoundException e) {
            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }
}