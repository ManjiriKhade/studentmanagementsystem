package com.demo.studentmanagementsystem.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

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
import com.demo.studentmanagementsystem.repository.CourseRepository;

public class CourseServiceTest {
	@Mock
	private CourseRepository courseRepositoryMock;

	@InjectMocks
	private CourseService courseService;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testAddCourse() {
		Course course = new Course();
		course.setCourseId(1l);
		Mockito.when(courseRepositoryMock.save(Mockito.any(Course.class))).thenReturn(course);
		Long courseId = null;
		try {
			courseId = courseService.addCourse(course);
		} catch (CustomJPAException e) {
			e.printStackTrace();
		}
		Assert.assertEquals(1, courseId.longValue());
	}

	@Test
	public void testRemoveCourse() {
		Long courseId = 1l;
		Course course = new Course();
		course.setCourseId(courseId);
		course.setCourseName("Spring");
		Mockito.when(courseRepositoryMock.findById(Mockito.anyInt())).thenReturn(Optional.of(course));
		try {
			courseService.removeCourse(courseId);
		} catch (CustomJPAException e) {
			e.printStackTrace();
		}
	}
	
	@Test(expected=CustomJPAException.class)
	public void testRemoveCourseWithEmptyCourse() {
		Long courseId = 1l;
		Mockito.when(courseRepositoryMock.findById(Mockito.anyInt())).thenReturn(Optional.empty());
		try {
			courseService.removeCourse(courseId);
		} catch (CustomJPAException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testRegisterStudentToCourse() {
		Long courseId = 1l;
		Course course = new Course();
		course.setCourseId(courseId);
		course.setCourseName("Spring");
		course.setStudents(Collections.emptyList());
		Mockito.when(courseRepositoryMock.findById(Mockito.anyInt())).thenReturn(Optional.of(course));
		List<Student> students = Collections.emptyList();
		try {
			courseService.registerStudentsToCourse(courseId, students);
		} catch (CustomJPAException e) {
			e.printStackTrace();
		}

	}
	
	@Test(expected=CustomJPAException.class)
	public void testRegisterStudentToCoursEmptyCourse() {
		Long courseId = 1l;
		Mockito.when(courseRepositoryMock.findById(Mockito.anyInt())).thenReturn(Optional.empty());
		List<Student> students = Collections.emptyList();
		try {
			courseService.registerStudentsToCourse(courseId, students);
		} catch (CustomJPAException e) {
			e.printStackTrace();
		}

	}

	@Test
	public void testGetCourseByCourseName() {
		String courseName = "DevOps";
		try {
			courseService.getCourseByCourseName(courseName);
		} catch (CustomJPAException e) {
			e.printStackTrace();
		}
	}
}
