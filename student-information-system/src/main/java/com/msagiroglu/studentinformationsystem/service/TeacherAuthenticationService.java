package com.msagiroglu.studentinformationsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.msagiroglu.studentinformationsystem.model.Teachers;
import com.msagiroglu.studentinformationsystem.repository.TeachersRepository;

@Service
public class TeacherAuthenticationService {

	private final TeachersRepository teachersRepository;
	private final PasswordEncoder passwordEncoder;

	@Autowired
	public TeacherAuthenticationService(TeachersRepository teachersRepository, PasswordEncoder passwordEncoder) {
		this.teachersRepository = teachersRepository;
		this.passwordEncoder = passwordEncoder;
	}

	public Teachers authenticateTeacher(String email, String password) {
		return teachersRepository.findByEmail(email)
				.filter(teacher -> passwordEncoder.matches(password, teacher.getPassword())).orElse(null);
	}

	public Teachers getAuthenticatedTeacher() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof Teachers) {
			return (Teachers) principal;
		} else {
			throw new IllegalStateException("Authenticated user is not a teacher.");
		}
	}
}
