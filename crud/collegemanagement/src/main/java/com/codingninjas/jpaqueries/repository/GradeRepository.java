package com.codingninjas.jpaqueries.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.codingninjas.jpaqueries.entities.Grade;
import com.codingninjas.jpaqueries.entities.Student;

public interface GradeRepository extends JpaRepository<Grade, Integer> {
    @Query(value = "SELECT AVG(marks) FROM grade WHERE student_id = :studentId", nativeQuery = true)
    Double getAverageGradeByStudentId(@Param("studentId") Integer studentId);
}