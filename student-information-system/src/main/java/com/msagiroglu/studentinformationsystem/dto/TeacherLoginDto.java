package com.msagiroglu.studentinformationsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeacherLoginDto {
	private String email;
	private String password;
}
