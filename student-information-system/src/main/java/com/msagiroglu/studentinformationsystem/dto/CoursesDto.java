package com.msagiroglu.studentinformationsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CoursesDto {
	private Long course_id;
	private String name;
	private String code;
}
