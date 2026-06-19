package com.example.StudentManagementSystem.repository;

import com.example.StudentManagementSystem.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * StudentsDal (Data Access Layer)
 *
 * <p>Spring Data JPA repository interface for {@link Student} entities.
 * By extending {@link JpaRepository}, this interface automatically provides
 * a full set of CRUD operations including:
 * <ul>
 *   <li>{@code save(Student)}         – insert or update a student record</li>
 *   <li>{@code findById(Integer)}     – find a student by primary key</li>
 *   <li>{@code findAll()}             – retrieve all student records</li>
 *   <li>{@code deleteById(Integer)}   – delete a student by primary key</li>
 *   <li>{@code existsById(Integer)}   – check existence by primary key</li>
 * </ul>
 *
 * <p>No custom query methods are required for this assignment; all needed
 * operations are inherited from {@link JpaRepository}.
 *
 * <p>Type parameters: {@code Student} is the entity type; {@code Integer}
 * is the type of the primary key field {@code sid}.
 */
@Repository
public interface StudentsDal extends JpaRepository<Student, Integer> {
}
