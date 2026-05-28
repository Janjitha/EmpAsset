package com.empasset.repository;

import com.empasset.model.Asset;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssetRepository extends JpaRepository<Asset,Integer>
{
    /*
     * JpaRepository provides
     * CRUD operations automatically
     *
     * save()
     * findAll()
     * findById()
     * delete()
     * deleteById()
     *
     * custom methods can also be added here
     * */
}