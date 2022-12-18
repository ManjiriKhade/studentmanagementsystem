package com.demo.studentmanagementsystem.domain;

import java.util.List;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;

@Entity
public class Course {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "course_id", unique = true, nullable = false, length = 20)
	private Long courseId;

	@Column(name = "course_name")
	private String courseName;

	@Column(name = "course_details")
	private String courseDetails;

	@Column(name = "course_duration")
	private String courseDuration;

	@ManyToMany(fetch = FetchType.LAZY, 
			    cascade = { CascadeType.PERSIST, CascadeType.MERGE, 
			    		    CascadeType.DETACH, CascadeType.REFRESH })
	@JoinTable(name = "student_course", 
	           joinColumns = {@JoinColumn(name = "course_id", nullable = false, updatable = false) },
	           inverseJoinColumns = {@JoinColumn(name = "student_id", nullable = false, updatable = false) })
	private List<Student> students;

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getCourseDetails() {
		return courseDetails;
	}

	public void setCourseDetails(String courseDetails) {
		this.courseDetails = courseDetails;
	}

	public String getCourseDuration() {
		return courseDuration;
	}

	public void setCourseDuration(String courseDuration) {
		this.courseDuration = courseDuration;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	@Override
	public int hashCode() {
		return Objects.hash(courseDetails, courseDuration, courseId, courseName, students);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		return Objects.equals(courseDetails, other.courseDetails)
				&& Objects.equals(courseDuration, other.courseDuration) && Objects.equals(courseId, other.courseId)
				&& Objects.equals(courseName, other.courseName) && Objects.equals(students, other.students);
	}

	@Override
	public String toString() {
		return "Course [courseId=" + courseId + ", courseName=" + courseName + ", courseDetails=" + courseDetails
				+ ", courseDuration=" + courseDuration + ", students=" + students + "]";
	}
	
	
	
}
