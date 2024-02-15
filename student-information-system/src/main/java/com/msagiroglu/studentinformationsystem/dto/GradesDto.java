package com.msagiroglu.studentinformationsystem.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class GradesDto {
	private Long grade_id;
	private Integer grade;
	private Date date;
	private Long studentId;
	private Long courseId;
	private String firstName;
	private String lastName;
}
