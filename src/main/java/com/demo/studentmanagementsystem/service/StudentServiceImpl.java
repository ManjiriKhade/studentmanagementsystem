package com.demo.studentmanagementsystem.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.studentmanagementsystem.domain.Course;
import com.demo.studentmanagementsystem.domain.Student;
import com.demo.studentmanagementsystem.exception.CustomJPAException;
import com.demo.studentmanagementsystem.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {
	private final static Logger LOG = LoggerFactory.getLogger(StudentServiceImpl.class);
	@Autowired
	StudentRepository studentRepository;
	
	
	@Override
	public Long addStudent(Student student) throws CustomJPAException {
		student = studentRepository.save(student);
		LOG.info("Student {} Successfully added", student.getStudentId());
		return student.getStudentId();
	}

	@Override
	public List<Student> searchStudentsByStudentName(String studentName) throws CustomJPAException {
		List<Student> students = studentRepository.findAll();
		List<Student> filterStudentsByName = students.stream()
				.filter(s -> s.getStudentName().toLowerCase().matches(studentName.toLowerCase())).toList();
		return filterStudentsByName;
	}


	@Override
	public List<Student> searchStudentsByContactNumber(String contactNumber) throws CustomJPAException {
		List<Student> students = studentRepository.findAll();
		List<Student> filterStudentsByContactNumber = students.stream()
				.filter(s -> s.getContactNumber().toLowerCase().matches(contactNumber.toLowerCase())).toList();
		return filterStudentsByContactNumber;
	}

	@Override
	public List<Student> searchStudentsByGender(String gender) throws CustomJPAException {
		List<Student> students = studentRepository.findAll();
		List<Student> filterStudentsByGender = students.stream()
				.filter(s -> s.getGender().toLowerCase().matches(gender.toLowerCase())).toList();
		return filterStudentsByGender;
	}

	@Override
	public List<Student> getAllStudents() throws CustomJPAException {
		List<Student> allStudents = studentRepository.findAll();
		return allStudents;
	}

	@Override
	public void removeStudent(Long studentId) throws CustomJPAException {
		Optional<Student> optional = studentRepository.findById(studentId.intValue());

		if (!optional.isPresent()) {
			throw new CustomJPAException("Student do not exist! Please provide correct Student Id ");
		}
		studentRepository.delete(optional.get());
	}

	@Override
	public Student addCourseForStudent(Long studentId, List<Course> courses) throws CustomJPAException {
		Optional<Student> optional = studentRepository.findById(studentId.intValue());
		if(!optional.isPresent()) {
			throw new CustomJPAException("Failed to add courses to student As Specified Student does not exists!");
		}
		Student student = optional.get();
		courses.addAll(student.getCourses());
		student.setCourses(courses);
		student = studentRepository.save(student);
		
		return student;
	}

}
