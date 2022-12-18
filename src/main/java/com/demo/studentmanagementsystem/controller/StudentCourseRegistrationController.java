package com.demo.studentmanagementsystem.controller;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.demo.studentmanagementsystem.domain.Course;
import com.demo.studentmanagementsystem.domain.Student;
import com.demo.studentmanagementsystem.exception.CustomJPAException;
import com.demo.studentmanagementsystem.service.CourseServiceImpl;
import com.demo.studentmanagementsystem.service.StudentService;



@RestController
@RequestMapping("api/v1")
public class StudentCourseRegistrationController {
	public static final Log LOGGER = LogFactory.getLog(StudentCourseRegistrationController.class);

	@Autowired
	private StudentService studentService;
	@Autowired
	private CourseServiceImpl courseService;
	@Autowired
	Environment environment;
	
	//Rest Points related to student functionality

	@RequestMapping(method = RequestMethod.POST, value = "/addStudent")
	public void addStudent(@RequestBody Student student) {
		try {
			studentService.addStudent(student);
			LOGGER.info(environment.getProperty("Student " + "INFO.INSERT_SUCCESS"));
		} catch (Exception e) {
			if (e.getMessage() != null)
				LOGGER.info(environment.getProperty(e.getMessage(), environment.getProperty("ERROR.COMMON_MSG")));
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/getStudentsByName/{studentname}")
	public List<Student> getStudentsByname(@PathVariable String studentname) {
		try {
			List<Student> students = studentService.searchStudentsByStudentName(studentname);
			students.forEach(LOGGER::info);
			return students;
		} catch (Exception e) {
			if (e.getMessage() != null)
				LOGGER.info(environment.getProperty(e.getMessage(), environment.getProperty("ERROR.COMMON_MSG")));
		}
		return null;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/getStudentsByContactNumber/{contactNumber}")
	public List<Student> getStudensByContactNumber(@PathVariable String contactNumber) {
		try {
			List<Student> students = studentService.searchStudentsByContactNumber(contactNumber);
			students.forEach(LOGGER::info);
			return students;
		} catch (Exception e) {
			if (e.getMessage() != null)
				LOGGER.info(environment.getProperty(e.getMessage(), environment.getProperty("ERROR.COMMON_MSG")));
		}
		return null;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/getStudentsByGender/{gender}")
	public List<Student> getStudentsByGender(@PathVariable String gender) {
		try {
			List<Student> students = studentService.searchStudentsByGender(gender);
			students.forEach(LOGGER::info);
			return students;
		} catch (Exception e) {
			if (e.getMessage() != null) {
				LOGGER.info(environment.getProperty(e.getMessage(), environment.getProperty("ERROR.COMMON_MSG")));
			}
		}
		return null;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/getStudents")
	public List<Student> getAllStudentsList() {
		try {
			List<Student> students = studentService.getAllStudents();
			students.forEach(LOGGER::info);
			return students;
		} catch (Exception e) {
			if (e.getMessage() != null) {
				LOGGER.info(environment.getProperty(e.getMessage(), environment.getProperty("ERROR.COMMON_MSG")));
			}
		}
		return null;
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/deleteStudent/{studentId}")
	public void deleteStudent(@PathVariable Long studentId) {
		try {
			studentService.removeStudent(studentId);
			LOGGER.info("Student " + "INFO.DELETE_SUCCESS");
		} catch (Exception e) {
			if (e.getMessage() != null) {
				LOGGER.info(environment.getProperty(e.getMessage(), environment.getProperty("ERROR.COMMON_MSG")));
			}
		}
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "enrollCourseForStudent/{studentId}")
	public Student enrollCourseForStudent(@PathVariable Long studentId, @RequestBody List<Course> courses) {
		try {
			Student student = studentService.addCourseForStudent(studentId, courses);
			LOGGER.info("STUDENT_ENROLLEDTO_COURSE");
			return student;
		} catch (CustomJPAException e) {
			if (e.getMessage() != null) {
				LOGGER.info(environment.getProperty(e.getMessage(), environment.getProperty("ERROR.COMMON_MSG")));
			}
		}
		return null;
	}
	
	
	//Rest Points related to course functionality
	
	@RequestMapping(method = RequestMethod.POST, value = "/addCourse")
	public void addCourse(@RequestBody Course course) {
		try {
			courseService.addCourse(course);
			LOGGER.info(environment.getProperty("Course " + "INFO.INSERT_SUCCESS"));
		} catch (Exception e) {
			if (e.getMessage() != null)
				LOGGER.info(environment.getProperty(e.getMessage(), environment.getProperty("ERROR.COMMON_MSG")));
		}
	}
	
	@RequestMapping(method=RequestMethod.GET,value="/getCourseByName/{courseName}")
	public Course getCourseByName(@PathVariable String courseName) {
		try {
			Course course = courseService.getCourseByCourseName(courseName);
			LOGGER.info(course);
			return course;
		} catch (Exception e) {
			if (e.getMessage() != null)
				LOGGER.info(environment.getProperty(e.getMessage(), environment.getProperty("ERROR.COMMON_MSG")));
		}
		return null;		
	}

	@RequestMapping(method=RequestMethod.GET,value="/getAllCourses")
	public List<Course> getAllCourses(){
		try {
			List<Course> courses = courseService.getAllCourses();
			courses.forEach(LOGGER::info);
			return courses;
		} catch (CustomJPAException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;		
	}
	
	@RequestMapping(method=RequestMethod.DELETE,value="/deleteCourse/{courseId}")
	public void removeCourse(@PathVariable Long courseId) {
		try {
			courseService.removeCourse(courseId);
			LOGGER.info("Course " + "INFO.DELETE_SUCCESS");
		} catch (Exception e) {
			if (e.getMessage() != null)
				LOGGER.info(environment.getProperty(e.getMessage(), environment.getProperty("ERROR.COMMON_MSG")));
		}		
	}
	
	@RequestMapping(method=RequestMethod.POST,value="/enrollStudentsToCourse/{courseId}")
	public Course enrollStudentsToCourse(@PathVariable Long courseId, @RequestBody List<Student> students) {
		try {
			Course course = courseService.registerStudentsToCourse(courseId, students);
			LOGGER.info("STUDENT_ENROLLEDTO_COURSE");
			return course;
		} catch (Exception e) {
			if (e.getMessage() != null)
				LOGGER.info(environment.getProperty(e.getMessage(), environment.getProperty("ERROR.COMMON_MSG")));
		}
		return null;	
	}
	
	@RequestMapping(method=RequestMethod.GET,value="/getStudentsByCourseName/{courseName}")
	public List<Student> getStudentsByCourseName(@PathVariable String courseName){
		try {
			List<Student> students = courseService.searchStudentsByCourseName(courseName);
			students.forEach(LOGGER::info);
			return students;
		} catch (Exception e) {
			if (e.getMessage() != null)
				LOGGER.info(environment.getProperty(e.getMessage(), environment.getProperty("ERROR.COMMON_MSG")));
		}
		return null;
		
	}
	
}
