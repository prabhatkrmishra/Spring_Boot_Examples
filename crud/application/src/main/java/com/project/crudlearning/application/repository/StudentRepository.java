package com.project.crudlearning.application.repository;

import com.project.crudlearning.application.models.Student;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class StudentRepository {
    @Autowired
    EntityManager em;

    @Transactional
    public void addStudentToDb(Student student) {
        // Get Hibernate Session from EntityManager
        Session session = em.unwrap(Session.class);
        // session.save is deprecated
        session.persist(student);
    }
}
