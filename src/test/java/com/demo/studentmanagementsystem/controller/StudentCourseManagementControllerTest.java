package com.demo.studentmanagementsystem.controller;

import java.util.Collections;
import java.util.List;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.demo.studentmanagementsystem.domain.Course;
import com.demo.studentmanagementsystem.domain.Student;
import com.demo.studentmanagementsystem.service.CourseService;
import com.demo.studentmanagementsystem.service.StudentService;

public class StudentCourseManagementControllerTest {
	private final static Logger LOG = LoggerFactory.getLogger(StudentCourseRegistrationController.class);

	@Mock
	private StudentService studentServiceMock;
	@Mock
	private CourseService courseServiceMock;

	@InjectMocks
	private StudentCourseRegistrationController studentCourseRegistrationController;
	
	@Before(value = "")
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testAddStudent() {
		Student student = new Student();
		student.setStudentId(1l);
		studentCourseRegistrationController.addStudent(student);
	}

	@Test
	public void testRemoveStudent() {
		Long studentId = 1l;
		studentCourseRegistrationController.deleteStudent(studentId);
	}

	@Test
	public void testAddCourse() {
		Course course = new Course();
		course.setCourseId(1l);
		LOG.info("Course  ::Course Name {}", course.getCourseName());
		studentCourseRegistrationController.addCourse(course);
	}

	@Test
	public void testRemoveCourse() {
		Long courseId = 1l;
		studentCourseRegistrationController.removeCourse(courseId);
	}

	@Test
	public void testEnrollStudentToCourse() {
		Long courseId = 1l;
		List<Student> students = Collections.emptyList();
		studentCourseRegistrationController.enrollStudentsToCourse(courseId, students);
	}

	@Test
	public void testGetStudentsByCourseName() {
		String courseName = "SpringBoot";
		studentCourseRegistrationController.getStudentsByCourseName(courseName);
	} 
}
