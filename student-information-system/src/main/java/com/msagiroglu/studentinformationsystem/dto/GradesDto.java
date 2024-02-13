package com.msagiroglu.studentinformationsystem.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class GradesDto {
	private Integer grade;
	private Date date;
	private Long studentId;
	private Long courseId;
}
