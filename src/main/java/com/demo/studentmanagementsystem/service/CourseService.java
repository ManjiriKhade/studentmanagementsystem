package com.demo.studentmanagementsystem.service;

import java.util.List;
import java.util.Set;

import com.demo.studentmanagementsystem.domain.Course;
import com.demo.studentmanagementsystem.domain.Student;
import com.demo.studentmanagementsystem.exception.CustomJPAException;

public interface CourseService {

	public Long addCourse(Course course) throws CustomJPAException;
	public Course getCourseByCourseName(String courseName) throws CustomJPAException;
	public List<Course> getAllCourses() throws CustomJPAException;
	public void removeCourse(Long courseId) throws CustomJPAException;
	public Course registerStudentsToCourse(Long courseId, List<Student> student) throws CustomJPAException;
	public List<Student> searchStudentsByCourseName(String courseName) throws CustomJPAException;

}
