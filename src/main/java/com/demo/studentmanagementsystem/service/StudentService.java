package com.demo.studentmanagementsystem.service;

import java.util.List;
import java.util.Set;

import com.demo.studentmanagementsystem.domain.Course;
import com.demo.studentmanagementsystem.domain.Student;
import com.demo.studentmanagementsystem.exception.CustomJPAException;

public interface StudentService {
	public Long addStudent(Student student) throws CustomJPAException;
	public List<Student> searchStudentsByStudentName(String studentName) throws CustomJPAException;
	public List<Student> searchStudentsByContactNumber(String contactNumber) throws CustomJPAException;
	public List<Student> searchStudentsByGender(String gender) throws CustomJPAException;
	public List<Student> getAllStudents() throws CustomJPAException;
	public void removeStudent(Long student) throws CustomJPAException;
	public Student addCourseForStudent(Long studentId, List<Course> courses) throws CustomJPAException;
	
	
}
