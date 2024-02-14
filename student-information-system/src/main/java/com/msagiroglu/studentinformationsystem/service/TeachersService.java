package com.msagiroglu.studentinformationsystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.msagiroglu.studentinformationsystem.model.Teachers;
import com.msagiroglu.studentinformationsystem.repository.TeachersRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TeachersService {
	private final TeachersRepository teachersRepository;
	private final PasswordEncoder passwordEncoder;

	@Transactional(readOnly = true)
	public List<Teachers> findAllTeachers() {
		return teachersRepository.findAll();
	}

	@Transactional(readOnly = true)
	public Optional<Teachers> findTeacherById(Long id) {
		return teachersRepository.findById(id);
	}

	@Transactional
	public Teachers saveTeacher(Teachers teacher) {
		teacher.setPassword(passwordEncoder.encode(teacher.getPassword()));
		return teachersRepository.save(teacher);
	}

	@Transactional
	public Teachers updateTeacher(Teachers teacherDetails) {
		if (teacherDetails.getTeacherId() == null) {
			throw new IllegalArgumentException("Teacher ID cannot be null for update operation");
		}

		Teachers existingTeacher = teachersRepository.findById(teacherDetails.getTeacherId()).orElseThrow(
				() -> new IllegalArgumentException("Teacher not found with id: " + teacherDetails.getTeacherId()));

		existingTeacher.setFirstName(teacherDetails.getFirstName());
		existingTeacher.setLastName(teacherDetails.getLastName());
		existingTeacher.setEmail(teacherDetails.getEmail());
		if (teacherDetails.getPassword() != null && !teacherDetails.getPassword().isEmpty()) {
			existingTeacher.setPassword(passwordEncoder.encode(teacherDetails.getPassword()));
		}
		return teachersRepository.save(existingTeacher);
	}

	@Transactional
	public void deleteTeacher(Long id) {
		teachersRepository.deleteById(id);
	}
}
