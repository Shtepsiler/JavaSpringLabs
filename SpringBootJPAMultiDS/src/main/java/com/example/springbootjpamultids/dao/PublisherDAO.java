package com.example.springbootjpamultids.dao;

import com.example.springbootjpamultids.config.Constants;
import com.example.springbootjpamultids.entity1.Publisher;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PublisherDAO {

    @Autowired
    @PersistenceContext( unitName= Constants.JPA_UNIT_NAME_1)
    private EntityManager entityManager;

    public List<Publisher> listPublishers() {
        String sql = "Select e from " + Publisher.class.getName() + " e ";
        Query query = entityManager.createQuery(sql, Publisher.class);
        return query.getResultList();
    }

    public Publisher findById(Long id) {
        return this.entityManager.find(Publisher.class, id);
    }

}
