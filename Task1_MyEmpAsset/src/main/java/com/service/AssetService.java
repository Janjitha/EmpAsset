package com.service;

import com.config.HibernateConfig;
import com.model.Asset;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class AssetService {

    public void addAsset(Asset asset){

        Session session =
                HibernateConfig
                        .getSessionFactory()
                        .openSession();

        Transaction tx = session.beginTransaction();

        session.save(asset);

        tx.commit();

        session.close();

        System.out.println("Asset inserted");
    }

    public void updateAsset(int id){

        Session session =
                HibernateConfig
                        .getSessionFactory()
                        .openSession();

        Transaction tx = session.beginTransaction();

        Asset asset = session.get(Asset.class,id);

        asset.setAssetValue(90000);

        session.update(asset);

        tx.commit();

        session.close();

        System.out.println("Asset Updated");
    }

    public void deleteAsset(int id){

        Session session =
                HibernateConfig
                        .getSessionFactory()
                        .openSession();

        Transaction tx = session.beginTransaction();

        Asset asset = session.get(Asset.class,id);

        if(asset != null){

            session.delete(asset);

            System.out.println("Asset deleted");
        }

        tx.commit();

        session.close();
    }
}