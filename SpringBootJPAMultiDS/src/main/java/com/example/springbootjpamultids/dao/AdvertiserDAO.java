package com.example.springbootjpamultids.dao;
import com.example.springbootjpamultids.config.Constants;
import com.example.springbootjpamultids.entity2.Advertiser;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AdvertiserDAO {


    @Autowired
    @PersistenceContext(unitName = Constants.JPA_UNIT_NAME_2)
    private EntityManager entityManager;

    public List<Advertiser> listAdvertisers() {
        String sql = "Select e from " + Advertiser.class.getName() + " e ";
        Query query = entityManager.createQuery(sql, Advertiser.class);
        return query.getResultList();
    }

    public Advertiser findById(Long id) {
        return this.entityManager.find(Advertiser.class, id);
    }

}
