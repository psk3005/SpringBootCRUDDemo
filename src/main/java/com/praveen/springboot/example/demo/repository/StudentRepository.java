package com.praveen.springboot.example.demo.repository;

import com.praveen.springboot.example.demo.data.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
