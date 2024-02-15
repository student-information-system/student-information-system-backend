package com.msagiroglu.studentinformationsystem.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "grades")
@Data
@NoArgsConstructor
public class Grades {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long grade_id;

	@Column(name = "grade", nullable = false)
	private Integer grade;

	@Column(name = "date")
	private Date date;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "student_id", nullable = false)
	private Students student;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "course_id", nullable = false)
	private Courses course;

	public Grades(Integer grade, Date date, Students student, Courses course) {
		this.grade = grade;
		this.date = date;
		this.student = student;
		this.course = course;
	}
}
