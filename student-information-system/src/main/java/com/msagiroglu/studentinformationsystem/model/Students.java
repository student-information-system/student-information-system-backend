package com.msagiroglu.studentinformationsystem.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "students")
@Data
@NoArgsConstructor
public class Students {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long student_id;

	@Column(name = "first_name", nullable = false, length = 50)
	private String first_name;

	@Column(name = "last_name", nullable = false, length = 50)
	private String last_name;

	@Column(name = "school_number", nullable = false, unique = true, length = 20)
	private String schoolNumber;

	@Column(name = "password", nullable = false)
	private String password;

	public Students(String first_name, String last_name, String schoolNumber, String password) {
		this.first_name = first_name;
		this.last_name = last_name;
		this.schoolNumber = schoolNumber;
		this.password = password;
	}
}
