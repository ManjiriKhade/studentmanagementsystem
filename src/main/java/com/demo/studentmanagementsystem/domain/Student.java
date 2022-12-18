package com.demo.studentmanagementsystem.domain;

import java.sql.Date;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

@Entity
public class Student{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "student_id", unique = true, nullable = false, length = 20)
	private Long studentId;

	@Column(name = "student_Name")
	private String studentName;

	@Column(name = "contact_Number")
	private String contactNumber;

	@Column(name = "address")
	private String address;

	@Column(name = "gender")
	private String gender;
	
	@ManyToMany(fetch = FetchType.LAZY, 
				cascade = { CascadeType.PERSIST, CascadeType.MERGE,
							CascadeType.DETACH,CascadeType.REFRESH })
	@JoinTable(
			name = "student_course", 
			joinColumns = {@JoinColumn(name = "student_id", nullable = false, updatable = false) }, 
			inverseJoinColumns = {@JoinColumn(name = "course_id", nullable = false, updatable = false) })	
	private List<Course> courses;

	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	@Override
	public String toString() {
		return "Student [studentId=" + studentId + ", studentName=" + studentName + ", contactNumber=" + contactNumber
				+ ", address=" + address + ", courses=" + courses + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((contactNumber == null) ? 0 : contactNumber.hashCode());
		result = prime * result + ((studentId == null) ? 0 : studentId.hashCode());
		result = prime * result + ((studentName == null) ? 0 : studentName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		return Objects.equals(address, other.address) && Objects.equals(contactNumber, other.contactNumber)
				&& Objects.equals(courses, other.courses) && Objects.equals(studentId, other.studentId)
				&& Objects.equals(studentName, other.studentName);
	}
	
	
	
	
	
}
