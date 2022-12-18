package com.demo.studentmanagementsystem.service;



import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.demo.studentmanagementsystem.domain.Course;
import com.demo.studentmanagementsystem.domain.Student;
import com.demo.studentmanagementsystem.exception.CustomJPAException;
import com.demo.studentmanagementsystem.repository.StudentRepository;

public class StudentServiceTest {
	@Mock
	private StudentRepository studentRepositoryMock;
	
	@Mock
	private CourseService courseServiceMock;

	@InjectMocks
	private StudentService studentService;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testAddCourse() {
		Student student = new Student();
		student.setStudentId(1l);
		Mockito.when(studentRepositoryMock.save(Mockito.any(Student.class))).thenReturn(student);
		Long courseId = null;
		try {
			courseId = studentService.addStudent(student);
		} catch (CustomJPAException e) {
			e.printStackTrace();
		}
		Assert.assertEquals(1, courseId.longValue());
	}

	@Test
	public void testRemoveCourse() {
		Long courseId = 1l;
		Student student = new Student();
		student.setStudentId(1l);
		Mockito.when(studentRepositoryMock.findById(Mockito.anyInt())).thenReturn(Optional.of(student));
		try {
			studentService.removeStudent(courseId);
		} catch (CustomJPAException e) {
			e.printStackTrace();
		}
	}

	@Test(expected = CustomJPAException.class)
	public void testRemoveCourseWithEmptyCourse() {
		Long courseId = 1l;
		Mockito.when(studentRepositoryMock.findById(Mockito.anyInt())).thenReturn(Optional.empty());
		try {
			studentService.removeStudent(courseId);
		} catch (CustomJPAException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testRegisterStudentToCourse() {
		Long studentId = 1l;
		Student source = new Student();
		source.setStudentId(studentId);
		source.setCourses(Collections.emptyList());
		Mockito.when(studentRepositoryMock.findById(Mockito.anyInt())).thenReturn(Optional.of(source));
		List<Course> courses = Collections.emptyList();
		try {
			studentService.addCourseForStudent(studentId, courses);
		} catch (CustomJPAException e) {
			e.printStackTrace();
		}

	}

	@Test(expected = CustomJPAException.class)
	public void testRegisterStudentToCoursEmptyCourse() {
		Long courseId = 1l;
		Mockito.when(studentRepositoryMock.findById(Mockito.anyInt())).thenReturn(Optional.empty());
		List<Course> courses = Collections.emptyList();
		try {
			studentService.addCourseForStudent(courseId, courses);
		} catch (CustomJPAException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetStudentsByCourseName() {
		String courseName = "DevOps";
		Course course = new Course();
		course.setCourseId(1l);
		course.setCourseName(courseName);
		
		List<Student> students = new ArrayList<>();
		
		Student student = new Student();
		student.setStudentName("NA");
		students.add(student);
		course.setStudents(students);
		course.setCourseName("NA");
		List<Student> studentsByName = null;
		try {
			studentsByName = courseServiceMock.searchStudentsByCourseName(courseName);
		} catch (CustomJPAException e) {
			e.printStackTrace();
		}
		Assert.assertNotNull(studentsByName);
	}
	
	
}
