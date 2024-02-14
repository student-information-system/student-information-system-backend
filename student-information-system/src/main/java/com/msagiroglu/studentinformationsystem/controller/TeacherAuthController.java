package com.msagiroglu.studentinformationsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.msagiroglu.studentinformationsystem.dto.TeacherLoginDto;
import com.msagiroglu.studentinformationsystem.model.Teachers;
import com.msagiroglu.studentinformationsystem.service.TeacherAuthenticationService;

@RestController
@RequestMapping(value = "/teacher-auth")
public class TeacherAuthController {

	private final TeacherAuthenticationService teacherAuthenticationService;

	@Autowired
	public TeacherAuthController(TeacherAuthenticationService teacherAuthenticationService) {
		this.teacherAuthenticationService = teacherAuthenticationService;
	}

	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody TeacherLoginDto teacherLoginDto) {
		Teachers authenticatedTeacher = teacherAuthenticationService.authenticateTeacher(teacherLoginDto.getEmail(),
				teacherLoginDto.getPassword());

		if (authenticatedTeacher != null) {
			return ResponseEntity.ok("Öğretmen başarıyla doğrulandı.");
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("E-posta veya şifre hatalı.");
	}
}
