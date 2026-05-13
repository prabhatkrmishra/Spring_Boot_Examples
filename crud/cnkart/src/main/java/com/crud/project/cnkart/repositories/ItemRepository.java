package com.crud.project.cnkart.repositories;

import com.crud.project.cnkart.models.Item;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ItemRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public Optional<Item> findById(int id) {
        // Get Hibernate Session from EntityManager
        Session session = entityManager.unwrap(Session.class);

        Item item = entityManager.find(Item.class, id);

        return Optional.ofNullable(item);
    }
}
