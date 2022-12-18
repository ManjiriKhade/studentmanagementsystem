package com.demo.studentmanagementsystem.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.studentmanagementsystem.domain.Course;

@Repository(value = "courseRepository")
public interface CourseRepository extends JpaRepository<Course, Integer> {
	public Optional<Course> findCourseByCourseName(String courseName);
}
