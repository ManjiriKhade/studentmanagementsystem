package com.demo.studentmanagementsystem.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.studentmanagementsystem.domain.Course;
import com.demo.studentmanagementsystem.domain.Student;
import com.demo.studentmanagementsystem.dto.CourseDTO;
import com.demo.studentmanagementsystem.exception.CustomJPAException;
import com.demo.studentmanagementsystem.repository.CourseRepository;

@Service
public class CourseServiceImpl implements CourseService {
	@Autowired
	CourseRepository courseRepository;
	
	private final static Logger LOG = LoggerFactory.getLogger(CourseServiceImpl.class);


	@Override
	public Long addCourse(Course course) throws CustomJPAException {
		course = courseRepository.save(course);
		LOG.info("Course : {} has been succesfully added", course.getCourseId());
		return course.getCourseId();
	}

	@Override
	public Course getCourseByCourseName(String courseName) throws CustomJPAException {
		Optional<Course> optional = courseRepository.findCourseByCourseName(courseName);
		if (!optional.isPresent()) {
			throw new CustomJPAException("Course - " + courseName + " Already Present.");
		}
		Course course = optional.get();
		return course;
	}

	@Override
	public List<Course> getAllCourses() throws CustomJPAException {
		List<Course> courses = courseRepository.findAll();
		return courses;
	}

	@Override
	public void removeCourse(Long courseId) throws CustomJPAException {
		Optional<Course> optional = courseRepository.findById(courseId.intValue());

		if (!optional.isPresent()) {
			throw new CustomJPAException("Course do not exist! Please provide correct Course Id ");
		}
		courseRepository.delete(optional.get());
	}

	
	public Course registerStudentsToCourse(Long courseId, List<Student> students) throws CustomJPAException {
		LOG.info("CourseId :: {} , Student :: {}", courseId, students);
		Optional<Course> optional = courseRepository.findById(courseId.intValue());
		if(!optional.isPresent()) {
			throw new CustomJPAException("Failed to register Student. Invalid CourseId :: " + courseId);
		}
		
		Course course = optional.get();
		students.addAll(course.getStudents());
		course.setStudents(students);
		return course;
	}

	@Override
	public List<Student> searchStudentsByCourseName(String courseName) throws CustomJPAException {
		Course course = getCourseByCourseName(courseName);
		List<Student> studentsWithSpecifiedCourseName = new ArrayList<Student>(course.getStudents());
		return studentsWithSpecifiedCourseName;
	}


	

}
