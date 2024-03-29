package com.msagiroglu.studentinformationsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TeachersDto {
	private Long teacherId;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
}
