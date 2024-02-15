package com.msagiroglu.studentinformationsystem.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "courses")
@Data
@NoArgsConstructor
public class Courses {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long course_id;

	@Column(name = "course_name", nullable = false, length = 100)
	private String name;

	@Column(name = "course_code", nullable = false, unique = true, length = 20)
	private String code;

	@ManyToOne
	@JoinColumn(name = "teacher_id")
	private Teachers teacher;

	public Courses(String name, String code) {
		this.name = name;
		this.code = code;
	}
}