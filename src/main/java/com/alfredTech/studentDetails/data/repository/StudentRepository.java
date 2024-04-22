package com.alfredTech.studentDetails.data.repository;

import com.alfredTech.studentDetails.data.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student>findStudentByEmail(String email);
}
