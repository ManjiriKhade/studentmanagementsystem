package com.demo.studentmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.studentmanagementsystem.domain.Student;

@Repository(value = "studentRepository")
public interface StudentRepository extends JpaRepository<Student, Integer>{

}
