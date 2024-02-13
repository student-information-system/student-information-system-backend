package com.msagiroglu.studentinformationsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class StudentsDto {
	private String first_name;
	private String last_name;
	private String schoolNumber;
	private String password;
}
