package com.msagiroglu.studentinformationsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.msagiroglu.studentinformationsystem.dto.TeacherLoginDto;
import com.msagiroglu.studentinformationsystem.model.Teachers;
import com.msagiroglu.studentinformationsystem.repository.TeachersRepository;

@Service
public class TeacherDetailsService implements UserDetailsService {

	@Autowired
	private TeachersRepository teachersRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Teachers teacher = teachersRepository.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("Öğretmen bulunamadı: " + email));

		return User.withUsername(teacher.getEmail()).password(teacher.getPassword()).roles("TEACHER").build();
	}

	public Teachers authenticateTeacher(TeacherLoginDto teacherLoginDto) {
		Teachers teacher = teachersRepository.findByEmail(teacherLoginDto.getEmail())
				.orElseThrow(() -> new UsernameNotFoundException("Öğretmen bulunamadı: " + teacherLoginDto.getEmail()));

		if (passwordEncoder.matches(teacherLoginDto.getPassword(), teacher.getPassword())) {
			return teacher;
		} else {
			throw new IllegalArgumentException("Şifre hatalı");
		}
	}
}
