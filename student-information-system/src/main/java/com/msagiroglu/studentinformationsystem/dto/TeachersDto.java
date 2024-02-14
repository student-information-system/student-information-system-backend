package com.msagiroglu.studentinformationsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TeachersDto {
	private String first_name;
	private String last_name;
	private String email;
	private String password;
}
